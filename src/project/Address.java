/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

/**
 * Address class contains the suite number, house number and the street name
 * of the property
 * @author vasug
 */
public class Address {
    private String suite;
    private Integer houseNum;
    private String streetName;
    
    /**
     * Constructor for Address class
     * @param suite Suite number 
     * @param houseNum House number 
     * @param streetName Street name
     */
    public Address(String suite, Integer houseNum, String streetName){
        this.suite = suite;
        this.houseNum = houseNum;
        this.streetName = streetName;
    }
    /**
     * Getter for house number 
     * @return house number
     */
    public Integer getHouseNum(){
        return houseNum;
    }
    /**
     * Getter for suite name
     * @return suite name
     */
    public String getSuite(){
        return suite;
    }
    /**
     * Getter for street name 
     * @return street name
     */
    public String getStreetName(){
        return streetName;
    }

    @Override
    public String toString(){
        StringBuilder strBuilder = new StringBuilder();
        if (this.suite != null){
            strBuilder.append(this.suite + " ");
        }
        if (this.houseNum != null){
            strBuilder.append(this.houseNum + " ");
        }
        if (this.streetName != null){
            strBuilder.append(this.streetName);
        }
        return strBuilder.toString();
    }
}