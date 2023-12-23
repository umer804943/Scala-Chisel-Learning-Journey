package lab2
import chisel3._
import chisel3.util._
class Task2 extends Module {
  val io = IO(new Bundle {
    val in = Vec(4, Input(Bool()))
    val sel = Vec(2, Input(Bool()))
    val shift_type = Input(Bool())
    val out = Vec(4, Output(Bool()))
  })
// Start you code here
// End your code here
  io.out(0) := MuxLookup(Cat(io.sel(1).asUInt, io.sel(0).asUInt), false.B, Array(
    (0.U) -> (io.in(0)),
    (1.U) -> (io.in(1)),
    (2.U) -> (io.in(2)),
    (3.U) -> (io.in(3)),
  )) 
  io.out(1) := MuxLookup(Cat(io.sel(1).asUInt, io.sel(0).asUInt), false.B, Array(
    (0.U) -> (io.in(1)),
    (1.U) -> (io.in(2)),
    (2.U) -> (io.in(3)),
    (3.U) -> (Mux(io.shift_type, false.B, io.in(0)))
  ))
  io.out(2) := MuxLookup(Cat(io.sel(1).asUInt, io.sel(0).asUInt), false.B, Array(
    (0.U) -> (io.in(2)),
    (1.U) -> (io.in(3)),
    (2.U) -> (Mux(io.shift_type, false.B, io.in(0))),
    (3.U) -> (Mux(io.shift_type, false.B, io.in(1)))
  ))
  io.out(3) := MuxLookup(Cat(io.sel(1).asUInt, io.sel(0).asUInt), false.B, Array(
    (0.U) -> (io.in(3)),
    (1.U) -> (Mux(io.shift_type, false.B, io.in(0))),
    (2.U) -> (Mux(io.shift_type, false.B, io.in(1))),
    (3.U) -> (Mux(io.shift_type, false.B, io.in(2)))
  ))
}
// println((new chisel3.stage.ChiselStage).emitVerilog(new barrel_shift))
