package sample;
/*
 *
 * Name: CPU.java
 * Author: Richard Hsu and Steve
 * Overview: This will be the CPU class of the Processor. 
 * It is made up of different functions to handle the different
 * instructions that need to be carried out. (EX: Load/Store...)
 *
*/
import java.util.BitSet;

public class CPU
{
	// 2 floating point registers
	short[] FR = new short[2];
	
	// 4 general purpose registers
    short[] GPR = new short[4];

    // 3 index registers but 4 different values, 0 , 1, 2, 3
    short[] IR = new short[4];

    //16bit MSR
    short MSR = 0;
    
    //4 boolean bit MFR as an array
    boolean[] MFR = new boolean[4];
    
    // 1 instruction set register
    short ISR = 0;
    short ISR_2 =0;
    // 1 12 bit program counter
    int PC = 0;

    // make memory buffer register
	short MBR = 0;

	// make memory address register
	short MAR = 0;

	// Condition code = supposed to be 4 bits - we can change implementation
	
	boolean[] CC = new boolean[4];
	
	int[] parameters;
	int[] parameters_2;   // create an array for the parameters
	
	int opcode = 0;
	int opcode_2 = 0;
	
	public short process_instruction(int index, cache cache) {
		// fetch the value that PC is pointing to (an instruction) and move it into mbr
		ISR_2 = ISR;
		MBR = cache.read(index);
		// fetch the value that is in MBR to ISR
		ISR = MBR;
		System.out.println("Instruction stored at ISR. ISR = " + ISR);
		System.out.println("Here is the end of fetch stage.");
		//stage one -- fetch
		
		//find the opcode
		parameters = parameters_2;
		opcode_2 = opcode;
		if(ISR_2 != 0) {
			opcode = ISR >> 10;
			opcode = opcode & 0x3F;
			System.out.println("opcode is " + opcode);
			parameters_2 = parseInstruction(ISR, opcode);
			// parse the instruction at ISR into Opcode, R, IX, I, Address
		}
		System.out.println("Here is the end of decode stage.");
		//stage two -- decode
		
		// check the parameters array and see that everything is there
		//for (int i = 0; i < parameters.length; i++) {
			//System.out.println(parameters[i]);
		//}


		// use opcode to figure out what instruction to run
		if(opcode_2 != 0) {
			switch (opcode_2) {
			case 1:
				// call load
				ldr(parameters[1], parameters[2], parameters[3], parameters[4], cache);
				break;
			case 2:
				str(parameters[1], parameters[2], parameters[3], parameters[4], cache);
				break;
			case 3:
				lda(parameters[1], parameters[2], parameters[3], parameters[4], cache);
				break;
			case 4:
				amr(parameters[1], parameters[2], parameters[3], parameters[4], cache);
				break;
			case 5:
				smr(parameters[1], parameters[2], parameters[3], parameters[4], cache);
				break;
			case 6:
				air(parameters[1], parameters[4]);
				break;
			case 7:
				sir(parameters[1], parameters[4]);
				break;
			case 10:
				jz(parameters[1], parameters[2], parameters[3], parameters[4], cache);
				break;
			case 11:
				jne(parameters[1], parameters[2], parameters[3], parameters[4], cache);
				break;
			case 12:
				jcc(parameters[1], parameters[2], parameters[3], parameters[4], cache);
				break;
			case 13:
				jma(parameters[1], parameters[2], parameters[3], parameters[4], cache);
				break;
			case 14:
				jsr(parameters[1], parameters[2], parameters[3], parameters[4], cache);
				break;
			case 15:
				rfs(parameters[4], cache);
				break;
			case 16:
				sob(parameters[1], parameters[2], parameters[3], parameters[4], cache);
				break;
			case 17:
				jge(parameters[1], parameters[2], parameters[3], parameters[4], cache);
				break;
			case 20:
				mlt(parameters[1], parameters[2]);
				break;
			case 21:
				dvd(parameters[1], parameters[2]);
				break;
			case 22:
				trr(parameters[1], parameters[2]);
				break;
			case 23:
				and(parameters[1], parameters[2]);
				break;
			case 24:
				orr(parameters[1], parameters[2]);
				break;
			case 25:
				not(parameters[1]);
				break;
			case 31:
				src(parameters[1], parameters[2], parameters[4]);
				break;
			case 32:
				rrc(parameters[1], parameters[2], parameters[4]);
				break;
			case 33:
				fadd(parameters[1], parameters[2], parameters[3], parameters[4], cache);
				break;
			case 34:
				fsub(parameters[1], parameters[2], parameters[3], parameters[4], cache);
			case 35:
				vadd(parameters[1], parameters[2], parameters[3], parameters[4], cache);
			case 36:
				vsub(parameters[1], parameters[2], parameters[3], parameters[4], cache);;
				break;
			case 50:
				ldfr(parameters[1], parameters[2], parameters[3], parameters[4], cache);
				break;
			case 51:
				jsr(parameters[1], parameters[2], parameters[3], parameters[4], cache);
			case 41:
				ldx(parameters[2], parameters[4], parameters[3], cache);
				break;
			case 42:
				stx(parameters[2], parameters[4], parameters[3], cache);
				break;
			case 61:
				in(parameters[1]);
				break;
			case 62:
				return out(parameters[1]);
			//case 63:
			//	chk(parameters[2], parameters[4]);
			//	break;
			default:
				MFR[2] = true;
				break;
			}
		}
		System.out.println("Here is the end of excute stage.");
		//stage three -- execute
		return 0;
	}


