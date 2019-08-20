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
public class City {
    private int CityID;
    private String CityName;
    
    public City(int cityID, String cityName) {
        this.setCityID(cityID);
        this.setCityName(cityName);
    }
    
    public void setCityID(int cityID) {
        CityID = cityID;
    }
    
    public int getCityID() {
        return CityID;
    }
    
    public void setCityName(String cityName) {
        CityName = cityName;
    }
    
    public String getCityName() {
        return CityName;
    }
}
