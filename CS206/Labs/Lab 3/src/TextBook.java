/**
* This class represents a textbook object.
* This is the version completed after class on September 18, with changes
* to the author data member and methods that use the author data member.
*
* LAB 3: The author data member type has been updated to be an ArrayList
* of PersonName objects. You need to update the methods to use this new
* type for author.
*
* LAB 3: All of the methods that you need to update are identified in the method
* header comment with the words "LAB 3" and a description of the changes you
* need to make. The test program will also help to guide you in making the
* proper changes.
*
* @author Tom Giagtzoglou
*/

// We are using the ArrayList class in this program.
// ArrayList is in the java.util package.
// In order to use this class, we must import it.

import java.util.ArrayList;

public class TextBook {
    // Data Members (the properties of a TextBook)

    /** The title of this book */
    private String title;

    /** The author: updated to be an ArrayList of PersonName objects */
    private ArrayList<PersonName> author;

    /** The edition of this book */
    private int edition;

    /** The ISBN code of this book */
    private String isbn;

    /** The number of pages contained in this book */
    private int numberOfPages;

    /** The format of this book (such as hard cover, paperback, electronic) */
    private String format;

    /** The publisher of this book */
    private Publisher publisher;

    /** The keywords that describe this book */
    private ArrayList<String> keywords;

    /** A description of this book's cover design */
    private String coverDesign;

    /**
    * Initializes the data members of the TextBook object.
    *
    * @param t The title of this TextBook
    * @param a The author of this textbook.
    * @param e The edition of this TextBook
    * @param i The ISBN code of this textbook.
    * @param n The number of pages in this textbook.
    * @param f The format of this textbook.
    * @param p The publisher of this textbook.
    * @param k The keywords for this textbook.
    * @param c The cover design for this textbook.
    */
    public TextBook(String t, ArrayList<PersonName> a, int e, String i, int n, String f,
                        Publisher p, ArrayList<String> k, String c)
    {
        // Call the mutator methods to set the data members
        setTitle(t);
        setAuthor(a);
        setEdition(e);
        setISBN(i);
        setNumberOfPages(n);
        setFormat(f);
        setPublisher(p);
        setKeywords(k);
        setCoverDesign(c);
    }

    /**
    * Initializes the data members of the TextBook object to default values.
    *
    * LAB 3: Alter this constructor to call the parameterized constructor using
    * an ArrayList<PersonName> for the author.
    */
    public TextBook() {
        this("Java Book", new ArrayList<PersonName>(), 1, "123-456-789-1011", 100, "paperback",
                new Publisher(), new ArrayList<String>(), "blank");
        // In order to provide a default Publisher object, we call the Publisher
        // class default constructor from within the call to the this
        // TextBook object's default constructor.
    }

    // Accessor methods

    /**
    * Returns the title of this TextBook.
    *
    * @return The title of this TextBook
    */
    public String getTitle() {
        return title;
    }

    /**
    * Retrieves the author of the textbook object.
    *
    * LAB 3: alter this method to use the ArrayList<PersonName> for the
    * author data member. Return a copy of the author data member so that
    * you do not allow this object's author to be altered outside of this class.
    *
    * @return The author of this textbook.
    */
    public ArrayList<PersonName> getAuthor() {
        ArrayList<PersonName> authorCopy = new ArrayList<PersonName>();


        for (int i = 0; i < author.size() ; i++) {
            PersonName copy = new PersonName(author.get(i));
            authorCopy.add(copy);
        }


        return authorCopy;

    }

    /**
    * Retrieves the ISBN code of this textbook object.
    *
    * @return The ISBN code of this textbook.
    */
    public String getISBN() {
        return isbn;
    }

    /**
    * Retrieves the number of pages in this textbook object.
    *
    * @return The number of pages in this textbook.
    */
    public int getNumberOfPages() {
        return numberOfPages;
    }

    /**
    * Retrieves the format of this textbook object.
    *
    * @return The format of this textbook.
    */
    public String getFormat() {
        return format;
    }

    /**
    * Retrieves the publisher of this textbook object. Returns a new Publisher
    * object in order to prevent any other class from altering the
    * Publisher object that belongs to this object.
    *
    * @return The publisher of this textbook.
    */
    public Publisher getPublisher() {
        return new Publisher(publisher.getName(), publisher.getAddress(),
                                publisher.getWebsiteURL());
    }

    /**
    * Retrieves the keywords for this textbook object. Returns a new
    * ArrayList object in order to prevent any other class from altering
    * the keywords of this object.
    *
    * @return The keywords for this textbook.
    */
    public ArrayList<String> getKeywords() {
        ArrayList<String> kw = new ArrayList<String>();

        // Copy the keywords from this object's ArrayList to the new ArrayList
        for(int i = 0; i < keywords.size(); i++) {
            kw.add(keywords.get(i));
        }
        return kw;
    }

    /**
    * Retrieves the cover design for this textbook object.
    *
    * @return The cover design for this textbook.
    */
    public String getCoverDesign() {
        return coverDesign;
    }

    /**
    * Returns the edition of this TextBook.
    *
    * @return The edition of this TextBook
    */
    public int getEdition() {
        return edition;
    }

    // Mutator Methods

    /**
    * Sets the title of this TextBook.
    *
    * @param t The title of this TextBook
    */
    public void setTitle(String t){
        title = t;
    }

