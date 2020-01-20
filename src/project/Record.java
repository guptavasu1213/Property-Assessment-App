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
    private String _suite;
    private Integer _houseNum;
    private String _streetName;
    private Long _assessedVal;
    private String _assessmentClass;
    private Integer _neighbourhoodID;
    private String _neighbourhoodName;
    private String _wardName;
    private boolean _garagePresent; // Y = true; N = False
    private BigDecimal _latitude;
    private BigDecimal _longitude;
    
    // Getters
    public Long getAccNum(){
        return _accNum;
    }
    public String getSuite(){
        return _suite;
    }
    public Integer getHouseNum(){
        return _houseNum;
    }
    public String getStreetName(){
        return _streetName;
    }
    public Long getAssessedVal(){
        return _assessedVal;
    }
    public String getAssessmentClass(){
        return _assessmentClass;
    }
    public Integer getNeighbourhoodID(){
        return _neighbourhoodID;  
    }
    public String getNeighbourhoodName(){
        return _neighbourhoodName;
    }
    public String getWardName(){
        return _wardName;
    }
    public String getGarageStatus(){
        if (_garagePresent == true){
            return "Y";
        }
        else{
            return "N";
        }
    }
    public BigDecimal getLatitude(){
        return _latitude;
    }
    public BigDecimal getLongitude(){
        return _longitude;
    }
    
    
    
    private static BigDecimal checkEmptyArgumentBigDecimal(String arg){
        if (arg.trim().equals("")){
            return null;
        }
        else{
            return new BigDecimal(arg);
        }
    }
    
    private static Integer checkEmptyArgumentInteger(String arg){
        if (arg.trim().equals("")){
            return null;
        }
        else{
            return Integer.parseInt(arg);
        }
    }
    private static Long checkEmptyArgumentLong(String arg){
        if (arg.trim().equals("")){ // CONFIRM FOR STRINGS£££££££££££££
            return null;
        }
        else{
            return Long.parseLong(arg);
        }
    }
    /**
     *
     * @param arg
     * @return
     */
    private static String checkEmptyArgumentString(String arg){
        if (arg.trim().equals("")){
            return null;
        }
        else{
            return arg;
        }
    }
    
    // Constructor
    public Record(String accNum, String suite, String houseNum, String streetName, 
            String assessedVal, String assessmentClass, String neighbourhoodID, 
            String neighbourhoodName, String wardName, String garagePresent, 
            String latitude, String longitude){
        this._suite =  checkEmptyArgumentString(suite);
        this._accNum = checkEmptyArgumentLong(accNum);
        this._houseNum = checkEmptyArgumentInteger(houseNum);
        this._streetName = checkEmptyArgumentString(streetName);
        this._assessedVal = checkEmptyArgumentLong(assessedVal);
        this._assessmentClass = checkEmptyArgumentString(assessmentClass);
        this._neighbourhoodID = checkEmptyArgumentInteger(neighbourhoodID);
        this._neighbourhoodName = checkEmptyArgumentString(neighbourhoodName);
        this._wardName = checkEmptyArgumentString(wardName);
        this._garagePresent = garagePresent.equals("Y"); // return true or false
        this._latitude = checkEmptyArgumentBigDecimal(latitude);
        this._longitude = checkEmptyArgumentBigDecimal(longitude);
    }
    @Override
    public String toString(){
        return String.format(
                "Account Number: %10d\tSuite: %7s\tLongitude: %f", 
                this._accNum, this._suite, this._longitude);
//        return "Account Number: " + this.accNum +
//                "\tSuite: " + this.suite +
//                "\tAssessed Value: " + this.assessedVal +
//                "\tLongitude: " + this.longitude;
    }
}