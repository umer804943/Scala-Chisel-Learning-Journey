package lab4
import chisel3._
import chisel3.util._
class LM_IO_Interface_ImmdValGen extends Bundle {
  val instr = Input(UInt(32.W))
  val immd_se = Output(UInt(32.W))
}

// Immediate Generator
class Task2 extends Module {
  val io = IO(new LM_IO_Interface_ImmdValGen)
  io.immd_se := 0.U;
  switch(io.instr(6, 0))
  {
    is(19.U) // I-Type 0010011
    {
      io.immd_se := Cat(Fill(20, 0.U), io.instr(31,20))
    }
    is(3.U) // I-Type(Load Commands) 0000011
    {
      io.immd_se := Cat(Fill(20, 0.U), io.instr(31,20))
    }
    is(35.U) // S-Type 0100011
    {
      io.immd_se := Cat(Fill(20, 0.U), Cat(io.instr(31,25) , io.instr(11,7)))
    }
    is(99.U) // B-Type 1100011
    {
      io.immd_se := Cat(Fill(19, 0.U), Cat(io.instr(31), Cat( io.instr(7), Cat( io.instr(30, 25), Cat(io.instr(11,8) , "b0".U)))))
    }
    is(111.U) // J-Type(jal) 1101111
    {
      io.immd_se := Cat( Fill(11, 0.U), Cat( io.instr(31) , Cat( io.instr(19, 12), Cat( io.instr(20), Cat( io.instr(30, 21), "b0".U)))))
    }
    is(103.U) // J-Type(jalr) 0000011
    {
      io.immd_se := Cat( Fill(11, 0.U), Cat( io.instr(31) , Cat( io.instr(19, 12), Cat( io.instr(20), Cat( io.instr(30, 21), "b0".U)))))
    }
    is(55.U) // U-Type(lui) 0110111
    {
      io.immd_se := Cat(io.instr(31, 12), Fill(12, 0.U))
    }
    is(23.U) // U-Type(auipc) 0010111
    {
      io.immd_se := Cat(io.instr(31, 12), Fill(12, 0.U))
    }
    // is(115.U) // ecall, ebreak 1110011 //not an immediate, it is func 12
    // {
    //   io.immd_se := Cat(io.instr(31, 12), "b0".U)
    // }

  }
}

