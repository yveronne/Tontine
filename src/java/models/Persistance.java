/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Leeroy
 */
public interface Persistance {

    public  ResultSet getFromDB(String req);
    public boolean executeQuerry(String req);
    public ArrayList<String> getListInformationFromDB(String req);
    
}
