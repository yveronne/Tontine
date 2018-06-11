/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leeroy
 */
public abstract class PersistantClass implements Persistance{
    
    public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    public static final String DB_URL = "jdbc:mysql://localhost/tontinebd";
    public static final String USER = "root";
    public static final String PASS = "";
    public static Statement stmt = null;
    public static Connection conn = null;
    
    public ResultSet getFromDB(String req){
        ResultSet rs = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();
            String sql;
            rs = stmt.executeQuery(req);
        } catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
         }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
         }
        return rs;
    }
    
    public String getInformationFromDB(String req){

        ResultSet rs = getFromDB(req);
        String res = null;
        if (rs==null) {
            throw new RuntimeException();
        }
        try{
            while(rs.next()){
                res = rs.getString(1);
            }
        } catch (SQLException ex) { 
            Logger.getLogger(PersistantClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
    
    public ArrayList<String> getListInformationFromDB(String req){
        ResultSet rs = getFromDB(req);
        ArrayList<String> liste= new ArrayList();
        if (rs==null) throw new RuntimeException();
        try{
            while(rs.next()){
                liste.add(rs.getString(1));
            }
        } catch (SQLException ex) { 
            Logger.getLogger(PersistantClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        return liste;
    }
    
    public boolean executeQuerry(String req){
        
        boolean res = true;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();
            stmt.executeUpdate(req);
            conn.close();
        } catch(SQLException se){
            //Handle errors for JDBC
            res = false;
            se.printStackTrace();
         }catch(Exception e){
            //Handle errors for Class.forName
            res = false;
            e.printStackTrace();
         }
        return res;
    }
    
}
