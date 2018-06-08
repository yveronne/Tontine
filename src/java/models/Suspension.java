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
public class Suspension extends PersistantClass{
    
    private Date dateDebut;
    private Date dateFin;
    private String matricule;
    private int id;
    private String IDGest;
    
    public Suspension(int id){
        this.copy(rechercherParID(id));
    }
    
    public Suspension(Date d, Date m, int id, String mat, String idg){
        this.dateDebut = d;
        this.id = id;
        this.dateFin = m;
        this.matricule = mat;
        this.IDGest = idg;
    }
    
    public void copy(Suspension d){
        this.dateDebut = d.dateDebut;
        this.id = d.id;
        this.matricule = d.matricule;
        this.dateFin = d.dateFin;
        this.IDGest = d.IDGest;
    }
    
    public Suspension(String mat, Date d, Date f, String idg){
        this.dateDebut = d;
        this.dateFin = f;
        this.matricule = mat;
        this.IDGest = idg;
    }
    
    public Suspension rechercherParID(int id){
        Suspension p = null;
        try{
            String sql;
            sql = "SELECT * FROM suspension WHERE id="+id;
            ResultSet rs = getFromDB(sql);
            
            while(rs.next()){//Date d, long m, int id, int ide){
                p =  new Suspension(rs.getDate("dateDebut"), rs.getDate("dateFin"), rs.getInt("id")
                         , rs.getString("matricule"), rs.getString("IDGest"));
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
        
        boolean b = executeQuerry("insert into suspension set dateDebut='"+dateDebut+"', matricule='"+matricule+"', dateFin='"+dateFin+"', IDGest='"+IDGest+"'");
        if (b) id = Integer.parseInt(getInformationFromDB("select max(id) from suspension where matricule='"+matricule+"' and dateDebut='"+dateDebut+"'"));
        return b;
    }
    
    public boolean isActive(){
        Date d = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        return (dateDebut.before(d) && dateFin.after(d));
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getMatricule() {
        return matricule;
    }

    public int getId() {
        return id;
    }
    
}