	// converts the address PC is pointing to into an int
	/*private static int bitSetToInt(BitSet bitSet)
	{
	    int bitInteger = 0;
	    for(int i = 0 ; i < 32; i++)
	   	{
	        if(bitSet.get(i))
	        {
	            bitInteger |= (1 << i);
	        }
	    }
	    System.out.println("Converting: " + bitInteger);
	    return bitInteger;
	} */

	// parse the value stored at that index to turn instruction into a sequence of bits
	private int [] parseInstruction(short instruction, int opcode)
	{
		int GPRValue = 0;
		int IRValue = 0;
		int IAValue = 0;
		int AddressValue = 0;
		int [] parameters = new int[5];
		switch(opcode) {
			case 0:
				System.out.println("case 0?");;
				break;
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 10:
			case 11:
			case 12:
			case 13:
			case 14:
			case 15:
			case 16:
			case 17:
			case 20:
			case 21:
			case 22:
			case 23:
			case 24:
			case 25:
			case 31:
			case 32:
			case 33:
			case 34:
			case 35:
			case 36:
			case 41:
			case 42:
			case 50:
			case 51:
			case 61:
			case 62:
			case 63:
				GPRValue = (instruction & 0x3FF) >> 8;
				IRValue = (instruction & 0xFF) >> 6;
				IAValue = (instruction & 0x3F) >> 5;
				AddressValue = instruction & 0x1F;
				parameters[0] = opcode; 
				parameters[1] = GPRValue;
				parameters[2] = IRValue;
				parameters[3] = IAValue;
				parameters[4] = AddressValue;
				break;
			default:
				break;

		}
		return parameters;
	}

	// load instruction
	private void ldr(int R, int IX, int I, int address, cache cache)
	{
		// calculate the effective address
		int EA = getEA(IX, I, address, cache);
		// load register IX with the contents of the specified address.
		if(EA < 6) {
			MFR[0] = true;
			return;
		}
		else if(EA > 2048) {
			MFR[3] = true;
			return;
		}
		GPR[R] = cache.read(EA);
		System.out.println("Load completed");
		System.out.println(GPR[R]);
	}

	private void str(int R, int IX, int I, int address, cache cache)
	{
		int EA = getEA(IX, I, address, cache);
		//store
		if(EA < 6) {
			MFR[0] = true;
			return;
		}
		else if(EA > 2048) {
			MFR[3] = true;
			return;
		}
		cache.write(EA, GPR[R]);
		System.out.println("Store completed");
		System.out.println(cache.read(EA));
	}

