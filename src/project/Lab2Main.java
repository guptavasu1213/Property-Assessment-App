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
public class Lab2Main {
    public static void main(String[] args) {
        Project lab2 = new Project();
        String fileName = lab2.getFileName();
       
        lab2.setUpRecords(fileName);    
        // Part 1
        lab2.allRecordsStats();
        
        // Part 2
        lab2.findPropertyByAccount();
        
        // Part 3
        lab2.neighbourhoodStats();
    }
}
