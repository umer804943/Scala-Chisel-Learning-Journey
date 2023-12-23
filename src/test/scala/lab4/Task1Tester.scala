package lab4
import chisel3._
import chisel3.util
import org.scalatest._
import chiseltest._
import chiseltest.experimental.TestOptionBuilder._
// import chiseltest.internel.VerilatorBackendAnnotation


import scala.util._


class Task1Tester extends FreeSpec with ChiselScalatestTester {
  "Lab4 Task1Tester Chisel Tester" in {
    test(new Task1) { a =>
      for(i <- 0 until 100)
      {
        // var fnct3 = Random.nextInt(7)
        var fnct3 = 1
        if(fnct3 == 2)
        {
          fnct3 = 1
        }
        else if(fnct3 == 3)
        {
          fnct3 = 4
        }
        val arg_x = Random.nextLong() & 0xFFFFFFFFL //nextLong as want UInt to to module.
        val arg_y = Random.nextLong() & 0xFFFFFFFFL

        val output = fnct3 match
        {
          case 0 => arg_x.toInt == arg_y.toInt
          case 1 => arg_x.toInt != arg_y.toInt
          case 4 => arg_x.toInt < arg_y.toInt
          case 5 => arg_x.toInt >= arg_y.toInt
          case 6 => arg_x < arg_y
          case 7 => arg_x >= arg_y
        }

        a.io.fnct3.poke(fnct3.asUInt)
        a.io.branch.poke(true.B)
        a.io.arg_x.poke(arg_x.asUInt)
        a.io.arg_y.poke(arg_y.asUInt)
        a.clock.step(1)
        a.io.br_taken.expect(output.asBool)
      }
    }
  }
}