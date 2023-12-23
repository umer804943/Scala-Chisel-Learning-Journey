package lab1

import chisel3._
class Exercise1(counterBits: Int) extends Module {
  val io = IO(new Bundle {
    val result = Output(Bool())
  })
  val max = (1.S << counterBits) - 1.S
  val count = RegInit(0.S(4.W))
  when(count === max) {
    count := 0.S
  }.otherwise {
    count := count + 1.S
  }
  io.result := count(2.U)
  println(s" counter created with max value $max and count $count")

}
