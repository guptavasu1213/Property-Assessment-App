package project;

import java.util.List;

/**
 * This class creates a list of records found from a .csv file.
 * Allows the lookup of records in 
 * Allows the calculation of statistics.
 * @author vasug
 */
public class Project_Model {
    PropertyAssessments properties;

    /**
     * Constructor for the class
     * Gets the contents from the given .csv file and import them into a list of
     * records
     * @param fileName File name of the .csv file
     */
    public Project_Model(String fileName){
        properties = new PropertyAssessments(fileName);
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
     * Facilitates the calculation of statistics of all the records and 
     * displaying them on the console 
     */
    public String findStats(List<Record> records){
        return properties.findStats(records);
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
        return properties.findByNeighbourhood(neighbourhoodName, records);
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
        return properties.findByAssessmentClass(assessmentClass, records);
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
        return properties.findByAddress(address, records);
    }
    
    /**
     * Looks up the property record 
     */
    public Record findPropertyByAccount(long userAccNo){
        return properties.findPropertyByAccount(userAccNo);
    }
}
