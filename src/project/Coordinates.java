/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.math.BigDecimal;

/**
 *
 * @author vasug
 */
class Coordinates {
    private BigDecimal _latitude;
    private BigDecimal _longitude;    

    // Constructor
    public Coordinates(BigDecimal latitude, BigDecimal longitude){
        this._latitude = latitude;
        this._longitude = longitude;
    }
    
    // Getters
    public BigDecimal getLatitude(){
        return _latitude;
    }
    public BigDecimal getLongitude(){
        return _longitude;
    }

}
