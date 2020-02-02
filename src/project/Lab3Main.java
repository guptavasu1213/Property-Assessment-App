/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

/**
 * This is the driver class for the project
 * @author vasug
 */
public class Lab3Main {
    public static void main(String[] args) {
        Project lab3 = new Project();
        String fileName = lab3.getFileName();
        
        lab3.setUpRecords(fileName);    
        
        lab3.assessmentClassStats();
    }    
}
