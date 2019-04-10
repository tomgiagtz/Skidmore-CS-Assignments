/**
* Represents a mailing address in the United States.
*
* This class is given to you for use in CS206 Programming Assignment 1.
* DO NOT MODIFY THIS CLASS!
* DO NOT MAKE ANY CHANGES TO THIS FILE!
*
* @author Christine Reilly
*/

public class Address {

    /** The street address, such as 123 Main St. */
    private String street;

    /** City where the address is located */
    private String city;

    /** State where the address is located. Should be 2 characters. */
    private String state;

    /** Mailing zip code for the address. */
    private String zip;

    /**
    * Constructor initializes the data members to the parameter values.
    *
    * @param s The street address
    * @param c City where the address is located
    * @param t State where the address is located
    * @param z Mailing zip code for the address
    */
    public Address(String s, String c, String t, String z) {
        setStreet(s);
        setCity(c);
        setState(t);
        setZip(z);
    }

    /**
    * Default constructor initializes the data members to default values.
    */
    public Address() {
        this(null,null,null,null);
    }

    /**
    * Returns the street address.
    *
    * @return The street address
    */
    public String getStreet() {
        return street;
    }

    /**
    * Returns the city where the address is located.
    *
    * @return The city where the address is located.
    */
    public String getCity() {
        return city;
    }

    /**
    * Returns the state where the address is located.
    *
    * @return The state where the address is located.
    */
    public String getState() {
        return state;
    }

    /**
    * Returns the zip code for the address.
    *
    * @return The zip code for the address.
    */
    public String getZip() {
        return zip;
    }

    /**
    * Sets the street address.
    *
    * @param s The street address
    */
    public void setStreet(String s) {
        street = s;
    }

    /**
    * Sets the city where the address is located.
    *
    * @param c The city where the address is located.
    */
    public void setCity(String c) {
        city = c;
    }

    /**
    * Sets the state where the address is located.
    *
    * @param s The two character state where the address is located.
    */
    public void setState(String s) throws IllegalArgumentException {
        if(s.length() != 2) {
            throw new IllegalArgumentException("Parameter s must be 2 characters");
        }
        state = s;
    }

    /**
    * Sets the zip code for the address.
    *
    * @param z Represents the 5 digit zip code as a String.
    */
    public void setZip(String z) throws IllegalArgumentException {
        if(z.length() != 5) {
            throw new IllegalArgumentException("Parameter z must be 5 characters");
        }
        zip = z;
    }

    /**
    * Returns a string representation of this address object in the form:
    * street1; street2; city, state zip
    * If the street2 field is null, it is not included.
    *
    * @return A string representation of this address object
    */
    public String toString() {
        return street + "; " + city + ", " + state + " " + zip;
    }

    /**
    * Returns true if the parameter object is the equal to this Address
    * object. Equality is defined as having the same values for all data
    * members.
    *
    * @param obj The object to compare this object with
    * @return True if the two objects are the same
    */
    public boolean equals(Object obj) {

        // Use the instanceof operator to check if obj is an Address object
        if(obj instanceof Address) {
            // Create a Publisher object and cast obj to Publisher type
            Address a = (Address)obj;

            // Compare the parameter object to this object
            // Use the String equals method when comparing String objects!
            if(street.equals(a.getStreet()) && city.equals(a.getCity())
                && state.equals(a.getState()) && zip.equals(a.getZip())) {
                return true;
            }
        }
        // obj is not a Publisher object or the two objects have different
        // values for the data members.
        return false;
    }

    /**
    * Returns a new Address object that is an exact copy of this object.
    *
    * @return A new Address object that is an exact copy of this object
    */
    public Address copyOf() {
        return new Address(street, city, state, zip);

    }

}
