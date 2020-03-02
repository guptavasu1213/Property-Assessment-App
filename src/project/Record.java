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
    private final Long accNum;
    private Long assessedVal;    
    private String garagePresent; 
    private String assessmentClass;
    
    private Address address;
    private Neighbourhood neighbourhoodInfo;        
    private Coordinates coordinates;
    
    
    /**
     * Getter for account number
     * @return Account number
     */
    public Long getAccNum(){
        return accNum;
    }
    /**
     * Getter for assessed value
     * @return Assessed value
     */
    public Long getAssessedVal(){
        return assessedVal;
    }
    /**
     * Getter for garage presence. Either "Y" or "N"
     * @return "Y" or "N"
     */
    public String getGarageStatus(){
        return garagePresent;
    }
    /**
     * Getter for Address object
     * @return Address object
     */
    public Address getAddress(){
        return address;
    }
    /**
     * Getter for Neighbourhood object
     * @return Neighbourhood object
     */    
    public Neighbourhood getNeighbourhoodInfo(){
        return neighbourhoodInfo;
    }
    public String getAssessmentClass(){
        return assessmentClass;
    }
    /**
     * Getter for Coordinates object
     * @return Coordinates objects
     */
    public Coordinates getCoordinates(){
        return coordinates;
    }

    /**
     * Checks if the class has all attributes set to null
     * @return True when all attributes and sub-attributes are null
     */    
    public boolean isEmpty(){
        return this.accNum == null && this.assessedVal == null && 
                this.garagePresent == null &&
                this.address.getHouseNum() == null && 
                this.address.getStreetName() == null &&
                this.address.getSuite() == null &&
                this.coordinates.getLatitude() == null &&
                this.coordinates.getLongitude() == null &&
                this.assessmentClass == null &&
                this.neighbourhoodInfo.getID() == null &&
                this.neighbourhoodInfo.getName() == null &&
                this.neighbourhoodInfo.getWardName() == null;
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
        
        this.accNum = accNum;
        this.assessedVal = assessedVal;
        this.garagePresent = garagePresent;
        this.assessmentClass = assessmentClass;
        
        this.address = new Address(suite, houseNum, streetName);
        this.neighbourhoodInfo = new Neighbourhood(neighbourhoodID, 
                neighbourhoodName, wardName);
        this.coordinates = new Coordinates(latitude, longitude);
    }  
    /**
     * Getter for
     * @return 
     */
    @Override
    public String toString(){
        return String.format(
                "Account Number: %10d\tSuite: %7s\tLongitude: %f", 
                this.accNum, this.address.getSuite(), this.coordinates.getLongitude());
//        return "Account Number: " + this.accNum +
//                "\tSuite: " + this.suite +
//                "\tAssessed Value: " + this.assessedVal +
//                "\tLongitude: " + this.longitude;
    }
}