/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * This class contains the list of records importing them from the specified
 * .csv file. After importing, 
 * @author vasug
 */
public class Project {
    private ArrayList<Record> records = new ArrayList<>();
    private ArrayList<String> assessmentClasses = new ArrayList<>();

    public List<String> getAssessmentClasses(){
        return assessmentClasses;
    }
    /**
     * Prompts the user for the file name and returns back the name. 
     * If no file name is specified, the default file is chosen.
     * @return File name 
     */
    public String getFileName() {
        Scanner console = new Scanner(System.in);  
        System.out.print("CSV filename [press enter for default file]: ");
        String fileName = console.nextLine();
        
        // When user does not enter anything, use default file name
        if (fileName.equals("")){
            return "Property_Assessment_Data_2020.csv";
        }
        return fileName;
    }
    /**
     * Getting the String value from the Scanner object
     * @param csvReader Scanner object
     * @return String value if the field has a valid string value; 
     * @return null if the field is empty
     */
    public String getString(Scanner csvReader){
        String tmpStr = csvReader.next().trim();
        if (tmpStr.isEmpty()){
            return null;         
        }
        return tmpStr;
    }    
    /**
     * Getting the Long value from the Scanner object
     * @param csvReader Scanner object
     * @return Long value if the field has a valid long value; 
     * @return null if the field does not have a long value
     */
    public Long getLong(Scanner csvReader){
        if (csvReader.hasNextLong()){
            return (csvReader.nextLong());
        }
        csvReader.next(); // Ignoring the value
        return null; 
    }
    /**
     * Getting the Integer value from the Scanner object
     * @param csvReader Scanner object
     * @return Integer value if the field has a valid Integer value;
     * @return null if the field does not have a long value
     */ 
    public Integer getInt(Scanner csvReader){
        if (csvReader.hasNextInt()){
            return (csvReader.nextInt());
        }
        csvReader.next(); // Ignoring the value
        return null; 
    }
    /**
     * Getting the BidDecimal value from the Scanner object
     * @param csvReader Scanner object
     * @return BigDecimal value if the field has a valid BigDecimalvalue;
     * @return null if the field does not have a long value
     */ 
    public BigDecimal getBigDecimal(Scanner csvReader){
        if (csvReader.hasNextBigDecimal()){
            return (csvReader.nextBigDecimal());
        }
//        csvReader.next(); // Ignoring the value
        return null; 
    }    
    public String getAssessmentClassString(Scanner csvReader){
        String tmpStr = csvReader.next().trim();
        if (tmpStr.isEmpty()){
            return null;         
        }
        if (!assessmentClasses.contains(tmpStr)){
            assessmentClasses.add(tmpStr);
        }
        return tmpStr;
    }
    
    
    public ArrayList<Record> setUpRecords(String fileName) {
        Scanner csvReader = null;
        try {
            csvReader = new Scanner(Paths.get(fileName));
        } catch (IOException ex) {
            System.out.println("\nFile Not Found.\nProgram Terminated.\n");
            System.exit(1); // Terminate program
        }

        // Setting up the records
        // Skipping the header for the file
        if (csvReader.hasNextLine()){
            csvReader.nextLine();
        }
        
        // Iterating through each record
        while (csvReader.hasNextLine()){
            csvReader.useDelimiter(",|\\r"); // Two delimiters- "," and "\r" 
            // Creating a new record 
            Record temp = new Record(getLong(csvReader), getString(csvReader), 
                    getInt(csvReader), getString(csvReader), getLong(csvReader), 
                    getAssessmentClassString(csvReader), getInt(csvReader), 
                    getString(csvReader),
                    getString(csvReader), getString(csvReader), 
                    getBigDecimal(csvReader), getBigDecimal(csvReader));
            
            if (temp.isEmpty()){ // When all attributes of temp are not null
                csvReader.nextLine();
                continue;
            }
            records.add(temp);
            csvReader.nextLine(); // Skips over the "\r\n" at the end of each line
        }
        csvReader.close();
        return records;
    }
    
    /**
     * Calculates the minimum value in the list
     * @param assessedVals The ArrayList of assessed values
     * @return Minimum value in the list
     */
    public long minAssessedVal(ArrayList<Long> assessedVals){
        return Collections.min(assessedVals);
    }
    /**
     * Calculates the maximum value in the list
     * @param assessedVals The ArrayList of assessed values
     * @return Maximum value in the list
     */
    public long maxAssessedVal(ArrayList<Long> assessedVals){
        return Collections.max(assessedVals);
    }
    
