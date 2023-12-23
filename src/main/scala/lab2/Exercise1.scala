package lab2

import chisel3._
// Mux IO interface class
class Mux2to1_IO extends Bundle {
  val in_A = Input(UInt(32.W))
  val in_B = Input(UInt(32.W))
  val select = Input(Bool())
  val out = Output(UInt())
}
// 2 to 1 Mux implementation
class Exercise1 extends Module {
  val io = IO(new Mux2to1_IO)
// update the output
  when(io.select === true.B)
  {
    io.out := io.in_A
  }.otherwise
  {
    io.out := io.in_B
  }


  // io.out := userDefinedMux(io.select, io.in_A, io.in_B)


  //Func doesn't working
  def userDefinedMux(selectPin : Bool, inputA : UInt, inputB : UInt) : UInt = {
    var selectedValue = 0.U //Default Value --> Must be given for returning value without when otherwise.
    when(io.select === true.B)
    {
      selectedValue = inputA.asUInt
    }.otherwise
    {
      selectedValue = inputB.asUInt
    }
    selectedValue.asUInt
  }

//   println((new chisel3.stage.ChiselStage).emitVerilog(new Exercise1()))
}
