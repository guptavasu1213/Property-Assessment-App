/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        primaryStage.setTitle("Employee Example");
        
        // For Label + table 
        // placed vertically (above one another)
        VBox vb1 = new VBox(5);    // set spacing between these nodes to 10
        
        // For Label + text field 
        // placed vertically (above one another)
        VBox vb2 = new VBox(5);     // set spacing between these nodes to 5
        
        // vb1 and vb2 will be placed horizontally 
        HBox hb = new HBox(50);     // set spacing between vb1 and vb2 to 20
        
        // set the scene
        Scene scene = new Scene(hb, 1350, 800);
       
        Label label1 = new Label("Employee Data");
        label1.setFont(new Font("Ariel",20));
        createSimpleTable();
        
        vb1.setAlignment(Pos.CENTER_LEFT);
        vb1.getChildren().addAll(label1,table);
        
        Label label2 = new Label("Name");
        label2.setFont(new Font("Ariel",16));
    
        TextField tf = new TextField();
        
        vb2.setAlignment(Pos.CENTER_LEFT);
        vb2.getChildren().addAll(label2, tf);
        
        hb.setAlignment(Pos.BASELINE_LEFT);
        hb.getChildren().addAll(vb2, vb1);
        
 
        primaryStage.setScene(scene);
        primaryStage.show();

        /*
        primaryStage.setTitle("Employee Example");
        
        
        // For Label + text field 
        // placed vertically (above one another)
//        VBox vb2 = new VBox(5);     // set spacing between these nodes to 5
        
        // vb1 and vb2 will be placed horizontally 
//        HBox hb = new HBox(50);     // set spacing between vb1 and vb2 to 20
        
        // set the scene
       
//        Label label1 = new Label("Employee Data");
//        label1.setFont(new Font("Ariel",20));
        createSimpleTable();
        
        // For Label + table 
        // placed vertically (above one another)
        VBox vb1 = new VBox();    // set spacing between these nodes to 10
        

//        vb1.setAlignment(Pos.CENTER_LEFT);
        vb1.getChildren().addAll(table);
        
//        hb.setAlignment(Pos.BASELINE_LEFT);
//        hb.getChildren().addAll(vb2, vb1);
//        
        Scene scene = new Scene(vb1, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
        */
    }
    private void createSimpleTable(){   
        Project lab4 = new Project();
        String fileName = lab4.getFileName();
        
        ArrayList<Record> records = lab4.setUpRecords(fileName); 
        data = FXCollections.observableArrayList(records);

        lab4.allRecordsStats();
                    
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
        table.setPrefWidth(1000);
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
