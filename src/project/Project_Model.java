package project;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class creates a list of records found from a .csv file.
 * Allows the lookup of records in 
 * Allows the calculation of statistics.
 * @author vasug
 */
public class Project_Model {
    private PropertyAssessments properties;

    /**
     * Constructor for the class
     * Gets the contents from the given .csv file and import them into a list of
     * records
     * @param fileName File name of the .csv file
     */
    public Project_Model(){
        properties = new PropertyAssessments();
    }
       
    /**
     * @return all the records stored
     */
    public List<Record> getAllRecords(){
        return properties.getAllRecords();
    }
    /**
     * @return All the assessment classes in records
     */
    public List<String> getAssessmentClasses(){
        return properties.getAssessmentClasses();
    }
    /**
     * 
     * @param filteredRecords
     * @return 
     */
    public List<String> getWardList(List<Record> filteredRecords){
        List<String> wardList = new ArrayList<>();
        for (Record property : filteredRecords){
            if (!wardList.contains( property.getNeighbourhoodInfo().getWardName())){
                wardList.add(property.getNeighbourhoodInfo().getWardName());
            }
        }
        return wardList;
    }
        public Double getWardAssessmentMean(String wardName, List<Record> filteredRecords){
        if (filteredRecords.size() == 0 ) return 0.0;
        
        Double sum = 0.0;
        for (Record property : filteredRecords){
            if (property.getNeighbourhoodInfo().getWardName().equalsIgnoreCase(wardName)){
                sum += property.getAssessedValue();
            }
        }
        return sum/filteredRecords.size();
    }

    public List<String> getNeighbourhoodList(List<Record> filteredRecords){
        List<String> neighbourhoodList = new ArrayList<>();
        for (Record property : filteredRecords){
            if (!neighbourhoodList.contains( property.getNeighbourhoodInfo().getName())){
                neighbourhoodList.add(property.getNeighbourhoodInfo().getName());
            }
        }
        return neighbourhoodList;
    }
    public Double getNeigbourhoodAssessmentMean(String neighName, List<Record> filteredRecords){
        if (filteredRecords.size() == 0 ) return 0.0;
        
        Double sum = 0.0;
        for (Record property : filteredRecords){
            if (property.getNeighbourhoodInfo().getName().equalsIgnoreCase(neighName)){
                sum += property.getAssessedValue();
            }
        }
        return sum/filteredRecords.size();
    }
    public long countAssessedValuesBetweenRange(Long lowerLim, Long upperLim, 
            List<Record> filteredRecords){
        long count = 0;
        for (Record property : filteredRecords){
            if ((property.getAssessedValue() >= lowerLim) && (property.getAssessedValue() < upperLim)){
                count++;
            }
        }        
        return count;
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
            assessedVals.add(records.get(i).getAssessedValue());
        }

        // Calculating stats
        List<Object> calculatedStats = properties.calculateStats(assessedVals);
        // Displaying stats
        System.out.println( "Descriptive Statistics For All Property Classes:\n");
        return properties.displayStats(assessedVals.size(), (long) calculatedStats.get(0), 
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
            records = properties.getAllRecords();
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
            records = properties.getAllRecords();
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
            records = properties.getAllRecords();
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
        for (Record record : properties.getAllRecords()) {
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
