package lab1

import chisel3._
class Exercise3(size: Int, maxValue: Int) extends Module {
  val io = IO(new Bundle {
    val result = Output(Bool())
  })
// ' genCounter ' with counter size 'n '
  def genCounter(n: Int, max: Int) = {
    val count = RegInit(0.U(n.W))
    when(count === max.asUInt) {
      count := 0.U
    }.otherwise {
      count := count + 1.U
    }
    count
  }
// genCounter instantiation
  val counter1 = genCounter(size, maxValue) // Chisel datatypes to Scala typecasting is not possible but Scala to Chisel possible.
  io.result := counter1(size - 1)
  // println((new chisel3.stage.ChiselStage).emitVerilog(new Exercise3(8, 255)))
}
