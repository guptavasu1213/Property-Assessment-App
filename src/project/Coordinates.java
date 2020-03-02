/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.math.BigDecimal;

/**
 * Coordinates class contains the longitude and latitude of the property
 * @author vasug
 */
public class Coordinates {
    private BigDecimal _latitude;
    private BigDecimal _longitude;    

    /**
     * Constructor for Coordinate class
     * @param latitude Latitude of property
     * @param longitude Longitude of property
     */
    public Coordinates(BigDecimal latitude, BigDecimal longitude){
        this._latitude = latitude;
        this._longitude = longitude;
    }
    
    /**
     * Getter for latitude
     * @return Latitude of property
     */
    public BigDecimal getLatitude(){
        return _latitude;
    }
    /**
     * Getter for latitude
     * @return Longitude of property
     */
    public BigDecimal getLongitude(){
        return _longitude;
    }

}
