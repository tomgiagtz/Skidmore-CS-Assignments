/**
* Represents a linked list that contains Book objects.
*
* @author Christine Reilly and <your name>
*/

import java.util.*;

public class BookLinkedList {

    /** A reference to the first node in the Linked List */
    private Node head;

    /**
    * Constructs an empty list.
    */
    public BookLinkedList() {
        head = null;
    }

    /**
    * Returns the size of this list.
    *
    * @return The size of this list.
    */
    public int size() {
        int count = 0; // Number of objects in the list

        Node temp = head; // Reference to the current node

        while(temp != null) {
            // Increment the count of objects in the list
            count++;

            // Set temp to the next node in the list
            temp = temp.getNext();
        }
        return count;
    }

    /**
    * Appends the specified Book to the end of this list.
    *
    * @b The Book to place at the end of this list.
    */
    public boolean add(Book b) {
        // create a new node
        Node n = new Node(b);

        if(head == null) {
            // list is empty
            head = n;
        } else {
            // list is not empty
            // Traverse the list until the last node is found
            Node temp = head;

            while(temp.getNext() != null) {
                // There is another node after the current one.
                // Set temp to the next node in the list.
                temp = temp.getNext();
            }
            // Now temp is the last node in the list. Update temp so that
            // the new node is added to the list.
            temp.setNext(n);
        }

        return true;
    }

    /**
    * Inserts the book at the given index. Shifts the book currently at that
    * position, and any subsequent Books, to the right.
    *
    * @param i The index where the book should be inserted.
    * @param b The Book object to insert into this list.
    * @throws IndexOutOfBoundsException if index is less than zero or greater than list size
    */
    public void add(int i, Book b) throws IndexOutOfBoundsException {
        // Check that i is greater than 0 and less than the current number of
        // elements in this list.
        if(i < 0 || i > this.size()) {
            throw new IndexOutOfBoundsException("index is not valid");
        }

        int count = 0; // current position in the list
        Node current = head; // current node in the list
        Node prev = null; // previous node in the list
        Node newNode = new Node(b); // new node containing the Book parameter

        // Traverse the list to the position where the insert will occur
        while(count < i) {
                // Set the previous node to be the current node
                prev = current;

                // Update the current node to be the next node
                current = current.getNext();

                // Add 1 to the count of node
                count++;
        }

        // Insert the node

        // First, set the previous node to reference the new node.
        // If this insert is at the beginning of the list, update head.
        if(prev == null) {
            // inserting at the beginning of the list
            head = newNode;
        } else {
            // The previous node's next references the new node
            prev.setNext(newNode);
        }

        // Second, set the new node's next to reference the next node in
        // the list. If this insert is at the end of the list, leave next set
        // to null.

        // The new node's next references the current node
       newNode.setNext(current);
    }

    /**
    * Prints the title of each book in the list.
    */
    public void printTitles() {
        // Traverse the list
        // Print the title of each book
        Node temp = head;

        while(temp != null) {
            System.out.print(temp.getData().getTitle() + ", ");
            temp = temp.getNext();
        }
        System.out.println();
    }

    /**
    * LAB 5: Student must complete this method
    *
    * Returns the element found at index i. Does not alter the list.
    *
    * @param i The index of the desired element.
    * @return The element found at index i
    * @throws IndexOutOfBoundsException if i is less than zero or greater than (list size - 1)
    */
    public Book get(int i) throws IndexOutOfBoundsException {
        if (i < 0 || i > size() - 1){
            throw new IndexOutOfBoundsException("Invalid index");
        }
        int count = 0;
        Node temp = head;
        while (count < i) {
            temp = temp.getNext();
            count++;
        }

        Book retBook = temp.getData();

        // Placeholder return. Delete when this method is finished.
        return retBook;
    }



    /**
    * LAB 5: Student must complete this method
    *
    * Returns true if the list contains the specified element.
    *
    * @return true if the list contains the specified element.
    */
    public boolean contains(Book b) {

        Node temp = head;
        boolean contain = false;

        //traverse the elements while we haven't found the element
        while(temp.getNext() != null && !contain) {

            if (temp.getData().equals(b)){
                //the list contains the element
                contain = true;
                break;
            } else {
                //go to the next node
                temp = temp.getNext();
            }
        }
        return contain;
    }


    /**
    * LAB 5: Student must complete this method
    *
    * Removes and returns the first Book from this list.
    *
    * @return The book removed from this list.
    * @throws NoSuchElementException if the list is empty
    */
    public Book removeFirst() throws NoSuchElementException {
        //get book from head node
        Book retBook = head.getData();
        //get the second node
        Node newFirst = head.getNext();

        //point the head at the second node
        head = newFirst;

        return retBook;
    }

    /**
    * LAB 5: Student must complete this method
    *
    * Removes and returns the last Book from this list.
    *
    * @return The book removed from this list.
    * @throws NoSuchElementException if the list is empty
    */
    public Book removeLast() throws NoSuchElementException {
        Node prev = null;
        Node current = head;

        //when this loop ends, prev will be the second to last node, and current will be the last node
        while (current.getNext() != null) {

            prev = current;

            current = current.getNext();
        }
        //get return book
        Book retBook = current.getData();
        //make the second to last node, the last node
        prev.setNext(null);


        return retBook;
    }

    /**
    * LAB 5: Student must complete this method
    *
    * Removes and returns the Book from this list at the specified index.
    *
    * @return The Book that was removed from this list.
    * @throws IndexOutOfBoundsException if i is less than zero or greater than (list size - 1)
    */
    public Book remove(int i) throws IndexOutOfBoundsException {
        if (i < 0 || i > size() - 1){
            throw new IndexOutOfBoundsException("Invalid index");
        }

        Node current = head;
        Node prev = null;
        int count = 0;
        while (count < i) {
            prev = current;
            current = current.getNext();
            count++;
        }
        // handle if current is the last element in the list and there is no next node
        Node next;
        if (current.getNext() == null){
            next = null;
        } else {
            next = current.getNext();
        }
        Book retBook = current.getData();

        //handle if there is never a prev (because we're removing the first element)
        if (prev == null){
            head = next;
        } else {
            prev.setNext(next);
        }

        //return removed book
        return retBook;
    }

    /**
    * LAB 5: Student must complete this method
    *
    * Removes all elements from this list. The list will be empty after this
    * method returns.
    */
    public void clear() {
        head = null;
    }
}
