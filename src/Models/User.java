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
public class User {
    private int UserID;
    private String UserName;
    
    public User(int id, String name) {
        setUserID(id);
        setUserName(name);
    }
    
    public void setUserID(int uid) {
        UserID = uid;
    }
    
    public int getUserID() {
        return UserID;
    }
    
    public void setUserName(String uName) {
        UserName = uName;
    }
    
    public String getUserName() {
        return UserName;
    }
}
