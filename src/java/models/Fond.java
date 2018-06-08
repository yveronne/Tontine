/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Leeroy
 */
public class Fond extends PersistantClass{
    
    private Date dateDebutEmprunts;
    private Date dateFinEmprunts;
    private long depotInitial;
    private long soldeDisponible;
    private int id;
    private String IDGest;

    public Fond(int id){
        this.copy(rechercherParID(id));
    }
    
    public Fond(){
        
    }
    
    public Fond(Date d1, Date d2, long dpi, long sd, String idgest){
        dateDebutEmprunts = d1;
        dateFinEmprunts = d2;
        depotInitial = dpi;
        soldeDisponible = sd;
        IDGest = idgest;
    }
    
    public Fond(int id, Date d1, Date d2, long dpi, long sd, String idGest){
        dateDebutEmprunts = d1;
        dateFinEmprunts = d2;
        depotInitial = dpi;
        soldeDisponible = sd;
        this.id = id;
        this.IDGest = idGest;
    }
    
    public int getId() {
        return id;
    }

    public Date getDateDebutEmprunts() {
        return dateDebutEmprunts;
    }


    public void copy(Fond d){
        dateDebutEmprunts = d.dateDebutEmprunts;
        dateFinEmprunts = d.dateFinEmprunts;
        depotInitial = d.depotInitial;
        soldeDisponible = d.soldeDisponible;
        id = d.id;
        this.IDGest = d.IDGest;
    }
    
    public String getIDGest(){
        return IDGest;
    }
    
    public Date getDateFinEmprunts() {
        return dateFinEmprunts;
    }

    public long getDepotInitial() {
        return depotInitial;
    }

    public long getSoldeDisponible() {
        return soldeDisponible;
    }
    
    public boolean ajouterDepot(long montnant){
        boolean c = executeQuerry("update fond set depotInitial="+(depotInitial+montnant)+" where id ="+id);
        if (c) c = executeQuerry("update fond set soldeDisponible="+(soldeDisponible+montnant)+" where id ="+id);
        if (c) depotInitial+=montnant;
        if (c) soldeDisponible+=montnant;
        return c;
    }
    
    public boolean ajouterSolde(long montnant){
        boolean c = executeQuerry("update fond set soldeDisponible="+(soldeDisponible+montnant)+" where id ="+id);
        if (c) soldeDisponible+=montnant;
        return c;
    }
    
    public boolean diminuerSolde(long montnant){
        boolean c = executeQuerry("update fond set soldeDisponible="+(soldeDisponible-montnant)+" where id ="+id);
        if (c) soldeDisponible-=montnant;
        return c;
    }
    
    public boolean creerFond(){
        
        Date now = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        int e = Integer.parseInt(getInformationFromDB("select count(id) from fond where dateDebutEmprunts < '"+now+"' and dateFinEmprunts > '"+now+"'"));
        if (e>0) return false;
        
        boolean b = executeQuerry("insert into fond set dateDebutEmprunts='"+dateDebutEmprunts+"', dateFinEmprunts='"+dateFinEmprunts+"', depotInitial="+depotInitial+", soldeDisponible="+soldeDisponible+", IDGest='"+IDGest+"'");
        if (b) this.id = Integer.parseInt(getInformationFromDB("select max(id) from fond"));
        return b;
    }
    
    public Fond trouverFondActuel(){
        String s = "select id from fond where dateFinEmprunts > '"
                +(new java.sql.Date(Calendar.getInstance().getTime().getTime()))+"'";System.out.println(s);
        return new Fond(Integer.parseInt(getInformationFromDB(s)));
    }
    
    Fond rechercherParID(int id){
        Fond p = null;
        try{
            String sql;
            sql = "SELECT * FROM fond WHERE id="+id;
            ResultSet rs = getFromDB(sql);
            
            while(rs.next()){//public Fond(Date d1, Date d2, long dpi, long sd)
                p =  new Fond(rs.getInt("id"), rs.getDate("dateDebutEmprunts"), rs.getDate("dateFinEmprunts"), rs.getLong("depotInitial"), rs.getLong("soldeDisponible"), rs.getString("IDGest"));
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
    
    public ArrayList<Adherent> getListAdherent(){
        ArrayList<Adherent> la = new ArrayList();
        ArrayList<String> l = getListInformationFromDB("select matricule from part where idFond="+id);
        for(String s:l){
            la.add(new Adherent(s));
        }
        return la;
    }
    
    public ArrayList<Part> getListeParts(){
        ArrayList<Part> lr = new ArrayList();
        ArrayList<String> l = getListInformationFromDB("select id from part where idFond="+id);
        for(String s:l){
            lr.add(new Part(Integer.parseInt(s)));
        }
        return lr;
    }
    
    public ArrayList<Suspension> getListeTouteSuspension(){
        ArrayList<Suspension> lr = new ArrayList();
        ArrayList<String> l = getListInformationFromDB("select id from suspension");
        for(String s:l){
            lr.add(new Suspension(Integer.parseInt(s)));
        }
        return lr;
    }
    
    public ArrayList<Suspension> getListeSuspensionActive(){
        ArrayList<Suspension> lr = new ArrayList();
        ArrayList<String> l = getListInformationFromDB("select id from suspension where dateFin > '"+(new java.sql.Date(Calendar.getInstance().getTime().getTime()))+"'");
        for(String s:l){
            lr.add(new Suspension(Integer.parseInt(s)));
        }
        return lr;
    }
    
    public ArrayList<Exclusion> getListeExclusion(){
        ArrayList<Exclusion> lr = new ArrayList();
        ArrayList<String> l = getListInformationFromDB("select id from exclusion");
        for(String s:l){
            lr.add(new Exclusion(Integer.parseInt(s)));
        }
        return lr;
    }
    
    public String toString(){
        return this.dateDebutEmprunts+"-"+this.dateFinEmprunts+"-"+this.id+"-"+this.IDGest+"-"+this.soldeDisponible;
    }
    
    public ArrayList<Emprunt> getListAllEmprunt(){
        ArrayList<Emprunt> le = new ArrayList();
        ArrayList<String> l = getListInformationFromDB("select id from emprunt where idFond="+id);
        for(String s:l){
            le.add(new Emprunt(Integer.parseInt(s)));
        }
        return le;
    }
}
