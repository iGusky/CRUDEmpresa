/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uv.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gusky
 */
public class ConexionDB {
    private static ConexionDB con = null;
    private Connection connection = null;
    
    public static ConexionDB getInstance() {
        
        if(con == null){
            con = new ConexionDB();
        } 
        return con;
    }
    
    public Connection getConection(){
        return connection;
    }
    
    //Constructor privatizado para poder hacer singleton.
    private ConexionDB() {
        System.out.println("Se creó una instancia");
        String url = "jdbc:postgresql://localhost:5432/";
        String uname = "";
        String password = "";
        
        try{
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, uname, password);    
                
        } catch (SQLException e){         
            System.err.println("Problema de conexión\n"+e);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean execute(String query){
        boolean res = false;
        try {
            Statement st = connection.createStatement();
            st.execute(query);
            res=true;
        } catch (Exception e) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, e);
        }
        return res;
    }
    
    public ResultSet query(String query) {
        ResultSet rs = null;
        try {
            Statement st = connection.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
}
