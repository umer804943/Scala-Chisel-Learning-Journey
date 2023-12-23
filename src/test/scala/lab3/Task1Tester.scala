package lab3

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Task1Tester extends FreeSpec with ChiselScalatestTester {
  "Lab3 Task1Tester Chisel Tester" in {
    test(new Task1) { a =>
      a.io.fnct3.poke(6.U)
      a.io.branch.poke(true.B)
      a.io.arg_x.poke(16.U)
      a.io.arg_y.poke(16.U)
      a.clock.step(1)
      a.io.br_taken.expect(true.B)
    }
  }
}