/**
 * A class for reviewing Java, includes a method for moving odd ints before evens in an array
 * @author tom
 */
import java.util.Arrays;

public class Lab2 {
    /**
     * Creates example integer arrays and tests the moveOddsBeforeEvens methods, outputting the results.
     *
     */
    public static void main(String[] args) {

        int[] origArray = {5, 6, 3, 1, 10, 7, 8, 11, 201, 198, 43};
        int[] altArray1 = {3, 1, 5, 1, 57, 43, 43, 11, 91, 33};
		int[] altArray2 = {4, 32, 16, 98,-106, 0, 12, 42};
        int[] altArray3 = {-7, 0, 1, -100,-101, -32, -31, 3, 5, 8};
        int[] altArray4 = {};

        System.out.println("Unsorted : \t" + Arrays.toString(origArray));
        moveOddsBeforeEvens(origArray);
        System.out.println("Sorted : \t" + Arrays.toString(origArray));

        System.out.println("Unsorted : \t" + Arrays.toString(altArray1));
        moveOddsBeforeEvens(altArray1);
        System.out.println("Sorted : \t" + Arrays.toString(altArray1));

        System.out.println("Unsorted : \t" + Arrays.toString(altArray2));
        moveOddsBeforeEvens(altArray2);
        System.out.println("Sorted : \t" + Arrays.toString(altArray2));

        System.out.println("Unsorted : \t" + Arrays.toString(altArray3));
        moveOddsBeforeEvens(altArray3);
        System.out.println("Sorted : \t" + Arrays.toString(altArray3));

        System.out.println("Unsorted : \t" + Arrays.toString(altArray4));
        moveOddsBeforeEvens(altArray4);
        System.out.println("Sorted : \t" + Arrays.toString(altArray4));
    }

    /**
     * moves all odd numbers before evens in an array
     * @param arr an array that will be sorted with odds first followed by evens
     */
    private static void moveOddsBeforeEvens(int[] arr) {

        int j = arr.length - 1;                                 // create a counter for moving backwards through the array

        for (int i = 0; i < arr.length; i++) {                  // a forward loop through the array
            if (arr[i] % 2 == 0) {                              // if arr[i] is even
                while (j > 0  && i < j) {                       // loop backwards through the array, but not past the forward loop's increment
                    if (arr[j] % 2 != 0) {                      // find an odd number
                        swap(arr, i, j);                        // swap the even and odd number
                        break;                                  // stop looping
                    }
                    j--;                                        // go to the previous element
                }
            }
        }
    }

    /**
     * swaps elements at any two indices
     * @param arr array for elements to be swapped in
     * @param index1 first index
     * @param index2 second index
     */
    public static void swap(int[] arr, int index1, int index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}
