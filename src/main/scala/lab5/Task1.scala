package lab5
import chisel3._
import chisel3.util._
// import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

class Task1(width: Int) extends Module{
  require(width >= 0)
// your code begin from here
// your code end here

  val io = IO(new Bundle{
    val in0 = Input(UInt(width.W))
    val in1 = Input(UInt(width.W))
    val sum = Output(UInt(width.W))
  })

  io.sum := io.in0 + io.in1
}
