/**
 * Assembler for the CS318 simple computer simulation
 *
 * @author Tom Giagtzoglou
 */

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;


public class Assembler {

    /**
     * Definitions of opcodes. LSB is at array index 0, so the opcodes are
     * written right to left (reverse of reading in English)
     */
    private static final boolean[] OP_ADD = {false, false, false, true, true, false, true, false, false, false, true};
    private static final boolean[] OP_SUB = {false, false, false, true, true, false, true, false, false, true, true};
    private static final boolean[] OP_AND = {false, false, false, false, true, false, true, false, false, false, true};
    private static final boolean[] OP_ORR = {false, false, false, false, true, false, true, false, true, false, true};
    private static final boolean[] OP_LDR = {false, true, false, false, false, false, true, true, true, true, true};
    private static final boolean[] OP_STR = {false, false, false, false, false, false, true, true, true, true, true};
    private static final boolean[] OP_CBZ = {false, false, true, false, true, true, false, true};
    private static final boolean[] OP_B = {true, false, true, false, false, false};
    private static final boolean[] OP_HLT = {false, true, false, false, false, true, false, true, false, true, true};
    private static final boolean[] SHIFT = {false, false, false, false, false, false};
    private static final int COMMAND_LENGTH = 32;


    /**
     * Assembles the code file. When this method is finished, the dataFile and
     * codeFile contain the assembled data segment and code segment, respectively.
     *
     * @param inFile   The pathname to the assembly language file to be assembled.
     * @param dataFile The pathname where the data segment file should be written.
     * @param codeFile The pathname where the code segment file should be written.
     */
    public static void assemble(String inFile, String dataFile, String codeFile)
            throws FileNotFoundException, IOException {

        // do not make any changes to this method

        ArrayList<LabelOffset> labels = pass1(inFile, dataFile, codeFile);
        pass2(inFile, dataFile, codeFile, labels);
    }

    /**
     * First pass of the assembler. Writes the number of bytes in the data segment
     * and code segment to their respective output files. Returns a list of
     * code segment labels and their relative offsets.
     *
     * @param inFile   The pathname of the file containing assembly language code.
     * @param dataFile The pathname for the data segment binary file.
     * @param codeFile The pathname for the code segment binary file.
     * @return List of the code segment labels and relative offsets.
     * @throws RuntimeException if the assembly code file does not have the
     *                          correct format, or another error while processing the assembly code file.
     */
    private static ArrayList<LabelOffset> pass1(String inFile, String dataFile, String codeFile)
            throws FileNotFoundException, RuntimeException {
        String path = "";
        File inf = new File(path + inFile);
        File daf = new File(path + dataFile);
        File cof = new File(path + codeFile);

        if (!inf.canRead()) {
            throw new FileNotFoundException("Invalid input file name: " + inFile);
        }

        ArrayList<LabelOffset> labels = new ArrayList<LabelOffset>();

        int dataCount = 0;
        int offset = 0;

        Scanner input = new Scanner(inf);
        String line;

        //to keep track of how we should parse the line
        boolean isData = false;
        boolean isCode = false;

        String currLab = "";


        while (input.hasNextLine()) {

            line = input.nextLine().trim();


            if (line.equals(".data")) {
                isData = true;
                isCode = false;
                System.out.println("Reading data");
            }
            if (line.equals(".global main")) {
                isData = false;
                isCode = true;

                System.out.println("Reading code");

            }
            Scanner curr = new Scanner(line);

            if (isData) {
                if (line.startsWith(".word")) {
                    //remove .word from line then split the integers with ',' as delimiter
                    line = line.replace(".word", "");
                    String[] nums = line.split(",");
                    dataCount += nums.length;
                }

            }


            if (isCode) {
                // labels start <label>:
                if (line.endsWith(":")) {
                    currLab = line.replace(":", "");
                    LabelOffset currCom = new LabelOffset();
                    currCom.label = currLab;
                    currCom.offset = offset;
                    labels.add(currCom);
                } else if (!line.startsWith(".")) {
                    offset += 4;
                }

            }

        }


        PrintWriter dataOut = new PrintWriter(daf);
        PrintWriter codeOut = new PrintWriter(cof);
        dataOut.println(dataCount * 4);
        codeOut.println(offset + 4);

        dataOut.close();
        codeOut.close();


        return labels;
    } // end of pass1

