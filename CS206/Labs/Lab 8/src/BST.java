/**
 * Represents a Binary Search Tree where the data is type Object
 *
 * @author Christine Reilly and Tom Giagtzoglou
 */
public class BST<T extends Comparable<T>> {

    /**
     * A boolean to determine printing to console string representations for insert methods.
     */
    private final boolean VERBOSE = false;

    /**
     * The root node of this tree
     */
    private BTNode<T> root;

    /**
     * Constructor creates an empty tree
     */
    public BST() {
        root = null;
    }

    /**
     * Returns true if the tree is empty, false otherwise.
     *
     * @return true if the tree is empty, false otherwise
     */
    public boolean isEmpty() {
        return (root == null);
    }

    /**
     * Inserts the key into the tree. This is the recursive implementation.
     *
     * @param key The key to insert into the tree
     */
    public void insert(T key) {
        if (root == null){
            root = new BTNode<T>(key);
            return;
        }
        if (VERBOSE) {System.out.println();}
        if (VERBOSE) {System.out.println("Checking " +key + " under " + root.getKey());}

        if (((T) root.getKey()).compareTo(key) > 0){
            if (VERBOSE) {System.out.println(key + " is smaller than " + root.getKey());}
            insert(key, root.getLeft(), root);
        }
        if (((T) root.getKey()).compareTo(key) < 0){
            if (VERBOSE) {System.out.println(key + " is greater than " + root.getKey());}
            insert(key, root.getRight(),root);
        }
    } // end of insert method

    /**
     * Recursive method to insert a key into the tree
     * @param key The key to insert
     * @param currNode Current root of the subtree
     * @param parent Parent of the current root
     */
    private void insert(T key, BTNode<T> currNode, BTNode<T> parent) {
        if (currNode == null) {
            if (VERBOSE) {System.out.println("Adding " + key + " as a child of " + parent.getKey());}
            currNode = new BTNode<T>(key);
            currNode.setParent(parent);
            if (key.compareTo((T) parent.getKey()) < 0){
                if (VERBOSE) {System.out.println(key + " is smaller than " + parent.getKey());}
                parent.setLeft(currNode);
                return;
            } else {
                if (VERBOSE) {System.out.println(key + " is greater than " + parent.getKey());}
                parent.setRight(currNode);
                return;
            }
        }
        if (VERBOSE) {System.out.println("Checking " +key + " under " + currNode.getKey());}
        T currKey = (T) currNode.getKey();
        //if less than, check left
        if (key.compareTo(currKey) < 0) {
            if (VERBOSE) {System.out.println(key + " is smaller than " + currKey);}

            insert(key, currNode.getLeft(), currNode);
        }
        //if greater than, check right
        if (key.compareTo(currKey) > 0) {
            if (VERBOSE) {System.out.println(key + " is greater than " + currKey);}
            insert(key, currNode.getRight(), currNode);
        }
        if (VERBOSE) {System.out.println();}

    }




    /**
     * Search the tree for the given key.
     *
     * @param key The value to search for
     * @return The node where the key is found, or null if the key is not found
     */
    public BTNode<T> search(T key) {
        // Begin the search at the root
        return search(key, root);
    }

    /**
     * Search a subtree for the given key. Uses recursion.
     *
     * @param key    The value to search for
     * @param stRoot The root node of the subtree to search
     * @return The node where the key is found, or null if the key is not found
     */
    private BTNode<T> search(T key, BTNode<T> stRoot) {
        if (stRoot == null) {
            // The key was not found in this subtree, return null
            return null;
        } else {
            // Keep searching for the key
            if (stRoot.getKey().equals(key)) {
                // This subtree root node's key matches the search key
                // Return this subtree root node
                return stRoot;
            } else {
                // Decide whether to look left or right
                if (((T) stRoot.getKey()).compareTo(key) > 0) {
                    // search key belongs in the left subtree
                    // Recursively call this method.
                    BTNode<T> result = search(key, stRoot.getLeft());
                    return result;
                } else {
                    // search key belongs in the right subtree
                    // Recursively call this method.
                    BTNode<T> result = search(key, stRoot.getRight());
                    return result;
                }
            }
        }
    } // end of search method

    /**
     * Print the keys using the in-order algorithm.
     */
    public void inOrderPrint() {
        // Call the recursive inOrderPrint method
        inOrderPrint(root);
    }

    /**
     * Recursive method for the in-order print.
     *
     * @param stRoot The root of the sub-tree to print
     */
    private void inOrderPrint(BTNode<T> stRoot) {
        if (stRoot != null) {
            // Recursively call method to print the left sub-tree
            inOrderPrint(stRoot.getLeft());

            // Print the sub-tree root
            System.out.println(stRoot.getKey());

            // Recursively call method to print the right sub-tree
            inOrderPrint(stRoot.getRight());
        }

        // Base case is stRoot == null; method will return.
    } // end inOrderPrint method

