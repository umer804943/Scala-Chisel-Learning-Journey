package lab5
import chisel3._
import chisel3.util._
// import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}

//Inclusive I think typecaste inside module or homogenous typecaste 
// Tasking 2 different types of inputs
class Task3[T <: Data](width : Int ,genIn1 : T, genIn2 : T) extends Module{
  val io = IO(new Bundle{
    val in0 = Input(genIn1)
    val in1 = Input(genIn2)
    val sel = Input(Bool())
    val out = Output(UInt(width.W))
  })

  //Inclusive TypeCasting
  val castedIn0 = io.in0.asUInt
  val castedIn1 = io.in1.asUInt

  io.out := Mux(io.sel, castedIn0, castedIn1)

  /*  
  Analysis And Findings:
  Mux shoud have both inputs of same type that's why inclusively typecasted
  */
}
