# Design Documentation

The interface is the same as Part 3 except for small changes. 

We chose to implement the Floating Point and Vector Operations or IV A.

## Pipelining

To implement pipelining, there are four stages: fetch, decode, execute, and write-back. 

When the SSS button is clicked, the program will process different instructions in different stages. 

Suppose we have 4 instructions called i1, i2, i3, and i4. 

When we first click the SSS button, the CPU will fetch instriction i1 into the instruction register, IR and the rest will do nothing becuase there is no data yet. 

When we click the SSS button a second tiem, the CPU will pass the data in IR to the second stage for decoding and fetch the next instruction (i2).

Following this, when we click the SSS a third time, the CPU executes instruction i1, decodes instruction i2, and fetches instruction i3. Finally, after another click, the CPU will fetch instruction i4, decode instruction i3, execute instruction i2, and write the result of instruction i1 back into cache. 

Note: If the instruction depends on the previous instruction's result, we will make a bubble in some stage to wait for that result. We also add buffers between the stage to implement the stages. But those buffers will be hidden from the outside. 

## Floating Point and Vector Operations

To implement floating point numbers, we added two floating point registers to the CPU which are both 16 bits in length. 

The syntax for the instructions is the following:

The first bit of the exponent is the sign bit, (s). This is followed by 7 bits of exponent (EXP), and 8 bits for the mantissa. 

To carry out operations, we first convert the floating point number by (-1)^s * 2^EXP + binarytoint(mantissa) * 10^((-1) * (getlength(binarytoint(mantissa))). Then we can perform the regular add or subtract. When we are done, we can convert the result back into floating number in the format of s, EXP, and the mantissa. 

* Note: CNVRT (Opcode 37) concept is a little vague. the F indicator is not shown in the instruction. 

For the vector operations, we use the floating point registers (FR) to record the length of the vector. We can then treat it as an adder for a batch of memeory with another and store it in that batch of memory. In the insturction, we take the legnth of the vector as the number of times the loop will execute. 

Moreover, c(EA) is the address of the first vector and c(EA + 1) is the starting address of the second vector. Each of these vectors is c(FR) long. 

Note: Since VSUB instruction as the same opcode as the TRAP instricition, we made VSUB's opcode as 37 which is is hold for CNVRT. 




