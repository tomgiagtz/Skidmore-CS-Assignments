import com.sun.javaws.exceptions.InvalidArgumentException;
import com.sun.org.apache.regexp.internal.RE;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class InteractiveProgram {
    public static void main(String[] args) throws FileNotFoundException{
        String inFileName = "";

        welcomeText();

        ArrayList<RetailLocation> locations = new ArrayList<RetailLocation>();
        inFileName = getLocationsFromInput(locations);

        int menuChoice = 0;
        while(menuChoice != 7) {
            menuChoice = presentMenu();
            switch (menuChoice) {
                case 1: listLocations(locations);
                    break;
                case 2: listMonthlySales(locations);
                    break;
                case 3: getLocationWithMaxTotalSales(locations);
                    break;
                case 4: getLocationWithMaxAverageSales(locations);
                    break;
                case 5: removeLocation(locations);
                    break;
                case 6: updateMonthlySales(locations);
                    break;
            }
        }
        writeToFile(locations, inFileName);

        System.out.println("All changes have been saved.");
        System.out.println("Thank you for using the Retail Location Evaluation Program!");




    }

    /**
     * Prompts user for a file name, then imports Retail Location data from that file.
     * @param locations ArrayList that input will be added to.
     * @return A string containing the name of the file used for input.
     */
    private static String getLocationsFromInput(ArrayList<RetailLocation> locations) throws FileNotFoundException{

        //required vars for the method
        File f;
        Scanner console = new Scanner(System.in);
        Scanner input;
        Scanner line;
        String inFileName;
        String inLine;

        Address currAdd;
        String city;
        String state;
        String zip;
        String street;

        //get input data from use via console input
        System.out.println("Please enter the path of the Data File: ");
        inFileName = console.nextLine();
        f = new File(inFileName);

        while(!f.canRead()) {
            System.out.println("Error: File " + inFileName + " not found. Try Again.");
            System.out.println("Please enter the path of the Data File: ");
            inFileName = console.nextLine();
            f = new File(inFileName);

        }

        input = new Scanner(f);

        //import data from input file

        int numLocations = 0;
        while(input.hasNextLine()){
            inLine = input.nextLine();
            line = new Scanner(inLine);

            int locationId;
            double[] monthlySales = new double[12];

            locationId = line.nextInt();
            for (int i = 0; i < monthlySales.length; i++) {
                monthlySales[i] = line.nextDouble();
            }
            //read data for Address
            city = line.next();

            //handling for case that the city has a space within ie. 'Carson City'
                String temp = line.next();
                while (temp.length() != 2) {
                    city += temp + " ";
                    temp = line.next();
                }

            state = temp;
            zip = line.next();

            street = "";
            while(line.hasNext()) {
                street += line.next() + (line.hasNext() ? " " : "");
            }

            //use data to create RetailLocations and add them to the locations list.
            currAdd = new Address(street, city, state, zip);

            RetailLocation inputLocation = new RetailLocation(locationId, currAdd, monthlySales);
            locations.add(inputLocation);

            numLocations++;
        }

        System.out.println("Added " + numLocations + " retail location(s) from the file " + inFileName);
        return inFileName;
    }

    private static void welcomeText() {
        System.out.println("*********************************************************");
        System.out.println("* 	Welcome to the Retail Location Evaluation Program!	*");
        System.out.println("*														*");
        System.out.println("*  Follow the prompts to input a file before evaluation *");
        System.out.println("* 						can begin.						*");
        System.out.println("*********************************************************");
    }
		/**
		* Presents menu to user 
		*
		* @return User's choice as an int 
		*/
    private static int presentMenu() {
        Scanner console = new Scanner(System.in);

        System.out.println("*********************************************************");
        System.out.println("*	Main Menu:											*");
        System.out.println("*	1:	List all locations								*");
        System.out.println("*	2:	List monthly sales for a locations 				*");
        System.out.println("* 	3:  List the location id with the highest sales for	*");
        System.out.println("*		the year										*");
        System.out.println("* 	4:  List the location id with the highest average 	*");
        System.out.println("*		sales for the year								*");
        System.out.println("*	5: Delete a location 								*");
        System.out.println("*	6: Update the monthly sales for a location			*");
        System.out.println("*	7: Exit the Program									*");
        System.out.println("*********************************************************");

        System.out.print("Please choose a menu option: ");


        int selection = 0;
        selection = Integer.parseInt(console.nextLine());
        while (selection > 7 || selection < 1) {
            selection = Integer.parseInt(console.nextLine());
            System.out.println("Error: Please enter a number from 1 to 7");
        }
        System.out.println();
        return selection;


    }

    /**
     * Prints locations in the locations array list to console.
     * @param locations list to be printed to console.
     */
    private static void listLocations(ArrayList<RetailLocation> locations) {
        System.out.println("*********************************************************");
        System.out.println("*	                Printing locations					*");
        System.out.println("*********************************************************");
        for (int i = 0; i < locations.size(); i++) {
            System.out.println(locations.get(i));
        }
    }


    /**
     * Given a locations list and a unique id, this method will find the location with that id.
     * @param locations Locations array list
     * @return Retail Location corresponding to the id
     *
     */
    private static RetailLocation getLocationById(ArrayList<RetailLocation> locations) {
        Scanner console = new Scanner(System.in);
        boolean hasID = false;
        RetailLocation foundLoc = locations.get(0);
        while(!hasID) {
            System.out.print("Please enter a location id: ");
            int id = console.nextInt();
            System.out.println();
            for (int i = 0; i < locations.size(); i++) {
                RetailLocation currLoc = locations.get(i);
                if (currLoc.getLocationId() == id) {
                    foundLoc = currLoc;
                    hasID = true;
                    break;
                }
            } if(!hasID) {
                System.out.println("Could not find a location with that ID. Please Try Again.");
            }
        }
        System.out.println("Found the location at: " + foundLoc.getMailingAddress().toString());
        return foundLoc;
    }

    /**
     * Lists monthly sales for a given location
     * @param locations contains locations data
     */
    private static void listMonthlySales(ArrayList<RetailLocation> locations) {
        Scanner console = new Scanner(System.in);

        System.out.println("*********************************************************");
        System.out.println("*	                Monthly Sales   					*");
        System.out.println("*********************************************************");

        System.out.println();
        RetailLocation currLoc = getLocationById(locations);
        double montlySales[] = new double[12];
        currLoc.getMonthlySales(montlySales);

        System.out.println();
        System.out.println("The monthly sales for location #" + currLoc.getLocationId() + " are: ");
        System.out.println(Arrays.toString(montlySales));


    }

    private static void getLocationWithMaxTotalSales(ArrayList<RetailLocation> locations) {
        Scanner console = new Scanner(System.in);

        System.out.println("*********************************************************");
        System.out.println("*	                Max Total Sales   					*");
        System.out.println("*********************************************************");

        System.out.println();

        RetailLocation maxTotalSalesLoc = locations.get(0);
        double currMaxSales = maxTotalSalesLoc.getTotalSales();

        for (int i = 0; i < locations.size(); i++) {
            RetailLocation currLoc = locations.get(i);
            double currTotSales = currLoc.getTotalSales();

            if (currTotSales > currMaxSales) {
                maxTotalSalesLoc = currLoc;
                currMaxSales = currTotSales;
            }
        }

        System.out.println("The location with the highest total sales this year is location #"+ maxTotalSalesLoc.getLocationId() + ".");
        System.out.println("Location #"+ maxTotalSalesLoc.getLocationId() + "'s total sales are: $" + currMaxSales);
        System.out.println();


    }

    private static void getLocationWithMaxAverageSales(ArrayList<RetailLocation> locations) {
        Scanner console = new Scanner(System.in);

        System.out.println("*********************************************************");
        System.out.println("*	                Max Average Sales  					*");
        System.out.println("*********************************************************");

        System.out.println();
        RetailLocation maxAvgSalesLoc = locations.get(0);
        double currMaxAvgSales = maxAvgSalesLoc.getAverageSales();

        for (int i = 0; i < locations.size(); i++) {
            RetailLocation currLoc = locations.get(i);
            double currAvgSales = currLoc.getAverageSales();

            if (currAvgSales > currMaxAvgSales) {
                maxAvgSalesLoc = currLoc;
                currMaxAvgSales = currAvgSales;
            }
        }

        System.out.println("The location with the highest average sales this year is location #"+ maxAvgSalesLoc.getLocationId() + ".");
        System.out.println("Location #"+ maxAvgSalesLoc.getLocationId() + "'s average sales are: $" + currMaxAvgSales);
        System.out.println();

    }

    private static void removeLocation(ArrayList<RetailLocation> locations) {
        Scanner console = new Scanner(System.in);

        System.out.println("*********************************************************");
        System.out.println("*	                Remove Location   					*");
        System.out.println("*********************************************************");

        System.out.println();
        RetailLocation toRemove = getLocationById(locations);
        System.out.println("Removing location #"+ toRemove.getLocationId() + ".");
        locations.remove(toRemove);

    }

    private static void updateMonthlySales(ArrayList<RetailLocation> locations){
        Scanner console = new Scanner(System.in);

        System.out.println("*********************************************************");
        System.out.println("*	             Update Monthly Sales		    		*");
        System.out.println("*********************************************************");

        System.out.println();
        RetailLocation toUpdate = getLocationById(locations);
        double salesAmount = 0;

        System.out.println("Please enter the sales amount for location #" + toUpdate.getLocationId() + ":");
        salesAmount = console.nextDouble();
        toUpdate.updateMonthlySales(salesAmount);
        System.out.println("Sales for location #" + toUpdate.getLocationId() + " have been updated.");
        System.out.println();

    }
    private static void writeToFile(ArrayList<RetailLocation> locations, String writeFileName) throws FileNotFoundException{
        File f = new File(writeFileName);
        if(!f.canWrite()) throw new FileNotFoundException();

        PrintStream writeUpdates = new PrintStream(f);


        for (int i = 0; i < locations.size(); i++) {
            RetailLocation currLoc = locations.get(i);

            int id = currLoc.getLocationId();
            double[] monthlySales = new double[12];
            currLoc.getMonthlySales(monthlySales);
            Address currAdd = currLoc.getMailingAddress();
            String city = currAdd.getCity();
            String state = currAdd.getState();
            String zip = currAdd.getZip();
            String street = currAdd.getStreet();

            writeUpdates.print(id + " ");
            for (int j = 0; j < monthlySales.length; j++) {
                writeUpdates.print(monthlySales[j] + " ");
            }
            writeUpdates.print( city + " " +
                                state + " " +
                                zip + " " +
                                street + " ");
            writeUpdates.println();
        }

    }

}
