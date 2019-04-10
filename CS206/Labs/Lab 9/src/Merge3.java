/**
 * CS206 Lab 9: 3-way merge
 * <p>
 * Student's Name: Tom Giagtzoglou
 */

public class Merge3 {
    /**
     * Lab 9: STUDENT MUST COMPLETE THIS METHOD
     * <p>
     * Merges three sorted sublist into a single sorted list. This method could be
     * used as the merge step in a 3-way merge sort. When this method returns, list
     * is in sorted order from index start to index end (inclusive).
     *
     * @param list  Array that contains the sublists
     * @param start Beginning index of sub-list A
     * @param mid1  End index of sub-list A; sub-list B begins at index mid1 + 1
     * @param mid2  End index of sub-list B; sub-list C begins at index mid2 + 1
     * @param end   End index of sub-list C
     */
    public static void merge3(int[] list, int start, int mid1, int mid2, int end) {

        int[] tempArr = new int[end - start + 1];
        int i = 0;
        int li = start; // index for left third
        int mi = mid1 + 1; //index for middle third
        int ri = mid2 + 1;

        //loop through elements
        while (i < tempArr.length) {
            //values correspond to if the left mid or right sub-lists have reached their end
            boolean leEnd = li > mid1;
            boolean miEnd = mi > mid2;
            boolean riEnd = ri > end;

            int temp;
            if (leEnd && !miEnd && !riEnd) {temp = getMinOf3(list, mi, mi, ri);} else
            if (!leEnd && miEnd && !riEnd) {temp = getMinOf3(list, li, li, ri);} else
            if (!leEnd && !miEnd && riEnd) {temp = getMinOf3(list, li, li, mi);} else
            if (leEnd && miEnd && !riEnd) {temp = getMinOf3(list, ri, ri, ri);} else
            if (!leEnd && miEnd && riEnd) {temp = getMinOf3(list, li, li, li);} else
            if (leEnd && !miEnd && riEnd) {temp = getMinOf3(list, mi, mi, mi);} else {
                temp = getMinOf3(list, li, mi, ri);
            }


            //get smallest of the three values
            //int temp = getMinOf3(list, li, mi, ri);
            tempArr[i] = list[temp];
            if (temp == li && !leEnd) {
                li++;
            } else
            if (temp == mi && !miEnd) {
                mi++;
            } else
            if (temp == ri && !riEnd) {
                ri++;
            }
            i++;
        }

        //copy elements back into list
        int j = start;
        for (i = 0; i < tempArr.length; i++) {
            list[j] = tempArr[i];
            j++;
        }


    }

    /**
     * Prints the elements of an array, one element per line.
     *
     * @param list The array to print
     */
    public static void printList(int[] list) {
        for (int i = 0; i < list.length; i++) {
            System.out.println(list[i]);
        }
    }

    /**
     * Returns the index of the minimum element given 3 elements in an array.
     *
     * @param list Array containing elements
     * @param i1   1st element in array
     * @param i2   2nd element in array
     * @param i3   3rd element in array
     * @return index of element with minimum value
     */
    private static int getMinOf3(int[] list, int i1, int i2, int i3) {
        //i1 is smallest
        if (list[i1] <= list[i2] && list[i1] <= list[i3]) {
            return i1;
        }
        //i2 is smallest
        if (list[i2] <= list[i1] && list[i2] <= list[i3]) {
            return i2;
        }
        //i3 is smallest
        return i3;

    }
}
