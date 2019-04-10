/**
* STARTER CODE FOR CS206 FALL 2017 PROGRAMMING ASSIGNMENT 1
*
* Represents a single location of a retail business and includes data about
* sales over the past twelve months.
*
* @author your name
*/
import java.util.Arrays;

public class RetailLocation {

    /** The identification number of this retail location */
    private int locationId;

    /** The mailing address of this retail location */
    private Address mailingAddress;

    /** The total sales per month for the past twleve months for this retail location */
    private double[] monthlySales;

    /**
    * Constructor initializes data members to parameter values.
    *
    * @param i The identification number of this retail location
    * @param a The mailing address of this retail location
    * @param m The total sales per month for the past twleve months for this retail location
    */
    public RetailLocation(int i, Address a, double[] m) {
        setLocationId(i);
        setMailingAddress(a);
        setMonthlySales(m);
    }

    /**
    * Default constructor initializes data members to default values
    */
    public RetailLocation() {

        Address defaultAdd = new Address();
        setLocationId(0);
        setMailingAddress(defaultAdd);
        setMonthlySales(new double[12]);
    }

    /**
    * Returns the identification number of this retail location.
    *
    * @return The identification number of this retail location
    */
    public int getLocationId() {
        return locationId;
    }

    /**
    * Returns a copy of the mailing address of this retail location.
    *
    * @return A copy of the mailing address of this retail location
    */
    public Address getMailingAddress() {
        Address copy = new  Address(mailingAddress.getStreet(),
                                    mailingAddress.getCity(),
                                    mailingAddress.getState(),
                                    mailingAddress.getZip());
        return copy;
    }

    /**
    * Copies the total sales per month for the past twleve months into the
    * parameter array.
    *
    * @param m An array to copy the total sales per month into. Array length must be 12.
    */
    public void getMonthlySales(double[] m) throws IllegalArgumentException {
        if (m.length != 12) {
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < monthlySales.length; i++) {
            m[i] = monthlySales[i];
        }

    }

    /**
    * Sets the identification number of this retail location.
    *
    * @param i The identification number of this retail location
    */
    public void setLocationId(int i) {
        locationId = i;
    }

    /**
    * Sets the mailing address of this retail location.
    *
    * @param a The mailing address of this retail location
    */
    public void setMailingAddress(Address a) {
        mailingAddress = a;
    }

    /**
    * Sets the monthly sales by copying the total sales per month for the past
    * twleve months from the parameter array.
    *
    * @param m An array to copy the total sales per month from. Array length must be 12.
    */
    public void setMonthlySales(double[] m) throws IllegalArgumentException {
        if (m.length != 12) {
            throw new IllegalArgumentException();
        }
        double[] copy = new double[12];
        for (int i = 0; i < m.length; i++) {
            copy[i] = m[i];
        }

        monthlySales = copy;
    }

    /**
     * Returns a string representation of this RetailLocation object in the form:
     * locationId; mailingAddress; list of monthly sales
     *
     * @return A string representation of this RetailLocation object
     */
    public String toString() {
        return  "Location ID: " + locationId +
                ", Mailing Address: " + mailingAddress +
                ", Monthly Sales: " + Arrays.toString(monthlySales);
    }

    /**
    * Returns true if the parameter object is the equal to this Address
    * object. Equality is defined as having the same values for all data
    * members.
    *
    * @param o The object to compare this object with
    * @return True if the two objects are the same
    */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RetailLocation obj = (RetailLocation) o;

        if (locationId != obj.locationId){
            return false;
        }
        if (!mailingAddress.equals(obj.getMailingAddress())) {
            return false;
        }
        double[] objMonthlySales = new double[12];
        obj.getMonthlySales(objMonthlySales);
        if (Arrays.equals(monthlySales, objMonthlySales)){
            return false;
        }

        return true;
    }

    /**
    * Returns the total sales from the past twelve months. This is the sum
    * of the values in the sales per month data member.
    *
    * @return The total sales from the past twelve months
    */
    public double getTotalSales() {
        double totalSales = 0;
        for (int i = 0; i < monthlySales.length; i++) {
            totalSales += monthlySales[i];
        }
        return totalSales;
    }

    /**
    * Returns the average sales over the past twelve months.
    *
    * @return The average sales over the past twelve months
    */
    public double getAverageSales() {
        double totalSales = getTotalSales();
        return totalSales / 12;
    }

    /**
    * Updates the monthly sales array. Drops the oldest month's data and
    * adds the newest month's data that is passed as the parameter. In order
    * to maintain the oldest to newest order of the array, the values in the
    * array are shifted between when the oldest is dropped and the newest is
    * added.
    *
    * @param d Total sales for the new month
    */
    public void updateMonthlySales(double d) {
        for (int i = 0; i < monthlySales.length - 1; i++) {
            monthlySales[i] = monthlySales[i + 1];
        }
        monthlySales[monthlySales.length -1] = d;
    }
}
