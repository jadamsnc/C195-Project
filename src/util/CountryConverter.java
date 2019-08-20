/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import Models.Country;
import javafx.util.StringConverter;

/**
 *
 * @author malic
 */
public class CountryConverter extends StringConverter<Country>{
    @Override
    public Country fromString(String countryAsString) {
        String[] parts = countryAsString.split(" ");
        return new Country(Integer.parseInt(parts[0]), parts[1]);
    }
    
    @Override
    public String toString(Country country) {
        return country.getCountryID() + " " + country.getCountryName();
    }
    
}
