/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import Models.User;
import javafx.util.StringConverter;

/**
 *
 * @author malic
 */
public class UserConverter extends StringConverter<User>{
    @Override
    public User fromString(String customerAsString) {
        String[] parts = customerAsString.split(" ");
        return new User(Integer.parseInt(parts[0]), parts[1]);
    }
    
    @Override
    public String toString(User user) {
        return user.getUserID() + " " + user.getUserName();
    }
}
