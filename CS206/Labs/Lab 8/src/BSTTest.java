import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.ArrayList;

public class BSTTest {
    public static void main(String[] args) {

        //create books for testing compare to
        Book b1 = new Book("title 1", "author 1", 1);
        Book b2 = new Book("title 2", "author 2", 2);
        Book b3 = new Book("title 1","author 1", 2);
        Book b4 = new Book("title 2","author 3", 2);
        Book b5 = new Book("title 2", "author 3", 2);

        //test compareTo

        System.out.println("Testing compareTo method");

        if (b1.compareTo(b2) != -1){
            fail("b1s title is lexicographically less than b2s");
        }
        if (b2.compareTo(b3) != 1){
            fail("b2s title is lexicographically greater than b3s");
        }
        if (b1.compareTo(b3) != -1){
            fail("b1s edition is less than b3s");
        }
        if (b4.compareTo(b2) != 1){
            fail("b4s author is lexicographically greater than b3s");
        }
        if (b5.compareTo(b4) != 0){
            fail("b5 and b4 should be equal");
        }
        System.out.println("End of testing compareTo method");
        System.out.println();


        //test BTNode to hold object of any type

        System.out.println("Testing BTNode class");

        String s1 = "string 1";
        Double d1 = 1.00001;
        BTNode n1 = new BTNode<Book>(s1);
        BTNode n2 = new BTNode<Book>(d1);
        BTNode n3 = new BTNode<Book>(b1);

        n1.setLeft(n2);
        n1.setRight(n3);

        if (n1.getKey() != s1){
            fail("n1s key should be s1");
        }
        if (n1.getLeft() != n2){
            fail("n1s left node should be n2");

        }
        if (n1.getRight() != n3){
            fail("n1s right node should be n3");
        }

        System.out.println("End of testing BTNode class");
        System.out.println();

        //test BST methods

        System.out.println("Testing BST class");
        b5.setEdition(3);

        //add nodes
        BST<Book> t1 = new BST<Book>();
        t1.insert(b2);
        t1.insert(b1);
        t1.insert(b3);
        t1.insert(b4);
        t1.insert(b5);


        //check nodes IN Order
        ArrayList<BTNode<Book>> nodes = new ArrayList<BTNode<Book>>();

        nodes.add(t1.search(b1));
        nodes.add(t1.search(b3));
        nodes.add(t1.search(b2));
        nodes.add(t1.search(b4));
        nodes.add(t1.search(b5));

        BTNode<Book> root = nodes.get(2);

        if (!root.getKey().equals(b2)){
            fail("The root node should contain b2");
        }

        BTNode<Book> b1Node = nodes.get(0);
        if (!root.getLeft().equals(b1Node)) {
            fail("The root's left node should contain b1");
        }
        if (!b1Node.getRight().equals(nodes.get(1))){
            fail("The b1 nodes right child should be the b3 node");
        }

        BTNode<Book> b4Node = nodes.get(3);
        if (!root.getRight().equals(b4Node)){
            fail("The root's right child should be the b4 node");
        }
        if (!b4Node.getRight().equals(nodes.get(4))){
            fail("The b4 nodes right child should be the b5 node");
        }

        //check max and min value methods
        if (t1.getMax().getKey() != b5){
            fail("b5 is the max value lexicographically");
        }
        if (t1.getMin().getKey() != b1){
            fail("b1 is the min value lexicographically");
        }

        t1.delete(b5);
        if (t1.getMax().getKey() != b4){
            fail("b4 is the max value lexicographically after removing b5");
        }







    }


    /**
     * Outputs a fail message with a reason
     * @param s reason for failure
     */
    public static void fail(String s){
        System.out.println("FAIL: " + s);
    }
}
