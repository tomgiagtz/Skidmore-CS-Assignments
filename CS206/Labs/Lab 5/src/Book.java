/**
* LAB 5: DO NOT MODIFY THIS FILE
*
* This class is a simple representation of a book. The data members are
* fairly simple in order to make this class easier to use as an example.
*
* @author Christine Reilly
*/
public class Book {
    /** The title of this book */
    private String title;

    /** The author of this book */
    private String author;

    /** The edition of this book */
    private int edition;

    /**
    * Initializes the data members of this Book object.
    *
    * @param t The title of this book
    * @param a The author of this book
    * @param e The edition of this book
    */
    public Book(String t, String a, int e) {
        setTitle(t);
        setAuthor(a);
        setEdition(e);
    }

    /**
    * Initializes the data members of this Book object to default values.
    */
    public Book() {
        this("Book Title", "Any Person", 1);
    }

    /**
    * Retrieves the title of this Book.
    *
    * @return The title of this Book
    */
    public String getTitle() {
        return title;
    }

    /**
    * Retrieves the author of this Book.
    *
    * @return The author of this Book.
    */
    public String getAuthor() {
        return author;
    }

    /**
    * Retrives the edition of this Book.
    *
    * @return The edition of this Book
    */
    public int getEdition() {
        return edition;
    }

    /**
    * Sets the title of this Book.
    *
    * @param t The title of this Book
    */
    public void setTitle(String t) {
        title = t;
    }

    /**
    * Sets the author of this Book.
    *
    * @param a The author of this Book.
    */
    public void setAuthor(String a) {
        author = a;
    }

    /**
    * Sets the edition of this Book.
    *
    * @param e The edition of this Book
    */
    public void setEdition(int e) {
        edition = e;
    }

    /**
    * Returns a string representation of this Book object.
    *
    * @return A string representation of this Book object.
    */
    public String toString() {
        return title + ", Edition " + edition + " by " + author;
    }

    /**
    * Returns true if the parameter object is equal to this Book. Equality is
    * defined as having equal values for all data members.
    *
    * @param obj The object to compare this object with
    * @return True if the two objects are the same
    */
    public boolean equals(Object obj) {

        // Use the instanceof operator to check if obj is a TextBook object
        if(obj instanceof Book) {
            // Cast obj to Book type
            Book b = (Book)obj;

            // Compare the parameter object to this object
            if(title.equals(b.getTitle()) && author.equals(b.getAuthor())
                && edition == b.getEdition()) {
                return true;
            }
        }

        // If this line is reached, the two objects are not equal
        return false;
    }
}
