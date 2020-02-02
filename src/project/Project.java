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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vasug
 */
public class Project {
    private ArrayList<Record> records = new ArrayList<>();

    // Takes input from user and assigns the file name accordingly
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
    
    // Getting the Long value from the Scanner object
    private Long getLong(Scanner csvReader){
        if (csvReader.hasNextLong()){
            return (csvReader.nextLong());
        }
        csvReader.next(); // Ignoring the value
        return null; 
    }
    // Getting the String value from the Scanner object
    private String getString(Scanner csvReader){
        String tmpStr = csvReader.next().trim();
        if (tmpStr.isEmpty()){
            return null;         
        }
        return tmpStr;
    }
    // Getting the Integer value from the Scanner object
    private Integer getInt(Scanner csvReader){
        if (csvReader.hasNextInt()){
            return (csvReader.nextInt());
        }
        csvReader.next(); // Ignoring the value
        return null; 
    }
    // Getting the BigDecimal value from the Scanner object
    private BigDecimal getBigDecimal(Scanner csvReader){
        if (csvReader.hasNextBigDecimal()){
            return (csvReader.nextBigDecimal());
        }
        csvReader.next(); // Ignoring the value
        return null; 
    }    
    
    public void setUpRecords(String fileName) {
        Scanner csvReader = null;
        try {
            csvReader = new Scanner(Paths.get(fileName));
        } catch (IOException ex) {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
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
                    getString(csvReader), getInt(csvReader), getString(csvReader),
                    getString(csvReader), getString(csvReader), 
                    getBigDecimal(csvReader), getBigDecimal(csvReader));

            records.add(temp);
            csvReader.nextLine(); // Skips over the "\r\n" at the end of each line
        }
        csvReader.close();
    }
    
    // Calculates the number of records in the file
    private int countRecords(ArrayList<Long> assessedVals){
        return assessedVals.size();
    }
    
    // Minimum value in the array list
    private long minAssessedVal(ArrayList<Long> assessedVals){
        return Collections.min(assessedVals);
    }
    // Maximum value in the array list
    private long maxAssessedVal(ArrayList<Long> assessedVals){
        return Collections.max(assessedVals);
    }
    
    // Average
    private BigDecimal calcMean(ArrayList<Long> assessedVals){
        BigDecimal sum = new BigDecimal("0");
        BigDecimal tmp;
        for (Long val : assessedVals){
            tmp = new BigDecimal(BigInteger.valueOf(val));
            sum = sum.add(tmp);
        }
        return sum.divide(new BigDecimal(BigInteger.valueOf(assessedVals.size())), 5, RoundingMode.CEILING);
    }
    
    // Calculating standard deviation
    private double calcStandardDeviation(ArrayList<Long> assessedVals, BigDecimal mean){
        BigDecimal standardDeviation = new BigDecimal("0.0");
        for (Long val : assessedVals) {
            standardDeviation = standardDeviation.add((BigDecimal.valueOf(val).subtract(mean)).pow(2));
        }
        standardDeviation = standardDeviation.divide(new BigDecimal(BigInteger.valueOf(assessedVals.size()-1)), 5);
        return Math.sqrt(standardDeviation.doubleValue());
    }
    
    // Calculating median
    private double calculateMedian(ArrayList<Long> assessedVals){
        Collections.sort(assessedVals);
        int n = assessedVals.size();
        // Check for even case
        if (n % 2 != 0) {
            return (double)assessedVals.get(n/2); 
        } // Odd case
        return (double)(assessedVals.get((n-1)/2) + assessedVals.get(n/2)) / 2.0;         
    }
    // Calculates the parameters for the statistics
    private List<Object> calculateStats(ArrayList<Long> assessedVals){
        Long min = this.minAssessedVal(assessedVals);
        Long max = this.maxAssessedVal(assessedVals);
        Long range = max - min; 
        BigDecimal mean = this.calcMean(assessedVals);
        double std_dev = this.calcStandardDeviation(assessedVals, mean);
        double median = this.calculateMedian(assessedVals);
        
        return Arrays.asList(min, max, range, mean, std_dev, median);
    }
    // Displays all the parameters for the statistics
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
    
    // Calculates and displays the stats for all the records in the file
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
    
    // Looks for the user specified account and displays information
    public void findPropertyByAccount(){
        // User input for Account Number
        Scanner console = new Scanner(System.in);  
        System.out.print("Find a property assessment by account number: ");
        long userAccNo = console.nextLong();
        
        // Displaying the information of the user requested account
        for (Record record : records) {
            if (record.getAccNum() == userAccNo){ // match
                // CHECK FOR NULLLLLLL====================
                System.out.println("\nAccount Number = " + record.getAccNum() +
                        "\nAddress = " + record.getAddress().getSuite() + " " + 
                            record.getAddress().getHouseNum() + " " + record.getAddress().getStreetName() + 
                        "\nAssessed value = $" + (double) record.getAssessedVal() +
                        "\nAssessment class = " + record.getNeighbourhood().getAssessmentClass() +
                        "\nNeighbourhood = " + record.getNeighbourhood().getName() + 
                                             " (" + record.getNeighbourhood().getWardName() + ")" +
                        "\nLocation = (" + record.getCoordinates().getLongitude() + ", " + 
                            record.getCoordinates().getLatitude() + ")");
            return;
            }
        }
        // When account does not exist
        System.out.println("\nSorry, couldn't find the account with the account" + 
                " number " + userAccNo + ".");
    }
    
    // Calculates and displays the stats for user defined neighbourhood
    public void neighbourhoodStats(){
        // User input for Neighbourhood Name
        Scanner console = new Scanner(System.in);  
        System.out.print("\nNeighbourhood: ");
        String neighbourhoodName = console.nextLine();
        
         // Building an array of Assessed Values for the Neighbourhood
        ArrayList<Long> assessedVals = new ArrayList<>();
        for (int i = 0; i < records.size(); i++) {
            if ((records.get(i).getNeighbourhood().getName() != null) && 
                (records.get(i).getNeighbourhood().getName().equalsIgnoreCase(neighbourhoodName))){
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
    // Calculates and displays the stats for all the user specified assessed values
    public void assessmentClassStats(){
        // User input for Assessment Class Name
        Scanner console = new Scanner(System.in);  
        System.out.print("\nAssessment Class: ");
        String assessmentClass = console.nextLine();
        
         // Building an array of Assessed Values for the Assessment Class
        ArrayList<Long> assessedVals = new ArrayList<>();
        for (int i = 0; i < records.size(); i++) {
            if ((records.get(i).getNeighbourhood().getName() != null) && 
                (records.get(i).getNeighbourhood().getAssessmentClass().equalsIgnoreCase(assessmentClass))){
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
}