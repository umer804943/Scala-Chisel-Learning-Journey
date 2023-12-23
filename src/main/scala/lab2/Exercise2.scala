package lab2

// 8 to 1 mux using MuxLookup
import chisel3._
import chisel3.util._
class Exercise2 extends Module {
  val io = IO(new Bundle {
    val in0 = Input(Bool())
    val in1 = Input(Bool())
    val in2 = Input(Bool())
    val in3 = Input(Bool())
    val in4 = Input(Bool())
    val in5 = Input(Bool())
    val in6 = Input(Bool())
    val in7 = Input(Bool())
    val sel = Input(UInt(3.W))
    val out = Output(Bool())
  })

  io.out := MuxLookup(io.sel(2), false.B, Array(
    (0.U) ->  MuxLookup(io.sel(1), false.B, Array(
              (0.U) -> MuxLookup(io.sel(0), false.B, Array(
                       (0.U) -> io.in0,
                       (1.U) -> io.in1
                       )),
              (1.U) -> MuxLookup(io.sel(0), false.B, Array(
                       (0.U) -> io.in2,
                       (1.U) -> io.in3
                       ))
              )),
    (1.U) ->  MuxLookup(io.sel(1), false.B, Array(
              (0.U) -> MuxLookup(io.sel(0), false.B, Array(
                       (0.U) -> io.in4,
                       (1.U) -> io.in5
                       )),
              (1.U) -> MuxLookup(io.sel(0), false.B, Array(
                       (0.U) -> io.in6,
                       (1.U) -> io.in7
                       ))
              ))
  ))

  
  //Equal Priority
  // val selectPin01Start = Mux(io.sel(1), Mux(io.sel(0), io.in3, io.in2), Mux(io.sel(0), io.in1, io.in0))
  // val selectPin01Last = Mux(io.sel(1), Mux(io.sel(0), io.in7, io.in6), Mux(io.sel(0), io.in5, io.in4))
  // io.out := Mux(io.sel(2), selectPin01Last, selectPin01Start)

}
// println((new chisel3.stage.ChiselStage).emitVerilog(new MuxLookup()))
