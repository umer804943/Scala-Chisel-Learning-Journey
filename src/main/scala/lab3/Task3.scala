package lab3
import chisel3._
import chisel3.util._
class LM_IO_Interface_decoder_with_valid extends Bundle {
  val in = Input(UInt(2.W))
  val out = Valid(Output(UInt(4.W)))
}

//decoder_with_valid
class Task3 extends Module {
  val io = IO(new LM_IO_Interface_decoder_with_valid)
  
  io.out.valid := false.B
  io.out.bits := 0.U

  switch(io.in)
  {
    is(0.U)
    {
        io.out.bits := 1.U // 0001
        io.out.valid := true.B
    }
    is(1.U)
    {
        io.out.bits := 2.U // 0010
        io.out.valid := true.B
    }
    is(2.U)
    {
        io.out.bits := 4.U // 0100
        io.out.valid := true.B
    }
    is(3.U)
    {
        io.out.bits := 8.U // 1000
        io.out.valid := true.B
    }
  }
}
