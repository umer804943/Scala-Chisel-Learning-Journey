package lab1

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Exercise2Tester extends FreeSpec with ChiselScalatestTester {
  "Exercise2Tester Chisel Tester" in {
    test(new Exercise2(3.U)) { a =>
      a.clock.step(8)
      a.io.result.expect(true.B)
    }
  }
}