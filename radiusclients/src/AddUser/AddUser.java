/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AddUser;

import java.sql.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ZORO
 */
public class AddUser {
    private String user;
    private String pass;
    private int timeOUt;
    private String url;
    private Connection connection;
    public AddUser(){
        try {
            System.out.println("Connecting database...");
            String aurl = "jdbc:mysql://192.168.140.133:3306/radiusdb";
            this.connection = DriverManager.getConnection(aurl, "radiususer", "radiuspass");
            System.out.println("Connect Success!");
        } catch (SQLException ex) {
            Logger.getLogger(AddUser.class.getName()).log(Level.SEVERE, null, ex);
        }
}
}