	private void lda(int R, int IX, int I, int address, cache cache)
	{
		int EA = getEA(IX, I, address, cache);
		if(EA < 6) {
			MFR[0] = true;
			return;
		}
		else if(EA > 2048) {
			MFR[3] = true;
			return;
		}
		GPR[R] = (short)EA;
		System.out.println("Store completed");
		System.out.println(GPR[R]);

	}
    /** This is a method that loads index register from memory. 
     *  @param: int IX, int address, short[] IR, short[] memory, int param
     *  @return: void
     */
    private void ldx(int IX, int address, int I, cache cache) {
        if(I == 0) {
            IR[IX] = (short) address;
        }
        else{
            IR[IX] = cache.read(address);
        }
        System.out.println("Load completed");
        System.out.println("IR[IX]: " + IR[IX]);

    }
    /** This is a method that stores index register to memory. 
     *  @param: int IX, int address, short[] IR, short[] memory, int param
     *  @return: void
     */
    private void stx(int IX, int address, int I, cache cache) {
        if (I == 0) {
            //Main.memory.memorybank[address] = IR[IX];
            cache.write(address, IR[IX]);
        }else{
            cache.write((int)cache.read(address), IR[IX]);
        }
        System.out.println("Load completed");
        System.out.println("that is correct! no more test!");

    }

	private void amr(int R, int IX, int I, int address, cache cache)
	{
		int EA = getEA(IX, I, address, cache);
		if(EA < 6) {
			MFR[0] = true;
			return;
		}
		else if(EA > 2048) {
			MFR[3] = true;
			return;
		}
		GPR[R] = (short) (cache.read(EA) + GPR[R]);
		System.out.println("Load completed");
		System.out.println(GPR[R]);

	}

	private void smr(int R, int IX, int I, int address, cache cache)
	{
		int EA = getEA(IX, I, address, cache);
		if(EA < 6) {
			MFR[0] = true;
			return;
		}
		else if(EA > 2048) {
			MFR[3] = true;
			return;
		}
		GPR[R] = (short) (GPR[R] - cache.read(EA));
		System.out.println("Load completed");
		System.out.println(GPR[R]);

	}

	private void air(int R, int im)
	{
		GPR[R] += (short) im;
		System.out.println("Load completed");
		System.out.println(GPR[R]);

	}

	private void sir(int R, int im)
	{
		GPR[R] -= (short) im;
		System.out.println("Load completed");
		System.out.println(GPR[R]);

	}

	// jump instruction
	private void jz(int R, int IX, int I, int address, cache cache)
	{
		int EA = getEA(IX, I, address, cache);
		if(EA < 6) {
			MFR[0] = true;
			return;
		}
		else if(EA > 2048) {
			MFR[3] = true;
			return;
		}
		if (GPR[R] == 0)
		{
			setPC(EA - 1);
			System.out.println("getea: " + EA);
			System.out.println("getPC: " + getPC());
		}
//		else 
//		{
//			setPC(getPC() + 1);
//		}
	}

	private void jne(int R, int IX, int I, int address, cache cache)
	{
		int EA = getEA(IX, I, address, cache);
		if(EA < 6) {
			MFR[0] = true;
			return;
		}
		else if(EA > 2048) {
			MFR[3] = true;
			return;
		}
		if (GPR[R] != 0)
		{
			setPC(EA - 1);
		}
	}

	private void jcc(int R, int IX, int I, int address, cache cache)
	{
		int EA = getEA(IX, I, address, cache);
		if(EA < 6) {
			MFR[0] = true;
			return;
		}
		else if(EA > 2048) {
			MFR[3] = true;
			return;
		}
		System.out.println(CC[R]);
		if (CC[R] != false)
		{

			setPC(EA - 1);
		}
		//else 
		//{
			//System.out.println("CC " + R + " is: " + CC[R]);
			//setPC(getPC() + 1);
		//}
	}

	private void jma(int R, int IX, int I, int address, cache cache)
	{
		int EA = getEA(IX, I, address, cache);
		if(EA < 6) {
			MFR[0] = true;
			return;
		}
		else if(EA > 2048) {
			MFR[3] = true;
			return;
		}
		setPC(EA - 1);
	}


