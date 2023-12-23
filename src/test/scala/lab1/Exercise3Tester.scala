package lab1

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Exercise3Tester extends FreeSpec with ChiselScalatestTester {
  "Exercise3Tester Chisel Tester" in {
    test(new Exercise3(4, 8)) { a =>
      a.clock.step(8)
      a.io.result.expect(true.B)
    }
  }
}