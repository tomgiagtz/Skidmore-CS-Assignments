import java.util.Arrays;

/**
* Simulates the arithmetic and logic unit (ALU) of a processor. Follows the
* ARMv8 architecture, with the exception of the number of bits used for input
* and output values. Uses the BINARY_LENGTH constant from the Binary class as
* the nubmer of bits for inputs and output.
*
* The ALU must be implemented using logic operations (AND, OR, NOT) on each
* set of input bits because the goal of this assignment is to learn about how
* a computer processor uses logic gates to perform arithmetic. The Java
* arithmetic operations should not be used in this Java class.
*
* @author your name and your partner's name
*/
public class ALU {
    /** Input A: a 32 bit binary value */
    private boolean[] inputA;

    /** Input B: a 32 bit binary value */
    private boolean[] inputB;

    /** Output: a 32 bit binary value */
    private boolean[] output;

    /** ALU Control line */
    private int control;

    /** Zero flag */
    private boolean zeroFlag;

    /** Carry flag */
    private boolean carryFlag;

    /** Overflow flag */
    private boolean overflowFlag;

    /**
    * Constructor initializes inputs and output to random binary values,
    * intializes all control bits to true, initalizes the control value to 15,
    * and initializes flag bits to false.
    * Inputs and output arrays should have the length of the BINARY_LENGTH
    * constant from the Binary class.
    */
    public ALU() {
        setInputA(randomBin());
        setInputB(randomBin());
        output = randomBin();
        control = 15;
        zeroFlag = false;
        carryFlag = false;
        overflowFlag  = false;
    }

    /**
    * Sets the value of inputA.
    *
    * @param b The value to set inputA to
    *
    * @exception IllegalArgumentException if array b does not have length
    * Binary.BINARY_LENGTH
    */
    public void setInputA(boolean[] b) {
        if (b.length != Binary.BINARY_LENGTH) {
            throw new IllegalArgumentException("Parameter array does not have length: " + Binary.BINARY_LENGTH);
        }
        inputA = b;
    }

    /**
    * Sets the value of inputB.
    *
    * @param b The value to set inputB to
    *
    * @exception IllegalArgumentException if array b does not have length
    * Binary.BINARY_LENGTH
    */
    public void setInputB(boolean[] b) {
        if (b.length != Binary.BINARY_LENGTH) {
            throw new IllegalArgumentException("Parameter array does not have length: " + Binary.BINARY_LENGTH);
        }
        inputB = b;
    }

    /**
    * Sets the value of the control line to one of the following values. Note
    * that we are not implementing all possible control line values.
    * 0 for AND;
    * 1 for OR;
    * 2 for ADD;
    * 6 for SUBTRACT;
    * 7 for PASS INPUT B.
    *
    * @param c The value to set the control to.
    * @exception IllegalArgumentException if c is not 0, 1, 2, 6, or 7.
    */
    public void setControl(int c) {

        switch (c) {
            case 0: control = 0;
                break;
            case 1: control = 1;
                break;
            case 2: control = 2;
                break;
            case 6: control = 6;
                break;
            case 7: control = 7;
                break;
            default:    throw new IllegalArgumentException("c is not 0, 1, 2, 6, or 7");

        }
    }

    /**
    * Returns a copy of the value in the output.
    *
    * @return The value in the output
    */
    public boolean[] getOutput() {
        boolean[] out = new boolean[Binary.BINARY_LENGTH];
        for (int i = 0; i < Binary.BINARY_LENGTH; i++) {
            out[i] = output[i];
        }
        return output;
    }

    /**
    * Returns the value of the zeroFlag data member. The zeroFlag data member
    * is set to true when the result of the ALU operation is zero.
    *
    * @return The value of the zeroFlag data member
    */
    public boolean getZeroFlag() {
        return zeroFlag;
    }

    /**
    * Returns the value of the carryFlag data member. The carryFlag data member
    * is set to true if the ALU addition operation has a carry out of the most
    * significant bit.
    *
    * @return The value of the carryFlag data member
    */
    public boolean getCarryFlag() {
        return carryFlag;
    }

    /**
    * Returns the value of the overflowFlag data member. The overflowFlag data
    * member is set to true if the ALU addition operation has a result that
    * is overflow when the operands are signed integers.
    *
    * @return The value of the overflowFlag data member
    */
    public boolean getOverflowFlag() {
        return overflowFlag;
    }


    /**
    * Activates the ALU so that the ALU performs the operation specified by
    * the control data member on the two input values. When this method is
    * finished, the output data member contains the result of the operation.
    *
    * @exception RuntimeException if the control data member is not set to
    * a valid operation code.
    */
    public void activate() {
        overflowFlag = false;
        carryFlag = false;
        zeroFlag = false;
        switch (control) {
            case 0: and();
                break;
            case 1: or();
                break;
            case 2: add();
                break;
            case 6: sub();
                break;
            case 7: passB();
                break;
            default:    throw new RuntimeException("control data member is not set to a valid operation code");
        }

    }

