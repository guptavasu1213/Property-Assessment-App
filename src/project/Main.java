/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author vasug
 */
public class Main extends Application {
    Project_Controller controller;
    Project_Model model;
    Project_Viewer viewer;
    
    @Override
    public void start(Stage primaryStage) {
        model = new Project_Model();
        viewer = new Project_Viewer(primaryStage);
        controller = new Project_Controller(model, viewer);
        
        controller.displayAllRecords();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
