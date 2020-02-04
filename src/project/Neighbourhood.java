/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

/**
 * Neighbourhood class contains the neighbourhood ID, neighbourhood name, 
 * ward name, and the assessment class (residential/non-residential) of the 
 * property
 * @author vasug
 */
class Neighbourhood {
    private String _assessmentClass;
    private Integer _ID;    
    private String _Name;
    private String _wardName;
    
    /**
     * Constructor for Neighbourhood class
     * @param assessmentClass residential or non-residential
     * @param neighbourhoodID Neighbourhood ID
     * @param neighbourhoodName Neighbourhood Name 
     * @param wardName Ward name/number
     * @author vasug
     */
    public Neighbourhood(String assessmentClass, Integer neighbourhoodID, 
            String neighbourhoodName, String wardName){
        this._assessmentClass = assessmentClass;
        this._ID = neighbourhoodID;
        this._Name = neighbourhoodName;
        this._wardName = wardName;
    }
    
    /**
     * Getter for assessment class
     * @return assessment class
     * @author vasug
     */
    public String getAssessmentClass(){
        return _assessmentClass;
    }
    /**
     * Getter for Neighbourhood ID
     * @return Neighbourhood ID is returned
     * @author vasug
     */
    public Integer getID(){
        return _ID;  
    }
    /**
     * Getter for neighbourhood name
     * @return Neighbourhood name
     * @author vasug
     */
    public String getName(){
        return _Name;
    }
    /**
     * Getter for Ward name
     * @return ward name
     * @author vasug
     */
    public String getWardName(){
        return _wardName;
    }    
}
