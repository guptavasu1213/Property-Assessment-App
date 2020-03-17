package project;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * This class is used for creating a GUI to allow the user to search through the
 * records using multiple filters and seeing the records passing those filters
 * in a table.
 * @author vasug Vasu Gupta
 */
public class Project_Viewer {
    private TableView table;
    private ObservableList<Record> data;

    private VBox SearchVBox; // LHS
    private VBox tableVBox;  // RHS
    
    private HBox screenHBox; // For holding LHS and RHS
    private Scene scene;
    
    private TextField accNumField;
    private TextField addressField;
    private TextField neighField;
    private ChoiceBox<String> assessmentClassChoiceBox;
    private Button searchButton;
    private Button clearButton;
            
    private Label statsLabel;
    
    private Stage primaryStage;

    /**
     * Constructor for the class. Sets the primary stage for the JavaFx Application
     * Sets up the GUI with the left hand side with search fields and
     * the right hand side with the an empty table with columns defined
     * @param primaryStage 
     */
    public Project_Viewer(Stage primaryStage) {
        this.primaryStage = primaryStage;
        setUpGUI();
    }
    /**
     * Sets the stats label on the left hand side with the String passed as param
     * @param result 
     */
    public void setStatsLabel(String result){
        statsLabel.setText(result);
    }
    /**
     * Returns the assessment class selected in the drop down menu
     * @return Selected class in the drop down. Null if nothing is selected.
     */
    public String getAssessmentClass(){
        return assessmentClassChoiceBox.getValue();
    }
    /**
     * Sets the assessment classes in the drop down as the values from the
     * dataset.
     * @param assClasses List of all the assessment classes in the dataset
     */
    public void setAssessmentClasses(List<String> assClasses){
        assessmentClassChoiceBox.getItems().addAll(assClasses);
    }
    /**
     * Gets the text in the neighbourhood text field on LHS
     * @return String storing the text in neighbourhood text field
     */
    public String getNeighbourhood(){
        return neighField.getText().trim();        
    }    
    /**
     * Gets the text in the address text field on LHS
     * @return String storing the text in address text field
     */
    public String getAddress(){
        return addressField.getText().trim();        
    }
    
    /**
     * Gets the text in the account number text field on LHS
     * @return String storing the text in account number text field
     */
    public String getAccNum(){
        return accNumField.getText().trim();
    }
    
