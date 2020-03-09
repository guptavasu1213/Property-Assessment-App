/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

/**
 *
 * @author vasug
 */
public class Project_Controller {
    private Project_Model model; // Stores data
    private Project_Viewer viewer; // Stores the UI of the app
    private Stage primaryStage;
    
    /**
     * Constructor
     * @param model
     * @param viewer 
     */
    public Project_Controller(Project_Model model, Project_Viewer viewer){
        this.model = model;
        this.viewer = viewer;
//        setAccountNumEvent();
        setSearchEvent();
    }
    
    public void getAndShow(){
        String fileName = model.getFileName();
        List<Record> records = model.setUpRecords(fileName);
    }
    
    public void displayAllRecords(){
        // Sets up the structure of the GUI with the table
        List<Record> records = model.setUpRecords("Property_Assessment_Data_2020-MOD.csv");
        // Fills in the values of the table
        viewer.updateGUI(records);
        // Sets up the assesment classes in the table by pulling out the classes
        // from the table
        viewer.setAssessmentClasses(getAssessmentClasses());
    }
    
    public void setTableEmpty(){
        viewer.setStatsLabel("No such record found");        
        viewer.updateGUI(new ArrayList<Record>());
    }
            
    public List<Record> searchByAccNo(String userString){
        Long userAccNo;
        // When the user enters something (not an empty string)
        try{
            userAccNo = Long.parseLong(userString);
            System.out.println("Account num: " + userAccNo);
        } catch(NumberFormatException ex){
            setTableEmpty();
            viewer.setStatsLabel("Invalid Account Number!");
            return null;
        }
        
        // When the input is valid
        Record userRecord = model.findPropertyByAccount(userAccNo);
        if (userRecord == null){
            setTableEmpty();
            viewer.setStatsLabel("Account number does not exist.\nTry Again!");
            return null;
        }
        else{
            System.out.println("FOUND IT");
            ArrayList<Record> temp = new ArrayList<>();
            temp.add(userRecord); // MAYBE MOVE THIS AROUND###########
            viewer.updateGUI(temp);
            return temp;
        }                  
    }

    public void setSearchEvent(){
        EventHandler<ActionEvent> handler = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<Record> finalList = null; // Stores the records passing all filters
                
                String userAccNo = viewer.getAccNum();
                String userAddress = viewer.getAddress();
                String userAssClass =  viewer.getAssessmentClass();
                String userNeighbourhood = viewer.getNeighbourhood();
                
                // When all fields are empty
                if (userAccNo.equals("") && userAddress.equals("")&& 
                        userNeighbourhood.equals("") && userAssClass == null){
                    viewer.updateGUI(model.getAllRecords());
                    viewer.setStatsLabel(model.findStats(model.getAllRecords()));
                    System.out.println("All fields empty");
                    return;
                }
                
                // Search by Account number
                if (!userAccNo.equals("")){ 
                    finalList = searchByAccNo(userAccNo);
                    if (finalList == null){return;}
                }
                // Search by Address
                if (!userAddress.equals("")){ 
                    finalList = model.findByAddress(userAddress, finalList);
                    if (finalList == null){
                        setTableEmpty();
                        return;
                    }
                }
                // Search by neighbourhood
                if (!userNeighbourhood.equals("")){ 
                    finalList = model.findByNeighbourhood(userNeighbourhood, finalList);
                    if (finalList == null){
                        setTableEmpty();
                        return;
                    }
                }
                // Search by Assessment Class
                if (userAssClass != null){ 
                    finalList = model.findByAssessmentClass(userAssClass, finalList);
                    if (finalList == null){
                        setTableEmpty();
                        return;
                    }
                }                
                
                // When there is no such record
                if (finalList == null){
                    setTableEmpty();
                }
                else { // Updating the table and the stats on data
                    viewer.setStatsLabel(model.findStats(finalList));
                    viewer.updateGUI(finalList);
                }
            }
        };
        viewer.setSearchListener(handler); // Setting the handler for the button
    }
    
    public List<String> getAssessmentClasses(){
        return model.getAssessmentClasses();
    }
}
