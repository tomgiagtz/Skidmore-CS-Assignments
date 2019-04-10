/**
* DO NOT MODIFY THIS FILE!
*
* Test program for CS206 Lab 6
*
* @author Christine Reilly
*/
public class Lab6 {
    public static void main(String[] args) {
        // Create some Book objects
        Book b1 = new Book("Book One","A. Author", 1);
        Book b2 = new Book("Book Two", "B. Author", 9);
        Book b3 = new Book("Book Three", "C. Author", 2);
        Book b4 = new Book("Book Four", "D. Author", 8);
        Book b5 = new Book("Book Five", "E. Author", 3);
        Book b6 = new Book("Book Six", "F. Author", 7);
        Book b7 = new Book("Book Seven", "G. Author", 4);
        Book b8 = new Book("Book Eight", "H. Author", 6);
        Book b9 = new Book("Book Nine", "I. Author", 5);

        //////////////////////
        // TEST 1: Test the DLLNode class
        // Only requires the DLLNode class to be complete.
        //////////////////////
        System.out.println("Starting Test 1 (DLLNode test).... ");

        DLLNode n1 = new DLLNode(b1);
        DLLNode n2 = new DLLNode(b2);
        DLLNode n3 = new DLLNode(b3);

        // The getNext and getPrevious methods should return null
        if(n1.getNext() != null) {
            fail("n1.getNext is not null");
        }
        if(n1.getPrevious() != null) {
            fail("n1.getNext is not null");
        }

        // The getData method should return a reference to the same object
        // as b1 refers to
        if(n1.getData() != b1) {
            fail("n1.getData should return reference to b1");
        }

        // Test the setNext and setPrevious methods
        n1.setPrevious(n2);
        n1.setNext(n3);
        if(n1.getPrevious() != n2) {
            fail("n1.getPrevious should return reference to n2");
        }
        if(n1.getNext() != n3) {
            fail("n1.getNext should return reference to n3");
        }

        // Test the setData method
        n1.setData(b4);
        if(n1.getData() != b4) {
            fail("After calling setData, n1.getData should return reference to b4");
        }
        System.out.println(".... Finished Test 1");
        System.out.println();

        //////////////////////
        // Prepare for testing the BookDLL methods that the student writes
        // for Lab 6
        //////////////////////

        // Construct a doubly linked list of Book objects
        BookDLL bookDLL = new BookDLL();

        // Add three books to the list
        bookDLL.add(b9);
        bookDLL.add(b8);
        bookDLL.add(b7);


        //////////////////////
        // TEST 2: Test the methods that return the title lists
        // Requires:
        //    * Everything required for Test 1
        //    * A BookDLL list that contains books b9, b8, and b7
        //    * BookDLL titlesForward method
        //    * BookDLL titlesReverse method
        //////////////////////
        System.out.println("Starting Test 2 (title list methods).... ");
        String forward = "Book Nine, Book Eight, Book Seven";
        String reverse = "Book Seven, Book Eight, Book Nine";
        if(!bookDLL.titlesForward().equals(forward)) {
            fail("Forward titles are not correct");
        }
        if(!bookDLL.titlesReverse().equals(reverse)) {
            fail("Reverse titles are not correct");
        }
        System.out.println(".... Finished Test 2");
        System.out.println();


        //////////////////////
        // TEST 3: Test the add at index i method
        // Requires:
        //    * Everything required for Test 2
        //    * BookDLL add at index method
        //////////////////////
        System.out.println("Starting Test 3 (add at index i method).... ");
        // Add in the middle (at index 1)
        bookDLL.add(1, b6);
        if(bookDLL.size() != 4) {
            fail("bookDLL size should be 4");
        }
        if(bookDLL.get(0) != b9) {
            fail("Book object at index 0 should be reference to b9");
        }
        if(bookDLL.get(1) != b6) {
            fail("Book object at index 1 should be reference to b6");
        }
        if(bookDLL.get(2) != b8) {
            fail("Book object at index 2 should be reference to b8");
        }
        if(bookDLL.get(3) != b7) {
            fail("Book object at index 3 should be reference to b7");
        }

        // Add at the last index (not at end of the list)
        bookDLL.add(bookDLL.size()-1, b5);
        if(bookDLL.size() != 5) {
            fail("bookDLL size should be 5");
        }
        if(bookDLL.get(0) != b9) {
            fail("Book object at index 0 should be reference to b9 (1)");
        }
        if(bookDLL.get(1) != b6) {
            fail("Book object at index 1 should be reference to b6 (1)");
        }
        if(bookDLL.get(2) != b8) {
            fail("Book object at index 2 should be reference to b8 (1)");
        }
        if(bookDLL.get(3) != b5) {
            fail("Book object at index 3 should be reference to b5");
        }
        if(bookDLL.get(4) != b7) {
            fail("Book object at index 4 should be reference to b7 (1)");
        }

        // Add at the end of the list
        bookDLL.add(bookDLL.size(), b4);
        if(bookDLL.size() != 6) {
            fail("bookDLL size should be 6");
        }
        if(bookDLL.get(0) != b9) {
            fail("Book object at index 0 should be reference to b9 (2)");
        }
        if(bookDLL.get(1) != b6) {
            fail("Book object at index 1 should be reference to b6 (2)");
        }
        if(bookDLL.get(2) != b8) {
            fail("Book object at index 2 should be reference to b8 (2)");
        }
        if(bookDLL.get(3) != b5) {
            fail("Book object at index 3 should be reference to b5 (1)");
        }
        if(bookDLL.get(4) != b7) {
            fail("Book object at index 4 should be reference to b7 (2)");
        }
        if(bookDLL.get(5) != b4) {
            fail("Book object at index 5 should be reference to b4");
        }

        // Add at the beginning of the list
        bookDLL.add(0, b3);
        if(bookDLL.size() != 7) {
            fail("bookDLL size should be 7");
        }
        if(bookDLL.get(0) != b3) {
            fail("Book object at index 0 should be reference to b3");
        }
        if(bookDLL.get(1) != b9) {
            fail("Book object at index 1 should be reference to b9 (3)");
        }
        if(bookDLL.get(2) != b6) {
            fail("Book object at index 2 should be reference to b6 (3)");
        }
        if(bookDLL.get(3) != b8) {
            fail("Book object at index 3 should be reference to b8 (3)");
        }
        if(bookDLL.get(4) != b5) {
            fail("Book object at index 4 should be reference to b5 (2)");
        }
        if(bookDLL.get(5) != b7) {
            fail("Book object at index 5 should be reference to b7 (3)");
        }
        if(bookDLL.get(6) != b4) {
            fail("Book object at index 6 should be reference to b4 (1)");
        }

        System.out.println(".... Finished Test 3");
        System.out.println();

        //////////////////////
        // TEST 4: Test the exception thrown by the add at index method
        //////////////////////
        System.out.println("Starting Test 4 (exception thrown by add at index).... ");
        System.out.println("You should see two PASS messages, and no FAIL messages:");

        // Test the exception thrown by the add at index method
        try {
            bookDLL.add(-1, b1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("PASS 1");
        }
        try {
            bookDLL.add(bookDLL.size() + 1, b1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("PASS 2");
        }
        try {
            bookDLL.add(bookDLL.size(), b1);
        } catch (IndexOutOfBoundsException e) {
            fail("add method should allow index equivalent to the list size");
        }

        System.out.println(".... Finished Test 4");
        System.out.println();

        //////////////////////
        // TEST 5: Check the title lists again
        //////////////////////
        System.out.println("Starting Test 5 (check title lists again).... ");
        forward = "Book Three, Book Nine, Book Six, Book Eight, Book Five, Book Seven, Book Four, Book One";
        reverse = "Book One, Book Four, Book Seven, Book Five, Book Eight, Book Six, Book Nine, Book Three";
        if(!bookDLL.titlesForward().equals(forward)) {
            fail("Forward titles are not correct");
        }
        if(!bookDLL.titlesReverse().equals(reverse)) {
            fail("Reverse titles are not correct");
        }
        System.out.println(".... Finished Test 5");
        System.out.println();


    } // end of main method

    /**
    * Prints "FAIL:" followed by the message passed as a parameter.
    *
    * @param s A message to print
    */
    public static void fail(String s) {
        System.out.println("FAIL: " + s);
    }
} // end of Lab6 class