    /**
    * Performs the bitwise AND operation
    */
    private void and() {
        boolean curr;
        for (int i = 0; i < Binary.BINARY_LENGTH; i++) {
            curr = inputA[i] && inputB[i];
            output[i] = curr;
        }
    }

    /**
    * Performs the bitwise OR operation
    */
    private void or() {
        boolean curr;
        for (int i = 0; i < Binary.BINARY_LENGTH; i++) {
            curr = inputA[i] || inputB[i];
            output[i] = curr;
        }
    }

    /**
    * Performs the addition operation using ripple-carry addition of each bit.
    */
    private void add() {
        boolean[] curr = new boolean[2];
        zeroFlag = true;

        for (int i = 0; i < Binary.BINARY_LENGTH; i++) {
            boolean carry = curr[1];
            curr = addBit(inputA[i], inputB[i], carry);
            output[i] = curr[0];
            if (output[i]) {
                zeroFlag = false;
            }
        }
        if (curr[1]) { // last carry out is 1
            carryFlag = true;
        }
        boolean msbA = inputA[Binary.BINARY_LENGTH-1];
        boolean msbB = inputB[Binary.BINARY_LENGTH-1];
        boolean msbO = output[Binary.BINARY_LENGTH-1];

        boolean msbSame = !xor(msbA, msbB); //if inputs have same sign

        if (msbSame && (msbA != msbO)){//
            overflowFlag = true;
        }

    }

    /**
    * Performs the subtraction operation using a ripple-carry adder. In order
    * to perform subtraction with the bit adder, set the first carry-in to 1
    * and invert the bits of inputB.
    */
    private void sub() {
        boolean[] curr = new boolean[2];

        curr[1] = true; //first carry in = 1
        boolean[] bInv = invert(inputB);
        zeroFlag = true;


        for (int i = 0; i < Binary.BINARY_LENGTH; i++) {
            boolean carry = curr[1];
            curr = addBit(inputA[i], bInv[i], carry);
            output[i] = curr[0];
            if (output[i]) {
                zeroFlag = false;
            }
        }
        if (curr[1]) { // last carry out is 1
            carryFlag = true;
        }
        boolean msbA = inputA[Binary.BINARY_LENGTH-1];
        boolean msbB = bInv[Binary.BINARY_LENGTH-1];
        boolean msbO = output[Binary.BINARY_LENGTH-1];

        boolean msbSame = !xor(msbA, msbB); //if inputs have same sign

        if (msbSame && (msbA != msbO)){//
            overflowFlag = true;
        }

    }


    /**
    * Copies inputB to the output.
    */
    private void passB() {
        if (Arrays.equals(inputB, new boolean[Binary.BINARY_LENGTH])){
            zeroFlag = true;
        }
        output = inputB;
    }

    /**
    * Simulates a 1-bit adder.
    *
    * @param a Represents an input bit
    * @param b Represents an input bit
    * @param c Represents the carry in bit
    * @return An array of length 2, index 0 holds the output bit and index 1
    * holds the carry out
    */
    private boolean[] addBit(boolean a, boolean b, boolean c) {
        boolean[] out = new boolean[2];

        // This method may only use the Java logic operations && (logical and),
        // || (logical or), and ! (logical not). Do not use any Java arithmetic
        // operators in this method.

        boolean aXORb = xor(a,b);
        out[0] = xor(aXORb, c); //SUM using XOR

        out[1] = (a && b) || (aXORb && c); //carry using AND

        return out;
    }

    /**
     *
     * Inverts the bit of a binary number
     * @param b The binary number
     * @return The inverted binary number
     * @exception IllegalArgumentException Parameter array does not have length Binary.BINARY_LENGTH
     */
    private boolean[] invert(boolean[] b) {

        if (b.length != Binary.BINARY_LENGTH) {
            throw new IllegalArgumentException("Parameter array does not have length: " + Binary.BINARY_LENGTH);
        }

        boolean[] inv = new boolean[Binary.BINARY_LENGTH];
        for (int i = 0; i < Binary.BINARY_LENGTH; i++) {
            inv[i] = !b[i];
        }
        return inv;
    }

    /**
     * Returns the exclusive or result of two bits
     * @param a bit 0
     * @param b bit 1
     * @return a exclusive or b
     */
    private boolean xor(boolean a, boolean b){
        return (a || b) && !(a && b);
    }


    /**
     * Generates a random binary value
     *
     * @return A random binary number
     */
    private boolean[] randomBin(){

        int range =  (int) Math.pow(2, Binary.BINARY_LENGTH);
        long randDec = (int)( (Math.random() * range) - (range/2));

        boolean[] rand = Binary.sDecToBin(randDec);
        return rand;
    }

}
