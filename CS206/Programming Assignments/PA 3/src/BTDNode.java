import java.util.LinkedList;

/**
 * Represents one node in a Binary Tree where the data is generic.
 *
 * @author Christine Reilly
 */
public class BTDNode<T> {

    /**
     * The data in this node
     */
    private LinkedList<T> key = new LinkedList<>();

    /**
     * Reference to the left child node
     */
    private BTDNode<T> left;

    /**
     * Reference to the right child node
     */
    private BTDNode<T> right;

    /**
     * Reference to the parent of this node
     */
    private BTDNode<T> parent;

    /**
     * Initialize the data and set the children nodes to null.
     *
     * @param d The data to put into this node
     */
    public BTDNode(T d) {
        setKey(d);
        left = null;
        right = null;
        parent = null;
    }

    /**
     * Accessor method for the data list.
     *
     * @return The list of data contained by this node
     */
    public LinkedList<T> getKey() {
        return key;
    }

    /**
     * Accessor method for the left child node.
     *
     * @return A reference to the left child node
     */
    public BTDNode<T> getLeft() {
        return left;
    }

    /**
     * Accessor method for the right child node.
     *
     * @return A reference to the right child node
     */
    public BTDNode<T> getRight() {
        return right;
    }

    /**
     * Accessor method for the parent node.
     *
     * @return A reference to the parent node
     */
    public BTDNode<T> getParent() {
        return parent;
    }

    /**
     * Mutator method for the list of data
     *
     * Handles the case of changing the data held by the node
     *
     * @param newKeyList
     */
    public void setKey(LinkedList<T> newKeyList){
        key = newKeyList;
    }

    /**
     * Mutator method for the data.
     *
     * Checks if the data matches the node, then adds data to the end of the LinkedList
     *
     * @param d The data to put into this node
     * @throws IllegalArgumentException if data does not belong in this node
     */
    public void setKey(T d) throws IllegalArgumentException {
        if ( !key.isEmpty() && !d.equals(key.get(0))) {
            throw new IllegalArgumentException("This key does not belong in this node");
        }
        key.add(d);
    }

    /**
     * Mutator method for the left child node.
     *
     * @param l A reference to the node to set as the left child
     */
    public void setLeft(BTDNode<T> l) {
        left = l;
    }

    /**
     * Mutator method for the right child node.
     *
     * @param r A reference to the node to set as the right child
     */
    public void setRight(BTDNode<T> r) {
        right = r;
    }

    /**
     * Mutator method for the parent node.
     *
     * @param p A reference to the node to set as the parent of this node
     */
    public void setParent(BTDNode<T> p) {
        parent = p;
    }

}
