package lab2

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Exercise1Tester extends FreeSpec with ChiselScalatestTester {
  "Lab2 Exercise1Tester Chisel Tester" in {
    test(new Exercise1) { a =>
      a.io.in_A.poke(4.U)
      a.io.in_B.poke(5.U)
      a.io.select.poke(true.B)
      a.clock.step(1)
      a.io.out.expect(4.U)
    }
  }
}