/**
* CS206 Lab 9: Test Program
*
* @author Christine Reilly
*/
import java.util.Arrays;

public class Lab9 {
    public static void main(String[] args){
        int i, j;
        int[] listA = {35, 52, 70, 83, 87, 6, 24, 36, 49, 86, 33, 34, 41, 78, 88};
        int[] listA_s = {6, 24, 33, 34, 35, 36, 41, 49, 52, 70, 78, 83, 86, 87, 88};
        int[] listB = {5, 7, 8, 9, 10, 20, 25, 38, 74, 78, 93, 96, 23, 27, 36, 58, 68, 81};
        int[] listB_s = {5, 7, 8, 9, 10, 20, 23, 25, 27, 36, 38, 58, 68, 74, 78, 81, 93, 96};
        int[] listC = {25, 38, 74, 78, 93, 96, 5, 7, 8, 9, 10, 20, 23, 27, 36, 58, 68, 81};
        int[] listD = {25, 38, 74, 78, 93, 96, 23, 27, 36, 58, 68, 81, 5, 7, 8, 9, 10, 20};

        Merge3.merge3(listA, 0, 4, 9, listA.length-1);
        if(!Arrays.equals(listA, listA_s)) {
            System.out.println("FAIL Test A");
        } else {
            System.out.println("Pass Test A");
        }

        Merge3.merge3(listB, 0, 5, 11, listB.length-1);
        if(!Arrays.equals(listB, listB_s)) {
            System.out.println("FAIL Test B");
            Merge3.printList(listB);
        } else {
            System.out.println("Pass Test B");
        }

        Merge3.merge3(listC, 0, 5, 11, listC.length-1);
        // listC has same values as listB, so is the listB_s sorted array
        if(!Arrays.equals(listC, listB_s)) {
            System.out.println("FAIL Test C");
            Merge3.printList(listC);
        } else {
            System.out.println("Pass Test C");
        }

        Merge3.merge3(listD, 0, 5, 11, listD.length-1);
        // listD has same values as listB, so is the listB_s sorted array
        if(!Arrays.equals(listD, listB_s)) {
            System.out.println("FAIL Test D");
            Merge3.printList(listC);
        } else {
            System.out.println("Pass Test D");
        }
    }


}
