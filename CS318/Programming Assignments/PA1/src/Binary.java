import java.util.Arrays;

/**
* Methods for converting between binary and decimal.
*
* @author Tom Giagtzoglou
*/
public class Binary {

    /** Class constant defines the length of binary numbers. */
    public static final int BINARY_LENGTH = 32;
    private static final long MAX_UNSIGNED = twoToThePower(BINARY_LENGTH)-1;
    private static final long MAX_SIGNED = twoToThePower(BINARY_LENGTH-1)-1;
    private static final long MIN_SIGNED = -1 * twoToThePower(BINARY_LENGTH-1);

    /**
    * Converts a two's complement binary number to signed decimal
    *
    * @param b The two's complement binary number
    * @return The equivalent decimal value
    * @exception IllegalArgumentException Parameter array length is not BINARY_LENGTH.
    */
    public static long binToSDec(boolean[] b) {
        if (b.length != BINARY_LENGTH) {
            throw new IllegalArgumentException("Parameter array length is not BINARY_LENGTH.") ;
        }
        long dec = 0;
        if (b[BINARY_LENGTH-1]) {
            boolean[] pos = toComp(b);
            dec = binToUDec(pos) * -1;
        } else {
            dec = binToUDec(b);
        }
        return dec;
    }

    /**
    * Converts an unsigned binary number to unsigned decimal
    *
    * @param b The unsigned binary number
    * @return The equivalent decimal value
    * @exception IllegalArgumentException Parameter array length is not BINARY_LENGTH.
    */
    public static long binToUDec(boolean[] b) {
        if (b.length != BINARY_LENGTH) {
            throw new IllegalArgumentException("Parameter array length is not BINARY_LENGTH.") ;
        }
        long dec = 0;
        for (int i = 0; i < BINARY_LENGTH; i++) {
            if (b[i]) {
                dec += twoToThePower(i);
            }
        }
        return dec;
    }

    /**
    * Converts a signed decimal number to two's complement binary
    *
    * @param d The decimal value
    * @return The equivalent two's complement binary representation
    * @exception IllegalArgumentException Parameter is outside valid range.
    */
    public static boolean[] sDecToBin(long d) {
        if (d > MAX_SIGNED || d < MIN_SIGNED) {
            throw new IllegalArgumentException("Parameter is outside valid range");
        }
        if (d > 0) {
            return uDecToBin(d);
        }
        return toComp(uDecToBin(-d));
    }

    /**
    * Converts an unsigned decimal number to binary
    *
    * @param d The decimal value
    * @return The equivalent binary representation
    * @exception IllegalArgumentException Parameter is outside valid range.
    */
    public static boolean[] uDecToBin(long d) {
        if (d > MAX_SIGNED || d < MIN_SIGNED) {
            throw new IllegalArgumentException("Parameter is outside valid range");
        }
        boolean[] b = new boolean[BINARY_LENGTH];
        long remainder = 0;
        long result = d;
        int i = 0;
        while (result >= 1 && i < BINARY_LENGTH) {
            remainder = result % 2;
            if (remainder == 1) {
                b[i] = true;
                result = (result - 1) / 2;
            } else {
                result /= 2;
            }
            i++;
        }
        return b;

    }

    /**
    * Returns a string representation of the binary number. Uses an underscore
    * to separate each group of 4 bits.
    *
    * @param b The binary number
    * @return The string representation of the binary number.
    * @exception IllegalArgumentException Parameter array length is not BINARY_LENGTH.
    */
    public static String toString(boolean[] b) {
        if (b.length != BINARY_LENGTH) {
            throw new IllegalArgumentException("Parameter array length is not BINARY_LENGTH.") ;
        }
        String out =  "";
        for (int i = BINARY_LENGTH -1; i >= 0; i--) {
            out += (b[i] ? "1": "0");
            if (i % 4 == 0 && i != 0) {out += "_";}
        }
        return out;
    }

