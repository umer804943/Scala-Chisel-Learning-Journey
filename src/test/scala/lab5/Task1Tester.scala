package lab5

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Task1Tester extends FreeSpec with ChiselScalatestTester {
  "Task1Tester Chisel Tester" in {
    test(new Task1(32)) { a =>

      a.io.in0.poke(5.U)
      a.io.in1.poke(10.U)

    //   a.clock.step(1)
      a.io.sum.expect(15.U)

    }
  }
}