/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author malic
 */
public class Country {
    private int CountryID;
    private String CountryName;
    
    public Country(int countryID, String countryName) {
        this.setCountryID(countryID);
        this.setCountryName(countryName);
    }
    
    public void setCountryID(int countryID) {
        CountryID = countryID;
    }
    
    public void setCountryName(String countryName) {
        CountryName = countryName;
    }
    
    public int getCountryID() {
        return CountryID;
    }
    
    public String getCountryName() {
        return CountryName;
    }
}
