package lab2

// Mux - Onehot example
import chisel3._
import chisel3.util._
class Exercise3 extends Module {
  val io = IO(new Bundle {
    val in0 = Input(UInt(1.W))
    val in1 = Input(UInt(1.W))
    val in2 = Input(UInt(1.W))
    val in3 = Input(UInt(1.W))
    val out = Output(UInt(2.W))
  })
  val in4 = Cat(Cat(io.in3, io.in2), Cat(io.in1, io.in0))
  io.out := Mux1H(in4, Seq(0.U, 1.U, 2.U, 3.U))
  // Decoder 4 to 2 --> tell which input on
  // Mux1H takes n(num of inputs) sel pin.
  // so 1 means 1st or show in min width (0) input
  //    2 means 2nd or show in min width (1) input
  //    4 means 3rd or show in min width (2) input
  //    8 means 4th or show in min width (3) input
  
}
// println((new chisel3.stage.ChiselStage).emitVerilog(new mux_onehot_4to1()))
