package lab2

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Exercise2Tester extends FreeSpec with ChiselScalatestTester {
  "Lab2 Exercise2Tester Chisel Tester" in {
    test(new Exercise2) { a =>
      a.io.in0.poke((0.U).asBool)
      a.io.in1.poke((0.U).asBool)
      a.io.in2.poke((0.U).asBool)
      a.io.in3.poke((0.U).asBool)
      a.io.in4.poke((1.U).asBool)
      a.io.in5.poke((0.U).asBool)
      a.io.in6.poke((0.U).asBool)
      a.io.in7.poke((0.U).asBool)
      a.io.sel.poke(4.U)
      a.io.out.expect(true.B)
      a.clock.step(1)
    }
  }
}