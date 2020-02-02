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
class Neighbourhood {
    private String _assessmentClass;
    private Integer _ID;    
    private String _Name;
    private String _wardName;
    
    // Constructor
    public Neighbourhood(String assessmentClass, Integer neighbourhoodID, String neighbourhoodName, String wardName){
        this._assessmentClass = assessmentClass;
        this._ID = neighbourhoodID;
        this._Name = neighbourhoodName;
        this._wardName = wardName;
    }
    
    // Getters
    public String getAssessmentClass(){
        return _assessmentClass;
    }
    public Integer getID(){
        return _ID;  
    }
    public String getName(){
        return _Name;
    }
    public String getWardName(){
        return _wardName;
    }    
}
