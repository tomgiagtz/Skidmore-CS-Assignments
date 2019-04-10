/**
* LAB 5: DO NOT MODIFY THIS FILE
*
* Class that represents one item in a list.
*
* @author Christine Reilly
*/
public class Node {

    /** The data contained in this node */
    private Book data;

    /** A reference to the next node in the list */
    private Node next;


    /**
    * Constructs a node.
    *
    * @param b The data to put into the new node.
    */
    public Node(Book b) {
        setData(b);
        next = null;
    }

    /**
    * Mutator method for the data held within this node.
    *
    * @param b The book to contain in this node.
    */
    public void setData(Book b) {
        data = b;
    }

    /**
    * Mutator method for the next node data member.
    *
    * @param n The node to set as the next node.
    */
    public void setNext(Node n) {
        next = n;
    }

    /**
    * Returns the data contained in this node.
    *
    * @return The data contained in this node.
    */
    public Book getData() {
        return data;
    }

    /**
    * Returns a reference to the next node in the list.
    *
    * @return A reference to the next node in the list.
    */
    public Node getNext() {
        return next;
    }
}
