package lab4
import chisel3._
import chisel3.util
import org.scalatest._
import chiseltest._
import chiseltest.experimental.TestOptionBuilder._
// import chiseltest.internel.VerilatorBackendAnnotation


import scala.util._

// object ALUOP {
//   val ALU_ADD = 0.U(4.W)
//   val ALU_SUB = 1.U(4.W)
//   val ALU_AND = 2.U(4.W)
//   val ALU_OR = 3.U(4.W)
//   val ALU_XOR = 4.U(4.W)
//   val ALU_SLT = 5.U(4.W)
//   val ALU_SLL = 6.U(4.W)
//   val ALU_SLTU = 7.U(4.W)
//   val ALU_SRL = 8.U(4.W)
//   val ALU_SRA = 9.U(4.W)
//   val Branch_BEQ = 10.U(4.W)
//   val Branch_BNE = 11.U(4.W)
//   val Branch_BLT = 12.U(4.W)
//   val Branch_BGE = 13.U(4.W)
//   val Branch_BLTU = 14.U(4.W)
//   val Branch_BGEU = 15.U(4.W)
// }
// class TestALU ( c : ALU ) extends FreeSpec with ChiselScalaTester --> c: ALU 
class Task3Tester extends FreeSpec with ChiselScalatestTester{
    "Task3Tester" in{
        test(new Exercise1){ c =>
            //ALU Operations
            val array_op = Array(ALUOP.ALU_ADD, ALUOP.ALU_SUB, ALUOP.ALU_AND, ALUOP.ALU_OR, ALUOP.ALU_XOR, ALUOP.ALU_SLT, ALUOP.ALU_SLL, ALUOP.ALU_SLTU, ALUOP.ALU_SRL, ALUOP.ALU_SRA, ALUOP.Branch_BEQ, ALUOP.Branch_BNE, ALUOP.Branch_BLT, ALUOP.Branch_BGE, ALUOP.Branch_BLTU, ALUOP.Branch_BGEU)

            for (i <- 0 until 100)
            {
                var src_a = Random.nextLong() & 0xFFFFFFFFL
                var src_b = Random.nextLong() & 0xFFFFFFFFL
                var opr = Random.nextInt(15)
                var aluop = array_op(opr)

                val result = aluop match // default case given as should initilize as in when elsewhen
                {
                    case ALUOP.ALU_ADD => src_a + src_b
                    case ALUOP.ALU_SUB => src_a - src_b
                    case ALUOP.ALU_AND => src_a & src_b
                    case ALUOP.ALU_OR => src_a | src_b
                    case ALUOP.ALU_XOR => src_a ^ src_b
                    case ALUOP.ALU_SLT => if (src_a.toInt < src_b.toInt) 1 else 0 // There is not use .asInstanceof[Int], it is only used when child to parent conversion.
                    case ALUOP.ALU_SLL => src_a << (src_b & 0x1F)
                    case ALUOP.ALU_SLTU => if (src_a < src_b) 1 else 0
                    case ALUOP.ALU_SRL => src_a >> (src_b & 0x1F)
                    case ALUOP.ALU_SRA => src_a.toInt >> (src_b & 0x1F).toInt  // int should be shift by int same as in long
                    case ALUOP.Branch_BEQ => if (src_a.toInt == src_b.toInt) 1 else 0
                    case ALUOP.Branch_BNE => if (src_a.toInt != src_b.toInt) 1 else 0
                    case ALUOP.Branch_BLT => if (src_a.toInt < src_b.toInt) 1 else 0
                    case ALUOP.Branch_BGE => if (src_a.toInt >= src_b.toInt) 1 else 0
                    case ALUOP.Branch_BLTU => if (src_a < src_b) 1 else 0
                    case ALUOP.Branch_BGEU => if (src_a >= src_b) 1 else 0
                    case _ => 0
                }

                val result1: BigInt = if(result < 0)
                {
                    (BigInt(0xFFFFFFFFL) + result + 1) & 0xFFFFFFFFL
                }
                else
                {
                    (result & 0xFFFFFFFFL)
                }

                c.io.in_A.poke(src_a.asUInt)
                c.io.in_B.poke(src_b.asUInt)
                c.io.alu_Op.poke(aluop)
                c.clock.step(1)
                c.io.out.expect(result1.asUInt)
            }
            c.clock.step(1)
        }
    }
}