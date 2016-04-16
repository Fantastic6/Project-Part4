# Test Instructions 

## Instructions Test

### FADD (Floating add memory to register)

1. Set FR[0] = 2.2
2. Set mem[8] = 1
3. Run 10000100001000 = FADD 0, 0, 8[,0]
4. FR[0] should be equal to 3.2

### FSUB (Floating subtract memory from register)

1. Set FR[0] = 2.2
2. Set mem[8] = 1
3. Run 1000100000100 = FSUB 0, 0, 8[,0]
4. FR[0] should be equal to 1.2

### VADD (Vector Add)

1. Set FR[0] = 2.0
2. Set mem[8] = 10
3. Set mem[9] = 12
4. Set mem[10] = 1
5. Set mem[11] = 2
6. Set mem[12] = 3
7. Set mem[13] = 4
8. Run 100011000001000 = VADD 0, 0, 8[,0]
9. Now, mem[10] = 4, mem[11] = 6, mem[12] = 3, mem[13] = 4.

### VSUB (Vector Subtract)

1. Set FR[0] = 2.0
2. Set mem[8] = 10
3. Set mem[9] = 12 
4. Set mem[10] = 3
5. Set mem[11] = 4
6. Set mem[12] = 1
7. Set mem[13] = 2
8. Run 100100000001000 = VSUB 0, 0, 8[,0]. 
9. Now, mem[10] = 2, mem[11] = 3, mem[12] = 1, mem[13] = 2.

### LDFR

1. Set FR[0] = 0
2. Set mem[8] = 1
3. Run 10010100001000 = LDFR 0, 0, 8[,0]
4. Now, we have FR[0] = 1.0

### STFR

1. Set FR[0] = 2.0
2. Set mem[8] = 1
3. Run 10011000001000 = STFR 0, 0, 8[,0]. 
4. Now, we have mem[8] = 2.


## Pipeline Test 

1. Load Program2.txt from Part 3. 
2. We run it by clicking on the SSS button. If you chech the console, you can see the following strings: 
	- "First Stage finish -- Fetch", 
	- "Second Stage finish -- Decode" 
	- "Third Stage finish -- Execute" and 
	- "Last Stage finish -- WriteBack"
3. Check opcode values for different stage. We can see different opcodes, which means we actually rum different instructions iin our CPU.

* Note: Stage information will not be showen if we just click the run button.

* PS: We kept the code for Program 1 and Program 2 unchanged, but we change the sentences for program 2 to make the test faster.
