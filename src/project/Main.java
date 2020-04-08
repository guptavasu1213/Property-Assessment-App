/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Driver of the application
 * @author vasug
 */
public class Main extends Application {
    Project_Controller controller;
    Project_Model model;
    Project_Viewer viewer;
    
    @Override
    /**
     * Runs at the launch of JavaFX Application
     */
    public void start(Stage primaryStage) {
        model = new Project_Model();
        viewer = new Project_Viewer(primaryStage);
        controller = new Project_Controller(model, viewer);
        // Showing all the records from the model to the tables in the viewer GUI
        controller.displayAllRecords();
    }

    /**
     * Launches the JavaFx application
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
