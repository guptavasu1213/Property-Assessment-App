/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

/**
 *
 * @author vasug
 */
public class Address {
    private String _suite;
    private Integer _houseNum;
    private String _streetName;
    
    // Constructor
    public Address(String suite, Integer houseNum, String streetName){
        this._suite = suite;
        this._houseNum = houseNum;
        this._streetName = streetName;
    }
    // Getters
    public Integer getHouseNum(){
        return _houseNum;
    }
    public String getSuite(){
        return _suite;
    }
    public String getStreetName(){
        return _streetName;
    }
}