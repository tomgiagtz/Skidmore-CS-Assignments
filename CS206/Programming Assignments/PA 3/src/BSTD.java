import java.util.LinkedList;

/**
* Represents a Binary Search Tree that contains a generic type of data.
*
* @author Christine Reilly
*/
public class BSTD<T extends Comparable<T>>{

    /** The root node of this tree */
    private BTDNode<T> root;

    /**
    * Constructor creates an empty tree
    */
    public BSTD() {
        root = null;
    }

    /**
    * Returns true if the tree is empty, false otherwise.
    *
    * @return true if the tree is empty, false otherwise
    */
    public boolean isEmpty() {
        return(root == null);
    }

    /**
    * Inserts the key into the tree. This is the recursive implementation.
    *
    * @param key The key to insert into the tree
    */
    public void insert(T key) {
        insert(key, root);
    }

    /**
    * Inserts the key into the tree. This is the recursive implementation.
    *
    * @param key The key to insert into the tree
    * @param stRoot Root of the sub-tree to insert into
    */
    private void insert(T key, BTDNode<T> stRoot) {
        BTDNode<T> nodeToInsert = new BTDNode<T>(key);

        if(isEmpty()) {
            // If tree is empty, set the root to be this new node
            root = nodeToInsert;
        }
        else {
            //BTDNode<T> temp = root;
            if(key.compareTo(stRoot.getKey().get(0)) < 0) {
                // insert belongs in the left sub-tree
                if(stRoot.getLeft() == null) {
                    // sub-tree root has no left child, set this node
                    // as the left child
                    stRoot.setLeft(nodeToInsert);
                    nodeToInsert.setParent(stRoot);
                    return;
                } else {
                    // keep looking down the tree for a place to insert
                    insert(key, stRoot.getLeft());
                }
            } else {
                // insert belongs in the right sub-tree
                if(stRoot.getRight() == null) {
                    // sub-tree root has no right child, set this node
                    // as the right child
                    stRoot.setRight(nodeToInsert);
                    nodeToInsert.setParent(stRoot);
                    return;
                } else {
                    // keep looking down the tree for a place to insert
                    insert(key,stRoot.getRight());
                }
            }
        }
    } // end of private insert method

    /**
    * Search the tree for the given key.
    *
    * @param key The value to search for
    * @return The node where the key is found, or null if the key is not found
    */
    public BTDNode<T> search(T key) {
        // Begin the search at the root
        return search(key, root);
    }

