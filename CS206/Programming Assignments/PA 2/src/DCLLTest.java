/**
 * A class to test a DCLL and a DLLNode
 * <p>
 * For Programming assignment 2
 * <p>
 * Makes use of test code from Lab6Test.java
 *
 * @author Christine Reilly and Tom Giagtzoglou
 */
public class DCLLTest {
    public static void main(String[] args) {
        //Create some objects
        String s1 = "String 1";
        String s2 = "String 2";
        String s3 = "String 3";
        String s4 = "String 4";
        double d1 = 1.0001231;
        double d2 = 2.0001232;
        double d3 = 3.0001233;
        double d4 = 4.0001234;

        //BEGIN TEST OF DLLNode

        System.out.println("Testing DLL Node");

        //Some nodes
        DLLNode n1 = new DLLNode(s1);
        DLLNode n2 = new DLLNode(d1);
        DLLNode n3 = new DLLNode(s2);

        //getNext and getPrevious should return null

        if (n1.getNext() != null) {
            fail("Get next should be null");
        }
        if (n1.getPrevious() != null) {
            fail("Get previous should be null");
        }

        //Test getData method
        if (n1.getData() != s1) {
            fail("n1.getData should return reference to s1");
        }

        //test setNext and setPrevious methods

        n1.setNext(n2);
        n3.setPrevious(n2);
        if (n1.getNext() != n2) {
            fail("n1.getNext should return reference to n2");
        }
        if (n3.getPrevious() != n2) {
            fail("n3.getPrevious should return reference to n2");
        }

        //test setData method

        n1.setData(s2);

        if (n1.getData() != s2) {
            fail("after setting data to s2, n1.getData should return s2");
        }

        System.out.println("End of DLLNode test");
        System.out.println();
        //END TEST OF DLLNode

        //BEGIN TEST OF DCLL

        System.out.println("Testing DCLL");

        DCLL list = new DCLL();

        list.add(s2);
        list.add(d2);
        list.add(d3);

        //test get method


        //start testing string methods
        String forward = "String 2, 2.0001232, 3.0001233";
        String backward = "3.0001233, 2.0001232, String 2";

        //test if strings are correct forward and reverse
        if (!list.forwardString().equals(forward)) {
            fail("Forward string is not correct");
        }
        if (!list.reverseString().equals(backward)) {
            fail("Reverse string is not correct");
        }

        //end string tests

        //start testing add method, size method

        list.add(1, s3);


        if (list.size() != 4) {
            fail("list size should be 4");
        }
        if (list.get(0) != s2) {
            fail("index 0 should be a reference to s2");
        }
        if (list.get(1) != (s3)) {
            fail("index 1 should be a reference to s3");
        }
        if ((double) list.get(2) != (d2)) {
            fail("index 2 should be a reference to d2");
        }
        if ((double) list.get(3) != (d3)) {
            fail("index 3 should be a reference to d3");
        }

        //clear list
        list.clear();
        if (list.get(0) != null) {
            fail("list should be empty");
        }
        // test isEmpty

        if (!list.isEmpty()) {
            fail("list should be empty");
        }

        //add at beginning of list with no elements

        list.add(0, d1);
        if ((double) list.get(0) != d1) {
            fail("index 0 should be a reference to d1");
        }


        list.add(s2);
        list.add(s4);

        // add one from end of list

        list.add(list.size() - 1, s3);

        if ((double) list.get(0) != d1) {
            fail("index 0 should be a reference to d1");
        }
        if (list.get(1) != s2) {
            fail("index 1 should be a reference to s2");
        }
        if (list.get(2) != s3) {
            fail("index 2 should be a reference to s3");
        }
        if (list.get(3) != s4) {
            fail("index 3 should be a reference to s4");
        }

        //add at end of list

        list.add(list.size(), d4);

        if ((double) list.get(0) != d1) {
            fail("index 0 should be a reference to d1");
        }
        if (list.get(1) != s2) {
            fail("index 1 should be a reference to s2");
        }
        if (list.get(2) != s3) {
            fail("index 2 should be a reference to s3");
        }
        if (list.get(3) != s4) {
            fail("index 3 should be a reference to s4");
        }
        if ((double) list.get(4) != d4) {
            fail("index 4 should be a reference to d4");
        }

        //test contains method

        if (!list.contains(s2)) {
            fail("List should contain a reference to s2");
        }
        if (list.contains(d2)) {
            fail("List should not contain a reference to d2");
        }
        if (!list.contains(d1)) {
            fail("List should contain a reference to d1");
        }
        if (!list.contains(d4)) {
            fail("List should contain a reference to d4");
        }

        //test indexOf method
        if (list.indexOf(d1) != 0) {
            fail("search for d1 should return index 0");
        }
        if (list.indexOf(s2) != 1) {
            fail("search for s2 should return index 1");
        }
        if (list.indexOf(s3) != 2) {
            fail("search for s3 should return index 2");
        }
        if (list.indexOf(s4) != 3) {
            fail("search for s4 should return index 3");
        }
        if (list.indexOf(d4) != 4) {
            fail("search for d4 should return index 4");
        }
        if (list.indexOf(d2) != -1) {
            fail("search for d2 should return -1");
        }
        //test for first occurrence
        list.add(1, s3);
        if (list.indexOf(s3) != 1) {
            fail("search for s3 should return index 1");
        }
        if (list.size() != 6) {
            fail("list size should be 6");
        }

        //test lastIndexOf method

        if (list.lastIndexOf(d1) != 0) {
            fail("search for last index of d1 should return index 0");
        }
        if (list.lastIndexOf(s2) != 2) {
            fail("search for last index of s3 should return index 2");
        }
        if (list.lastIndexOf(s3) != 3) {
            fail("search for last index of s3 should return index 3");
        }
        if (list.lastIndexOf(s4) != 4) {
            fail("search for last index of s4 should return index 4");
        }
        if (list.lastIndexOf(d4) != 5) {
            fail("search for last index of d4 should return index 4");
        }
        if (list.lastIndexOf(d2) != -1) {
            fail("search for last index of d2 should return -1");
        }
        //test for last occurrence
        list.add(3, s3);
        if (list.lastIndexOf(s3) != 4) {
            fail("search for last index of s3 should return index 4");
        }

        //test remove(index) method

        if (list.remove(2) != s2){
            fail("Removing index 2 should return string 2");
        }
        if (list.get(2) != s3) {
            fail("index 2 should return s3 after removing s2");
        }
        if ((double)list.remove(list.size()-1) != d4){
            fail("Removing last index should return d4");
        }
        if (list.get(list.size() -1) != s4) {
            fail("last index should return s4 after removing d4");
        }
        if ((double )list.remove(0) != d1) {
            fail("remove index 0 should return d1");
        }
        if (list.get(0) != s3){
            fail("index 0 should return s3 after removing index 0");
        }


        list.add(1, s1);

        //test remove(object) method
        list.remove(s3);
        if (list.get(0) != s1){
            fail("index 0 should return s1");
        }
        list.add(0, d1);
        list.add(3, d2);

        list.remove(d1);
        if(list.get(0) != s1){
            fail("index 0 should return s1 after removing d1");
        }
        if (list.remove(d3)){
            fail("removing d3 should return false");
        }
        list.remove(s4);
        if (list.get(list.size()-1) != s3){
            fail("last element should be s3 after removing s4");
        }

        System.out.println("End of DCLL test");





    }

    /**
     * Prints a fail message with a string as a reason
     *
     * @param s reason for failure
     */
    public static void fail(String s) {
        System.out.println("FAIL : " + s);
    }
}