    /**
     * Deletes a node from the BST.
     *
     * @param key The key of the node to delete
     * @return true if the node is successfully deleted; false if the key is not found in the tree
     */
    public boolean delete(T key) {
        if (isEmpty()) {
            // Tree is empty, there is nothing to delete
            return false;
        }

        // Use the search method to get the node
        BTNode<T> foundNode = search(key);
        if (foundNode == null) {
            // No node in the tree has the given key value
            return false;
        }

        // Delete the node
        if (foundNode == root && foundNode.getLeft() == null && foundNode.getRight() == null) {
            // Node is the root and is the only node in the tree. Removing this node results in an empty tree.
            root = null;
        } else {
            if (foundNode == root) {
                // Node is the root and has at least one child
                if (foundNode.getLeft() != null) {
                    // foundNode has a left sub-tree. Implement delete by moving
                    // the data from the right-most node in the left sub-tree to
                    // foundNode, then removing the right-most node in the left
                    // sub-tree from the tree

                    // Get the right-most node in the left sub-tree
                    BTNode<T> rightMostInLeft = getRightMostInLeftST(foundNode);

                    // Replace the foundNode (the one we're deleting) data with
                    // the data from rightMostInLeft
                    foundNode.setKey(rightMostInLeft.getKey());

                    if (rightMostInLeft.getParent().getLeft() == rightMostInLeft) {
                        rightMostInLeft.getParent().setLeft(rightMostInLeft.getLeft());
                        if (rightMostInLeft.getLeft() != null) {
                            rightMostInLeft.getLeft().setParent(rightMostInLeft.getParent());
                        }
                    } else {
                        // Remove the right-most node in the left sub-tree
                        rightMostInLeft.getParent().setRight(rightMostInLeft.getLeft());
                        if (rightMostInLeft.getLeft() != null) {
                            rightMostInLeft.getLeft().setParent(rightMostInLeft.getParent());
                        }
                    }
                } else {
                    // found node does not have a left sub-tree. Set the root
                    // of the right sub-tree to be the new tree root.
                    root = foundNode.getRight();
                    root.setParent(null);
                }

            } else {
                // Node is not the root
                if (foundNode.getLeft() == null && foundNode.getRight() == null) {
                    // Node is a leaf. Delete by setting parent's reference to this
                    // node to null.

                    if (foundNode.getParent().getLeft() == foundNode) {
                        // foundNode is the left child
                        foundNode.getParent().setLeft(null);
                    } else {
                        // foundNode is the right child
                        foundNode.getParent().setRight(null);
                    }
                } else {
                    // foundNode is somewhere in the middle of the tree
                    if (foundNode.getLeft() != null) {
                        // foundNode has a left sub-tree. Implement delete by moving
                        // the data from the right-most node in the left sub-tree to
                        // foundNode, then removing the right-most node in the left
                        // sub-tree from the tree

                        // Get the right-most node in the left sub-tree
                        BTNode<T> rightMostInLeft = getRightMostInLeftST(foundNode);

                        // Replace the foundNode (the one we're deleting) data with
                        // the data from rightMostInLeft
                        foundNode.setKey(rightMostInLeft.getKey());

                        if (rightMostInLeft.getParent().getLeft() == rightMostInLeft) {
                            rightMostInLeft.getParent().setLeft(rightMostInLeft.getLeft());
                            if (rightMostInLeft.getLeft() != null) {
                                rightMostInLeft.getLeft().setParent(rightMostInLeft.getParent());
                            }
                        } else {
                            // Remove the right-most node in the left sub-tree
                            rightMostInLeft.getParent().setRight(rightMostInLeft.getLeft());
                            if (rightMostInLeft.getLeft() != null) {
                                rightMostInLeft.getLeft().setParent(rightMostInLeft.getParent());
                            }
                        }
                    } else {
                        // found node does not have a left sub-tree. Set parent
                        // to refer to found node's right child
                        if (foundNode.getParent().getLeft() == foundNode) {
                            // foundNode is its parent's left child
                            foundNode.getParent().setLeft(foundNode.getRight());
                        } else {
                            // foundNode is its parent's right child
                            foundNode.getParent().setRight(foundNode.getRight());
                        }
                    }
                    // If node had a right child, set that child's parent to
                    // be foundNode's parent.
                    if (foundNode.getRight() != null) {
                        foundNode.getRight().setParent(foundNode.getParent());
                    }
                }
            }
        }

        // Node was found and deleted
        return true;
    } // end of delete method

    /**
     * Returns the right-most node in the left sub-tree. Used as part of the delete method.
     *
     * @param n The root node of the sub-tree
     * @return The right-most node in hte left sub-tree of n
     */
    private BTNode<T> getRightMostInLeftST(BTNode<T> n) {
        if (n.getLeft() == null) {
            // There is no left sub-tree
            return null;
        }
        BTNode<T> temp = n.getLeft(); // root of the left sub-tree of n

        while (temp.getRight() != null) {
            // set temp to the right child
            temp = temp.getRight();
        }

        // temp is the right-most node of the left sub-tree
        return temp;
    } // end getRightMostInLeftST method

    /**
     * Public usage to find the max value in the BST
     * @return Node containing the max value of this BST
     */
    public BTNode<T> getMax(){
        return getMax(root);
    }

    /**
     * Recursive method to find the max value in the BST
     * @param currNode Current node to get right subtree from
     * @return Node containing the max value of this BST
     */
    private BTNode getMax(BTNode<T> currNode){
        if (currNode.getRight() == null){
            return currNode;
        } else {
            return getMax(currNode.getRight());
        }
    }

    /**
     * Public usage to find the min value in the BST
     * @return Node containing the min value of this BST
     */
    public BTNode<T> getMin(){
        return getMin(root);
    }

    /**
     * Recursive method to find the min value in the BST
     * @param currNode Current node to get left subtree from
     * @return Node containing the min value of this BST
     */
    private BTNode getMin(BTNode<T> currNode){
        if (currNode.getLeft() == null){
            return currNode;
        } else {
            return getMin(currNode.getLeft());
        }
    }


}