    /**
    * Search a subtree for the given key. Uses recursion.
    *
    * @param key The value to search for
    * @param stRoot The root node of the subtree to search
    * @return The node where the key is found, or null if the key is not found
    */
    private BTDNode<T> search(T key, BTDNode<T> stRoot) {
        if(stRoot == null) {
            // The key was not found in this subtree, return null
            return null;
        }
        else {
            // Keep searching for the key
            if(stRoot.getKey().get(0).equals(key)) {
                // This subtree root node's key matches the search key
                // Return this subtree root node
                return stRoot;
            } else {
                // Decide whether to look left or right
                if(stRoot.getKey().get(0).compareTo(key) > 0) {
                    // search key belongs in the left subtree
                    // Recursively call this method.
                    BTDNode<T> result = search(key, stRoot.getLeft());

                    return result;
                } else {
                    // search key belongs in the right subtree
                    // Recursively call this method.
                    BTDNode<T> result = search(key, stRoot.getRight());
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
    private void inOrderPrint(BTDNode<T> stRoot) {
        if(stRoot != null) {
            // Recursively call method to print the left sub-tree
            inOrderPrint(stRoot.getLeft());

            // Print the sub-tree root
            LinkedList<T> currKey = stRoot.getKey();
            for (T curr : currKey){
                System.out.println(curr);
            }

            // Recursively call method to print the right sub-tree
            inOrderPrint(stRoot.getRight());
        }

        // Base case is stRoot == null; method will return.
    } // end inOrderPrint method

    /**
    * Deletes a node from the BSTD.
    *
    * @param key The key of the node to delete
    * @return true if the node is successfully deleted; false if the key is not found in the tree
    */
    public boolean delete(T key) {
        if(isEmpty()) {
            // Tree is empty, there is nothing to delete
            return false;
        }

        // Use the search method to get the node
        BTDNode<T> foundNode = search(key);
        if(foundNode == null) {
            // No node in the tree has the given key value
            return false;

        }

        // Delete the node
        if(foundNode == root && foundNode.getLeft() == null && foundNode.getRight() == null) {
            // Node is the root and is the only node in the tree. Removing this node results in an empty tree.

            root = null;
        } else {
            if(foundNode == root) {
                // Node is the root and has at least one child
                if(foundNode.getLeft() != null) {
                    // foundNode has a left sub-tree. Implement delete by moving
                    // the data from the right-most node in the left sub-tree to
                    // foundNode, then removing the right-most node in the left
                    // sub-tree from the tree

                    // Get the right-most node in the left sub-tree
                    BTDNode<T> rightMostInLeft = getRightMostInLeftST(foundNode);

                    // Replace the foundNode (the one we're deleting) data with
                    // the data from rightMostInLeft
                    foundNode.setKey(rightMostInLeft.getKey());

                    if(rightMostInLeft.getParent().getLeft() == rightMostInLeft) {
                        rightMostInLeft.getParent().setLeft(rightMostInLeft.getLeft());
                        if(rightMostInLeft.getLeft() != null) {
                            rightMostInLeft.getLeft().setParent(rightMostInLeft.getParent());
                        }
                    } else {
                        // Remove the right-most node in the left sub-tree
                        rightMostInLeft.getParent().setRight(rightMostInLeft.getLeft());
                        if(rightMostInLeft.getLeft() != null) {
                            rightMostInLeft.getLeft().setParent(rightMostInLeft.getParent());
                        }
                    }
                }
                else {
                    // found node does not have a left sub-tree. Set the root
                    // of the right sub-tree to be the new tree root.
                    root = foundNode.getRight();
                    root.setParent(null);
                }

            } else {

                // Node is not the root
                if(foundNode.getLeft() == null && foundNode.getRight() == null) {
                    // Node is a leaf. Delete by setting parent's reference to this
                    // node to null.

                    if(foundNode.getParent().getLeft() == foundNode) {
                        // foundNode is the left child
                        foundNode.getParent().setLeft(null);
                    } else {
                        // foundNode is the right child
                        foundNode.getParent().setRight(null);
                    }
                } else {
                    // foundNode is somewhere in the middle of the tree
                    if(foundNode.getLeft() != null) {
                        // foundNode has a left sub-tree. Implement delete by moving
                        // the data from the right-most node in the left sub-tree to
                        // foundNode, then removing the right-most node in the left
                        // sub-tree from the tree

                        // Get the right-most node in the left sub-tree
                        BTDNode<T> rightMostInLeft = getRightMostInLeftST(foundNode);

                        // Replace the foundNode (the one we're deleting) data with
                        // the data from rightMostInLeft
                        foundNode.setKey(rightMostInLeft.getKey());

                        if(rightMostInLeft.getParent().getLeft() == rightMostInLeft) {
                            rightMostInLeft.getParent().setLeft(rightMostInLeft.getLeft());
                            if(rightMostInLeft.getLeft() != null) {
                                rightMostInLeft.getLeft().setParent(rightMostInLeft.getParent());
                            }
                        } else {
                            // Remove the right-most node in the left sub-tree
                            rightMostInLeft.getParent().setRight(rightMostInLeft.getLeft());
                            if(rightMostInLeft.getLeft() != null) {
                                rightMostInLeft.getLeft().setParent(rightMostInLeft.getParent());
                            }
                        }
                    } else {
                        // found node does not have a left sub-tree. Set parent
                        // to refer to found node's right child
                        if(foundNode.getParent().getLeft() == foundNode) {
                            // foundNode is its parent's left child
                            foundNode.getParent().setLeft(foundNode.getRight());
                        } else {
                            // foundNode is its parent's right child
                            foundNode.getParent().setRight(foundNode.getRight());
                        }
                    }
                    // If node had a right child, set that child's parent to
                    // be foundNode's parent.
                    if(foundNode.getRight() != null) {
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
    private BTDNode<T> getRightMostInLeftST(BTDNode<T> n) {
        if(n.getLeft() == null) {
            // There is no left sub-tree
            return null;
        }
        BTDNode<T> temp = n.getLeft(); // root of the left sub-tree of n

        while(temp.getRight() != null) {
            // set temp to the right child
            temp = temp.getRight();
        }

        // temp is the right-most node of the left sub-tree
        return temp;
    } // end getRightMostInLeftST method

    /**
    * Returns the minimum key in this tree.
    *
    * @return The minimum key in this tree
    */
    public T getMinimumKey() {
        if(isEmpty()) {
            return null;
        }

        return getMinimumKey(root);
    }

    /**
    * Returns the minimum key in the subtree.
    *
    * @param stRoot The root node of the subtree to traverse
    * @return The minimum key in this tree
    */
    private T getMinimumKey(BTDNode<T> stRoot) {
        if(stRoot.getLeft() != null) {
            // recursive case
            return getMinimumKey(stRoot.getLeft());
        }
        // base case
        return stRoot.getKey().get(0);
    }

    /**
    * Returns the maximum key in this tree.
    *
    * @return The maximum key in this tree
    */
    public T getMaximumKey() {
        if(isEmpty()) {
            return null;
        }

        return getMaximumKey(root);
    }

    /**
    * Returns the maximum key in the subtree.
    *
    * @param stRoot The root node of the subtree to traverse
    * @return The maximum key in this tree
    */
    private T getMaximumKey(BTDNode<T> stRoot) {
        if(stRoot.getRight() != null) {
            // recursive case
            return getMaximumKey(stRoot.getRight());
        }
        // base case
        return stRoot.getKey().get(0);
    }

    public void printInRange(T start, T end){
        printInRange(root, start, end);
    }

    private void printInRange(BTDNode<T> currNode, T start, T end){
        if (currNode != null){
            T currKey = currNode.getKey().get(0);
            LinkedList<T> keyList= currNode.getKey();
            printInRange(currNode.getLeft(), start, end);
            if(currKey.compareTo(start) >= 0 && currKey.compareTo(end) <= 0){
                for (T d : keyList) {
                    System.out.println(d);
                }
            }
            printInRange(currNode.getRight(), start, end);
        }
    }


}
