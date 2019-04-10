import java.io.*;
import java.util.Scanner;
import java.util.PriorityQueue;

/**
* Main class for CS206 Lab 10. Student must complete three methods:
*    1) pqInt
*    2) pqNameMin
*    3) pqNameMax
*
* For Lab 10, student must also complete the compareTo method in the NameMin
* and NameMax classes.
*/
public class Lab10 {

    /**
    * DO NOT MODIFY THIS METHOD
    *
    * Main method for Lab 10.
    */
    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("calling pqInt method...");
        pqInt("ThousandInts.txt", 20);

        System.out.println("\ncalling pqNameMin method...");
        pqNameMin("NameList.txt", 15);

        System.out.println("\ncalling pqNameMax method...");
        pqNameMax("NameList.txt", 15);

    }

    /**
    * STUDENT MUST COMPLETE THIS METHOD
    * Follow the steps outlined by the comments to complete this method.
    *
    * Uses a Priority Queue to print the given number of lowest integers that
    * are read from the given file.
    *
    * @param filename The pathname of the input file
    * @param numItems The number of items to print
    */
    public static void pqInt(String filename, int numItems) throws FileNotFoundException {

        // A priority queue that contains integers. The lowest integer value
        // will have the highest priority (will be removed first).
        PriorityQueue<Integer> pqInt = new PriorityQueue<Integer>();

        // Read integers from the file specified by the filename parameter. Add
        // each integer to the pqInt priority queue.

        File file = new File(filename);

        if (!file.canRead()){
            throw new FileNotFoundException("Invalid filename");
        }

        Scanner input = new Scanner(file);
        Integer currLine;

        while (input.hasNext()){
            currLine = input.nextInt();
            pqInt.offer(currLine);
        }

        // Print the first numItems that are removed from the priority queue.
        for (int i = 0; i < numItems; i++) {
            System.out.println(pqInt.poll());
        }
    }

    /**
    * STUDENT MUST COMPLETE THIS METHOD
    * Follow the steps outlined by the comments to complete this method.
    *
    * Uses a Priority Queue to print the given number of (name priority) pairs
    * that are read from the given file, where the lowest value for priority
    * is the first item removed from the priority queue.
    *
    * @param filename The pathname of the input file
    * @param numItems The number of items to print
    *
    */
    public static void pqNameMin(String filename, int numItems) throws FileNotFoundException {

        // A priority queue that contains NameMin objects.
        PriorityQueue<NameMin> pqName = new PriorityQueue<NameMin>();

        // Read the input file and create a NameMin object for each line of the
        // input file. Add each NameMin object to the pqName priority queue.
        File file = new File(filename);

        if (!file.canRead()){
            throw new FileNotFoundException("Invalid filename");
        }

        Scanner input = new Scanner(file);
        Scanner line;
        String currLine;
        NameMin currName;


        while (input.hasNext()){
            currLine = input.nextLine();
            line = new Scanner(currLine);

            int priority = line.nextInt();
            String name = line.next();
            currName = new NameMin(name, priority);

            pqName.offer(currName);
        }

        // Print the first numItems that are removed from the priority queue.

        for (int i = 0; i < numItems; i++) {
            currName = pqName.poll();
            System.out.println(currName.getName() + " " + currName.getPriority());
        }
    }

    /**
    * STUDENT MUST COMPLETE THIS METHOD
    * Follow the steps outlined by the comments to complete this method.
    *
    * Uses a Priority Queue to print the given number of (name priority) pairs
    * that are read from the given file, where the highest value for priority
    * is the first item removed from the priority queue.
    *
    * @param filename The pathname of the input file
    * @param numItems The number of items to print
    *
    */
    public static void pqNameMax(String filename, int numItems) throws FileNotFoundException {

        // A priority queue that contains NameMax objects.
        PriorityQueue<NameMax> pqName = new PriorityQueue<NameMax>();

        // Read the input file and create a NameMax object for each line of the
        // input file. Add each NameMax object to the pqName priority queue.
        File file = new File(filename);

        if (!file.canRead()){
            throw new FileNotFoundException("Invalid filename");
        }

        Scanner input = new Scanner(file);
        Scanner line;
        String currLine;
        NameMax currName;


        while (input.hasNext()){
            currLine = input.nextLine();
            line = new Scanner(currLine);

            int priority = line.nextInt();
            String name = line.next();
            currName = new NameMax(name, priority);

            pqName.offer(currName);
        }

        // Print the first numItems that are removed from the priority queue.

        for (int i = 0; i < numItems; i++) {
            currName = pqName.poll();
            System.out.println(currName.getName() + " " + currName.getPriority());
        }

    }
}
