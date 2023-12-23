package lab3

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Task2Tester extends FreeSpec with ChiselScalatestTester {
  "Lab3 Task2Tester Chisel Tester" in {
    test(new Task2) { a =>
      a.io.instr.poke("b00000000000100000001000001101111".U)
      a.clock.step(1)
      a.io.immd_se.expect(3072.U)
    }
  }
}