    /**
     * Second pass of the assembler. Writes the binary data and code files.
     *
     * @param inFile   The pathname of the file containing assembly language code.
     * @param dataFile The pathname for the data segment binary file.
     * @param codeFile The pathname for the code segment binary file.
     * @param labels   List of the code segment labels and relative offsets.
     * @throws RuntimeException if there is an error when processing the assembly
     *                          code file.
     */
    public static void pass2(String inFile, String dataFile, String codeFile,
                             ArrayList<LabelOffset> labels) throws FileNotFoundException, IOException {

        String path = "";
        File inf = new File(path + inFile);
        File daf = new File(path + dataFile);
        File cof = new File(path + codeFile);

        if (!inf.canRead()) {
            throw new FileNotFoundException("Invalid input file name: " + inFile);
        }

        String dataOutput = "";
        String codeOutput = "";
        int offset = 0;

        Scanner input = new Scanner(inf);
        String line;

        //to keep track of how we should parse the line
        boolean isData = false;
        boolean isCode = false;

        while (input.hasNextLine()) {
            line = input.nextLine().trim();


            if (line.trim().equals(".data")) {
                isData = true;
                isCode = false;
                System.out.println("Writing data");
            }
            if (line.trim().equals(".global main")) {
                isData = false;
                isCode = true;
                System.out.println("Writing code");

            }

            if (isData) {
                if (line.startsWith(".word")) {
                    //remove .word from line then split the integers with ',' as delimiter
                    line = line.replace(".word ", "");
                    String[] nums = line.split(",");
                    for (int i = 0; i < nums.length; i++) {
                        long word = Long.parseLong(nums[i]);
                        dataOutput += (intToString(word));
                    }
                }
            }

            if (isCode) {
                if (!line.endsWith(":") && !line.startsWith(".")) {
                    // if line contains a command
                    codeOutput += commandToString(line, labels, offset);
                    offset += 4;
                }
            }

        }
        FileWriter dataOut = new FileWriter(daf, true);
        FileWriter codeOut = new FileWriter(cof, true);

        dataOut.write(dataOutput);
        codeOut.write(codeOutput);
        codeOut.write(haltCommToString());

        dataOut.close();
        codeOut.close();

    } // end of pass2


    /**
     * Takes a line of code and converts it to the binary machine lang
     * @param command line of code
     * @param labels Label Offset ArrayList
     * @param offset Current offset for this line
     * @return string of binary to output
     */
    private static String commandToString(String command, ArrayList<LabelOffset> labels, int offset) {

        String[] comm = cleanComm(command);
        boolean[] commBin;
        String output = "";

        if (comm.length == 4) {
            if (comm[0].equals("ADD") || comm[0].equals("SUB") || comm[0].equals("AND") || comm[0].equals("ORR")) { //if a standard command (ADD, SUB, AND, ORR)
                commBin = stdCommandToBin(comm[0], Long.parseLong(comm[1]), Long.parseLong(comm[2]), Long.parseLong(comm[3]));
            } else { //else a data access command
                commBin = dataCommToBin(comm[0], Long.parseLong(comm[1]), Long.parseLong(comm[2]), Long.parseLong(comm[3]));
            }
        } else {//if is a branch command
            if (comm[0].equals("B")) {
                commBin = bCommToBin(comm[0], comm[1], labels, offset);
            } else {
                commBin = cbzCommToBin(comm[0], Long.parseLong(comm[1]), comm[2], labels, offset);
            }
        }

        output = binToString(commBin);

        return output;
    }

    /**
     * Takes a command as individual arguments to convert a standard command to binary
     *
     * Standard includes the basic logic arithmetic
     * @param instr Command name (ADD, SUB, AND, ORR)
     * @param dest Destination register
     * @param source1 source register 1
     * @param source2 source register 2
     * @return machine language
     */
    private static boolean[] stdCommandToBin(String instr, long dest, long source1, long source2) {

        final int DEST_BR = 5;
        final int S1_BR = 10;
        final int SHFT_BR = 16;
        final int S2_BR = 21;


        boolean[] machLang = new boolean[COMMAND_LENGTH];
        boolean[] opCode;
        if (instr.equals("ADD")) {
            opCode = OP_ADD;
        } else if (instr.equals("SUB")) {
            opCode = OP_SUB;
        } else if (instr.equals("AND")) {
            opCode = OP_AND;
        } else {
            opCode = OP_ORR;
        }
        boolean[] src2Bin = Binary.uDecToBin(source2);
        boolean[] src1Bin = Binary.uDecToBin(source1);
        boolean[] destBin = Binary.uDecToBin(dest);

        for (int i = 0; i < COMMAND_LENGTH; i++) {
            if (i < DEST_BR) {
                machLang[i] = destBin[i];
            } else if (i < S1_BR) {
                machLang[i] = src1Bin[i - DEST_BR];
            } else if (i < SHFT_BR) {
                machLang[i] = SHIFT[i - S1_BR];
            } else if (i < S2_BR) {
                machLang[i] = src2Bin[i - SHFT_BR];
            } else {
                machLang[i] = opCode[i - S2_BR];
            }
        }

        return machLang;

    }


