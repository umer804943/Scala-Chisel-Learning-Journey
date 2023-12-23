package lab5
import chisel3._
import chisel3.util._
// import chisel3.iotesters.{ChiselFlatSpec, Driver, PeekPokeTester}
// your code for Transaction_in class
class TransactionIn[T <: Data](gen : T) extends Bundle
{
    val addField = Input(UInt(10.W))
    val dataField = Input(gen)
}

class TransactionOut[T <: Data](gen : T) extends Bundle
{
    val addField = Output(UInt(10.W))
    val dataField = Output(gen)
}

// your code for Transaction_out class
class Task2[T <: Data](gen: T) extends Module {
// your code begin
// your code end
    val io = IO(new Bundle{
        val in = Input(new TransactionIn(gen))
        val out = Output(new TransactionOut(gen)) //can be use  Flipped(new TansactionIn(gen))
    })
    // io.out.addField := io.in.addField
    // io.out.dataField := io.in.dataField

    // Bulk Connector connect input to output with same names of bundle or io
    io.out <> io.in
}
