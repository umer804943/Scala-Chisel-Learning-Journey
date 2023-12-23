package lab1

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Exercise1Tester extends FreeSpec with ChiselScalatestTester {
  "Exercise1Tester Chisel Tester" in {
    test(new Exercise1(3)) { a =>
      a.clock.step(7)
      a.io.result.expect(true.B)
    }
  }
}