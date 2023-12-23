package lab5

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Task3Tester extends FreeSpec with ChiselScalatestTester {
  "Task3Tester Chisel Tester" in {
    test(new Task3(32, UInt(32.W), SInt(32.W))) { a =>

      a.io.in0.poke(10.U)
      a.io.in1.poke(20.S)
      a.io.sel.poke(false.B)
      a.io.out.expect(20.U)

    }
  }
}