	// confused on R0 should contain pointer to arguments...
	private void jsr(int R, int IX, int I, int address, cache cache)
	{
		int EA = getEA(IX, I, address, cache);
		if(EA < 6) {
			MFR[0] = true;
			return;
		}
		else if(EA > 2048) {
			MFR[3] = true;
			return;
		}
		GPR[3] = (short) (getPC() + 1);
		setPC(EA - 1);
	}

	private void rfs(int im, cache cache)
	{
		GPR[0] = (short) im;

		int GPR3 = (int) GPR[3];
		//System.out.println("GPR[3]: " + GPR3);
		setPC(GPR3);
	}

	private void sob(int R, int IX, int I, int address, cache cache)
	{
		int EA = getEA(IX, I, address, cache);
		if(EA < 6) {
			MFR[0] = true;
			return;
		}
		else if(EA > 2048) {
			MFR[3] = true;
			return;
		}
		GPR[R] = (short) (GPR[R] - 1);
		if (GPR[R] > 0)
		{
			setPC(EA);
		}
		else
		{
			setPC(getPC() + 1);
		}
	}

	private void jge(int R, int IX, int I, int address, cache cache)
	{
		int EA = getEA(IX, I, address, cache);
		//System.out.println("GPR[0]: " + getGPRValue(0));
		//System.out.println(GPR[0]);
		if(EA < 6) {
			MFR[0] = true;
			return;
		}
		else if(EA > 2048) {
			MFR[3] = true;
			return;
		}
		if (GPR[R] >= 0)
		{
			setPC(EA);
		}
		else
		{
			setPC(getPC() + 1);
		}
	}

	private void mlt(int Rx, int Ry)
	{
		GPR[Rx + 1] = (short) ( (int)GPR[Rx] * (int)GPR[Ry] ) ;
		GPR[Rx] = (short) (((int)GPR[Rx] * (int)GPR[Ry]) >> 16);
		if(GPR[Rx] != 0) { CC[0] = true;}
	}

	private void dvd(int Rx, int Ry)
	{
		GPR[Rx + 1] = (short) ((int)GPR[Rx] % (int)GPR[Ry]);
		GPR[Rx] = (short) ((int)GPR[Rx] / (int)GPR[Ry]);
		System.out.println("GPR[Rx + 1]: " + GPR[Rx + 1]);
		if(GPR[Ry] == 0) { CC[3] = true;}
	}

	private void trr(int Rx, int Ry)
	{
		if (GPR[Rx] == GPR[Ry])
		{
			CC[3] = true;
		}
		else 
		{
			CC[3] = false;
		}
	}

	
	private void and(int Rx, int Ry)
	{
		if(GPR[Rx] != 0 && GPR[Ry] != 0) {
			GPR[Rx] = (short) 1;
		}
		else {
			GPR[Rx] = (short) 0;
		}
	}
	

	private void orr(int Rx, int Ry)
	{
		if(GPR[Rx] == 0 && GPR[Ry] == 0) {
			GPR[Rx] = (short) 0;
		}
		else {
			GPR[Rx] = (short) 1;
		}
	}
	
	private void not(int Rx)
	{
		if(GPR[Rx] == 0) {
			GPR[Rx] = 1;
		}
		else{
			GPR[Rx] = 0;
		}
	}

	
	private void src(int Rx, int ALLR, int count)
	{
		count = count & 0x0f;
		System.out.println("allr:" + ALLR);
		if(ALLR == 0) { //arithmetical right shift
			GPR[Rx] = (short) (GPR[Rx] >> count);
		}
		else if(ALLR == 1) {//arithmetical left shift
			GPR[Rx] = (short) (GPR[Rx] << count);

		}
		else if(ALLR == 2) {//logical right shift
			GPR[Rx] = (short) (GPR[Rx] >> count);
		}
		else {// logical left shift
			GPR[Rx] = (short) (GPR[Rx] << count);
		}
		
	}
	

	
	private void rrc(int Rx, int ALLR, int count)
	{
		count = count & 0x0f;
		if(ALLR == 0) { //right rotate
			GPR[Rx] = (short) ((GPR[Rx] >> count) | (GPR[Rx] << (16 - count)));
		}
		else { //left rotate
			GPR[Rx] = (short) ((GPR[Rx] << count) | (GPR[Rx] >> (16 - count)));
		}
	}// this function does not work.

