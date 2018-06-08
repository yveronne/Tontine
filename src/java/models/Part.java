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

/**
 *
 * @author Leeroy :: TERMINE
 */
public class Part extends PersistantClass{
    
    public static final long VALEUR=10000;
    
    private int id;
    private int nombre;
    private Date dateSouscription;
    private String matricule;
    private int idFond;
    private String IDGest;
    
    public Part(){
        
    }
    
    public Part(int id, int m, Date da, String ma, int idf, String idg){
        this.dateSouscription = da;
        this.id = id;
        this.matricule = ma;
        this.nombre = m;
        this.idFond = idf;
        this.IDGest = idg;
    }
    
    public Part(int id){
        this.copy(rechercherParID(id));
    }
    
    public Part(int m, String ma, int idf, String idg){
        this.matricule = ma;
        this.nombre = m;
        this.idFond = idf;
        this.IDGest = idg;
    }
    
    public void copy(Part p){
        this.dateSouscription = p.dateSouscription;
        this.matricule = p.matricule;
        this.nombre = p.nombre;
        this.id = p.id;
        this.idFond = p.idFond;
        this.IDGest = p.IDGest;
    }

    public int enregistrer(){
        executeQuerry("insert into part set matricule='"+matricule+"', nombre="+nombre+", idFond="+idFond+", IDGest='"+IDGest+"'");
        Fond f = new Fond(idFond);
        f.ajouterDepot(VALEUR*nombre);
        id = Integer.parseInt(getInformationFromDB("select id from part where matricule='"+matricule+"' and idFond="+idFond));
        return id;
    }

    public Part rechercherParID(int id){
        Part p = null;
        try{
            String sql;
            sql = "SELECT * FROM part WHERE id="+id;
            ResultSet rs = getFromDB(sql);
            
            while(rs.next()){//Part(int id, int m, Date da, String ma, int idf){
                p =  new Part(rs.getInt("id"), rs.getInt("nombre"), rs.getDate("dateSouscription"), rs.getString("matricule"), rs.getInt("idFond"), rs.getString("IDGest"));
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

    public int getId() {
        return id;
    }
    
    public String getIDGest(){
        return IDGest;
    }

    public boolean setId(int id) {
        boolean b = executeQuerry("update part set id="+id+" where id="+this.id);
        if (b) this.id = id;
        return b;
    }

    public int getNombre() {
        return nombre;
    }

    public boolean setNombre(int nombre) {
        boolean b = executeQuerry("update part set nombre="+nombre+" where id="+this.id);
        if (b) {
            long s = this.nombre*Part.VALEUR;
            Fond f = new Fond(idFond);
            f.ajouterDepot(Part.VALEUR*nombre-s);
            this.nombre = nombre;
        }
        return b;
    }

    public Date getDateSouscription() {
        return dateSouscription;
    }

    public void setDateSouscription(Date dateSouscription) {
        executeQuerry("update part set date='"+dateSouscription+"' where id="+this.id);
        this.dateSouscription = dateSouscription;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        executeQuerry("update part set matricule='"+matricule+"' where id="+this.id);
        this.matricule = matricule;
    }

    public int getIdFond() {
        return idFond;
    }

    public void setIdFond(int idFond) {
        executeQuerry("update part set idFond="+idFond+" where id="+this.id);
        this.idFond = idFond;
    }
    
}
