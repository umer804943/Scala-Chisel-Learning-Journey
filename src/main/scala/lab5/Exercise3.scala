package lab5
import chisel3._

class Exercise3[T <: Data](n : Int, generic : T)(op : (T, T) => T) extends Module{
  require(n > 0)

  val io = IO(new Bundle{
    val in = Input(Vec(n, generic)) //Vec(length, type)
    val out = Output(Vec(n-1, generic)) //Output of each reduce 
  })

  io.out(0) := op(io.in(0), io.in(1))

  for(i <- 1 until n-1)
  {
    io.out(i) := op(io.out(i-1), io.in(i+1)) 
  }
  
}
