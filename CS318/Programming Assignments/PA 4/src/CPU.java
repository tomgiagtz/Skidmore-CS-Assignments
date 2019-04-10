/**
 * Simulates an ARMv8 CPU following the datapath from Figure 4.23 in the textbook.
 *
 * @author <student's name>
 */

import jdk.nashorn.internal.runtime.regexp.joni.constants.OPCode;

import java.io.*;
import java.util.*;

public class CPU {

    /**
     * Flag to inidcate whether or not in grading mode. Grading mode prints additional messages.
     */
    private static final boolean GRADING = true;

    /**
     * Memory unit for instructions
     */
    private Memory instructionMemory;

    /**
     * Memory unit for data
     */
    private Memory dataMemory;

    /**
     * Register unit
     */
    private Registers registers;

    /**
     * Arithmetic and logic unit
     */
    private ALU alu;

    /**
     * Adder for incrementing the program counter
     */
    private ALU adderPC;

    /**
     * Adder for computing branches
     */
    private ALU adderBranch;

    /**
     * Control unit
     */
    private SimpleControl control;

    /**
     * Multiplexor output connects to Read Register 2
     */
    private Multiplexor2 muxRegRead2;

    /**
     * Mulitplexor ouptut connects to ALU input B
     */
    private Multiplexor2 muxALUb;

    /**
     * Multiplexor output connects to Register Write Data
     */
    private Multiplexor2 muxRegWriteData;

    /**
     * Multiplexor outptu connects to Program Counter
     */
    private Multiplexor2 muxPC;

    /**
     * Program counter
     */
    private boolean[] pc;

    /**
     * STUDENT SHOULD NOT MODIFY THIS METHOD
     * <p>
     * Constructor initializes all data members.
     *
     * @param iMemFile Path to the file with instruction memory contents.
     * @param dMemFile Path to the file with data memory contents.
     * @throws FileNotFoundException if a file cannot be opened.
     */
    public CPU(String iMemFile, String dMemFile) throws FileNotFoundException {

        // Create objects for all data members
        instructionMemory = new Memory(iMemFile);
        dataMemory = new Memory(dMemFile);
        registers = new Registers();
        alu = new ALU();
        control = new SimpleControl();
        muxRegRead2 = new Multiplexor2(5);
        muxALUb = new Multiplexor2(32);
        muxRegWriteData = new Multiplexor2(32);
        muxPC = new Multiplexor2(32);


        // Activate adderPC with ADD operation, and inputB set to 4
        // Send adderPC output to muxPC input 0
        adderPC = new ALU();
        adderPC.setControl(2);
        boolean[] four = Binary.uDecToBin(4L);
        adderPC.setInputB(four);

        // Initalize adderBranch with ADD operation
        adderBranch = new ALU();
        adderBranch.setControl(2);

        // initialize program counter to 0
        pc = new boolean[32];
        for (int i = 0; i < 32; i++) {
            pc[i] = false;
        }
    }

    /**
     * STUDENT SHOULD NOT MODIFY THIS METHOD
     * <p>
     * Runs the CPU (fetch, decode, execute cycle). Stops when a halt instruction
     * is reached.
     */
    public void run() throws FileNotFoundException {

        boolean[] instruction = fetch();
        boolean op = decode(instruction);

        // Loop until a halt instruction is decoded
        while (op) {
            execute();

            instruction = fetch();

            op = decode(instruction);
        }

        if (GRADING) {
            // Write memory contents to a file
            dataMemory.writeToFile("checkMem.txt");
        }
    }

    /**
     * STUDENT MUST COMPLETE THIS METHOD
     * <p>
     * Fetch the instruction from the instruction memory starting at address pc.
     *
     * @return The instruction starting at address pc
     */
    private boolean[] fetch() {


        boolean[] inst = instructionMemory.read32(pc);
        //set input to branch adder before adding 4
        adderBranch.setInputA(pc);


        //add 4 to pc
        adderPC.setInputA(pc);
        adderPC.activate();
        muxPC.setInput0(adderPC.getOutput());



        //return instruction
        return inst;
    }

