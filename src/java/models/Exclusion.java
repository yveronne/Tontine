/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

/**
 *
 * @author Leeroy
 */
public class Exclusion extends PersistantClass{
    
    private Date date;
    private String matricule;
    private int id;
    private String idGest;
    
    public Exclusion(int id){
        this.copy(rechercherParID(id));
    }
    
    public Exclusion(Date d, int id, String mat, String idg){
        this.date = d;
        this.id = id;
        this.matricule = mat;
        this.idGest = idg;
    }
    
    public void copy(Exclusion d){
        this.date = d.date;
        this.id = d.id;
        this.matricule = d.matricule;
        this.idGest = d.idGest;
    }
    
    public Exclusion(String mat, String idGest){
        this.matricule = mat;
        this.idGest = idGest;
    }
    
    public Exclusion rechercherParID(int id){
        Exclusion p = null;
        try{
            String sql;
            sql = "SELECT * FROM exclusion WHERE id="+id;
            ResultSet rs = getFromDB(sql);
            
            while(rs.next()){//Date d, long m, int id, int ide){
                p =  new Exclusion(rs.getDate("date"), rs.getInt("id")
                         , rs.getString("matricule"), rs.getString("idGest"));
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
    
    public boolean enregistrer(){
        
        boolean b = executeQuerry("insert into exclusion set matricule='"+matricule+"', idGest='"+idGest+"'");
        if (b) id = Integer.parseInt(getInformationFromDB("select max(id) from exclusion where matricule='"+matricule+"'"));
        return b;
    }

    public Date getDate() {
        return date;
    }

    public String getMatricule() {
        return matricule;
    }

    public int getId() {
        return id;
    }
    
    public String getIdGest(){
        return idGest;
    }

}
