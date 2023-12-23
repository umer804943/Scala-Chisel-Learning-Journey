package lab3

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Exercise2Tester extends FreeSpec with ChiselScalatestTester {
  "Lab3 Exercise2Tester Chisel Tester" in {
    test(new Exercise2) { a =>
      a.io.in_A.poke(4.U)
      a.io.in_B.poke(1.U)
      a.io.alu_Op.poke(15.U)
      a.clock.step(1)
      a.io.out.expect(0.U)
      a.io.sum.expect(3.U)
    }
  }
}