    /**
     * Calculates the mean of the values in the list in high precision
     * @param assessedVals The ArrayList of assessed values
     * @return Mean of the values
     */
    public BigDecimal calcMean(ArrayList<Long> assessedVals){
        BigDecimal sum = new BigDecimal("0");
        BigDecimal tmp;
        for (Long val : assessedVals){
            tmp = new BigDecimal(BigInteger.valueOf(val));
            sum = sum.add(tmp);
        }
        return sum.divide(new BigDecimal(BigInteger.valueOf(assessedVals.size())), 5, RoundingMode.CEILING);
    }
    
    // Calculating standard deviation
    /**
     * Calculates the standard deviation of the values in the list
     * @param assessedVals The ArrayList of assessed values
     * @param mean Mean of the values in the ArrayList
     * @return Standard deviation of the values
     */
    public double calcStandardDeviation(ArrayList<Long> assessedVals, BigDecimal mean){
        BigDecimal standardDeviation = new BigDecimal("0.0");
        for (Long val : assessedVals) {
            standardDeviation = standardDeviation.add((BigDecimal.valueOf(val).subtract(mean)).pow(2));
        }
        standardDeviation = standardDeviation.divide(new BigDecimal(BigInteger.valueOf(assessedVals.size()-1)), 5);
        return Math.sqrt(standardDeviation.doubleValue());
    }
    /**
     * Calculating the median of the values in the ArrayList
     * @param assessedVals The ArrayList of assessed values
     * @return Median of values
     */
    public double calculateMedian(ArrayList<Long> assessedVals){
        Collections.sort(assessedVals);
        int n = assessedVals.size();
        // Check for even case
        if (n % 2 != 0) {
            return (double)assessedVals.get(n/2); 
        } // Odd case
        return (double)(assessedVals.get((n-1)/2) + assessedVals.get(n/2)) / 2.0;         
    }
    
    /**
     * Facilitates the calculation of min, max, range, mean, median, and 
     * standard deviation of the values in the ArrayList
     * @param assessedVals The ArrayList of assessed values
     * @return A list of the calculated min, max, range, mean, median, and 
     * standard deviation 
     */
    public List<Object> calculateStats(ArrayList<Long> assessedVals){
        Long min = this.minAssessedVal(assessedVals);
        Long max = this.maxAssessedVal(assessedVals);
        Long range = max - min; 
        BigDecimal mean = this.calcMean(assessedVals);
        double std_dev = this.calcStandardDeviation(assessedVals, mean);
        double median = this.calculateMedian(assessedVals);
        
        return Arrays.asList(min, max, range, mean, std_dev, median);
    }
    /**
     * Prints the statistics on the console
     * @param n Number of values in the list
     * @param min Minimum value in the list
     * @param max Maximum value in the list
     * @param range Range of values in the list
     * @param mean Mean of the values in the list
     * @param std_dev Standard deviation of the values in the list
     * @param median Median of the values in the list
     */
    private void displayStats(long n, long min, long max, long range,
                              BigDecimal mean, double std_dev, double median){
        System.out.println("n: " + n +
                "\nMin: " + min +
                "\nMax: " + max +
                "\nRange: " + range + 
                "\nMean: " + mean.longValue() +
                "\nStd Deviation: " + (long) std_dev +
                "\nMedian: " + (long) median + "\n");
    }