    /**
     * Takes a command as individual arguments to convert a data access command to binary
     *
     * Standard includes the basic logic arithmetic
     * @param instr Command name (LDR, STR)
     * @param val value register
     * @param base base register
     * @param imt immediate
     * @return machine language for data access
     */
    private static boolean[] dataCommToBin(String instr, long val, long base, long imt) {

        final int VAL_BR = 5;
        final int BASE_BR = 10;
        final int SHFT_BR = 12;
        final int IMT_BR = 21;

        boolean[] machLang = new boolean[COMMAND_LENGTH];
        boolean[] opCode;

        boolean[] valBin = Binary.uDecToBin(val);
        boolean[] baseBin = Binary.uDecToBin(base);
        boolean[] imtBin = Binary.uDecToBin(imt);

        if (instr.equals("LDR")) {
            opCode = OP_LDR;
        } else { //else STR
            opCode = OP_STR;
        }

        for (int i = 0; i < COMMAND_LENGTH; i++) {
            if (i < VAL_BR) {
                machLang[i] = valBin[i];
            } else if (i < BASE_BR) {
                machLang[i] = baseBin[i - VAL_BR];
            } else if (i < SHFT_BR) {
                machLang[i] = false;
            } else if (i < IMT_BR) {
                machLang[i] = imtBin[i - SHFT_BR];
            } else {
                machLang[i] = opCode[i - IMT_BR];
            }
        }

        return machLang;
    }

    /**
     * Converts a branch command to binary machine language
     * @param inst Command name (B)
     * @param label label of branch
     * @param labels LabelOffset ArrayList
     * @param offset current offset of this linne
     * @return machine language for branch
     */
    private static boolean[] bCommToBin(String inst, String label, ArrayList<LabelOffset> labels, int offset) {

        final int IMT_BR = 26;

        boolean[] machLang = new boolean[COMMAND_LENGTH];
        boolean[] opCode = OP_B;

        long imt = getImmediate(label, labels) - offset;

        boolean[] imtBin = Binary.sDecToBin(imt);

        for (int i = 0; i < COMMAND_LENGTH; i++) {
            if (i < IMT_BR) {
                machLang[i] = imtBin[i];
            } else {
                machLang[i] = opCode[i - IMT_BR];
            }
        }


        return machLang;
    }

    /**
     * Converts a branch command to binary machine language
     * @param inst Command name (CBZ)
     * @param reg Reg to check for 0
     * @param label label of branch
     * @param labels LabelOffset ArrayList
     * @param offset current offset of this linne
     * @return machine language for branch
     */
    private static boolean[] cbzCommToBin(String inst, long reg, String label, ArrayList<LabelOffset> labels, int offset) {

        final int REG_BR = 5;
        final int IMT_BR = 24;

        boolean[] machLang = new boolean[COMMAND_LENGTH];
        boolean[] opCode = OP_CBZ;

        long imt = getImmediate(label, labels) - offset;
        boolean[] regBin = Binary.uDecToBin(reg);
        boolean[] imtBin = Binary.sDecToBin(imt);

        for (int i = 0; i < COMMAND_LENGTH; i++) {
            if (i < REG_BR) {
                machLang[i] = regBin[i];
            } else if (i < IMT_BR) {
                machLang[i] = imtBin[i - REG_BR];
            } else {
                machLang[i] = opCode[i - IMT_BR];
            }
        }

        return machLang;
    }

    /**
     * Converts Halt Command to String
     * @return string for halt command machine language
     */
    private static String haltCommToString() {
        boolean[] hltComm = new boolean[COMMAND_LENGTH];
        final int EMPTY_BR = 21;
        for (int i = 0; i < COMMAND_LENGTH; i++) {
            if (i < EMPTY_BR) {
                hltComm[i] = false;
            } else {
                hltComm[i] = OP_HLT[i - EMPTY_BR];
            }

        }
        return binToString(hltComm);
    }

    /**
     * Gets offset for a given label
     * @param label label to get offset for
     * @param labels list of labels and offsets
     * @return offset for a given label
     */
    private static long getImmediate(String label, ArrayList<LabelOffset> labels) {
        long imt = 0;
        for (LabelOffset curr : labels) {
            if (curr.label.equals(label)) {
                imt = curr.offset;
            }
        }
        return imt;
    }

    /**
     * Converts a boolean array to correctly formatted string
     * @param boolArr
     * @return correctly formatted string
     */
    private static String binToString(boolean[] boolArr) {
        String output = "";
        for (int i = 0; i < COMMAND_LENGTH; i++) {
            if (i != 0) {
                if (i % 8 == 0) {
                    output += "\n";
                } else {
                    output += " ";
                }
            } else {
                output += "";
            }
            output += boolArr[i];
        }
        return output + "\n";
    }

    /**
     * Converts an int to the binary array equivalent
     * @param num int to convert
     * @return binary array equivalent
     */
    private static String intToString(long num) {

        boolean[] data = Binary.sDecToBin(num);

        String output = binToString(data);

        return output;
    }

    /**
     * Cleans a command of extra characters
     * @param command
     * @return clean command
     */
    private static String[] cleanComm(String command) {
        String[] comm = command.split(" |,");
        boolean[] commBin;


        for (int i = 1; i < comm.length; i++) {
            comm[i] = comm[i].replace("[", "");
            comm[i] = comm[i].replace("]", "");
            comm[i] = comm[i].replace("R", "");
            comm[i] = comm[i].replace("#", "");
        }
        return comm;
    }
}
