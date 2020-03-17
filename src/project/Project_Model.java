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
 * This class creates a list of records found from a .csv file.
 * Allows the lookup of records in 
 * Allows the calculation of statistics.
 * @author vasug
 */
public class Project_Model {
    private List<Record> records = new ArrayList<>();
    private ArrayList<String> assessmentClasses = new ArrayList<>();

    /**
     * Constructor for the class
     * Gets the contents from the given .csv file and import them into a list of
     * records
     * @param fileName File name of the .csv file
     */
    public Project_Model(String fileName){
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
    }
            
    /**
     * @return all the records stored
     */
    public List<Record> getAllRecords(){
        return records;
    }
    /**
     * @return All the assessment classes in records
     */
    public List<String> getAssessmentClasses(){
        return assessmentClasses;
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
    /**
     * Adds the assessment class to a list and returns the content read from the
     * scanner object
     * @param csvReader
     * @return String storing the assessment class
     */
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
    private String displayStats(long n, long min, long max, long range,
                              BigDecimal mean, double std_dev, double median){
        String stats = "n: " + n +
                "\nMin: " + min +
                "\nMax: " + max +
                "\nRange: " + range + 
                "\nMean: " + mean.longValue() +
                "\nStd Deviation: " + (long) std_dev +
                "\nMedian: " + (long) median + "\n";
        System.out.println(stats);
        return stats;
    }

    /**
     * Facilitates the calculation of statistics of all the records and 
     * displaying them on the console 
     */
    public String findStats(List<Record> records){
        if (records.size() == 1){ // When there is only one record
            return "";
        }
        
        // Building an array of Assessed Values
        ArrayList<Long> assessedVals = new ArrayList<>(records.size());
        for (int i = 0; i < records.size(); i++) {
            assessedVals.add(records.get(i).getAssessedVal());
        }

        // Calculating stats
        List<Object> calculatedStats = calculateStats(assessedVals);
        // Displaying stats
        System.out.println( "Descriptive Statistics For All Property Classes:\n");
        return displayStats(assessedVals.size(), (long) calculatedStats.get(0), 
                (long) calculatedStats.get(1), (long) calculatedStats.get(2),
                (BigDecimal) calculatedStats.get(3), (double) calculatedStats.get(4),
                (double) calculatedStats.get(5));
    }
    /**
     * Finding the neighbourhood name in the passed list of records.
     * If the list is not defined, then the neighbourhood is found based on the 
     * list of all the records
     * @param neighbourhoodName The neighbourhood name to be searched
     * @param records The records to be searched for
     * @return List of records found with the given neighbourhood. Null if no such
     * records are found
     */
    public List<Record> findByNeighbourhood(String neighbourhoodName, List<Record> records){
        if (records == null){ // When the user entered list is null
            records = this.records;
        } 
        
        // Building an array of Assessed Values for the Neighbourhood
        List<Record> neighbourhoodList = new ArrayList<>();
        for (Record record : records) {
            if ((record.getNeighbourhoodInfo().getName() != null) && 
                (record.getNeighbourhoodInfo().getName().equalsIgnoreCase(neighbourhoodName))){
                neighbourhoodList.add(record);                
            }
        }
        // When there is no such neighbourhood
        if (neighbourhoodList.isEmpty()){
            System.out.println("No neighbourhood found");
            return null; // when nothing found
        }
        return neighbourhoodList;
    }
    /**
     * Finding the assessment class in the passed list of records.
     * If the list is not defined, then the assessment class is found based on 
     * the list of all the records
     * @param assessmentClass The assessment class  name to be searched
     * @param records The records to be searched for
     * @return List of records found with the given assessment class. 
     * Null if no such records are found
     */
    public List<Record> findByAssessmentClass(String assessmentClass, List<Record> records){
        if (records == null){ // When the user entered list is null
            records = this.records;
        }
        // Building an array of Assessed Values for the Neighbourhood
        List<Record> classList = new ArrayList<>();
        for (Record record : records) {
            if ((record.getAssessmentClass() != null) && 
                (record.getAssessmentClass().equalsIgnoreCase(assessmentClass))){
                classList.add(record);                
            }
        }
        // When there is no such neighbourhood
        if (classList.isEmpty()){
            System.out.println("No such class found");
            return null; // when nothing found
        }
        return classList;
    }
    /**
     * Finding the address in the passed list of records.
     * If the list is not defined, then the address is found based on 
     * the list of all the records
     * @param address The address name to be searched
     * @param records The records to be searched for
     * @return List of records found with the given address. 
     * Null if no such records are found
     */    
    public List<Record> findByAddress(String address, List<Record> records){
        if (records == null){ // When the user entered list is null
            records = this.records;
        }
        // Building an array of Assessed Values for the Neighbourhood
        List<Record> addressList = new ArrayList<>();
        for (Record record : records) {
            if (record.getAddress().toString().equalsIgnoreCase(address)){
                addressList.add(record);                
            }
        }
        // When there is no such neighbourhood
        if (addressList.isEmpty()){
            System.out.println("No such class found");
            return null; // when nothing found
        }
        return addressList;
    }
    
    /**
     * Looks up the property record 
     */
    public Record findPropertyByAccount(long userAccNo){        
        // Displaying the information of the user requested account
        for (Record record : records) {
            if (record.getAccNum() == userAccNo){ // match
                System.out.println("Account found with the given acc number!");
                return record;
            }
        }
        // When account does not exist
        System.out.println("\nSorry, couldn't find the account with the account" + 
                " number " + userAccNo + ".");
        return null;
    }
}