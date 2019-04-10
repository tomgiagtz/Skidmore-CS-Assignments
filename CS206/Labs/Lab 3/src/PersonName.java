/**
* This class represents a name of a person.
*
* For Lab 3, students are provided with the skeleton of this class.
* Student must complete all methods, as indicated in this code.
* Students may only add code where indicated. The data members and method
* declarations should not be altered.
*
* @author Tom Giagtzoglou
*/
public class PersonName {
	/** The person's first name */
	private String firstName;

	/** The person's middle name. null if person has no middle name */
	private String middleName;

	/** The person's last name */
	private String lastName;

	/**
	* Parameterized constructor. Initializes data members to the
	* parameter values.
	*
	* @param f The person's first name
	* @param m The person's middle name
	* @param l The person's last name
	*/
	public PersonName(String f, String m, String l) {
		setFirstName(f);
		setMiddleName(m);
		setLastName(l);

	}

    /**
     * Copys a new PersonName object from a given one.
     * @param copy PersonName object to be copied
     */

	public PersonName(PersonName copy) {
	    setFirstName(copy.firstName);
	    setMiddleName(copy.middleName);
	    setLastName(copy.lastName);
    }

	/**
	* Default constructor. Initialzes data members to default values
	*/
	public PersonName() {
		setFirstName("Elliot");
		setLastName("Alderson");

	}

	/**
	* Retrieves the person's first name
	*
	* @return The person's first name
	*/
	public String getFirstName() {
		return firstName;
	}

	/**
	* Retrieves the person's middle name
	*
	* @return The person's middle name
	*/
	public String getMiddleName() {
		return middleName;
	}

	/**
	* Retrieves the person's last name
	*
	* @return The person's last name
	*/
	public String getLastName() {
		return lastName;
	}

	/**
	* Sets the person's first name
	*
	* @param f The person's first name
	*/
	public void setFirstName(String f) {
		firstName = f;
	}

	/**
	* Sets the person's middle name
	*
	* @param m The person's middle name
	*/
	public void setMiddleName(String m) {
		middleName = m;
	}

	/**
	* Sets the person's last name
	*
	* @param l The person's last name
	*/
	public void setLastName(String l) {
		lastName = l;
	}

	/**
	* Returns a string representation of this PersonName object.
	* If the middle name is null, does not include the middle name.
	*
	* @return A string representation of this PersonName object.
	*/
	public String toString() {
		String mNameString;
		if (middleName == null) {
			mNameString = "";
		} else {
			mNameString = middleName + " ";
		}
		return  firstName + " " +
				mNameString +
				lastName;

	}

	/**
	* Returns true if the parameter object is equal to this PersonName
	* object. Equality is defined as having the same values for every
	* data member.
	*
	* @param obj The object to compare this object with
	* @return True if the two objects are the same
	*/
	public boolean equals(Object obj) {
		//handle if they exist in the same place in memory
		if (this == obj) {return true;}
		//handle if obj is null
		if (obj == null) {return false;}

		//ensure obj is of type PersonName, then cast it
		if (obj.getClass()  !=  this.getClass()) {return false;}

		PersonName objName = (PersonName) obj;

		if (middleName == null && objName.getMiddleName() == null) {

            return this.firstName.equals(objName.getFirstName()) &&
                    this.lastName.equals(objName.getLastName());

        } else {
            return this.firstName.equals(objName.getFirstName()) &&
                    this.middleName.equals(objName.getMiddleName()) &&
                    this.lastName.equals(objName.getLastName());
        }


	}

}
