/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

/**
 *
 * @author Leeroy :: TERMINE
 */
public class Retour extends PersistantClass{
    
    private Date dateRetour;
    private long montant;
    private int id;
    private int idEmprunt;
    String IDGest;
    
    public Retour(int id){
        this.copy(rechercherParID(id));
    }
    
    public Retour(Date d, long m, int id, int ide, String idg){
        this.dateRetour = d;
        this.id = id;
        this.idEmprunt = ide;
        this.montant = m;
        this.IDGest = idg;
    }
    
    public void copy(Retour d){
        this.dateRetour = d.dateRetour;
        this.id = d.id;
        this.idEmprunt = d.idEmprunt;
        this.montant = d.montant;
        this.IDGest = d.IDGest;
    }
    
    public Retour(long m, int ide, String idg){
        this.idEmprunt = ide;
        this.montant = m;
        this.IDGest = idg;
    }
    
    public String getIDGest(){
        return IDGest;
    }
    
    public Retour rechercherParID(int id){
        Retour p = null;
        try{
            String sql;
            sql = "SELECT * FROM retour WHERE id="+id;
            ResultSet rs = getFromDB(sql);
            
            while(rs.next()){//Date d, long m, int id, int ide){
                p =  new Retour(rs.getDate("dateRetour"), rs.getInt("montant"), rs.getInt("id")
                         , rs.getInt("idEmprunt"), rs.getString("IDGest"));
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
        Emprunt e = new Emprunt(idEmprunt);
        if(!e.isRembourse()){
            
            executeQuerry("insert into retour set idEmprunt="+idEmprunt+", montant="+montant+", IDGest ='"+IDGest+"'");
            id = Integer.parseInt(getInformationFromDB("select max(id) from retour where idEmprunt="+idEmprunt+" and montant="+montant));
            
            new Fond(e.getIdFond()).ajouterSolde(montant);
            int som = 0;
            for(Retour s:e.getListeRetour()){
                som+=s.getMontant();
            }
            if (som+montant >= e.calculerMontantRetour()) e.setRembourse(true);
            return true;
        }
        else return false;
    }
    
    public Date getDateRetour() {
        return dateRetour;
    }

    public long getMontant() {
        return montant;
    }

    public int getId() {
        return id;
    }

    public int getIdEmprunt() {
        return idEmprunt;
    }
    
}
