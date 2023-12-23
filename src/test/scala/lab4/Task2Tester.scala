package lab4
import chisel3._
import chisel3.util
import org.scalatest._
import chiseltest._
import chiseltest.experimental.TestOptionBuilder._
// import chiseltest.internel.VerilatorBackendAnnotation


import scala.util._

object Opcode
{
    val I_TYPE = 19
    val I_TYPE_LOAD = 3
    val S_TYPE = 35
    val B_TYPE = 99
    val J_TYPE_JAL = 111
    val J_TYPE_JALR = 103
    val U_TYPE_LUI = 55
    val U_TYPE_AUIPC = 23
    // val ECALL_EBREAK = 115 //not an immediate, it is func 12
}


class Task2Tester extends FreeSpec with ChiselScalatestTester {
  "Lab4 Task2Tester Chisel Tester" in {
    test(new Task2) { a =>
      for(i <- 0 until 100)
      {
        val opcodeArray = Array(Opcode.I_TYPE, Opcode.I_TYPE_LOAD, Opcode.S_TYPE, Opcode.B_TYPE, Opcode.J_TYPE_JAL,
                                Opcode.J_TYPE_JALR, Opcode.U_TYPE_LUI, Opcode.U_TYPE_AUIPC)
        val opcode = opcodeArray(Random.nextInt(7))
        // val opcode = 23
        val instr = (opcode + ((Random.nextLong << 7) & 0xFFFFFFF )) & 0xFFFFFFFF 
        // println(instr)
        val result = opcode match
        {
            case Opcode.I_TYPE => instr >> 20
            case Opcode.I_TYPE_LOAD => instr >> 20
            case Opcode.S_TYPE => ((instr >> 7) & 0x1F) + ((instr >> 25) << 5)
            case Opcode.B_TYPE =>  (((instr >> 8) & 0xF) << 1) + (((instr >> 25) & 0x3F) << 5) + (((instr >> 7) & 0x1) << 11) + (((instr >> 31) & 0x1) << 12)
            case Opcode.J_TYPE_JAL => (((instr >> 21) & 0x3FF) << 1) + (((instr >> 20) & 0x1) << 11) + (((instr >> 12) & 0xFF) << 12) + (((instr >> 31) & 0x1) << 20) 
            case Opcode.J_TYPE_JALR => (((instr >> 21) & 0x3FF) << 1) + (((instr >> 20) & 0x1) << 11) + (((instr >> 12) & 0xFF) << 12) + (((instr >> 31) & 0x1) << 20) 
            case Opcode.U_TYPE_LUI => (((instr >> 12) & 0xFFFFF) << 12)
            case Opcode.U_TYPE_AUIPC =>  (((instr >> 12) & 0xFFFFF) << 12)
        }
        // println("Result"+result)

        a.io.instr.poke(instr.asUInt)
        a.clock.step(1)
        a.io.immd_se.expect(result.asUInt)
      }
    }
  }
}