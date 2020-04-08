package project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * This class creates a list of records found from a .csv file.
 * Allows the lookup of records in 
 * Allows the calculation of statistics.
 * @author vasug
 */
public final class PropertyAssessments {
    private List<Record> records = new ArrayList<>();
    private ArrayList<String> assessmentClasses = new ArrayList<>();

    /**
     * Constructor for the class
     * Gets the contents from the given .csv file and import them into a list of
     * records
     * @param fileName File name of the .csv file
     */
    public PropertyAssessments(){
        BufferedReader buffer = null;

        try {
            URL url = new URL("https://data.edmonton.ca/resource/q7d6-ambg.csv");
            URLConnection connection = url.openConnection();            
            InputStreamReader input = new InputStreamReader(connection.getInputStream());

            String line = "";
            String csvSplitBy = ",";

            buffer = new BufferedReader(input);
            buffer.readLine(); // Skipping the header of the csv
            while ((line = buffer.readLine()) != null) {
                String[] room = line.split(csvSplitBy);
                for (int i = 0; i < room.length; i++) {
                    System.out.print("i: " + i + "  -" + room[i] + "-\t");                
                }
                System.out.println("");
                // Creating a new record 
                Record temp = new Record(getLong(sanitizeInput(room[0])), 
                        getString(sanitizeInput(room[1])), 
                        getInt(sanitizeInput(room[2])), 
                        getString(sanitizeInput(room[3])), 
                        getLong(sanitizeInput(room[4])), 
                        getAssessmentClassString(sanitizeInput(room[5])), 
                        getInt(sanitizeInput(room[6])), 
                        getString(sanitizeInput(room[7])),
                        getString(sanitizeInput(room[8])), 
                        getString(sanitizeInput(room[9])), 
                        getBigDecimal(sanitizeInput(room[10])), 
                        getBigDecimal(sanitizeInput(room[11])));
                
                if (temp.isEmpty()){ // When all attributes of temp are not null
                    continue;
                }
                records.add(temp);
            }
        } catch (MalformedURLException e) {
            System.out.println("Invalid URL");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (buffer != null) {
                try {
                    buffer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
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
     * The String is santized and the relevant part is extracted from it.
     * The source strings are of the form """", meaning they have double
     * quotes in the strings.
     * @param tmpStr The string to be sanitized
     * @return The string value after santization
     */
    private String sanitizeInput(String tmpStr){
        tmpStr = tmpStr.trim();
        if (tmpStr.equals("\"\"") || tmpStr.equals("")){ // Empty string
            return "";
        }
        return tmpStr.substring(1, tmpStr.length()-1);
    }
    /**
     * Getting the String value from the Scanner object
     * @param csvReader Scanner object
     * @return String value if the field has a valid string value; 
     * @return null if the field is empty
     */
    public String getString(String tmpStr){
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
    public Long getLong(String tmpStr){
        
        try {
            return Long.parseLong(tmpStr);
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * Getting the Integer value from the Scanner object
     * @param csvReader Scanner object
     * @return Integer value if the field has a valid Integer value;
     * @return null if the field does not have a long value
     */ 
    public Integer getInt(String tmpStr){
        try {
            return Integer.parseInt(tmpStr);
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * Getting the BidDecimal value from the Scanner object
     * @param csvReader Scanner object
     * @return BigDecimal value if the field has a valid BigDecimalvalue;
     * @return null if the field does not have a long value
     */ 
    public BigDecimal getBigDecimal(String tmpStr){
        try {
            return new BigDecimal(tmpStr);
        } catch (Exception e) {
            return null;
        }
    }
    /**
     * Adds the assessment class to a list and returns the content read from the
     * scanner object
     * @param csvReader
     * @return String storing the assessment class
     */
    public String getAssessmentClassString(String tmpStr){
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
