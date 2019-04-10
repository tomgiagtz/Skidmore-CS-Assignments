/**
* A test program for the Binary class in CS318 Programming Assignment 1.
* You should also write your own tests. The assignment will be graded using a
* different test program. This program tests all of the methods that you are
* required to implement, but it does not test all possible situations. Consider
* the edge cases that might occur and make sure that your code can handle those
* cases.
*
* @author Christine Reilly
*/
import java.util.Random;

public class TestBinary {
    public static void main(String[] args) {

        // A random number class. If you want the same set of random numbers
        // every time you run the program (useful when debugging), put an
        // integer as the parameter to the Random constructor.
        Random random = new Random();

        long decNum; // a decimal number
        long decNum1; // a decimal number
        long decNum2; // a decimal number
        long decNum3; // a decimal number
        boolean[] binNum; // a binary number
        boolean[] binNum1 = new boolean[Binary.BINARY_LENGTH]; // a binary number
        boolean[] binNum2 = new boolean[Binary.BINARY_LENGTH]; // a binary number
        boolean[] binNum3 = new boolean[Binary.BINARY_LENGTH]; // a binary number
        boolean[] binNum4 = new boolean[Binary.BINARY_LENGTH]; // a binary number
        boolean[] badBin = {true,true,false,true,false,true}; // a binary number less than 32 bits
        String binString; // string representation of a binary number
        String hexString; // string representation of a hexadecimal number

        // Set test number
        decNum1 = 2290649224L;
        decNum2 = -2004318072;
        for(int i = 0; i < binNum1.length; i++) {
            if((i+1) % 4 == 0)
                binNum1[i] = true;
            else
                binNum1[i] = false;
        }


        System.out.println("***** Testing unsigned numbers ....");

        binNum = Binary.uDecToBin(74951);
        binString = Binary.toString(binNum);
        if(!binString.equals("0000_0000_0000_0001_0010_0100_1100_0111")) {
            System.out.println("FAIL Test 1:");
            System.out.println("    binary string from code: " + binString);
            System.out.println("    correct binary string: 0000_0000_0000_0001_0010_0100_1100_0111");
        } else {
            System.out.println("PASSED Test 1");
        }

        decNum = Binary.binToUDec(binNum1);
        if(!(decNum == decNum1)) {
            System.out.println("FAIL Test 2:");
            System.out.println("    decimal number from code: " + decNum);
            System.out.println("    correct decimal number: " + decNum1);
        } else {
            System.out.println("PASSED Test 2");
        }

        System.out.println(".... Finished unsigned numbers *****\n");

        System.out.println("***** Testing signed numbers ....");

        binNum = Binary.sDecToBin(-74951);
        binString = Binary.toString(binNum);
        if(!binString.equals("1111_1111_1111_1110_1101_1011_0011_1001")) {
            System.out.println("FAIL Test 3:");
            System.out.println("    binary string from code: " + binString);
            System.out.println("    correct binary string: 1111_1111_1111_1110_1101_1011_0011_1001");
        } else {
            System.out.println("PASSED Test 3");
        }

        decNum = Binary.binToSDec(binNum1);
        if(!(decNum == decNum2)) {
            System.out.println("FAIL Test 4:");
            System.out.println("    decimal number from code: " + decNum);
            System.out.println("    correct decimal number: " + decNum2);
        } else {
            System.out.println("PASSED Test 4");
        }

        System.out.println(".... Finished signed numbers *****\n");

        System.out.println("***** Testing toHexString method ....");

        hexString = Binary.toHexString(binNum1);
        if(!hexString.equals("8888_8888")) {
            System.out.println("FAIL Test 5:");
            System.out.println("    hex string from code: " + hexString);
            System.out.println("    correct hex string: 8888_8888");
        } else {
            System.out.println("PASSED Test 5");
        }

        System.out.println(".... Finished toHexString method *****\n");

        System.out.println("***** Testing exceptions ....");
        System.out.println("You should see PASS messages A through F:");

        try {
            binNum = Binary.uDecToBin(4300000000L);
        } catch(IllegalArgumentException e) {
            System.out.println("PASS A");
        }

        try {
            decNum = Binary.binToUDec(badBin);
        } catch(IllegalArgumentException e) {
            System.out.println("PASS B");
        }

        try {
            binNum = Binary.sDecToBin(2300000000L);
        } catch(IllegalArgumentException e) {
            System.out.println("PASS C");
        }

        try {
            decNum = Binary.binToSDec(badBin);
        } catch(IllegalArgumentException e) {
            System.out.println("PASS D");
        }

        try {
            binString = Binary.toString(badBin);
        } catch(IllegalArgumentException e) {
            System.out.println("PASS E");
        }

        try {
            hexString = Binary.toHexString(badBin);
        } catch(IllegalArgumentException e) {
            System.out.println("PASS F");
        }


        System.out.println(".... Finished exceptions *****\n");


    }
}
