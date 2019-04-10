/**
* DO NOT MODIFY THIS FILE!
*
* Test program for Lab 5
*
* @author Christine Reilly
*/
public class Lab5Test {
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

        // Create an empty linked list
        BookLinkedList list = new BookLinkedList();

        int listSize; // size of the list
        Book b; // A temporary Book object

        // Add some nodes to the list
        list.add(b1);
        list.add(b2);
        list.add(1,b3);
        list.add(0,b4);
        list.add(4,b5);
        list.add(b6);
        list.add(b7);

        // Print the titles of the books in the list
        list.printTitles();
        System.out.println();

        listSize = list.size();

        /***** Test 1: contains method */
        System.out.println("Starting Test 1.... ");
        if(!list.contains(b2)) {
            System.out.println("    FAIL: contains should return true for b2");
        }
        if(list.contains(b9)) {
            System.out.println("    FAIL: contains should return false for b9");
        }
        System.out.println(".... Finished Test 1");
        System.out.println();
        /***** Test 1: end */


        /***** Test 2: remove first method */

        System.out.println("Starting Test 2.... ");

        b = list.removeFirst();
        listSize--;
        if(!b.equals(b4)) {
            System.out.println("    FAIL: removed book should be b4");
        }
        if(list.size() != listSize) {
            System.out.println("    FAIL: list size is not " + listSize);
        }

        b = list.removeFirst();
        listSize--;
        if(!b.equals(b1)) {
            System.out.println("    FAIL: removed book should be b1");
        }
        if(list.size() != listSize) {
            System.out.println("    FAIL: list size is not " + listSize);
        }

        System.out.println(".... Finished Test 2");
        System.out.println();

        /***** Test 2: end */


        /***** Test 3: remove last method */
        System.out.println("Starting Test 3.... ");

        b = list.removeLast();
        listSize--;
        if(!b.equals(b7)) {
            System.out.println("    FAIL: removed book should be b7");
        }
        if(list.size() != listSize) {
            System.out.println("    FAIL: list size is not " + listSize);
        }

        b = list.removeLast();
        listSize--;
        if(!b.equals(b6)) {
            System.out.println("    FAIL: removed book should be b6");
        }
        if(list.size() != listSize) {
            System.out.println("    FAIL: list size is not " + listSize);
        }

        System.out.println(".... Finished Test 3");
        System.out.println();

        /***** Test 3: end */

        // Add more nodes to the list, it was getting small
        list.add(b8);
        list.add(b9);
        listSize = list.size();
        System.out.print("Current titles in list: ");
        list.printTitles();
        System.out.println();

        /***** Test 4: remove at index method */
        System.out.println("Starting Test 4.... ");

        // Remove item at index 3
        b = list.remove(3);
        listSize--;
        if(!b.equals(b8)) {
            System.out.println("    FAIL: removed book should be b8");
        }
        if(list.size() != listSize) {
            System.out.println("    FAIL: list size is not " + listSize);
        }

        // Remove item at index 0 (first item)
        b = list.remove(0);
        listSize--;
        if(!b.equals(b3)) {
            System.out.println("    FAIL: removed book should be b3");
        }
        if(list.size() != listSize) {
            System.out.println("    FAIL: list size is not " + listSize);
        }

        // Remove item at index 2 (last item)
        b = list.remove(2   );
        listSize--;
        if(!b.equals(b9)) {
            System.out.println("    FAIL: removed book should be b9");
        }
        if(list.size() != listSize) {
            System.out.println("    FAIL: list size is not " + listSize);
        }

        System.out.println(".... Finished Test 4");
        System.out.println();

        /***** Test 4: end */

        System.out.print("Current titles in list: ");
        list.printTitles();
        System.out.println();

        /***** Test 5: clear method */
        System.out.println("Starting Test 5.... ");
        list.clear();

        if(list.size() != 0) {
            System.out.println("     Fail: list size is not zero");
        }
        System.out.println(".... Finished Test 5");
        System.out.println();

        /***** Test 5: end */

    }
}
