/**
* Test program for Lab 3. Your code should pass all tests.
* You may find it helpful to comment out parts of this code while you are
* working on Lab 3. When you turn in your code for Lab 3, your PersonName
* and TextBook classes should pass all of the tests in this file.
*
* @author Christine Reilly
*/
import java.util.ArrayList;

public class Lab3Test {
    public static void main(String[] args) {
        // Create a Publisher object
        Publisher pub = new Publisher("Pearson", "Boston, MA",
                        "http://www.pearson.com");

        // Create some PersonName objects
        PersonName p1 = new PersonName("Grace", "Murray", "Hopper");
        PersonName p2 = new PersonName("Alan", null, "Turing");
        PersonName p3 = new PersonName("Ada", null, "Lovelace");
        PersonName p4 = new PersonName(); // calling default constructor
        PersonName p5 = new PersonName("Grace", "Murray", "Hopper");

        // ***** Test 1: Test the equals method of the PersonName class
        System.out.println("***** Starting Test 1 (PersonName equals method).....");
        if(p1.equals(p2)) {
            System.out.println("    FAIL: p1 and p2 should not be equal");
        }
        if(!p1.equals(p5)) {
            System.out.println("    FAIL: p1 and p5 should be equal");
        }
        if(!p4.equals(p4)) {
            System.out.println("    FAIL: p4 should be equal to itself");
        }
        System.out.println(".....Finished Test 1 *****\n");

        // ***** Test 2: Test the mutator and accessor methods of the PersonName class
        System.out.println("***** Starting Test 2 (PersonName mutators and accessors).....");
        p5.setFirstName("Edgar");
        p5.setMiddleName("F.");
        p5.setLastName("Codd");
        if(!p5.getFirstName().equals("Edgar") || !p5.getMiddleName().equals("F.")
            || !p5.getLastName().equals("Codd")) {
            System.out.println("    FAIL: problem with mutator or accessor method");
        }
        System.out.println(".....Finished Test 2 *****\n");

        System.out.println("***** Starting Test 3 (PersonName toString method).....");
        System.out.println("Should be: Alan Turing ----->  " + p2.toString());
        System.out.println("Should be: Grace Murray Hopper ----->  " + p1.toString());
        System.out.println(".....Finished Test 3 *****\n");



        // Create an ArrayList of PersonName objects, then add four persons
        // to the list
        ArrayList<PersonName> personList = new ArrayList<PersonName>();
        personList.add(p1);
        personList.add(p2);
        personList.add(p3);
        personList.add(p4);

        // Create another ArrayList of PersonName objects, then add four
        // persons to the list so that this is different than the first list
        ArrayList<PersonName> personList2 = new ArrayList<PersonName>();
        personList2.add(p1);
        personList2.add(p2);
        personList2.add(p3);
        personList2.add(p5);

        // Create an ArrayList of String objects, then add some keywords to the list
        ArrayList<String> kw = new ArrayList<String>();
        kw.add("Computer Progamming");
        kw.add("Famous Computer Scientists");

        // Create a TextBook object
        TextBook tb1 = new TextBook("CS Superstars",
                personList, 4, "987-0-321-54140-6", 567, "hard cover",
                pub, kw, "Picture of a person");

        // Create a second TextBook object that has the same values for all
        // data members as tb1.
        TextBook tb2 = new TextBook("CS Superstars",
                personList, 4, "987-0-321-54140-6", 567, "hard cover",
                pub, kw, "Picture of a person");

        // Create a third TextBook object that has some different data member
        // values as tb1 and tb2. Only difference is the ArrayList of PersonNames.
        TextBook tb3 = new TextBook("CS Superstars",
                personList2, 4, "987-0-321-54140-6", 567, "hard cover",
                pub, kw, "Picture of a person");

        // ****** Test 4: test the TextBook equals method
        System.out.println("***** Starting Test 4 (TextBook equals method).....");
        if(!(tb1.equals(tb2))) {
            System.out.println("    FAIL: tb1 and tb2 should be equal");
        }
        if(tb1.equals(tb3)) {
            System.out.println("    FAIL: tb1 and tb3 should not be equal");
        }
        System.out.println(".....Finished Test 4 *****");
        System.out.println();

        // ***** Test 5: Cannot change the author ArrayList object of tb1
        System.out.println("***** Starting Test 5 (changing author ArrayList).....");

        // Change the personList object. This should not alter the tb1 author object
        personList.add(new PersonName("Barbara", null, "Liskov"));
        if(personList.size() == tb1.getAuthor().size()) {
            System.out.println("    FAIL: tb1's author should not be altered");
        }

        // Change one of the PersonName objects. This should not alter the
        // PersonName objects in tb1.
        p1.setFirstName("Admiral");
        ArrayList<PersonName> names = tb1.getAuthor();
        for(int i = 0; i < names.size(); i++) {
            if(names.get(i).getFirstName().equals("Admiral")) {
                System.out.println("    FAIL: name inside tb1's author list should not be altered");
            }
        }

        // Get the author ArrayList object from tb1, then change it. This should not
        // alter the tb1 author ArrayList object.
        ArrayList<PersonName> authors = tb1.getAuthor();
        authors.get(0).setLastName("Knuth");
        if(authors.get(0).getLastName().equals(tb1.getAuthor().get(0).getLastName())) {
            System.out.println("    FAIL: tb1 author should not be changed");
        }

        System.out.println(".....Finished Test 5 *****");
        System.out.println();

        System.out.println("Printing the String returned by TextBook toString()");
        System.out.println(tb1.toString());

    }
}
