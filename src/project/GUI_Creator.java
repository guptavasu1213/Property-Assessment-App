/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import static javafx.application.Application.launch;
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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 *
 * @author vasug
 */
public class GUI_Creator extends Application {
    TableView table;
    ObservableList<Record> data;
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Edmonton Property Data");
        // Setting up the data source
        Project lab4 = new Project();
        String fileName = lab4.getFileName();
        ArrayList<Record> records = lab4.setUpRecords(fileName); 
        
        // Creating search Vbox for the left-hand-side
        VBox SearchVBox = new VBox(10);    // set spacing between these nodes to 10
        SearchVBox.setPadding(new Insets(0, 0, 0, 10)); // Add padding to the left
        
        // Label for search Vbox
        Label assessmentLabel = new Label("Find Property Assessment");
        assessmentLabel.setFont(new Font("Ariel",20));
    
        // Label for Account Number
        Label accNumLabel = new Label("Account Number");
        accNumLabel.setFont(new Font("Ariel",15));

        // Text field for account number user input
        TextField accNumField = new TextField();
        // Event Listener
        accNumField.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent event) {
              try{
                  int UserAccNo = Integer.parseInt(accNumField.getText().trim());
                  System.out.println("Account num: " + UserAccNo);
              } catch(NumberFormatException ex){
                accNumField.setText("Invalid Account Number!");
              }
          }
        });
        
        // Label for Neighbourhood name
        Label addressLabel = new Label("Address");
        addressLabel.setFont(new Font("Ariel",15));
        // Text field for address user input
        TextField addressField = new TextField();
        // Event Listener
        addressField.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent event) {
              String userAddress = addressField.getText().trim();
              System.out.println("Address: " + userAddress);
          }
        });

        // Label for Neighbourhood name
        Label neighLabel = new Label("Neighbourhood");
        neighLabel.setFont(new Font("Ariel",15));

        // Text field for neighbourhood user input
        TextField neighField = new TextField();
        // Event Listener
        neighField.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent event) {
              String userNeighName = neighField.getText().trim();
              System.out.println("Neighbourhood: " + userNeighName);
          }
        });

        // Label for Neighbourhood name
        Label assessmentClassLabel = new Label("Account Number");
        assessmentClassLabel.setFont(new Font("Ariel",15));
        
        ChoiceBox<String> assessmentClassChoiceBox = new ChoiceBox<>();
        // Importing the assessment classes we have
        assessmentClassChoiceBox.getItems().addAll(lab4.getAssessmentClasses());
        // Event listener
        assessmentClassChoiceBox.setOnAction(new EventHandler<ActionEvent>() {
          public void handle(ActionEvent event) {
              String userAssessmentClass = assessmentClassChoiceBox.getValue();
              System.out.println("Class: " + userAssessmentClass);
          }
        });
        
        // Label for Stats
        Label statsLabel = new Label("Edmonton Property Assessments");
        statsLabel.setFont(new Font("Ariel", 13));
        
        SearchVBox.setAlignment(Pos.CENTER_LEFT);
        SearchVBox.getChildren().addAll(assessmentLabel, accNumLabel, 
                accNumField, addressLabel, addressField, neighLabel, neighField,
                assessmentClassLabel, assessmentClassChoiceBox, statsLabel);
        

        // Creating Vertical Box for the table on the right-hand-side
        VBox tableVBox = new VBox(10);     // set spacing between these nodes to 5
        tableVBox.setPadding(new Insets(0, 0, 0, 10));
        
        Label propertyLabel = new Label("Edmonton Properties");
        propertyLabel.setFont(new Font("Ariel",20));
        
        createSimpleTable(records); // 'table' is populated with data
        
        tableVBox.setAlignment(Pos.CENTER_LEFT);
        tableVBox.getChildren().addAll(propertyLabel,table);
        
        // SearchBox and TableBox will be placed horizontally in hb
        HBox screenHBox = new HBox(50);     // set spacing between vb1 and vb2 to 20
                
        // set the scene
        Scene scene = new Scene(screenHBox, 1350, 800);
        screenHBox.setAlignment(Pos.BASELINE_LEFT);
        screenHBox.getChildren().addAll(SearchVBox, tableVBox);
        
        // Setting the scene
        primaryStage.setScene(scene);
        primaryStage.show();

    }
    /**
     * Creating the table and populating the values in the table using Records
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
        latitude.setCellValueFactory(new PropertyValueFactory("_coordinates"));

        TableColumn longitude = new TableColumn("Longitude");
        longitude.setMinWidth(100);
        longitude.setCellValueFactory(new PropertyValueFactory("_coordinates"));

        table = new TableView<>();
        table.setEditable(false);
        table.setPrefSize(1000, 1500);
        table.getColumns().addAll(account, address, assessed_val, assessment_class, neighbourhood, latitude, longitude);
        table.setItems(data);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
