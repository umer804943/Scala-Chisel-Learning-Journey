package lab2

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Task1Tester extends FreeSpec with ChiselScalatestTester {
  "Lab2 Task1Tester Chisel Tester" in {
    test(new Task1) { a =>
      a.io.s0.poke((0.U).asBool)
      a.io.s1.poke((0.U).asBool)
      a.io.s2.poke((1.U).asBool)
      a.io.out.expect(32.U)
      a.clock.step(1)
    }
  }
}