	private void in(int R)
	{
		// call a helper method to get user input
		//short c = getUserInput();
		// move the input to register
		//int value = (int) c;
		GPR[R] = (short) Controller.resultIN;
		System.out.println("GPR"  + R + "is" + GPR[R]);
	}

	private short out(int R)
	{
		// print out whatever is inside R[R]
		System.out.println("return: " + GPR[R] + "R: " + R);
		return GPR[R];

	}
	
	private void trap() {
		MFR[1] = true;
		Main.myCache.write(2, (short)(PC + 1));
		PC = Main.myCache.read(Main.myCache.read(0));
	}

	/*private void chk(int R, int devId)
	{
		if (devId == 0)
		{
			short deviceStatus = 0;
			// check the console keyboard
			GPR[R] = deviceStatus;
		}
		else if (devId == 1)
		{
			short deviceStatus = 0;
			// check the console printer
			GPR[R] = deviceStatus;
		}
		
	}*/

	private void fadd(int R, int IX, int I, int address, cache cache) {
		if(I == 0) {
			 FR[R] = (short) (FR[R] + cache.read(address));
		}
		if(I == 1) {
			FR[R] = (short) (FR[R] + cache.read(cache.read(address)));
		}
	}
	
	private void fsub(int R, int IX, int I, int address, cache cache) {
		if(I == 0) {
			 FR[R] = (short) (FR[R] - cache.read(address));
		}
		if(I == 1) {
			FR[R] = (short) (FR[R] - cache.read(cache.read(address)));
		}
	}
	
	private void vadd(int R, int IX, int I, int address, cache cache) {
		if(I == 0) {
			for(int i = 0; i < FR[R]; i++) {
				cache.write(address + i, (short) (cache.read(address + i) + cache.read(address + i + 1))); 
			}
		}
		if(I == 1) {
			for(int i = 0; i < FR[R]; i++) {
				cache.write(address + i, (short) (cache.read(address + i) + cache.read(address + i + 1))); 
			}
		}
	}
	
	private void vsub(int R, int IX, int I, int address, cache cache) {
		if(I == 0) {
			for(int i = 0; i < FR[R]; i++) {
				cache.write(address + i, (short) (cache.read(address + i) - cache.read(address + i + 1))); 
			}
		}
		if(I == 1) {
			for(int i = 0; i < FR[R]; i++) {
				cache.write(address + i, (short) (cache.read(address + i) - cache.read(address + i + 1))); 
			}
		}
	}
	
	private void ldfr(int R, int IX, int I, int address, cache cache) {
		if(I == 0) {
			FR[R] = cache.read(address);
		}
		if(I == 1) {
			FR[R] = cache.read(cache.read(address));
		}
	}
	
	private void stfr(int R, int IX, int I, int address, cache cache) {
		if(I == 0) {
			cache.write(address, FR[R]);
		}
		if(I == 1) {
			cache.write(cache.read(address), FR[R]);
		}
	}
	
	private int getEA(int IX, int I, int address, cache cache)
	{
		// handle no indirect addressing
		if (I == 0)
		{
			if (IX == 0)
			{
				// load register IX with the contents of the specified address
				return address;
			}
			else
			{
				return address + IR[IX];
				
			}
			
		}
		else
		{
			if (IX == 0)
			{
				return cache.read(address);
				
			}
			else
			{
				return cache.read(address + IR[IX]);
			}
		}
	}

	// getters and setter for GPR
	public short getGPRValue(int index)
	{
		return GPR[index];
	}

	public void setGPRValue(int index, short value)
	{
		GPR[index] = value;
	}


	public int getPC()
	{
		return PC;
	}

	public void setPC(int value)
	{
		PC = value;
	}



    /** 
     *  This is a getter for IR.
     *  @param: int index
     *  @return: short
     */
    public short getIRValue(int index) {
        return IR[index];
    }

    /** 
     *  This is a setter for IR.
     *  @param: int index, short value
     *  @return: void
     */
    public void setIRValue(int index, short value) {
        IR[index] = value;
    }

