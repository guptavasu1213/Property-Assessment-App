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
public class Record {
    private final Long _accNum;
    
    private Address _address;
    private Neighbourhood _neighbourhoodInfo;        
    private Coordinates _coordinates;
    
    private Long _assessedVal;    
    private String _garagePresent; 
    
    // Getters
    public Long getAccNum(){
        return _accNum;
    }
    public Long getAssessedVal(){
        return _assessedVal;
    }
    public String getGarageStatus(){
        return _garagePresent;
    }
    public Address getAddress(){
        return _address;
    }
    public Neighbourhood getNeighbourhood(){
        return _neighbourhoodInfo;
    }
    public Coordinates getCoordinates(){
        return _coordinates;
    }
    
    // Constructor ================????????
    public Record() {
        this._accNum = null;
    }
        
    // Constructor
    public Record(Long accNum, String suite, Integer houseNum, String streetName, 
            Long assessedVal, String assessmentClass, Integer neighbourhoodID, 
            String neighbourhoodName, String wardName, String garagePresent, 
            BigDecimal latitude, BigDecimal longitude){

        this._accNum = accNum;
        this._address = new Address(suite, houseNum, streetName);
        this._neighbourhoodInfo = new Neighbourhood(assessmentClass,
            neighbourhoodID, neighbourhoodName, wardName);
        this._coordinates = new Coordinates(latitude, longitude);
        
        this._assessedVal = assessedVal;
        this._garagePresent = garagePresent;
    }
    @Override
    public String toString(){
        return String.format(
                "Account Number: %10d\tSuite: %7s\tLongitude: %f", 
                this._accNum, this._address.getSuite(), this._coordinates.getLongitude());
//        return "Account Number: " + this.accNum +
//                "\tSuite: " + this.suite +
//                "\tAssessed Value: " + this.assessedVal +
//                "\tLongitude: " + this.longitude;
    }
}