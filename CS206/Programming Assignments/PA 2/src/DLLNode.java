/**
*
* Class that represents one item in a doubly linked list,
*
* @author Tom Giagtzoglou
*/
public class DLLNode {

    /** The data contained in this node */
    private Object data;

    /** A reference to the next node in the list */
    private DLLNode next;

    /** A reference to the previous node in the list */
    private DLLNode prev;


    /**
    * Constructs a node.
    *
    * @param o The data to put into the new node.
    */
    public DLLNode(Object o) {
        setData(o);
        next = null;
        prev = null;
    }

    /**
    * Mutator method for the data held within this node.
    *
     * @param o The object to contain in this node.
     */
    public void setData(Object o) {
        data = o;
    }

    /**
    * Mutator method for the next node data member.
    *
    * @param n The node to set as the next node.
    */
    public void setNext(DLLNode n) {
        next = n;
    }

    /**
     * Mutator method for the previous node data member
     *
     * @param p the node to set as the previous node.
     */
    public void setPrevious(DLLNode p) {
        prev = p;
    }

    /**
    * Returns the data contained in this node.
    *
    * @return The data contained in this node.
    */
    public Object getData() {
        return data;
    }

    /**
    * Returns a reference to the next node in the list.
    *
    * @return A reference to the next node in the list.
    */
    public DLLNode getNext() {
        return next;
    }

    /**
     * Returns a reference to the previous node in the list.
     *
     * @return A reference to the previous node in the list.
     */
    public DLLNode getPrevious() {
        return prev;
    }
}
