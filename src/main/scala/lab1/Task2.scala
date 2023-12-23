package lab1

import chisel3._
import chisel3.util._
import java.io.File
class Task2(n: Int) extends Module {
  val io = IO(new Bundle {
    val data_in = Input(UInt(n.W))
    val reload = Input(Bool())
    val out = Output(Bool())
  })
  
  val counter = RegInit(0.U(n.W))
  val updown = RegInit(false.B) // Use RegInit as we don't want store last cycle value.
  io.out := false.B //should be initilized before when elsewhen otherwise

  when(counter < io.data_in && !updown) //Here we can't use io.out as we can throw io.out as dependent, it makes combinational loop.
  {
    io.out := (counter === 0.U) //As it is directly updates, so it is in elsewhen means after updates of counter. shows high on counter min
    counter := counter + 1.U
    updown := ((counter + 1.U) === io.data_in)// We need preemt updown value as counter updates in next cycle.
  }.elsewhen(updown && io.reload)
  {
    io.out := (counter === io.data_in) //As it is directly updates, so it is in elsewhen means after updates of counter. shows high on counter max
    counter := counter - 1.U
    updown := !((counter - 1.U) === 0.U) //Counter updates in next cycle, so we need to preemt its value in updown.
  }
}
