/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import Models.City;
import javafx.util.StringConverter;

/**
 *
 * @author malic
 */
public class CityConverter extends StringConverter<City> {
    @Override
    public City fromString(String cityAsString) {
        String[] parts = cityAsString.split(" ");
        return new City(Integer.parseInt(parts[0]), parts[1]);
    }
    
    @Override
    public String toString(City city) {
        return city.getCityID() + " " + city.getCityName();
    }
}
