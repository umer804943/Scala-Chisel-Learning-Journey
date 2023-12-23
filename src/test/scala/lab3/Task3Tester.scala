package lab3

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Task3Tester extends FreeSpec with ChiselScalatestTester {
  "Lab3 Task3Tester Chisel Tester" in {
    test(new Task3) { a =>
      a.io.in.poke(3.U)
      a.clock.step(1)
      a.io.out.valid.expect(true.B)
      a.io.out.bits.expect(8.U)
    }
  }
}