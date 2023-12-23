package lab5

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Task2Tester extends FreeSpec with ChiselScalatestTester {
  "Task2Tester Chisel Tester" in {
    test(new Task2(SInt(32.W))) { a =>

      a.io.in.addField.poke(1023.U)
      a.io.in.dataField.poke(10.S)

      a.io.in.addField.expect(1023.U)
      a.io.in.dataField.expect(10.S)

    }
  }
}