    /**
     * Getting the search button
     * @return Search Button
     */
    public Button getSearchButton(){
        return searchButton;
    }
    /**
     * Getting the clear button
     * @return Clear Button
     */
    public Button getClearButton(){
        return clearButton;
    }
    /**
     * Sets all the fields to empty values
     */
    public void resetAllFields(){
        accNumField.setText("");
        addressField.setText("");
        neighField.setText("");
        assessmentClassChoiceBox.setValue(null);
    }
    /**
     * Update the table on the RHS in the GUI with the value in records
     * @param records Value(s) to be filled in the table
     */
    public void updateGUI(List<Record> records){
        data = FXCollections.observableArrayList(records);
        table.setItems(data);
        
    }
    /**
     * Setting up Search button and Clear button side by side on the Left Hand Side of the GUI
     * @param buttons 
     */
    public void setUpButtons(HBox buttons){
        VBox searchButtonVBox = new VBox(10); // set spacing between these nodes to 10

        searchButton = new Button();
        searchButton.setText("Search");
        searchButtonVBox.setAlignment(Pos.CENTER_LEFT);
        searchButtonVBox.getChildren().addAll(searchButton);
        
        
        VBox clearButtonVBox = new VBox(10); // set spacing between these nodes to 10

        clearButton = new Button();
        clearButton.setText("Clear");
        clearButtonVBox.setAlignment(Pos.CENTER_LEFT);
        clearButtonVBox.getChildren().addAll(clearButton);

        buttons.setAlignment(Pos.BASELINE_LEFT);
        buttons.getChildren().addAll(searchButtonVBox, clearButtonVBox);        
    }
    /**
     * Sets up the LHS of the GUI by creating search fields, buttons and labels.
     */
    public void setUpSearchBoxGUI(){
        SearchVBox = new VBox(10);    // set spacing between these nodes to 10
        SearchVBox.setPadding(new Insets(0, 0, 0, 10)); // Add padding to the left
        
        // Label for search Vbox
        Label assessmentLabel = new Label("Find Property Assessment");
        assessmentLabel.setFont(new Font("Ariel",20));
    
        // Label for Account Number
        Label accNumLabel = new Label("Account Number");
        accNumLabel.setFont(new Font("Ariel",15));

        // Text field for account number user input
        accNumField = new TextField();
        
        // Label for Address
        Label addressLabel = new Label("Address");
        addressLabel.setFont(new Font("Ariel",15));
        // Text field for address user input
        addressField = new TextField();

        // Label for Neighbourhood name
        Label neighLabel = new Label("Neighbourhood");
        neighLabel.setFont(new Font("Ariel",15));
        
        // Text field for neighbourhood user input
        neighField = new TextField();

        // Label for Assessment class
        Label assessmentClassLabel = new Label("Assessment class");
        assessmentClassLabel.setFont(new Font("Ariel", 15));
        
        // Choice box drop down menu
        assessmentClassChoiceBox = new ChoiceBox<>();
        assessmentClassChoiceBox.setPrefWidth(200);

        // Creating Search and clear buttons
        HBox buttons = new HBox(10);    // set spacing between these nodes to 10
        setUpButtons(buttons); // Sets up Search and Clear button
        
        // Label for Stats
        Label statsHeadingLabel = new Label("Edmonton Property Assessments");
        statsHeadingLabel.setFont(new Font("Ariel", 13));

        // Label for Stats
        statsLabel = new Label("");
        statsLabel.setFont(new Font("Ariel", 13));
        statsLabel.setStyle("-fx-background-color: #ffffff");
        statsLabel.setMinSize(200, 200);
        statsLabel.setAlignment(Pos.TOP_LEFT);
        
        
        SearchVBox.setAlignment(Pos.CENTER_LEFT);
        SearchVBox.getChildren().addAll(assessmentLabel, accNumLabel, 
                accNumField, addressLabel, addressField, neighLabel, neighField,
                assessmentClassLabel, assessmentClassChoiceBox, buttons, statsHeadingLabel, statsLabel
                );   
    }
    /**
     * Sets up the RHS with the table structure (with columns), but no data in 
     * the table
     * @param propertyLabel 
     */
    public void setUpTableBoxGUI(Label propertyLabel){
        tableVBox = new VBox(10);
        tableVBox.setPadding(new Insets(0, 0, 0, 10));
        
        List<Record> records = new ArrayList<>(); // Empty records
        createSimpleTable(records); // 'table' is populated with data
        
        tableVBox.setAlignment(Pos.CENTER_LEFT);
        tableVBox.getChildren().addAll(propertyLabel,table);
        
    }
    /**
     * Sets up the GUI with the left hand side with search fields and
     * the right hand side with the an empty table with columns defined
     */
    public void setUpGUI(){        
        screenHBox = new HBox(50);     // creating a horizontal container
        
        primaryStage.setTitle("Edmonton Property Data"); // Title of the window
        
        Label propertyLabel = new Label("Edmonton Properties");
        propertyLabel.setFont(new Font("Ariel",20));
        
        // Setting up the search box on the LHS
        setUpSearchBoxGUI();
        
        // Setting up the RHS for the table
        setUpTableBoxGUI(propertyLabel);
            
        screenHBox.setAlignment(Pos.BASELINE_LEFT);
        // Adding the SearchBox and the TableBox to the Screen
        screenHBox.getChildren().addAll(SearchVBox, tableVBox); 
        
        // Setting the scene
        scene = new Scene(screenHBox, 1350, 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
        
    /** 
     * Creating the table, set up columns, and specify the attributes to use
     * from the records.
     * .csv file
     */
    private void createSimpleTable(List<Record> records){   
        data = FXCollections.observableArrayList(records);

        TableColumn<Record, Long> account = new TableColumn<>("Account");
        account.setMinWidth(150);
        account.setCellValueFactory(new PropertyValueFactory("accNum"));
        
        TableColumn address = new TableColumn("Address");
        address.setMinWidth(200);
        address.setCellValueFactory(new PropertyValueFactory("address"));
        
        TableColumn assessed_val = new TableColumn("Assessed Value");
        assessed_val.setMinWidth(150);
        assessed_val.setCellValueFactory(new PropertyValueFactory("assessedVal"));
        
        TableColumn assessment_class = new TableColumn("Assessment Class");
        assessment_class.setMinWidth(150);
        assessment_class.setCellValueFactory(new PropertyValueFactory("assessmentClass"));
        

        TableColumn neighbourhood = new TableColumn("Neighbourhood");
        neighbourhood.setMinWidth(150);
        neighbourhood.setCellValueFactory(new PropertyValueFactory("neighbourhoodInfo"));

        TableColumn latitude = new TableColumn("Latitude");
        latitude.setMinWidth(100);
        latitude.setCellValueFactory(new PropertyValueFactory("latitude"));

        TableColumn longitude = new TableColumn("Longitude");
        longitude.setMinWidth(100);
        longitude.setCellValueFactory(new PropertyValueFactory("longitude"));

        table = new TableView<>();
        table.setEditable(false);
        table.setPrefSize(1000, 1500);
        table.getColumns().addAll(account, address, assessed_val, assessment_class, neighbourhood, latitude, longitude);
        table.setItems(data);
    }
}
