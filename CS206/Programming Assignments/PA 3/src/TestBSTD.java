/**
* Tests the Binary Search Tree that contains duplicates.
*
* @author Christine Reilly
*/
public class TestBSTD {
    public static void main(String[] args) {
        Integer intReturn; // hold a return value
        boolean boolReturn;

        // Create a tree object
        BSTD<Integer> myTree = new BSTD<Integer>();

        System.out.println("Printing the empty tree:");
        myTree.inOrderPrint();
        System.out.println("Done printing the empty tree");

        // Insert a bunch of keys
        myTree.insert(new Integer(17));
        myTree.insert(new Integer(8));
        myTree.insert(new Integer(6));
        myTree.insert(new Integer(43));
        myTree.insert(new Integer(10));
        myTree.insert(new Integer(37));
        myTree.insert(new Integer(40));
        myTree.insert(new Integer(15));
        myTree.insert(new Integer(36));
        myTree.insert(new Integer(28));

        // Insert some duplicate values
        myTree.insert(new Integer(8));
        myTree.insert(new Integer(8));
        myTree.insert(new Integer(8));
        myTree.insert(new Integer(8));
        myTree.insert(new Integer(40));
        myTree.insert(new Integer(40));

        System.out.println("After inserts: In order traversal prints:");
        myTree.inOrderPrint();

        System.out.println("\n Testing print in range with range 9 to 40");
        myTree.printInRange(9, 40);

        System.out.println("\nTesting search, getMinimumKey, and getMaximumKey methods:");

        if(myTree.search(new Integer(37)) != null) {
            System.out.println("(correct) found " + 37);
        } else {
            System.out.println("(incorrect) did not find " + 37);
        }

        if(myTree.search(new Integer(51)) != null) {
            System.out.println("(incorrect) found " + 51);
        } else {
            System.out.println("(correct) did not find " + 51);
        }

        if(myTree.search(new Integer(8)) != null) {
            System.out.println("(correct) found " + 8);
        } else {
            System.out.println("(incorrect) did not find " + 8);
        }

        intReturn = myTree.getMinimumKey();
        System.out.println("Minimum key: " + intReturn);
        intReturn = myTree.getMaximumKey();
        System.out.println("Maximum key: " + intReturn);

        // Delete some nodes, then print new min and max
        myTree.delete(new Integer(43));
        myTree.delete(new Integer(8));
        myTree.delete(new Integer(6));

        System.out.println("\nAfter deletes:");
        System.out.println("In order traversal prints:");
        myTree.inOrderPrint();
        intReturn = myTree.getMinimumKey();
        System.out.println("Minimum key: " + intReturn);
        intReturn = myTree.getMaximumKey();
        System.out.println("Maximum key: " + intReturn);

        myTree.delete(new Integer(40));
        myTree.delete(new Integer(40));
        myTree.delete(new Integer(40));

        System.out.println("\nAfter more deletes:");
        System.out.println("In order traversal prints:");
        myTree.inOrderPrint();
        intReturn = myTree.getMinimumKey();
        System.out.println("Minimum key: " + intReturn);
        intReturn = myTree.getMaximumKey();
        System.out.println("Maximum key: " + intReturn);

        boolReturn = myTree.delete(new Integer(40));
        if(boolReturn == true) {
            System.out.println("ERROR B: delete should return false");
        }

        // Delete all the values in the tree
        myTree.delete(new Integer(8));
        myTree.delete(new Integer(37));
        myTree.delete(new Integer(10));
        myTree.delete(new Integer(17));
        myTree.delete(new Integer(8));
        myTree.delete(new Integer(36));
        myTree.delete(new Integer(8));
        myTree.delete(new Integer(28));
        myTree.delete(new Integer(8));
        myTree.delete(new Integer(17));
        myTree.delete(new Integer(15));

        System.out.println("\nAfter all deletes, should have empty tree:");
        System.out.println("In order traversal prints:");
        myTree.inOrderPrint();

    }
}
