//Type Parameterize Mux Using Bundle Only
package lab5
import chisel3._
import chisel3.util._

class MuxInterface[T <: Data](any : T) extends Bundle{
    val out = Output(any)
    val in1 = Input(any)
    val in2 = Input(any)
    val sel = Input(Bool())
}

class Exercise2[T <: Data](gen : T) extends Module{
  val io = IO(new MuxInterface(gen))
  io.out := Mux(io.sel, io.in1, io.in2) 
}