    /** 
     *  This is a getter for ISR.
     *  @param: void
     *  @return: short
     */
    public short getISRValue() {
        return ISR;
    }

    /** 
     *  This is a setter for ISR.
     *  @param: short value
     *  @return: void
     */
    public void setISRValue(short value) {
        ISR = value;
    }

   	// getters and setter for CC

   	public boolean getCCValue(int index)
   	{
   		return CC[index];
   	}

   	public void setCCValue(int index, boolean value)
   	{
   		CC[index] = value;
   	}

   	private short getUserInput()
	{
		return '1';
	}

    /*public static void main(String [] args)
	{
		//Memory memory = new Memory();

		//cache cache = new cache();
		//CPU cpu = new CPU();
		//System.out.println(PC.toString());

		//process_instruction(index, ISR, memory, IR, GPR);
		System.out.println("CPU");
		cache cache = new cache();
		CPU cpu = new CPU();
		//cpu.test_jz(cache);
		//cpu.test_jne(cache);
		//cpu.test_jcc(cache);
		//cpu.test_jma(cache);
		//cpu.test_jsr(cache);
		//cpu.test_rfs(cache);
		//cpu.test_sob(cache);
		//cpu.test_jge(cache);
		//cpu.test_mlt(cache);
		//cpu.test_mlt(cache);
		//cpu.test_dvd(cache);
		//cpu.test_trr(cache);
		//cpu.test_and(cache);
		//cpu.test_or(cache);
		//cpu.test_not(cache);
		//cpu.test_src(cache);
		cpu.test_rrc(cache);
		//cpu.test_in(cache);
		//cpu.test_out(cache);

	}*/

	
	public void test_jz(cache cache)
	{
		
		// index
		short instruction = 10534;
		short GPRValue = 0;
		cache.write(10, instruction);
		setGPRValue(1, GPRValue);
		process_instruction(10, cache);
		System.out.println("PC: " + getPC());
	}

	public void test_jne(cache cache)
	{
		
		// index
		short instruction = 11782;
		short GPRValue = 0;
		cache.write(10, instruction);
		setPC(10);
		setGPRValue(2, GPRValue);
		process_instruction(10, cache);
		System.out.println("PC: " + getPC());
	}

	public void test_jcc(cache cache)
	{
		
		// index
		short instruction = 12575;
		boolean CCValue = true;
		//short cacheValue = 67;
		cache.write(10, instruction);
		setCCValue(1, CCValue);
		//cache.write(31, cacheValue);
		process_instruction(10, cache);
		System.out.println("PC: " + getPC());
	}

	public void test_jma(cache cache)
	{
		
		// index
		short instruction = 13360;
		short cacheValue = 37;
		cache.write(10, instruction);
		setPC(10);
		cache.write(16, cacheValue);
		process_instruction(10, cache);
		System.out.println("PC: " + getPC());
	}

	public void test_jsr(cache cache)
	{
		
		// index
		short instruction = 14352;
		short cacheValue = 49;
		cache.write(10, instruction);
		setPC(10);
		cache.write(16, cacheValue);
		process_instruction(10, cache);
		System.out.println("PC: " + getPC());
		System.out.println("R[3]: " + getGPRValue(3));
	}

	public void test_rfs(cache cache)
	{
		
		// index
		short instruction = 15384;
		short GPRValue = 1;
		setGPRValue(3, GPRValue);
		//System.out.println("GPR" + getGPRValue(3));
		cache.write(10, instruction);
		//setPC(10);
		
		process_instruction(10, cache);
		System.out.println("GPR[0]: " + getGPRValue(0));
		System.out.println("PC: " + getPC());
	}

	public void test_sob(cache cache)
	{
		
		// index
		short instruction = 16668;
		short GPRValue = 13;
		setGPRValue(1, GPRValue);
		//System.out.println("GPR" + getGPRValue(3));
		cache.write(10, instruction);
		//setPC(10);
		
		process_instruction(10, cache);
		System.out.println("GPR[1]: " + getGPRValue(1));
		System.out.println("PC: " + getPC());
	}

