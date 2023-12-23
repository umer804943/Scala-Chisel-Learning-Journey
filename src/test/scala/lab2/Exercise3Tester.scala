package lab2

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Exercise3Tester extends FreeSpec with ChiselScalatestTester {
  "Lab2 Exercise3Tester Chisel Tester" in {
    test(new Exercise3) { a =>
      a.io.in0.poke((0.U).asBool)
      a.io.in1.poke((0.U).asBool)
      a.io.in2.poke((0.U).asBool)
      a.io.in3.poke((1.U).asBool)
      a.io.out.expect(3.U)
      a.clock.step(1)
    }
  }
}