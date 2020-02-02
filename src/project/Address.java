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
    private String _suite;
    private Integer _houseNum;
    private String _streetName;
    
    /**
     * Constructor for Address class
     * @param suite Suite number 
     * @param houseNum House number 
     * @param streetName Street name
     */
    public Address(String suite, Integer houseNum, String streetName){
        this._suite = suite;
        this._houseNum = houseNum;
        this._streetName = streetName;
    }
    /**
     * Getter for house number 
     * @return house number
     */
    public Integer getHouseNum(){
        return _houseNum;
    }
    /**
     * Getter for suite name
     * @return suite name
     */
    public String getSuite(){
        return _suite;
    }
    /**
     * Getter for street name 
     * @return street name
     */
    public String getStreetName(){
        return _streetName;
    }
}