	public void test_jge(cache cache)
	{
		
		// index
		short instruction = 17411;
		short GPRValue = 11;
		setGPRValue(0, GPRValue);
		cache.write(10, instruction);
		//setPC(10);
		
		process_instruction(10, cache);
		System.out.println("PC: " + getPC());
	}

	public void test_mlt(cache cache)
	{
		
		// index
		short instruction = 20608;
		short GPRValue0 = 12;
		short GPRValue2 = 3;
		setGPRValue(0, GPRValue0);
		setGPRValue(2, GPRValue2);
		cache.write(10, instruction);
		//setPC(10);
		
		process_instruction(10, cache);
		System.out.println("GPR[0]: " + getGPRValue(0));
		System.out.println("GPR[1]: " + getGPRValue(1));
		//System.out.println("GPR[2]: " + getGPRValue(2));
		//System.out.println("GPR[3]: " + getGPRValue(3));
	}

	public void test_dvd(cache cache)
	{
		
		// index
		short instruction = 21632;
		short GPRValue0 = 12;
		short GPRValue2 = 3;
		setGPRValue(0, GPRValue0);
		setGPRValue(2, GPRValue2);
		cache.write(10, instruction);
		//setPC(10);
		
		process_instruction(10, cache);
		System.out.println("GPR[0]: " + getGPRValue(0));
		System.out.println("GPR[1]: " + getGPRValue(1));
		//System.out.println("GPR[2]: " + getGPRValue(2));
		//System.out.println("GPR[3]: " + getGPRValue(3));
	}

	public void test_trr(cache cache)
	{
		
		// index
		short instruction = 22656;
		short GPRValue0 = 12;
		short GPRValue2 = 12;
		setGPRValue(0, GPRValue0);
		setGPRValue(2, GPRValue2);
		cache.write(10, instruction);
		//setPC(10);
		
		process_instruction(10, cache);
		System.out.println("GPR[3]: " + getCCValue(3));
		
	}

	public void test_and(cache cache)
	{
		
		// index
		short instruction = 23680;
		short GPRValue0 = 3;
		short GPRValue2 = 3;
		setGPRValue(0, GPRValue0);
		setGPRValue(2, GPRValue2);
		cache.write(10, instruction);
		//setPC(10);
		
		process_instruction(10, cache);
		System.out.println("GPR[0]: " + getGPRValue(0));
		
	}

	public void test_or(cache cache)
	{
		
		// index
		short instruction = 24704;
		short GPRValue0 = 3;
		short GPRValue2 = 5;
		setGPRValue(0, GPRValue0);
		setGPRValue(2, GPRValue2);
		cache.write(10, instruction);
		//setPC(10);
		
		process_instruction(10, cache);
		System.out.println("GPR[0]: " + getGPRValue(0));
		
	}

	public void test_not(cache cache)
	{
		
		// index
		short instruction = 25728;
		short GPRValue0 = 3;
		setGPRValue(0, GPRValue0);
		cache.write(10, instruction);
		//setPC(10);
		
		process_instruction(10, cache);
		System.out.println("GPR[0]: " + getGPRValue(0));
		
	}

	public void test_src(cache cache)
	{
		
		int instruction = 32707;
		short GPRValue0 = 7;
		setGPRValue(3, GPRValue0);
		cache.write(10, (short) instruction);

		
		process_instruction(10, cache);
		System.out.println("GPR[3]: " + getGPRValue(3));
		
	}

	public void test_rrc(cache cache)
	{
		
		int instruction = 33731;
		short GPRValue0 = 5;
		setGPRValue(3, GPRValue0);
		cache.write(10, (short) instruction);

		
		process_instruction(10, cache);
		System.out.println("GPR[3]: " + getGPRValue(3));
		
	}


	public void test_in(cache cache)
	{
		
		int instruction = 62464;
		cache.write(10, (short) instruction);
		
		process_instruction(10, cache);
		System.out.println("GPR[0]: " + getGPRValue(0));
		
	}

	public void test_out(cache cache)
	{
		
		int instruction = 63488;
		cache.write(10, (short) instruction);

		// sample char = 'A'
		short GPRValue0 = 65;
		setGPRValue(0, GPRValue0);
		process_instruction(10, cache);	
	}
	
}