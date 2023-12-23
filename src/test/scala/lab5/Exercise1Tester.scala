package lab5
import chisel3._
import chisel3.util
import org.scalatest._
import chiseltest._
import chiseltest.experimental.TestOptionBuilder._
// import chiseltest.internel.VerilatorBackendAnnotation


import scala.util._

object ALUOP {
  val ALU_ADD = 2.U(4.W)
  val ALU_SUB = 6.U(4.W)
  val ALU_AND = 0.U(4.W)
  val ALU_OR = 1.U(4.W)
  val ALU_XOR = 3.U(4.W)
  val ALU_SLT = 8.U(4.W)
  val ALU_SLL = 4.U(4.W)
  val ALU_SLTU = 9.U(4.W)
  val ALU_SRL = 5.U(4.W)
  val ALU_SRA = 7.U(4.W)
}

class Exercise1Tester extends FreeSpec with ChiselScalatestTester{
    "Exercise1Tester" in{
        test(new Exercise1(32)){ c =>
            //ALU Operations
            val array_op = Array(ALUOP.ALU_AND, ALUOP.ALU_OR, ALUOP.ALU_ADD, ALUOP.ALU_XOR, ALUOP.ALU_SLL, ALUOP.ALU_SRL, ALUOP.ALU_SUB, ALUOP.ALU_SRA, ALUOP.ALU_SLT, ALUOP.ALU_SLTU)

            for (i <- 0 until 100)
            {
                var src_a = Random.nextLong() & 0xFFFFFFFFL
                var src_b = Random.nextLong() & 0xFFFFFFFFL
                var opr = Random.nextInt(9)
                var aluop = array_op(opr)

                val result = aluop match
                {
                    case ALUOP.ALU_ADD => src_a + src_b
                    case ALUOP.ALU_SUB => src_a - src_b
                    case ALUOP.ALU_AND => src_a & src_b
                    case ALUOP.ALU_OR => src_a | src_b
                    case ALUOP.ALU_XOR => src_a ^ src_b
                    case ALUOP.ALU_SLT => if (src_a.toInt < src_b.toInt) 1 else 0
                    case ALUOP.ALU_SLL => (src_a << (src_b & 0x1F))
                    case ALUOP.ALU_SLTU => if (src_a < src_b) 1 else 0
                    case ALUOP.ALU_SRL => (src_a >> (src_b & 0x1F))
                    case ALUOP.ALU_SRA => (src_a.toInt >> (src_b & 0x1F).toInt)// int should be shift by int same as in long
                    case _ => 0
                }

                // val result1: BigInt = if(result < 0)
                // {
                //     (BigInt(0xFFFFFFFFL) + result + 1) & 0xFFFFFFFFL
                // }
                // else
                // {
                //     (result & 0xFFFFFFFFL)
                // }

                val result1: BigInt = result & 0xFFFFFFFFL // I think both are used for control left logicals and addition and signed to unsigned

                c.io.arg_x.poke(src_a.asUInt)
                c.io.arg_y.poke(src_b.asUInt)
                c.io.alu_oper.poke(aluop)
                c.clock.step(1)
                c.io.alu_out.expect(result1.asUInt)
            }
            c.clock.step(2)
        }
    }
}