/**
* This class represents basic information about a publishing business.
*
* This class is provided for your use in Lab 3.
* DO NOT MAKE ANY CHANGES TO THIS FILE!
*
* @author Christine Reilly
*/
public class Publisher {
    /** The name of this publisher */
    private String name;

    /** The mailing address of this publisher */
    private String address;
    // Note: we could have an Address class instead of a String
    // but we will use a String to keep the example more simple

    /** The URL for this publisher's website */
    private String websiteURL;

    /**
    * Parameterized constructor. Initializes the data members using the
    * parameter values.
    *
    * @param n The name of the publisher
    * @param a The mailing address of the publisher
    * @param w The URL for this publisher's website
    */
    public Publisher(String n, String a, String w) {
        setName(n);
        setAddress(a);
        setWebsiteURL(w);
    }

    /**
    * Default constructor initializes the data members to default values.
    */
    public Publisher() {
        this("publisher name", "123 Main St.; Any City, NY, USA",
            "http://www.google.com");
    }



    /**
    * Retrieves the name of the publisher
    *
    * @return The name of the publisher
    */
    public String getName() {
        return name;
    }

    /**
    * Retrieves the address of the publisher
    *
    * @return The address of the publisher
    */
    public String getAddress() {
        return address;
    }

    /**
    * Retrieves the website URL of the publisher
    *
    * @return The website URL of the publisher
    */
    public String getWebsiteURL() {
        return websiteURL;
    }

    /**
    * Sets the name of the publisher
    *
    * @param n The name of the publisher
    */
    public void setName(String n) {
        name = n;
    }

    /**
    * Sets the address of the publisher
    *
    * @param a The address of the publisher
    */
    public void setAddress(String a) {
        address = a;
    }

    /**
    * Sets the website URL of the publisher
    *
    * @param w The website URL of the publisher
    */
    public void setWebsiteURL(String w) {
        websiteURL = w;
    }

    /**
    * Returns a string representation of this publisher object in the form:
    * name; address; websiteURL.
    * Because this overrides the toString method that is inherited from the
    * Object class, the method header must be exactly the same as the method
    * header from the toString method in the Object class.
    *
    * @return A string representation of this publisher object.
    */
    public String toString() {
        return name + "; " + address + "; " + websiteURL;
    }

    /**
    * Returns true if the parameter object is the equal to this Publisher
    * object. Equality is defined as having the same values for all data
    * members.
    * Because this overrides the equals method that is inherited from the
    * Object class, the method header must be exactly the same as the method
    * header from the equals method in the Object class.
    *
    * @param obj The object to compare this object with
    * @return True if the two objects are the same
    */
    public boolean equals(Object obj) {

        // Use the instanceof operator to check if obj is a Publisher object
        if(obj instanceof Publisher) {
            // Create a Publisher object and cast obj to Publisher type
            Publisher p = (Publisher)obj;

            // Compare the parameter object to this object
            // Use the String equals method when comparing String objects!
            if(name.equals(p.getName()) && address.equals(p.getAddress())
                && websiteURL.equals(p.getWebsiteURL())) {
                    return true;
            } else {
                // The two publisher objects are not equal
                return false;
            }

        } else {
            // obj is not a Publisher object
            return false;
        }

    }


}
