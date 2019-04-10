/**
* LAB 6 -- Student must complete the following three methods:
*     add(int i, Book b)
*     titlesForward()
*     titlesReverse()
*
* Represents a doubly linked list that contains Book objects.
*
* @author Christine Reilly and <your name>
*/

import java.util.*;

public class BookDLL {

    // DO NOT ALTER THE DATA MEMBERS

    /** A reference to the first node in the Doubly Linked List */
    private DLLNode head;

    /** A reference to the last node in the Doubly Linked List */
    private DLLNode tail;


    /**
    * DO NOT ALTER THIS METHOD
    *
    * Constructs an empty list.
    */
    public BookDLL() {
        head = null;
        tail = null;
    }

    /**
    * DO NOT ALTER THIS METHOD
    *
    * Returns the size of this list.
    *
    * @return The size of this list.
    */
    public int size() {
        int count = 0; // Number of objects in the list

        DLLNode temp = head; // Reference to the current node

        while(temp != null) {
            // Increment the count of objects in the list
            count++;

            // Set temp to the next node in the list
            temp = temp.getNext();
        }
        return count;
    }

    /**
    * DO NOT ALTER THIS METHOD
    *
    * Returns the element found at index i. Does not alter the list.
    *
    * @param i The index of the desired element.
    * @return The element found at index i
    * @throws IndexOutOfBoundsException if i is less than zero or greater than (list size - 1)
    */
    public Book get(int i) throws IndexOutOfBoundsException {
        // Check that i is greater than 0 and less than the current number of
        // elements in this list - 1.
        if(i < 0 || i > this.size() - 1) {
            throw new IndexOutOfBoundsException("index is not valid");
        }
        DLLNode temp = head;
        int count = 0;

        // Traverse the list to the desired item
        while(temp.getNext() != null && count < i ) {
            temp = temp.getNext();
            count++;
        }
        return temp.getData();
    }

    /**
    * DO NOT ALTER THIS METHOD
    *
    * Appends the specified Book to the end of this list.
    *
    * @param b The Book to place at the end of this list.
    */
    public boolean add(Book b) {
        // create a new node
        DLLNode n = new DLLNode(b);

        if(head == null) {
            // list is empty
            head = n;
            tail = n;
        } else {
            // list is not empty
            // Set the current tail node's next to be the new node
            tail.setNext(n);

            // Set the new node's previous to be the current tail
            n.setPrevious(tail);

            // Update the tail of the list to refer to the new node
            tail = n;
        }
        return true;
    }

    /**
    * LAB 6: STUDENT MUST COMPLETE THIS METHOD
    *
    * Inserts the book at the given index. Shifts the book currently at that
    * position, and any subsequent Books, to the right.
    *
    * @param i The index where the book should be inserted.
    * @param b The Book object to insert into this list.
    * @throws IndexOutOfBoundsException if index is less than zero or greater than list size
    */
    public void add(int i, Book b) throws IndexOutOfBoundsException {
        if ( i < 0 || i > size()) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        int count = 0;

        DLLNode prev = null;
        DLLNode next = head;
        while(count < i && next.getNext() != null) {
            prev = next;
            next = next.getNext();
            count++;
        }


        DLLNode newNode = new DLLNode(b);

        //if we want to add the new node at the end
        if (i == size()) {
            next.setNext(newNode);
            newNode.setPrevious(next);
            tail = newNode;
            return;
        }
        //if we add to the beginning of the list
        if (i == 0) {
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
            return;
        }

        prev.setNext(newNode);
        newNode.setPrevious(prev);

        newNode.setNext(next);
        next.setPrevious(newNode);


    }


    /**
    * LAB 6: STUDENT MUST COMPLETE THIS METHOD
    *
    * Returns the book titles as a String in order from head to tail.
    * String format should be: title0, title1, title2, ..., title_n
    * (the commas must be printed correctly in this string)
    *
    * @return String representation of the book titles in forward order
    */
    public String titlesForward() {
        String output = "";
        DLLNode currNode = head;

        for (int i = 0; i < size(); i++) {
            Book currBook = currNode.getData();
            output += currBook.getTitle() +  (currNode.getNext() == null ? ("") : ", " );

            currNode = currNode.getNext();
        }
        return output;
    }

    /**
    * LAB 6: STUDENT MUST COMPLETE THIS METHOD
    *
    * Returns the book titles as a String in order from tail to head.
    * String format should be: title_n, title_n-1, ..., title0
    * (the commas must be printed correctly in this string)
    *
    * @return String representation of the book titles in reverse order
    */
    public String titlesReverse() {
        String output = "";
        DLLNode currNode = tail;

        for (int i = 0; i < size(); i++) {
            Book currBook = currNode.getData();
            output += currBook.getTitle() +  (currNode.getPrevious() == null ? ("") : ", " );

            currNode = currNode.getPrevious();
        }
        return output;
    }

}
