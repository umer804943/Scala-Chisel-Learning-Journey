package lab5

import chisel3._
import chisel3.tester._
import org.scalatest.FreeSpec
import chisel3.experimental.BundleLiterals._

class Exercise3Tester extends FreeSpec with ChiselScalatestTester {
  "Exercise3Tester Chisel Tester" in {
    test(new Exercise3(5, UInt(32.W))(_+_)) { a =>
    //   a.io.in.poke(VecInit(1.U, 2.U, 3.U, 4.U, 5.U))
    //   a.clock.step(1)
    //   a.io.out.expect(VecInit(3.U, 6.U, 10.U, 15.U))

      a.io.in(0).poke(1.U)
      a.io.in(1).poke(2.U)
      a.io.in(2).poke(3.U)
      a.io.in(3).poke(4.U)
      a.io.in(4).poke(5.U)

      a.clock.step(1)
      a.io.out(0).expect(3.U)
      a.io.out(1).expect(6.U)
      a.io.out(2).expect(10.U)
      a.io.out(3).expect(15.U)

    }
  }
}