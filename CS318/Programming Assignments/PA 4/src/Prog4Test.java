/**
* Test program for the CPU Simulation.
*
* @author Christine Reilly
*/
import java.util.Arrays;
import java.io.*;

public class Prog4Test {
    public static void main(String[] args) throws FileNotFoundException, IOException {

        // Assemble an assembly langauge code file
        // Make sure you test various assembly langauge programs in order
        // to fully test your CPU code.
        Assembler.assemble("testProg3.s", "testProg.data", "testProg.code");

        // Create a CPU object providing the instruction and data files
        CPU cpu = new CPU("testProg.code", "testProg.data");

        // Run the CPU
        cpu.run();

    } // end of main method

}
