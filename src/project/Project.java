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
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author vasug
 */
public class Project {
    ArrayList<Record> records = new ArrayList<>();

    // Takes input from user and assigns the file name accordingly
    private String getFileName() {
        Scanner console = new Scanner(System.in);  
        System.out.print("CSV filename [press enter for default file]: ");
        String fileName = console.nextLine();
        
        // When user does not enter anything, use default file name
        if (fileName.equals("")){
            return "Property_Assessment_Data_2020.csv";
        }
        return fileName;
    }
    private void setUpRecords(String fileName) {
        Scanner csvReader = null;
        try {
            csvReader = new Scanner(Paths.get(fileName));
        } catch (IOException ex) {
            Logger.getLogger(Project.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1); // Terminate program
        }

        // Setting up the records
        // Skipping the title for each column
        if (csvReader.hasNextLine()){
            csvReader.nextLine();
        }
        while (csvReader.hasNextLine()){
             csvReader.useDelimiter(",|\\r"); // Two delimiters- "," and "\r"

            Record temp = new Record(csvReader.next().trim(), csvReader.next().trim()
                    , csvReader.next().trim(),csvReader.next().trim()
                    , csvReader.next().trim(),csvReader.next().trim()
                    , csvReader.next().trim(),csvReader.next().trim()
                    , csvReader.next().trim(),csvReader.next().trim()
                    , csvReader.next().trim(),csvReader.next().trim());
            records.add(temp);
            
            csvReader.nextLine(); // Skips over the "\r\n" at the end of each line
        }
        csvReader.close();
    }
    
    private void printRecords(){
        records.forEach((rec) -> {
            System.out.println(rec);
        });
    }
    
    // Calculates the number of records in the file
    private int countRecords(){
        return records.size();
    }
    
    private long minAssessedVal(){
        long tempMin = records.get(0).getAssessedVal();
        for (Record rec : records){
            if  (rec.getAssessedVal() < tempMin){
                tempMin = rec.getAssessedVal();
            }            
        }
        return tempMin;
    }
    private long maxAssessedVal(){
        long tempMax = records.get(0).getAssessedVal();
        for (Record rec : records){
            if  (rec.getAssessedVal() > tempMax){
                tempMax = rec.getAssessedVal();
            }            
        }
        return tempMax;
    }
    
    // Average
    private BigDecimal calcMean(){
        BigDecimal sum = new BigDecimal("0");
        BigDecimal tmp;
        for (Record rec : records){
            tmp = new BigDecimal(BigInteger.valueOf(rec.getAssessedVal()));
            sum = sum.add(tmp);
        }
        return sum.divide(new BigDecimal(BigInteger.valueOf(records.size())), 5, RoundingMode.CEILING);
    }
    
    // Calculating standard deviation
    private double calcStandardDeviation(BigDecimal mean){
        BigDecimal standardDeviation = new BigDecimal("0.0");
        for (Record rec : records) {
            standardDeviation = standardDeviation.add((BigDecimal.valueOf(rec.getAssessedVal()).subtract(mean)).pow(2));
        }
        standardDeviation = standardDeviation.divide(new BigDecimal(BigInteger.valueOf(records.size()-1)), 5);
        return Math.sqrt(standardDeviation.doubleValue());
    }
    private double calcMedian(){
        // Building an array of Assessed Values
        long[] assessedVals = new long[records.size()];
        for (int i = 0; i < records.size(); i++) {
            assessedVals[i] = records.get(i).getAssessedVal();
        }
        // Sorting the array 
        Arrays.sort(assessedVals);
        
        int n = assessedVals.length;
        // Check for even case
        if (n % 2 != 0) {
            return (double)assessedVals[n / 2]; 
        } // Odd case
        return (double)(assessedVals[(n - 1) / 2] + assessedVals[n / 2]) / 2.0; 
    }
    private double calculateMedian(ArrayList<Long> assessedVals){
        Collections.sort(assessedVals);
        int n = assessedVals.size();
        // Check for even case
        if (n % 2 != 0) {
            return (double)assessedVals.get(n/2); 
        } // Odd case
        return (double)(assessedVals.get((n-1)/2) + assessedVals.get(n/2)) / 2.0;         
    }
    private void displayStats(){
        BigDecimal mean = this.calcMean();

        // Building an array of Assessed Values
        ArrayList<Long> assessedVals = new ArrayList<>(records.size());
        for (int i = 0; i < records.size(); i++) {
            assessedVals.add(records.get(i).getAssessedVal());
        }        
        
        System.out.println( "Descriptive Statistics For All Property Classes:" +
                "\nn: " + this.countRecords() +
                "\nMin: " + this.minAssessedVal() +
                "\nMax: " + this.maxAssessedVal() +
                "\nRange: " + (this.maxAssessedVal() - this.minAssessedVal()) + 
                "\nMean: " + mean +
                "\nStd Deviation: " + this.calcStandardDeviation(mean) +
                "\nMedian: " + this.calculateMedian(assessedVals));   
    }
    
    // Looks for the user specified account and displays information
    private void findPropertyByAccount(){
        // User input for account number
        Scanner console = new Scanner(System.in);  
        System.out.print("Find a property assessment by account number: ");
        long userAccNo = console.nextLong();
        
        // Displaying the information of the user requested account
        for (Record record : records) {
            if (record.getAccNum() == userAccNo){ // match
                System.out.println("\nAccount Number = " + record.getAccNum() +
                        "\nAddress = " + record.getSuite() + " " + 
                            record.getHouseNum() + " " + record.getStreetName() + 
                        "\nAssessed value = $" + record.getAssessedVal() +
                        "\nAssessment class = " + record.getAssessmentClass() +
                        "\nNeighbourhood = " + record.getNeighbourhoodName() + 
                                             " (" + record.getWardName() + ")" +
                        "\nLocation = (" + record.getLongitude() + ", " + 
                            record.getLatitude() + ")");
            return;
            }
        }
        // When account does not exist
        System.out.println("\nSorry, couldn't find the account with the account" + 
                " number " + userAccNo + ".");
    }
   
    private void neighbourhoodStats(){
        // User input for neighbourhood name
        Scanner console = new Scanner(System.in);  
        System.out.print("\nNeighbourhood: ");
        String neighbourhoodName = console.nextLine();
        
         // Building an array of Assessed Values for the Neighbourhood
        ArrayList<Long> assessedVals = new ArrayList<>();
        for (int i = 0; i < records.size(); i++) {
            if ((records.get(i).getNeighbourhoodName() != null) && 
                (records.get(i).getNeighbourhoodName().equalsIgnoreCase(neighbourhoodName))){
                // Populating the ArrayList for the given neighbourhood
                assessedVals.add(records.get(i).getAssessedVal());
            }
        }        
        // User entered neighbourhood name not in the data
        if (assessedVals.isEmpty()){
            System.out.println("\nCould not find a neightbourhood with given name.");
            return;
        }
        // ####################
        // CALCULATE STUFF BY REFACTORING CODE IN THE FUNCTIONS CREATED BEFORE
        // ####################
        // Refactor the functions so that they take the ArrayList as a parameter
        // and then perform the computations on the Arraylist
        
        System.out.println( "Descriptive Statistics For All Property Classes:" +
                "\nMedian: " + this.calculateMedian(assessedVals));   
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Project proj = new Project();
        String fileName = proj.getFileName();
       
        proj.setUpRecords(fileName);    
        // Part 1
        proj.displayStats();
//        proj.printRecords();
        
        // Part 2
//        proj.findPropertyByAccount();
        
        // Part 3
        proj.neighbourhoodStats();
    }
}

//sout