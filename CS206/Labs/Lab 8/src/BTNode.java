/**
* Represents one node in a Binary Tree where the data is type object
*
* @author Christine Reilly and Tom Giagtzoglou
*/
public class BTNode<T> {

    /** The data in this node */
    private Object key;

    /** Reference to the left child node */
    private BTNode<T> left;

    /** Reference to the right child node */
    private BTNode<T> right;

    /** Reference to the parent of this node */
    private BTNode<T> parent;

    /**
    * Initialize the data and set the children nodes to null.
    *
    * @param d The data to put into this node
    */
    public BTNode(Object d) {
        setKey(d);
        left = null;
        right = null;
        parent = null;
    }

    /**
    * Accessor method for the data.
    *
    * @return The data contained by this node
    */
    public Object getKey() {
        return key;
    }

    /**
    * Accessor method for the left child node.
    *
    * @return A reference to the left child node
    */
    public BTNode<T> getLeft() {
        return left;
    }

    /**
    * Accessor method for the right child node.
    *
    * @return A reference to the right child node
    */
    public BTNode<T> getRight() {
        return right;
    }

    /**
    * Accessor method for the parent node.
    *
    * @return A reference to the parent node
    */
    public BTNode<T> getParent() {
        return parent;
    }

    /**
    * Mutator method for the data.
    *
    * @param d The data to put into this node
    */
    public void setKey(Object d) {
        key = d;
    }

    /**
    * Mutator method for the left child node.
    *
    * @param l A reference to the node to set as the left child
    */
    public void setLeft(BTNode<T> l) {
        left = l;
    }

    /**
    * Mutator method for the right child node.
    *
    * @param r A reference to the node to set as the right child
    */
    public void setRight(BTNode<T> r) {
        right = r;
    }

    /**
    * Mutator method for the parent node.
    *
    * @param r A reference to the node to set as the parent of this node
    */
    public void setParent(BTNode<T> p) {
        parent = p;
    }

}
