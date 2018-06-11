/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Leeroy
 */
public class Souscription extends PersistantClass{
    
    Date dateSouscription;
    int nombre;
    int idPart;
    int id;
    String idGest;
    
    public Souscription(int id){
        this.copy(rechercherParID(id));
    }
    
    public Souscription(int idPart, int nombre, String idGest){
        this.idPart = idPart;
        this.nombre = nombre;
        this.idGest = idGest;
    }
    
    public Souscription(int idPart, int nombre, Date d, int id, String idGest){
        this.id = id;
        this.dateSouscription = d;
        this.nombre = nombre;
        this.idPart = idPart;
        this.idGest = idGest;
    }
    
    public void copy(Souscription s){
        this.id = s.id;
        this.dateSouscription = s.dateSouscription;
        this.nombre = s.nombre;
        this.idPart = s.idPart;
        this.idGest = s.idGest;
    }
    
    public boolean enregistrer() throws SQLException{
        boolean b = executeQuerry("insert into souscription set idPart="+idPart+", nombre="+nombre+", IDGest ='"+idGest+"'");
        id = Integer.parseInt(getInformationFromDB("select max(id) from souscription where idPart="+idPart));
        ResultSet rs = getFromDB("select dateSouscription from souscription where id="+id);
        while(rs.next()){
            dateSouscription = rs.getDate("dateSouscription");
        }
        System.out.println("Une nouvelle souscription de "+nombre*Part.VALEUR+", le fond a maintenant "+(new Fond(new Part(idPart).getIdFond()).getDepotInitial()));
        return b;
    }
    
    public Souscription rechercherParID(int id){
        Souscription p = null;
        try{
            String sql;
            sql = "SELECT * FROM souscription WHERE id="+id;
            ResultSet rs = getFromDB(sql);
            
            while(rs.next()){//int idPart, int nombre, Date d, int id)
                p =  new Souscription(rs.getInt("idPart"), rs.getInt("nombre"), rs.getDate("dateSouscription")
                         , rs.getInt("id"), rs.getString("IDGest"));
            }

         } catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
         }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
         }//end try
        return p;
    }
    
}
