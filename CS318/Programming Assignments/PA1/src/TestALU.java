/**
* A test program for CS318 ALU Assignment. You should also write your own tests.
* The assignment will be graded using a different test program. This program
* tests all of the methods that you are required to implement, but it does not
* test all possible outcomes. Consider the edge cases that might occur and make
* sure that your code can handle those cases.
*
* @author Christine Reilly
*/
import java.util.Arrays;

public class TestALU {
    public static void main(String[] args) {

        // Variables for binary numbers
        boolean[] binA = {false,true,true,true,false,true,true,true,false,false,false,true,false,true,false,false,false,true,true,false,false,false,true,false,false,false,false,false,false,false,true,false};
        boolean[] binB = {false,false,false,true,false,true,false,false,true,true,true,false,false,true,true,true,true,false,false,false,false,true,false,true,false,true,false,false,true,true,true,false};
        boolean[] binC = {false,true,true,true,false,true,true,true,false,false,false,true,false,true,false,false,false,true,true,false,false,false,true,false,false,false,false,false,false,false,true,true};
        boolean[] binZeros = new boolean[Binary.BINARY_LENGTH]; // set to all 0's
        boolean[] binOnes = new boolean[Binary.BINARY_LENGTH]; // set to all 1's
        boolean[] bin1 = new boolean[Binary.BINARY_LENGTH]; // set to decimal 1
        boolean[] binRes; // store result of operation

        // correct result of binA AND binB
        boolean[] andAB = {false,false,false,true,false,true,false,false,false,false,false,false,false,true,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,false,true,false};

        // correct result of binA OR binB
        boolean[] orAB = {false,true,true,true,false,true,true,true,true,true,true,true,false,true,true,true,true,true,true,false,false,true,true,true,false,true,false,false,true,true,true,false};

        // correct result of binA + binB, no carry out, signed overflow
        boolean[] addAB = {false,true,true,false,true,false,false,false,false,false,false,false,true,false,false,false,false,false,false,true,false,true,true,true,false,true,false,false,true,true,false,true};

        // correct result of binA - binB, no carry out, no signed overflow
        boolean[] subAB = {false,true,true,false,false,false,true,true,true,false,false,false,false,false,true,false,false,false,true,false,false,true,false,true,true,false,true,true,false,false,true,true};

        // correct result of binA + binC, no carry out, no signed overflow
        boolean[] addCB = {false,true,true,false,true,false,false,false,false,false,false,false,true,false,false,false,false,false,false,true,false,true,true,true,false,true,false,false,true,true,false,false};

        // correct result binA - binC, carry out, signed overflow
        boolean[] subCB = {false,true,true,false,false,false,true,true,true,false,false,false,false,false,true,false,false,false,true,false,false,true,false,true,true,false,true,true,false,false,true,false};

        // set binZeros to all false
        // set bin1 to all false (then change index 0 next)
        // set binOnes to all true
        for(int i = 0; i < Binary.BINARY_LENGTH; i++) {
            binZeros[i] = false;
            bin1[i] = false;
            binOnes[i] = true;
        }

        // set bin1 index 0 to true, now it holds decimal 1
        bin1[0] = true;

        // Construct an ALU Object
        ALU alu = new ALU();

        System.out.println("***** Testing logical AND operation ....");
        // Test 1: some random bits
        alu.setInputA(binA);
        alu.setInputB(binB);
        alu.setControl(0);
        alu.activate();
        binRes = alu.getOutput();
        if(!Arrays.equals(binRes, andAB)) {
            System.out.println("FAIL Test 1:");
            System.out.println("ALU result of A and B: " + Binary.toString(binRes));
            System.out.println("Correct result of A and B: " + Binary.toString(andAB));
        } else {
            System.out.println("PASSED Test 1");
        }

        // Test 2: zero flag
        alu.setInputA(binZeros);
        alu.setInputB(binB);
        alu.setControl(0);
        alu.activate();
        binRes = alu.getOutput();
        if(!Arrays.equals(binRes, binZeros)) {
            System.out.println("FAIL Test 2:");
            System.out.println("ALU result of ZEROS and B: " + Binary.toString(binRes));
            System.out.println("Correct result of ZEROS and B: " + Binary.toString(binZeros));
        } else {
            System.out.println("PASSED Test 2");
        }

        System.out.println(".... Finished AND *****\n");

        System.out.println("***** Testing logical OR operation ....");
        // Test 3: some random bits
        alu.setInputA(binA);
        alu.setInputB(binB);
        alu.setControl(1);
        alu.activate();
        binRes = alu.getOutput();
        if(!Arrays.equals(binRes, orAB)) {
            System.out.println("FAIL Test 3:");
            System.out.println("ALU result of A or B: " + Binary.toString(binRes));
            System.out.println("Correct result of A or B: " + Binary.toString(orAB));
        } else {
            System.out.println("PASSED Test 3");
        }

        // Test 4: zero flag
        alu.setInputA(binZeros);
        alu.setInputB(binZeros);
        alu.setControl(1);
        alu.activate();
        binRes = alu.getOutput();
        if(!Arrays.equals(binRes, binZeros)) {
            System.out.println("FAIL Test 4:");
            System.out.println("ALU result of ZEROS or ZEROS: " + Binary.toString(binRes));
            System.out.println("Correct result of ZEROS or ZEROS: " + Binary.toString(binZeros));
        } else {
            System.out.println("PASSED Test 4");
        }

        System.out.println(".... Finished OR *****\n");

        System.out.println("***** Testing addition operation ....");
        // Test 5: All 1's plus decimal 1 results in all 0's
        // yes carry out, no two's comp overflow
        alu.setInputA(binOnes);
        alu.setInputB(bin1);
        alu.setControl(2);
        alu.activate();
        binRes = alu.getOutput();
        if(!Arrays.equals(binRes, binZeros)) {
            System.out.println("FAIL Test 5 addition result:");
            System.out.println("ALU result of binOnes + bin1: " + Binary.toString(binRes));
            System.out.println("Correct result of binOnes + bin1: " + Binary.toString(binZeros));
        } else if(!alu.getZeroFlag() || !alu.getCarryFlag() || alu.getOverflowFlag()) {
            System.out.println("FAIL Test 5 ALU flags:");
            System.out.println("ALU result: zeroFlag = " + alu.getZeroFlag()
                        + "; carryFlag = " + alu.getCarryFlag()
                        + "; overflowFlag = " + alu.getOverflowFlag());
            System.out.println("Correct result: zeroFlag = true; "
                        + "carryFlag = true; overflowFlag = false");

        }
         else {
            System.out.println("PASSED Test 5");
        }

        // Test 6: yes two's comp overflow, no carry out
        alu.setInputA(binA);
        alu.setInputB(binB);
        alu.setControl(2);
        alu.activate();
        binRes = alu.getOutput();
        if(!Arrays.equals(binRes, addAB)) {
            System.out.println("FAIL Test 6 addition result:");
            System.out.println("ALU result of binA + binB: " + Binary.toString(binRes));
            System.out.println("Correct result of binA + binB: " + Binary.toString(addAB));
        } else if(alu.getZeroFlag() || alu.getCarryFlag() || !alu.getOverflowFlag()) {
            System.out.println("FAIL Test 6 ALU flags:");
            System.out.println("ALU result: zeroFlag = " + alu.getZeroFlag()
                        + "; carryFlag = " + alu.getCarryFlag()
                        + "; overflowFlag = " + alu.getOverflowFlag());
            System.out.println("Correct result: zeroFlag = false; "
                        + "carryFlag = false; overflowFlag = true");
        } else {
            System.out.println("PASSED Test 6");
        }

        // Test 7: no two's comp overflow, yes carry out
        alu.setInputA(binC);
        alu.setInputB(binB);
        alu.setControl(2);
        alu.activate();
        binRes = alu.getOutput();
        if(!Arrays.equals(binRes, addCB)) {
            System.out.println("FAIL Test 7 addition result:");
            System.out.println("ALU result of binC + binB: " + Binary.toString(binRes));
            System.out.println("Correct result of binC + binB: " + Binary.toString(addCB));
        } else if(alu.getZeroFlag() || !alu.getCarryFlag() || alu.getOverflowFlag()) {
            System.out.println("FAIL Test 7 ALU flags:");
            System.out.println("ALU result: zeroFlag = " + alu.getZeroFlag()
                        + "; carryFlag = " + alu.getCarryFlag()
                        + "; overflowFlag = " + alu.getOverflowFlag());
            System.out.println("Correct result: zeroFlag = false; "
                        + "carryFlag = true; overflowFlag = false");

        } else {
            System.out.println("PASSED Test 7");
        }

        System.out.println(".... Finished addition *****\n");

        System.out.println("***** Testing subtraction operation ....");

        // Test 8: Number minus itself results in zero
        alu.setInputA(binA);
        alu.setInputB(binA);
        alu.setControl(6);
        alu.activate();
        binRes = alu.getOutput();
        if(!Arrays.equals(binRes, binZeros)) {
            System.out.println("FAIL Test 8 subtraction result:");
            System.out.println("ALU result of binA - binA: " + Binary.toString(binRes));
            System.out.println("Correct result of binA - binA: " + Binary.toString(binZeros));
        } else {
            System.out.println("PASSED Test 8");
        }

        // Test 9: no two's comp overflow, no carry out
        alu.setInputA(binA);
        alu.setInputB(binB);
        alu.setControl(6);
        alu.activate();
        binRes = alu.getOutput();
        if(!Arrays.equals(binRes, subAB)) {
            System.out.println("FAIL Test 9 subtraction result:");
            System.out.println("ALU result of binA - binB: " + Binary.toString(binRes));
            System.out.println("Correct result of binA - binB: " + Binary.toString(subAB));
        } else if(alu.getZeroFlag() || alu.getCarryFlag() || alu.getOverflowFlag()) {
            System.out.println("FAIL Test 9 ALU flags:");
            System.out.println("ALU result: zeroFlag = " + alu.getZeroFlag()
                        + "; carryFlag = " + alu.getCarryFlag()
                        + "; overflowFlag = " + alu.getOverflowFlag());
            System.out.println("Correct result: zeroFlag = false; "
                        + "carryFlag = false; overflowFlag = false");
        } else {
            System.out.println("PASSED Test 9");
        }

        // Test 10: yes two's comp overflow, yes carry out
        alu.setInputA(binC);
        alu.setInputB(binB);
        alu.setControl(6);
        alu.activate();
        binRes = alu.getOutput();
        if(!Arrays.equals(binRes, subCB)) {
            System.out.println("FAIL Test 10 subtraction result:");
            System.out.println("ALU result of binC - binB: " + Binary.toString(binRes));
            System.out.println("Correct result of binC - binB: " + Binary.toString(subCB));
        } else if(alu.getZeroFlag() || !alu.getCarryFlag() || !alu.getOverflowFlag()) {
            System.out.println("FAIL Test 10 ALU flags:");
            System.out.println("ALU result: zeroFlag = " + alu.getZeroFlag()
                        + "; carryFlag = " + alu.getCarryFlag()
                        + "; overflowFlag = " + alu.getOverflowFlag());
            System.out.println("Correct result: zeroFlag = false; "
                        + "carryFlag = true; overflowFlag = true");
        } else {
            System.out.println("PASSED Test 10");
        }

        System.out.println(".... Finished subtraction *****\n");

        System.out.println("***** Testing pass input B operation ....");

        // Test 11
        alu.setInputA(binB);
        alu.setInputB(binA);
        alu.setControl(7);
        alu.activate();
        binRes = alu.getOutput();
        if(!Arrays.equals(binRes, binA)) {
            System.out.println("FAIL Test 11 pass input B:");
            System.out.println("ALU result of pass input B: " + Binary.toString(binRes));
            System.out.println("Correct result of pass input B: " + Binary.toString(binA));
        } else if(alu.getZeroFlag()) {
            System.out.println("FAIL Test 11 zero flag should be false.");
            System.out.println("ALU result: zeroFlag = " + alu.getZeroFlag());

        } else {
            System.out.println("PASSED Test 11");
        }

        // Test 12: pass input zero, check zero flag
        alu.setInputA(binC);
        alu.setInputB(binZeros);
        alu.setControl(7);
        alu.activate();
        binRes = alu.getOutput();
        if(!Arrays.equals(binRes, binZeros)) {
            System.out.println("FAIL Test 12 pass input B:");
            System.out.println("ALU result of pass input B: " + Binary.toString(binRes));
            System.out.println("Correct result of pass input B: " + Binary.toString(binZeros));
        } else if(!alu.getZeroFlag()) {
            System.out.println("FAIL Test 12 zero flag should be true.");
            System.out.println("ALU result: zeroFlag = " + alu.getZeroFlag());
        } else {
            System.out.println("PASSED Test 12");
        }
        System.out.println(".... Finished pass input B *****\n");

    }
}
