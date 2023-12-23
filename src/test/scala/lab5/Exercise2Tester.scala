package lab5
import chisel3._
import chisel3.util
import org.scalatest._
import chiseltest._
import chiseltest.experimental.TestOptionBuilder._
// import chiseltest.internel.VerilatorBackendAnnotation


import scala.util._

class Exercise2Tester extends FreeSpec with ChiselScalatestTester{

    "Exercise2Tester" in{
        val array_Type = Array(SInt(32.W), UInt(32.W))
        for(i <- array_Type)
        {
            test(new Exercise2(i)){ c =>
            //ALU Operations
    
                for (j <- 0 until 100)
                {
                    var src_a = Random.nextLong() & 0xFFFFFFFFL
                    var src_b = Random.nextLong() & 0xFFFFFFFFL
                    var array_sel = Array(true, false)
                    val sel = array_sel(Random.nextInt(1))

                    val result = sel match
                    {
                        case true => src_a 
                        // case ALUOP.ALU_ADD => if(i == UInt(32.W)) src_a + src_b else src_a.toInt + src_b.toInt
                        // case ALUOP.ALU_SUB => if(i == UInt(32.W)) src_a - src_b else src_a.toInt - src_b.toInt
                        // case ALUOP.ALU_AND => src_a & src_b
                        // case ALUOP.ALU_OR => src_a | src_b
                        // case ALUOP.ALU_XOR => src_a ^ src_b
                        // case ALUOP.ALU_SLT => if (src_a.toInt < src_b.toInt) 1 else 0
                        // case ALUOP.ALU_SLL => src_a << (src_b & 0x1F)
                        // case ALUOP.ALU_SLTU => if (src_a < src_b) 1 else 0
                        // case ALUOP.ALU_SRL => src_a >> (src_b & 0x1F)
                        // case ALUOP.ALU_SRA => (src_a.toInt >> (src_b & 0x1F).toInt) // int should be shift by int same as in long
                        case _ => src_b
                    }
    
                    // val result1: BigInt = if(result < 0)
                    // {
                    //     (BigInt(0xFFFFFFFFL) + result + 1) & 0xFFFFFFFFL  //(BigInt(0xFFFFFFFFL) + result + 1) for 1111101 to control 1 upto 32 bit 0011101 and then & 0xFFFFFFFFL 
                    // }
                    // else
                    // {
                    //     (result & 0xFFFFFFFFL)
                    // }
                        
                    if(i == UInt(32.W))
                    {
                        val result1: BigInt = result & 0xFFFFFFFFL  //controling upto 32bit and signed to unsigned
                        c.io.in1.poke(src_a.asUInt)
                        c.io.in2.poke(src_b.asUInt)
                        c.io.sel.poke(sel.asBool())
                        c.clock.step(1)
                        c.io.out.expect(result1.asUInt)
                    }else{
                        val result1: BigInt = (result & 0xFFFFFFFFL).toInt //controlling upto 32bit
                        c.io.in1.poke(src_a.asSInt)
                        c.io.in2.poke(src_b.asSInt)
                        c.io.sel.poke(sel.asBool())
                        c.clock.step(1)
                        c.io.out.expect(result1.asSInt)
                    }
                }
                c.clock.step(2)
            }
        }
    }
}