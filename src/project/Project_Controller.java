package project;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

/**
 * Controller class acts as a middleman between the Model and the Viewer class
 * @author vasug Vasu Gupta
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
    public Project_Controller(Project_Model model, Project_Viewer viewer) {
        this.model = model;
        this.viewer = viewer;
        setSearchEvent(); // Sets the Listener for the search button in the viewer
        setClearEvent(); // Sets the Listener for the clear button in the viewer
    }
   /**
    * The Sets up the GUI, and adds the records from the model to the table in the GUI
    */    
    public void displayAllRecords(){
        // Getting all the records stored in the model
        List<Record> records = model.getAllRecords();
        // Fills in the values of the table
        viewer.updateGUI(records);
        // Sets up the assesment classes in the table by pulling out the classes
        // from the records in model
        viewer.setAssessmentClasses(getAssessmentClasses());
        viewer.setStatsLabel(model.findStats(records));
    }
    
    /**
     * Sets the table in the GUI to display nothing
     */
    public void setTableEmpty(){
        viewer.setStatsLabel("No such record found");        
        viewer.updateGUI(new ArrayList<Record>());
    }
    /**
     * Looking up the records by Account number
     * Verifying the correct input
     * Reporting if the account number does not exist
     * @param userString
     * @return 
     */
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
            temp.add(userRecord); 
            return temp;
        }                  
    }
    
    /**
     * Getting all the assessment classes from the records
     * @return 
     */
    public List<String> getAssessmentClasses(){
        return model.getAssessmentClasses();
    }

    /**
     * Creating and setting the handler for the clear button
     * Populates the table with all the records and displays their statistics
     * The button resets all the text fields
     */
    public void setClearEvent(){
        viewer.getClearButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override // Action for clearing everything
            public void handle(ActionEvent event) {
                List<Record> records = model.getAllRecords();
                // Fills in the values of the table
                viewer.updateGUI(records);
                // Set the labels
                viewer.setStatsLabel(model.findStats(records));
                // Setting all the fields empty
                viewer.resetAllFields();
            }
        });        
    }
    
    /**
     * Setting the event listener for the search button in the viewer.
     */
    public void setSearchEvent(){
        viewer.getSearchButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                List<Record> finalList = null; // Stores the records passing all filters
                
                String userAccNo = viewer.getAccNum(); // Gets the text in account number text field
                String userAddress = viewer.getAddress(); // Gets the text in Address text field
                String userAssClass =  viewer.getAssessmentClass(); // Gets the assessment class selected from the drop down
                String userNeighbourhood = viewer.getNeighbourhood(); // Gets the text in neighbourhood text field
                
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
        });
    }    
}
