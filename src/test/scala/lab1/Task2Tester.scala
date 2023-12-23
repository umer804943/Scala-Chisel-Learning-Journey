package lab1

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Task2Tester extends FreeSpec with ChiselScalatestTester {
  "Task2Tester Chisel Tester" in {
    test(new Task2(4)) { a =>
      a.io.data_in.poke(8.U)
      a.io.reload.poke(true.B)
      a.clock.step(35)
      a.io.out.expect(false.B)
    }
  }
}
