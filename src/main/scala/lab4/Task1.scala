// Branch control ( Assignment )
package lab4
import chisel3._
import chisel3.util._
class LM_IO_Interface_BranchControl extends Bundle {
  val fnct3 = Input(UInt(3.W))
  val branch = Input(Bool())
  val arg_x = Input(UInt(32.W))
  val arg_y = Input(UInt(32.W))
  val br_taken = Output(Bool())
}

class Task1 extends Module {
  val io = IO(new LM_IO_Interface_BranchControl)
  io.br_taken := false.B
  switch(io.fnct3) {
    is("b000".U) {
      io.br_taken := (io.branch && (io.arg_x === io.arg_y)).asBool
    }
    is("b001".U) {
      io.br_taken := (io.branch && (io.arg_x =/= io.arg_y)).asBool
    }
    is("b100".U) {
      io.br_taken := (io.branch && (io.arg_x < io.arg_y)).asBool
    }
    is("b101".U) {
      io.br_taken := (io.branch && (io.arg_x >= io.arg_y)).asBool
    }
    is("b110".U) {
      io.br_taken := (io.branch && (io.arg_x.asUInt < io.arg_y.asUInt)).asBool
    }
    is("b111".U) {
      io.br_taken := (io.branch && (io.arg_x.asUInt >= io.arg_y.asUInt)).asBool
    }
  }
}