    /**
     * STUDENT MUST COMPLETE THIS METHOD
     * <p>
     * Decode the instruction. Sets the control lines and sends appropriate bits
     * from the instruction to various inputs within the processor.
     *
     * @param instruction The 32-bit instruction to decode
     * @return false if the opcode is HLT; true for any other opcode
     */
    private boolean decode(boolean[] instruction) {

        //cut out bits for various instructions
        boolean[] sixMSB = Arrays.copyOfRange(instruction, 26, 32);
        boolean[] eightMSB = Arrays.copyOfRange(instruction, 24, 32);
        boolean[] elevenMSB = Arrays.copyOfRange(instruction, 21, 32);

        //if hlt, return false
        if (Arrays.equals(elevenMSB, Opcode.HLT)) {
            if (GRADING) {
                System.out.println("HLT");
            }
            return false;

        } else if (Arrays.equals(sixMSB, Opcode.B)) { // if B

            if (GRADING) {
                System.out.println("B");
            }

            control.Uncondbranch = true;

            //sign extend and set input b of branch adder
            boolean[] imm = Arrays.copyOfRange(instruction, 0, 26);
            adderBranch.setInputB(signExtend(imm));


        } else if (Arrays.equals(eightMSB, Opcode.CBZ)) { // if CBZ

            if (GRADING) {
                System.out.println("CBZ");
            }

            control.Reg2Loc = true;
            control.ALUSrc = false;
            control.RegWrite = false;
            control.MemRead = false;
            control.MemWrite = false;
            control.Branch = true;
            control.ALUControl = 7;


            //sign extend and set input b of branch adder
            boolean[] imm = Arrays.copyOfRange(instruction, 5, 24);
            adderBranch.setInputB(signExtend(imm));

        } else if (Arrays.equals(elevenMSB, Opcode.LDR)) { // if LDR

            if (GRADING) {
                System.out.println("LDR");
            }

            control.ALUSrc = true;
            control.MemtoReg = true;
            control.RegWrite = true;
            control.MemRead = true;
            control.MemWrite = false;
            control.Branch = false;
            control.ALUControl = 2;


            //sign extend and set input to 1 of mux ALUb
            boolean[] imm = Arrays.copyOfRange(instruction, 12, 21);
            muxALUb.setInput1(signExtend(imm));

        } else if (Arrays.equals(elevenMSB, Opcode.STR)) { //if STR

            if (GRADING) {
                System.out.println("STR");
            }

            control.Reg2Loc = true;
            control.ALUSrc = true;
            control.RegWrite = false;
            control.MemRead = false;
            control.MemWrite = true;
            control.Branch = false;
            control.ALUControl = 2;

            //sign extend and set input to 1 of mux ALUb
            boolean[] imm = Arrays.copyOfRange(instruction, 12, 21);
            muxALUb.setInput1(signExtend(imm));

        } else { //else must be R-format


            control.Reg2Loc = false;
            control.ALUSrc = false;
            control.MemtoReg = false;
            control.RegWrite = true;
            control.MemRead = false;
            control.MemWrite = false;
            control.Branch = false;
            if (Arrays.equals(elevenMSB, Opcode.ADD)){
                if (GRADING) {
                    System.out.println("ADD");
                }
                control.ALUControl = 2;
            } else if (Arrays.equals(elevenMSB, Opcode.SUB)){
                if (GRADING) {
                    System.out.println("SUB");
                }
                control.ALUControl = 6;
            } else if (Arrays.equals(elevenMSB, Opcode.AND)){
                if (GRADING) {
                    System.out.println("AND");
                }
                control.ALUControl = 0;
            } else {
                if (GRADING) {
                    System.out.println("ORR");
                }
                control.ALUControl = 1;
            }
        }

        //set registers
        boolean[] valueReg = Arrays.copyOfRange(instruction, 0, 5);
        boolean[] baseReg = Arrays.copyOfRange(instruction, 5, 10);
        boolean[] reg2   = Arrays.copyOfRange(instruction, 16, 21);


        registers.setWriteRegNum(valueReg);
        registers.setRead1Reg(baseReg);
        muxRegRead2.setInput0(reg2);
        muxRegRead2.setInput1(valueReg);

        // placeholder return, replace with correct return value
        return true;
    }




    /**
     * STUDENT MUST COMPLETE THIS METHOD
     * <p>
     * Execute the instruction.
     */
    private void execute() {

        //set reg 2 based on Reg2Loc control line
        registers.setRead2Reg(muxRegRead2.output(control.Reg2Loc));

        //set input 0 for ALUb mux
        muxALUb.setInput0(registers.getReadReg2());

        //set inputs for alu

        alu.setInputA(registers.getReadReg1());
        alu.setInputB(muxALUb.output(control.ALUSrc));

        System.out.println(Binary.binToSDec(registers.getReadReg1()));
        System.out.println(Binary.binToSDec(muxALUb.output(control.ALUSrc)));

        //set control line
        alu.setControl(control.ALUControl);

        //activate alu
        alu.activate();
        boolean[] result = alu.getOutput();
        System.out.println(Binary.binToUDec(result));

        if (!control.Uncondbranch) {

            muxRegWriteData.setInput1(result);
            if (control.MemRead) {
                boolean[] readData = dataMemory.read32(result);
                muxRegWriteData.setInput0(readData);

            } else if (control.MemWrite) {
                dataMemory.write32(result, registers.getReadReg2());
            }

            if (control.RegWrite) {
                registers.setWriteRegData(muxRegWriteData.output(control.MemtoReg));
                registers.activateWrite();
            }

        }

        adderBranch.activate();
        muxPC.setInput1(adderBranch.getOutput());
        boolean muxBool = control.Uncondbranch | (alu.getZeroFlag() & control.Branch);
        pc = muxPC.output(muxBool);



    }

    /**
     * Sign-extends an immediate to Binary.BINARY_LENGTH bits
     * @param bin a signed immediate value
     * @return the same value as bin, but in an appropriately sized boolean array
     */
    private boolean[] signExtend(boolean[] bin) {
        boolean [] extended = new boolean[Binary.BINARY_LENGTH];
        for (int i = 0; i < Binary.BINARY_LENGTH; i++) {
            if (i < bin.length) {
                extended[i] = bin[i];
            } else {
                extended[i] = bin[bin.length-1];
            }
        }

        return extended;
    }
}
