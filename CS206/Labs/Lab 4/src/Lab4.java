/**
* Main program for CS206 Lab 4.
*
* LAB 4: Student must complete the methods as indicated by the method
* header comments.
*
* @author your name
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Lab4 {
    /**
    * DO NOT MAKE ANY CHANGES TO THIS METHOD
    *
    * Main method
    */
    public static void main(String[] args) throws FileNotFoundException {

        int choice = 0; // User's choice of menu option, initialized to zero

        // Construct an empty ArrayList that can contain Book objects
        ArrayList<Book> books = new ArrayList<Book>();


        // Call the method that reads book data from the input file
        readBooks("booksIn.txt", books);

        // Print the menu and get the user's choice
        do {
            choice = menu();

            if(choice == 1) {
                printBookList(books);
            } else if(choice == 2) {
                addBook(books);
            } else if(choice == 3) {
                writeBooks("booksOut.txt", books);
            }
        } while(choice != 4);

        // Before ending the program, write the list of books to the output file
        writeBooks("booksOut.txt", books);

        System.out.println("Good Bye!");

    } // end of main method

    /**
    * DO NOT MAKE ANY CHANGES TO THIS METHOD
    *
    * Prints the menu and returns the user's choice of menu option. Assumes
    * that the user is "well behaved" and enters the requested input.
    *
    * @return The user's choice of menu option
    */
    public static int menu() {
        int c;
        Scanner console = new Scanner(System.in);

        // Print the menu
        System.out.println();
        System.out.println("********************");
        System.out.println("Options for the Book List Program:");
        System.out.println("    1: Show the books in the list");
        System.out.println("    2: Add a new book to the list");
        System.out.println("    3: Save the book list to the output file");
        System.out.println("    4: Quit this program");
        System.out.print(".... Enter your choice (1, 2, 3, or 4):  ");
        c = console.nextInt();
        System.out.println();

        return c;
    } // end of menu method

    /**
    * LAB 4: STUDENT MUST COMPLETE THIS METHOD
    *
    * Reads book data from the input file into the ArrayList
    * Assumes each book entry in the input file has the format:
    *      edition title
    *      author
    * Notice that edition and title are on one line, and author is on the
    * next line. Each book entry in this file uses two lines.
    *
    * @param inFile The pathname of the input file to read from
    * @param bookList The ArrayList to add Book objects to
    */
    public static void readBooks(String inFile, ArrayList<Book> bookList) throws FileNotFoundException {
        File f = new File(inFile);
        if (!f.canRead()) throw new FileNotFoundException();

        Scanner input = new Scanner(f);
        while(input.hasNextLine()) {
            String inLine = input.nextLine();
            Scanner line = new Scanner(inLine);

            //retrieve data from each pair of lines
            int edition = line.nextInt();
            String title = "";
            while(line.hasNext()){
                title += line.next() + (line.hasNext() ? " " : "");
            }
            String author = input.nextLine();

            //create book object and add to book list
            Book inputedBook = new Book(title, author, edition);
            bookList.add(inputedBook);

        }




    }

    /**
    * LAB 4: STUDENT MUST COMPLETE THIS METHOD
    *
    * Writes book data from the ArrayList into the output file
    * Assumes each book entry in the input file has the format:
    *      edition title
    *      author
    * Notice that edition and title are on one line, and author is on the
    * next line. Each book entry in this file uses two lines.
    *
    * @param outFile The pathname of the output file to read from
    * @param bookList The ArrayList to add Book objects to
    */
    public static void writeBooks(String outFile, ArrayList<Book> bookList) throws FileNotFoundException{
        File f = new File(outFile);
        if(!f.canWrite()) throw new FileNotFoundException();

        PrintStream currBook = new PrintStream(f);

        for (int i = 0; i < bookList.size(); i++) {
            Book temp = bookList.get(i);
            currBook.print(temp.getEdition() + " ");
            currBook.println(temp.getTitle());
            currBook.println(temp.getAuthor());
        }

    }

    /**
    * LAB 4: STUDENT MUST COMPLETE THIS METHOD
    *
    * Prints information about every book in the list. Uses the toString method
    * from the Book class to get a string representation of a Book object.
    *
    * @param bookList The list of Book objects
    */
    public static void printBookList(ArrayList<Book> bookList) {

        for (int i = 0; i < bookList.size(); i++) {
            Book currBook = bookList.get(i);

            System.out.println(currBook);

        }
    }

    /**
    * LAB 4: STUDENT MUST COMPLETE THIS METHOD
    *
    * Gets information about a book from console input, then adds that new
    * book to the list of books.
    *
    * @param bookList The list of Book objects
    */
    public static void addBook(ArrayList<Book> bookList) {
        Scanner console = new Scanner(System.in);
        System.out.println("Please provide information about the new book:");

        System.out.print("Enter the book's title: ");
        String title = console.nextLine();

        System.out.print("Enter the book's edition as an integer: ");
        int edition = Integer.parseInt(console.nextLine());


        System.out.print("Enter the book's author: ");
        String author = console.nextLine();

        Book inputtedBook = new Book(title, author, edition);
        bookList.add(inputtedBook);


    }

}
