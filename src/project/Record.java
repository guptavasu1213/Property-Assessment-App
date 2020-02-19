/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.math.BigDecimal;

/**
 * Contains the record for a property including the account number, 
 * the address (Suite number, House number, Street name), Neighbouhood information
 * (neighbourhood ID, neighbourhood name, ward name, and assessment class ), 
 * Coordinates of the location (latitude and longitude), the assessed value of
 * the property, and information about presence of a garage.
 * @author vasug
 */
public class Record {
    private final Long _accNum;
    private Long _assessedVal;    
    private String _garagePresent; 
    
    private Address _address;
    private Neighbourhood _neighbourhoodInfo;        
    private Coordinates _coordinates;
    
    
    /**
     * Getter for account number
     * @return Account number
     */
    public Long getAccNum(){
        return _accNum;
    }
    /**
     * Getter for assessed value
     * @return Assessed value
     */
    public Long getAssessedVal(){
        return _assessedVal;
    }
    /**
     * Getter for garage presence. Either "Y" or "N"
     * @return "Y" or "N"
     */
    public String getGarageStatus(){
        return _garagePresent;
    }
    /**
     * Getter for Address object
     * @return Address object
     */
    public Address getAddress(){
        return _address;
    }
    /**
     * Getter for Neighbourhood object
     * @return Neighbourhood object
     */    
    public Neighbourhood getNeighbourhood(){
        return _neighbourhoodInfo;
    }
    
    /**
     * Getter for Coordinates object
     * @return Coordinates objects
     */
    public Coordinates getCoordinates(){
        return _coordinates;
    }

    /**
     * Checks if the class has all attributes set to null
     * @return True when all attributes and sub-attributes are null
     */    
    public boolean isEmpty(){
        return this._accNum == null && this._assessedVal == null && 
                this._garagePresent == null &&
                this._address.getHouseNum() == null && 
                this._address.getStreetName() == null &&
                this._address.getSuite() == null &&
                this._coordinates.getLatitude() == null &&
                this._coordinates.getLongitude() == null &&
                this._neighbourhoodInfo.getAssessmentClass() == null &&
                this._neighbourhoodInfo.getID() == null &&
                this._neighbourhoodInfo.getName() == null &&
                this._neighbourhoodInfo.getWardName() == null;
    }
    
    /**
     * Constructor for Record class
     * @param accNum Account number 
     * @param suite Suite Name/Number
     * @param houseNum House Number
     * @param streetName Street Name
     * @param assessedVal Assessed value of the property
     * @param assessmentClass Assessment class (Residential/Non-Residential)
     * @param neighbourhoodID Neighbourhood ID
     * @param neighbourhoodName Neighbourhood Name
     * @param wardName Ward Name 
     * @param garagePresent "Y" or "N" denoting the presence/absence of a garage
     * @param latitude Latitude of the property
     * @param longitude Longitude of the property
     */
    public Record(Long accNum, String suite, Integer houseNum, String streetName, 
            Long assessedVal, String assessmentClass, Integer neighbourhoodID, 
            String neighbourhoodName, String wardName, String garagePresent, 
            BigDecimal latitude, BigDecimal longitude){
        
        this._accNum = accNum;
        this._assessedVal = assessedVal;
        this._garagePresent = garagePresent;

        this._address = new Address(suite, houseNum, streetName);
        this._neighbourhoodInfo = new Neighbourhood(assessmentClass,
            neighbourhoodID, neighbourhoodName, wardName);
        this._coordinates = new Coordinates(latitude, longitude);
    }
    
    /**
     * Getter for
     * @return 
     */
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