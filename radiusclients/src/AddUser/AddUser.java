/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AddUser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    public AddUser() throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
            } catch (InstantiationException ex) {
                Logger.getLogger(AddUser.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(AddUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AddUser.class.getName()).log(Level.SEVERE, null, ex);
        }
               
        System.out.println("Connecting database...");
        this.url = "jdbc:mysql://192.168.140.133:3306/radiusdb";
        this.connection = DriverManager.getConnection(this.url,"radiususer","radiuspass");
        //                Class.forName("com.mysql.jdbc.Driver").newInstance();
        System.out.println("Connect Success!");
        stmt = this.connection.createStatement();
        rs = stmt.executeQuery("SELECT * FROM radcheck");
        while(rs.next()){
            System.out.println(rs.getString("username"));
            
        }
        

        } 
}

