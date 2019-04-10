import java.util.Random;

/**
 * Class that creates arrays of random integers and sorts and searches them to
 * report on the runtime of those operations
 *
 * Example Output:
 *      The sort did 199990000 comparisons for a list of size 20000
 *      Linear search on average did 19799.47 iterations for a list of size 20000
 *      Binary search on average did    14.35 iterations for a list of size 20000
 *
 * @author Tom Giagtzoglou
 */
public class SortAndSearchStats {
    /** Counter variable for statistic of number of iterations*/
    private static int count;
    /** Constant to determine size of array of random integers*/
    private static final int ARRAY_SIZE = 20000;
    /** Constant to determine range of random integers (1, RANGE) */
    private static final int RANGE = 1000000;

    /**
     * Generates an array of length ARRAY_SIZE, then fills this array with
     * random integerss between 1 and RANGE.
     * @return An array of random integers, with length ARRAY_SIZE
     */
    private static int[] createRandomArr() {
        Random gen = new Random();
        int[] randomArr = new int[ARRAY_SIZE];

        for (int i = 0; i < ARRAY_SIZE; i++) {
            randomArr[i] = gen.nextInt(RANGE);
        }

        return randomArr;
    }

    private static int[] sortArray(int[] randomArr) {
        int[] sortedArr;
        //create a copy of the random array
        sortedArr = randomArr.clone();
        sort(sortedArr);

        return(sortedArr);

    }

    /**
     * Sorts the parameter array
     * @param list array of integers
     */
    private static void sort(int[] list) {
        count = 0;
        for ( int pass = 1; pass < list.length; pass++ ) {
            for ( int i = 0; i < (list.length - pass); i++ ) {
                count++;
                if ( list[ i ] > list[ i + 1 ] ) {
                    swap( list, i, i + 1 );
                } // end if
            } // end inner for loop (i)
        } // end outer for loop (pass)
    }

    /**
     * Swaps to elements in an array
     * @param nums array for elements to be swapped
     * @param first position of first element
     * @param second position of second element
     */
    private static void swap(int[] nums, int first, int second ) {
        int hold;
        hold = nums[first];
        nums[first] = nums[second];
        nums[second] = hold;
    }

    /**
     * Searches an array of integers using the linear search algorithm.
     *
     * @param list The array of integers to search
     * @param key The value to search for
     * @return The index where the key value first occurs in the list, or -1 if the value is not found
     */
    public static int linearSearch(int[] list, int key) {
        count = 0;
        // Use a loop to examine each element in list
        for(int i = 0; i < list.length; i++) {
            count++;
            if(list[i] == key) {
                // key was found. return the index.
                return i;
            }
        }

        // key was not found in list
        return -1;
    }

    /**
     * Searches an array of integers using the binary search algorithm.
     *
     * @param list The array of integers to search
     * @param key The value to search for
     * @return An index where the key value occurs in the list, or -1 if the value is not found
     */
    public static int binarySearch(int[] list, int key) {
        count = 0;
        int low = 0; // lowest index of the range to search
        int high = list.length - 1; // highest index of the range to search
        int mid; // index at the middle of the range to search

        // loop until low is greater than high
        while(low <= high) {
            count++;

            // Middle of the search range
            // Recall that the result of dividing two integers is an integer!
            mid = (low + high) / 2;

            if(list[mid] == key) {
                // key is found at index mid
                return mid;
            } else if(list[mid] < key) {
                // key is greater than the current middle value
                // So key must be in the upper half of the list
                // set the new lowest index to mid + 1
                // keep the highest index unchanged
                low = mid + 1;
            } else {
                // key must be smaller than the current middle value
                // So key must be in the lower half of the list
                // set the new highest index to mid - 1
                // keep the lowest index unchanged
                high = mid - 1;
            }
        }

        // key was not found in list
        return -1;
    }


    /**
     * Tests run time for linear and binary searches
     * Prints to console average iterations for given list
     * @param random an array of random integers
     * @param sorted a sorted array of random integers
     */
    private static void testSearches(int[] random, int[] sorted){
        Random gen = new Random();
        int key = 0;
        double averageLinear = 0;
        double averageBinary = 0;

        //begin iterations of searching
        for (int i = 0; i < 5000; i++) {
            key = gen.nextInt(RANGE);
            linearSearch(random, key);
            averageLinear += (double) count / 5000;

            binarySearch(sorted, key);
            averageBinary += (double) count / 5000;

        }

        //print results
        System.out.printf("Linear search on average did %8.2f iterations for a list of size %d \n", averageLinear, ARRAY_SIZE);
        System.out.printf("Binary search on average did %8.2f iterations for a list of size %d \n", averageBinary, ARRAY_SIZE);
    }






    public static void main(String[] args) {
        int[] random = createRandomArr();

        int[] sorted = sortArray(random);

        System.out.println();
        System.out.println("The sort did " + count + " comparisons for a list of size " + ARRAY_SIZE);

        testSearches(random, sorted);

    }
}
