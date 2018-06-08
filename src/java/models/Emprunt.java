/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;

/**
 *
 * @author Leeroy :: TERMINE
 */
public class Emprunt extends PersistantClass{
    
    public static final double INTERET=0.1;
    
    private Date dateEmprunt;
    private long montant;
    private boolean rembourse;
    private int id;
    private String matricule;
    private int idFond;
    String IDGest;
    
    public Emprunt(int id){
        this.copy(rechercherParID(id));
    }
    
    public Emprunt(Date d, long m, boolean rem, int id, String mat, int idf, String idg){
        this.dateEmprunt = d;
        this.id = id;
        this.idFond = idf;
        this.matricule = mat;
        this.montant = m;
        this.rembourse = rem;
        this.IDGest = idg;
    }
    
    public void copy(Emprunt d){
        this.dateEmprunt = d.dateEmprunt;
        this.id = d.id;
        this.idFond = d.idFond;
        this.matricule = d.matricule;
        this.montant = d.montant;
        this.rembourse = d.rembourse;
        this.IDGest = d.IDGest;
    }
    
    public Emprunt(long m, String mat, int idf, String IDGest){
        this.idFond = idf;
        this.matricule = mat;
        this.montant = m;
        this.IDGest = IDGest;
    }
    
    public Date getDateEmprunt() {
        return dateEmprunt;
    }
    
    public String getIDGest(){
        return IDGest;
    }
    
    public int getId() {
        return id;
    }

    public String getMatricule() {
        return matricule;
    }

    public int getIdFond() {
        return idFond;
    }

    public long getMontant() {
        return montant;
    }

    public boolean isRembourse() {
        return rembourse;
    }

    public void setRembourse(boolean rembourse) {
        executeQuerry("update emprunt set rembourse="+true+" where id="+this.id);
        this.rembourse = rembourse;
    }
    
    public ArrayList<Retour> getListeRetour(){
        ArrayList<Retour> lr = new ArrayList();
        ArrayList<String> l = getListInformationFromDB("select id from retour where idEmprunt="+id);
        for(String s:l){
            lr.add(new Retour(Integer.parseInt(s)));
        }
        return lr;
    }
    
    public long calculerMontantRetour(){
        java.util.Date now = new java.util.Date();
        java.util.Date two = new java.util.Date(dateEmprunt.getTime());
        
        long difference =  Math.abs((now.getTime()-two.getTime())/86400000);
        return Math.round((1+ Emprunt.INTERET*(1+difference/31))*montant);
    }
    
    public Emprunt rechercherParID(int id){//Emprunt(Date d, long m, double inte, boolean rem, int id, String mat, int idf)
        Emprunt p = null;
        try{
            String sql;
            sql = "SELECT * FROM emprunt WHERE id="+id;
            ResultSet rs = getFromDB(sql);
            
            while(rs.next()){
                p =  new Emprunt(rs.getDate("dateEmprunt"), rs.getInt("montant"), rs.getBoolean("rembourse")
                         , rs.getInt("id"), rs.getString("matricule"), rs.getInt("idFond"), rs.getString("IDGest"));
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
        boolean b =executeQuerry("insert into emprunt set matricule='"+matricule+"', montant="+montant+", idFond="+idFond+", IDGest='"+IDGest+"'");
        if (b) id = Integer.parseInt(getInformationFromDB("select id from emprunt where matricule='"+matricule+"' and idFond="+idFond));
        return b;
    }
}
