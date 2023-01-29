# x86emu

### What is this?
An "x86 emulator" written in Java. The purpose is to create an x86 superoptimizer that can be used to automatically
discover novel transformations of input x86 sequences. The emulation component takes up the bulk of the program, as it
is the primary means by which instruction sequences are verified.

### Why is this?
I thought it'd be fun and interesting to write an x86 superoptimizer. The goal of this project is not automatic
transforms of x86 code created by a compiler, instead it is to find inefficiencies in compiler generated x86 to ideally
improve the code generator. Because of that, the execution and performance constraints of this tool are much looser.

### How is this?
The project works by parsing an input x86 sequence, calculating the starting and ending state of the cpu registers and
heap/stack memory, and generating millions of random sequences of instructions to find sequences that produce the same
result. Currently, branches, jumps, and loops are not supported.

### TODO:
This is a short-term TODO list for the project.
- [ ] Arithmetic instructions
   - [ ] Simple instructions
      - [x] Add 
      - [ ] Sub 
      - [x] And 
      - [x] Or 
      - [x] Xor 
      - [x] Shift instructions (shl, shr, sar)
      - [x] Not
      - [x] Neg
      - [ ] Inc
      - [ ] Dec
      - [ ] Ror (Rotate right)
      - [ ] Rol (Rotate left)
   - [ ] Complex instructions
      - This is blocked due to the register model not being able to represent these instructions clobbering various registers
      - [ ] Mul
      - [ ] IMul
      - [ ] Div
      - [ ] IDiv
- [ ] Data Instructions
  - [x] Mov
  - [ ] Lea
- [ ] Improved superoptimization
  - [ ] Loading of registers/memory as lhs
  - [ ] Using RegisterFiller on all livein registers
  - [ ] Allowing the use of temporary registers without comparison of output