package lab2

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Task2Tester extends FreeSpec with ChiselScalatestTester {
  "Lab2 Task2Tester Chisel Tester" in {
    test(new Task2) { a =>
      a.io.in(0).poke(true.B)
      a.io.in(1).poke(true.B)
      a.io.in(2).poke(false.B)
      a.io.in(3).poke(false.B)

      a.io.sel(0).poke(true.B)
      a.io.sel(1).poke(true.B)

      a.io.shift_type.poke(false.B)

      a.io.out(0).expect(false.B)
      a.io.out(1).expect(true.B)
      a.io.out(2).expect(true.B)
      a.io.out(3).expect(false.B)

      a.clock.step(1)
    }
  }
}