    /**
     * Facilitates the calculation of statistics of all the records and 
     * displaying them on the console 
     */
    public void allRecordsStats(){
        // Building an array of Assessed Values
        ArrayList<Long> assessedVals = new ArrayList<>(records.size());
        for (int i = 0; i < records.size(); i++) {
            assessedVals.add(records.get(i).getAssessedVal());
        }

        // Calculating stats
        List<Object> calculatedStats = calculateStats(assessedVals);
        // Displaying stats
        System.out.println( "Descriptive Statistics For All Property Classes:\n");
        displayStats(assessedVals.size(), (long) calculatedStats.get(0), 
                (long) calculatedStats.get(1), (long) calculatedStats.get(2),
                (BigDecimal) calculatedStats.get(3), (double) calculatedStats.get(4),
                (double) calculatedStats.get(5));
    }
    /**
     * Prompts the user for a neighbourhood name and facilitates the calculation 
     * of statistics of all the records associated with the user specified 
     * neighbourhood and displaying them on the console 
     */
    public void neighbourhoodStats(){
        // User input for Neighbourhood Name
        Scanner console = new Scanner(System.in);  
        System.out.print("\nNeighbourhood: ");
        String neighbourhoodName = console.nextLine();
        
         // Building an array of Assessed Values for the Neighbourhood
        ArrayList<Long> assessedVals = new ArrayList<>();
        for (int i = 0; i < records.size(); i++) {
            if ((records.get(i).getNeighbourhoodInfo().getName() != null) && 
                (records.get(i).getNeighbourhoodInfo().getName().equalsIgnoreCase(neighbourhoodName))){
                // Populating the ArrayList for the given neighbourhood
                assessedVals.add(records.get(i).getAssessedVal());
            }
        }        
        // User entered neighbourhood name not in the data
        if (assessedVals.isEmpty()){
            System.out.println("\nCould not find a neightbourhood with given name.");
            return;
        }        
        // Calculating stats
        List<Object> calculatedStats = calculateStats(assessedVals);
        // Displaying stats
        System.out.println( "Statistics (neighbourhood = " + neighbourhoodName + ")");
        displayStats(assessedVals.size(), (long) calculatedStats.get(0), 
                (long) calculatedStats.get(1), (long) calculatedStats.get(2),
                (BigDecimal) calculatedStats.get(3), (double) calculatedStats.get(4),
                (double) calculatedStats.get(5));
    }
    /**
     * Prompts the user for an Assessment Class and facilitates the calculation 
     * of statistics of all the records associated with the user specified 
     * assessedClass in a neighbourhood and displaying them on the console 
     */
    public void assessmentClassStats(){
        // User input for Assessment Class Name
        Scanner console = new Scanner(System.in);  
        System.out.print("\nAssessment Class: ");
        String assessmentClass = console.nextLine();
        
         // Building an array of Assessed Values for the Assessment Class
        ArrayList<Long> assessedVals = new ArrayList<>();
        for (int i = 0; i < records.size(); i++) {
            if ((records.get(i).getNeighbourhoodInfo().getName() != null) && 
                (records.get(i).getAssessmentClass().equalsIgnoreCase(assessmentClass))){
                // Populating the ArrayList for the given neighbourhood
                assessedVals.add(records.get(i).getAssessedVal());
            }
        }        
        // User entered Assessment Class name not in the data
        if (assessedVals.isEmpty()){
            System.out.println("\nCould not find a Assessment class with the given name.");
            return;
        }
        // Calculating stats
        List<Object> calculatedStats = calculateStats(assessedVals);
        // Displaying stats
        System.out.println( "Statistics (assessment class = " + assessmentClass + ")");
        displayStats(assessedVals.size(), (long) calculatedStats.get(0), 
                (long) calculatedStats.get(1), (long) calculatedStats.get(2),
                (BigDecimal) calculatedStats.get(3), (double) calculatedStats.get(4),
                (double) calculatedStats.get(5));
    }
    /**
     * Prompts the user for an account number and displays the account number,
     * address, assessed value, assessment class, neighbourhood information, and
     * the coordinated of the property associated with it. 
     * Displays an error message if the account number is not found.
     */
    public void findPropertyByAccount(){
        // User input for Account Number
        Scanner console = new Scanner(System.in);  
        System.out.print("Find a property assessment by account number: ");
        long userAccNo = console.nextLong();
        
        // Displaying the information of the user requested account
        for (Record record : records) {
            if (record.getAccNum() == userAccNo){ // match
                System.out.println("\nAccount Number = " + record.getAccNum() +
                        "\nAddress = " + record.getAddress().getSuite() + " " + 
                            record.getAddress().getHouseNum() + " " + record.getAddress().getStreetName() + 
                        "\nAssessed value = $" + (double) record.getAssessedVal() +
                        "\nAssessment class = " + record.getAssessmentClass() +
                        "\nNeighbourhood = " + record.getNeighbourhoodInfo().getName() + 
                                             " (" + record.getNeighbourhoodInfo().getWardName() + ")" +
                        "\nLocation = (" + record.getCoordinates().getLongitude() + ", " + 
                            record.getCoordinates().getLatitude() + ")");
            return;
            }
        }
        // When account does not exist
        System.out.println("\nSorry, couldn't find the account with the account" + 
                " number " + userAccNo + ".");
    }

}