    /**
    * Returns a hexadecimal representation of the unsigned binary number. Uses
    * an underscore to separate each group of 4 characters.
    *
    * @param b The binary number
    * @return The hexadecimal representation of the binary number.
    * @exception IllegalArgumentException Parameter array length is not BINARY_LENGTH.
    */
    public static String toHexString(boolean[] b) {
        if (b.length != BINARY_LENGTH) {
            throw new IllegalArgumentException("Parameter array length is not BINARY_LENGTH.") ;
        }
        String res = "";

        for (int i = BINARY_LENGTH -1; i > 0; i -= 4) {
            boolean[] currNib =  {b[i-3], b[i-2], b[i-1], b[i]};
            res += nibToHex(currNib);

            if (i%16 == 3 && (i - 16 > 0)) {res += "_";}

        }
        return res;
    }

    /**
     * Returns the result of 2 to the power of a non negative integer
     * @param pow The power 2 will be raised to.
     * @return The result of 2^i
     * @exception IllegalArgumentException Power is negative.
     */
    private static Long twoToThePower(int pow){
        if (pow < 0) {
            throw new IllegalArgumentException("Power argument is negative");
        }

        long res = 1;
        if (pow == 0) {return res;}
        for (int i = 0; i < pow ; i++) {
            res *=2;
        }
        return res;
    }

    /**
     * Converts a negative binary number represented in two's compliment to it's  complement
     * @param b the binary number
     * @return The complement
     * @exception IllegalArgumentException Parameter array length is not BINARY_LENGTH.
     */
    private static boolean[] toComp(boolean[] b) {
        if (b.length != BINARY_LENGTH) {
            throw new IllegalArgumentException("Parameter array length is not BINARY_LENGTH.") ;
        }
        boolean[] bNew = new boolean[BINARY_LENGTH];
        boolean flip = false;
        for (int i = 0; i < BINARY_LENGTH; i++) {
            if (!flip) {
                bNew[i] = b[i];
                if (b[i]) {
                    flip = true;
                }
            } else {
                bNew[i] = !b[i];
            }

        }
        return bNew;
    }

    /**
     * Converts a nibble (4-bits) to a hex character
     * @param nib the 4 bit word aka nibble
     * @return The hex character representation (0-9, A-F)
     * @exception IllegalArgumentException Parameter array length is not 4 bits
     */
    private static char nibToHex(boolean[] nib) {
        if (nib.length != 4) {
            throw new IllegalArgumentException("Parameter array length is not 4 bits");
        }
        boolean[] val = {false, false, false, false};
        if (Arrays.equals(nib, val)) {return '0';}
        val = new boolean[]{true, false, false, false};
        if (Arrays.equals(nib, val)) {return '1';}

        val = new boolean[]{false, true, false, false};
        if (Arrays.equals(nib, val)) {return '2';}
        val = new boolean[]{true, true, false, false};
        if (Arrays.equals(nib, val)) {return '3';}

        val = new boolean[]{false, false, true, false};
        if (Arrays.equals(nib, val)) {return '4';}
        val = new boolean[]{true, false, true, false};
        if (Arrays.equals(nib, val)) {return '5';}
        val = new boolean[]{false, true, true, false};
        if (Arrays.equals(nib, val)) {return '6';}
        val = new boolean[]{true, true, true, false};
        if (Arrays.equals(nib, val)) {return '7';}

        val = new boolean[]{false, false, false, true};
        if (Arrays.equals(nib, val)) {return '8';}
        val = new boolean[]{true, false, false, true};
        if (Arrays.equals(nib, val)) {return '9';}
        val = new boolean[]{false, true, false, true};
        if (Arrays.equals(nib, val)) {return 'A';}
        val = new boolean[]{true, true, false, true};
        if (Arrays.equals(nib, val)) {return 'B';}
        val = new boolean[]{false, false, true, true};
        if (Arrays.equals(nib, val)) {return 'C';}
        val = new boolean[]{true, false, true, true};
        if (Arrays.equals(nib, val)) {return 'D';}
        val = new boolean[]{false, true, true, true};
        if (Arrays.equals(nib, val)) {return 'E';}
        return 'F';
    }
}
