/**
 * Represents a doubly linked list that contains Objects.
 *
 * @author Tom Giagtzoglou
 */

import sun.jvm.hotspot.debugger.windbg.DLL;

import java.util.*;

public class DCLL {

    /**
     * A reference to the first node in the Doubly Linked List
     */
    private DLLNode head;

    /**
     * Constructs an empty list.
     */
    public DCLL() {
        head = null;
    }

    /**
     * Appends the specified object to the end of this list.
     *
     * @param o The object to place at the end of this list.
     */
    public boolean add(Object o) {
        // create a new node
        DLLNode n = new DLLNode(o);

        if (head == null) {
            // list is empty
            head = n;
            n.setNext(head);
            n.setPrevious(head);
        } else {
            // list is not empty
            // get node at end of list
            DLLNode tail = head.getPrevious();

            tail.setNext(n);
            n.setPrevious(tail);

            n.setNext(head);
            head.setPrevious(n);

        }
        return true;
    }

    /**
     * Inserts the object at the given index. Shifts the object currently at that
     * position, and any subsequent objects, to the right.
     *
     * @param i The index where the object should be inserted.
     * @param o The object to insert into this list.
     * @throws IndexOutOfBoundsException if index is less than zero or greater than list size
     */
    public void add(int i, Object o) throws IndexOutOfBoundsException {
        if (i < 0 || i > size()) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        int count = 0;

        DLLNode prev = null;
        DLLNode next = head;
        while (count < i && next.getNext() != head) {
            prev = next;
            next = next.getNext();
            count++;
        }


        DLLNode newNode = new DLLNode(o);

        if (head == null) {
            head = newNode;
            head.setNext(head);
            head.setPrevious(head);
        }

        DLLNode tail = head.getPrevious();

        //if we add to the beginning of the list
        if (i == 0) {

            tail.setNext(newNode);
            newNode.setPrevious(tail);

            head.setPrevious(newNode);
            newNode.setNext(head);
            head = newNode;


            return;
        }
        //if we want to add the new node at the end
        if (i == size()) {
            add(o);
            return;
        }


        prev.setNext(newNode);
        newNode.setPrevious(prev);

        newNode.setNext(next);
        next.setPrevious(newNode);


    }

    /**
     * Removes all elements from this list
     */
    public void clear() {
        head = null;
    }

    /**
     * Returns true if the list contains the specified element.
     *
     * @return true if the list contains the specified element.
     */
    public boolean contains(Object o) {

        DLLNode temp = head;


        if (temp.getData().equals(o)) { //handle if list is one element
            return true;
        } else if (head == null) { //if list is empty
            return false;
        }
        temp = head.getNext();
        //traverse the elements while we haven't found the element
        while (temp != head) {
            if (temp.getData().equals(o)) {
                //the list contains the element
                return true;
            } else {
                //go to the next node
                temp = temp.getNext();
            }
        }
        return false;
    }


    /**
     * Returns the element found at index i. Does not alter the list.
     *
     * @param i The index of the desired element.
     * @return The element found at index i
     * @throws IndexOutOfBoundsException if i is less than zero or greater than (list size - 1)
     */
    public Object get(int i) throws IndexOutOfBoundsException {
        // Check that i is greater than 0 and less than the current number of
        // elements in this list - 1.
        if (i < 0 || i > this.size()) {
            throw new IndexOutOfBoundsException("index is not valid");
        }

        if (head == null) {
            return null;
        }
        DLLNode temp = head;
        int count = 0;

        // Traverse the list to the desired item
        while (temp.getNext() != head && count < i) {
            temp = temp.getNext();
            count++;
        }
        return temp.getData();
    }

    /**
     * Returns the index of the first occurrence of the object
     *
     * @param o object to search for
     * @return the index of the first occurrence of the object, or -1 if not present
     */
    public int indexOf(Object o) {
        DLLNode temp = head;
        int currInd = 1;

        if (!contains(o)) { //if element isn't present
            return -1;
        }

        if (temp.getData().equals(o)) { //handle if list is one element
            return 0;
        }
        temp = head.getNext();

        while (temp != head) {

            if (temp.getData().equals(o)) {
                return currInd;
            }
            currInd++;
            temp = temp.getNext();
        }
        return -1;


    }

    /**
     * Returns true if list contains no elements
     *
     * @return true if list contains no elements
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Returns the index of the last occurrence of the object
     *
     * @param o object to search for
     * @return the index of the last occurrence of the object, or -1 if not present
     */
    public int lastIndexOf(Object o) {

        int currInd = size() - 1;
        if (!contains(o)) { //if element isn't present
            return -1;
        }
        DLLNode temp = head.getPrevious();

        while (currInd > 0) {
            if (temp.getData().equals(o)) {
                return currInd;
            }
            currInd--;
            temp = temp.getPrevious();
        }

        if (head.getData().equals(o)) {
            return 0;
        }
        return -1;

    }

    /**
     * Removes and returns the Object from this list at the specified index.
     *
     * @return The Object that was removed from this list.
     * @throws IndexOutOfBoundsException if i is less than zero or greater than (list size - 1)
     */
    public Object remove(int i) throws IndexOutOfBoundsException {
        if (i < 0 || i > size()) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        //handle removing first element
        if (i == 0){
            DLLNode tail = head.getPrevious();
            DLLNode oldHead = head;
            DLLNode newHead = head.getNext();
            tail.setNext(newHead);
            newHead.setPrevious(tail);
            head = newHead;

            return oldHead.getData();
        }
        DLLNode current = head;
        DLLNode prev = null;
        int count = 0;
        while (count < i) {
            prev = current;
            current = current.getNext();
            count++;
        }
        DLLNode next = current.getNext();

        prev.setNext(current.getNext());
        next.setPrevious(prev);

        return current.getData();
    }

    /**
     * Removes first occurrence of the object, returns true if remove successful
     *
     * @param o object to be removed
     * @return true if the list contained the object
     */
    public boolean remove(Object o) {
        int ind = indexOf(o);
        if (ind == -1) {
            return false;
        }

        return null != remove(ind);

    }

    /**
     * Returns the size of this list.
     *
     * @return The size of this list.
     */
    public int size() {

        if (head == null) {
            return 0;
        }
        int count = 1; // Number of objects in the list

        DLLNode temp = head; // Reference to the current node

        while (temp.getNext() != head) {
            // Increment the count of objects in the list
            count++;

            // Set temp to the next node in the list
            temp = temp.getNext();
        }
        return count;
    }

    /**
     * Returns the objects as a String in order from head to tail.
     * String format should be: title0, title1, title2, ..., title_n
     * (the commas must be printed correctly in this string)
     *
     * @return String representation of the objects in forward order
     */
    public String forwardString() {
        String output = "";
        DLLNode currNode = head;

        for (int i = 0; i < size(); i++) {

            Object currObj = currNode.getData();
            output += currObj.toString() + (currNode.getNext() == head ? ("") : ", ");

            currNode = currNode.getNext();
        }
        return output;
    }

    /**
     * Returns the objects as a String in order from tail to head.
     * String format should be: title_n, title_n-1, ..., title0
     * (the commas must be printed correctly in this string)
     *
     * @return String representation of the objects in reverse order
     */
    public String reverseString() {
        String output = "";
        DLLNode currNode = head;

        for (int i = 0; i < size(); i++) {
            Object currObj = currNode.getData();
            output = (currNode.getNext() == head ? ("") : ", ") + currObj.toString() + output;

            currNode = currNode.getNext();
        }
        return output;
    }

}