    /**
    * Sets the author of the textbook object.
    *
    * LAB 3: alter this method to use the ArrayList<PersonName> for the
    * author data member. Create a new ArrayList<PersonName> object for the
    * author data member, and copy the parameter into the new data member.
    * By creating a new object and copying the parameter, you make sure that
    * only this class can alter the author data member.
    *
    * @param a The author of this textbook.
    */
    public void setAuthor(ArrayList<PersonName> a) {
        author = new ArrayList<PersonName>();

        for (int i = 0; i < a.size() ; i++) {
            PersonName copy = new PersonName(a.get(i));
            author.add(copy);
        }
    }

    /**
    * Sets the edition of this TextBook.
    *
    * @param e The edition of this TextBook
    */
    public void setEdition(int e) {
        edition = e;
    }

    /**
    * Sets the ISBN code of this textbook object.
    *
    * @param i The ISBN code of this textbook.
    */
    public void setISBN(String i) {
        isbn = i;
    }

    /**
    * Sets the number of pages in this textbook object.
    *
    * @param n The number of pages in this textbook.
    */
    public void setNumberOfPages(int n) {
        numberOfPages = n;
    }

    /**
    * Sets the format of this textbook object.
    *
    * @param f The format of this textbook.
    */
    public void setFormat(String f) {
        format = f;
    }

    /**
    * Sets the publisher of this textbook object.
    *
    * @param p The publisher of this textbook.
    */
    public void setPublisher(Publisher p) {
        // Create a new Publisher object
        publisher = new Publisher(p.getName(), p.getAddress(), p.getWebsiteURL());
    }

    /**
    * Sets the keywords for this textbook object.
    *
    * @param k The keywords for this textbook.
    */
    public void setKeywords(ArrayList<String> k) {
        // Create a new keywords object
        keywords = new ArrayList<String>();

        // Copy the items from parameter k into keywords
        for(int i = 0; i < k.size(); i++) {
            keywords.add(k.get(i));
        }
    }

    /**
    * Sets the cover design for this textbook object.
    *
    * @param c The cover design for this textbook.
    */
    public void setCoverDesign(String c) {
        coverDesign = c;
    }

    /**
    * Returns a string representation of this TextBook object.
    * Note that \n is a special character for end of line.
    *
    * LAB 3: alter this method to use the ArrayList<PersonName> for the
    * author data member. The toString method should iterate through the
    * PersonName objects in the ArrayList and create a string representation
    * of each PersonName object.
    *
    * @return A string representation of this TextBook object.
    */
    public String toString() {
        String book =  title + ", Edition " + edition + "(" + isbn + ")" + " by ";

        book += author.get(0);
        for (int i = 1; i < author.size() ; i++) {
            book += ", " + author.get(i);
        }


        book += "; " + numberOfPages + " pages" +
                "\n    Publisher: " + publisher.toString() +
                "\n" + "    Keywords" + keywords;

        // loop through keywords ArrayList
        book += keywords.get(0);
        for(int i = 1; i < keywords.size(); i++) {
            book += "; " + keywords.get(i);
        }
        book += "\n    Format: " + format + "; Cover: " + coverDesign;

        return book;
    }

    /**
    * Returns true if the parameter object is the equal to this TextBook
    * object. Equality is defined as having the same values for all data
    * members.
    *
    * LAB 3: alter this method to use the ArrayList<PersonName> for the
    * author data member. Two author lists are equal if they hold the same
    * number of object and have objects with the same values. The objects
    * do not need to be in the same order in the two author lists.
    *
    * @param obj The object to compare this object with
    * @return True if the two objects are the same
    */
    public boolean equals(Object obj) {

        // Use the instanceof operator to check if obj is a TextBook object
        if(obj instanceof TextBook) {
            // Create a TextBook object and cast obj to TextBook type
            TextBook t = (TextBook)obj;

            // Compare the parameter object to this object
            // Use the String equals method when comparing String objects!
            if(title.equals(t.getTitle()) && author.size() == t.getAuthor().size()
                && edition == t.getEdition() && isbn.equals(t.getISBN())
                && numberOfPages == t.getNumberOfPages() && format.equals(t.getFormat())
                && publisher.equals(t.getPublisher()) && coverDesign.equals(t.getCoverDesign())
                && keywords.size() == t.getKeywords().size())
            {
                // Compare the ArrayList of keyword strings. The ArrayLists
                // must contain the same strings, but the strings do not need
                // to appear in the same order in both ArrayLists
                // Initialize alCompare to true. If any one of the keywords
                // of this TextBook object is not in the parameter object, then
                // alCompare will be set to false.
                boolean alCompare = true;
                //System.out.println("checking "+ author.size() + " authors");
                for (int i = 0; i < t.getAuthor().size(); i++) {
                    if (! (t.getAuthor().contains(author.get(i))) ) {
                        //System.out.println("false!");
                        alCompare = false;

                    } else {
                        //System.out.println("true!");
                    }
                }


                for (int i = 0; i < keywords.size(); i++) {
                    if(!(t.getKeywords().contains(keywords.get(i)))) {
                        alCompare = false;

                    }
                }
                return alCompare;
            } else {
                // The two TextBook objects are not equal
                return false;
            }

        } else {
            // obj is not a TextBook object
            return false;
        }
    }

}
