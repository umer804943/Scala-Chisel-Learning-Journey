package lab3

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Exercise1Tester extends FreeSpec with ChiselScalatestTester {
  "Lab3 Exercise1Tester Chisel Tester" in {
    test(new Exercise1) { a =>
      a.io.in.poke(8.U)
      a.clock.step(1)
      a.io.out.expect(3.U)
    }
  }
}