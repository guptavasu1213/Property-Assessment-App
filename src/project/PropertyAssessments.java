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
public class PropertyAssessments {
    private List<Record> records = new ArrayList<>();
    private ArrayList<String> assessmentClasses = new ArrayList<>();

    /**
     * Constructor for the class
     * Gets the contents from the given .csv file and import them into a list of
     * records
     * @param fileName File name of the .csv file
     */
    public PropertyAssessments(String fileName){
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
    public String displayStats(long n, long min, long max, long range,
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
}
