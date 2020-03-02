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
    private Integer _ID;    
    private String _Name;
    private String _wardName;
    
    /**
     * Constructor for Neighbourhood class
     * @param neighbourhoodID Neighbourhood ID
     * @param neighbourhoodName Neighbourhood Name 
     * @param wardName Ward name/number
     * @author vasug
     */
    public Neighbourhood(Integer neighbourhoodID, 
            String neighbourhoodName, String wardName){
        this._ID = neighbourhoodID;
        this._Name = neighbourhoodName;
        this._wardName = wardName;
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

    @Override
    public String toString(){
        return String.format(_Name);
    }    
}
