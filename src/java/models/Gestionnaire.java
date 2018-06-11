/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom2.Element;
import static org.jdom2.filter.Filters.document;
import org.jdom2.input.SAXBuilder;

/**
 *
 * @author Leeroy
 */
public class Gestionnaire {
    
    String id;
    String password;
    static ArrayList<String> adminsId = new ArrayList();
    static ArrayList<String> adminsPass = new ArrayList();
    
    public Gestionnaire(String id, String pass){
        this.id = id;
        this.password = pass;
    }
    
    public Gestionnaire(){
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
    public static void chargerAdmins (){
        SAXBuilder sxb = new SAXBuilder();
        org.jdom2.Document document = null;
        Element racine;
        adminsId = new ArrayList();
        adminsPass = new ArrayList();
        try
        {
           document = sxb.build(new File("admin.txt"));
        }
        catch(Exception e){e.printStackTrace();}
        racine = document.getRootElement();
        List list = racine.getChildren("admin");
        Iterator i = list.iterator();
        while(i.hasNext())
        {
          Element courant = (Element)i.next();
          adminsId.add(courant.getChild("id").getText());
          adminsPass.add(courant.getChild("password").getText());
          System.out.println(courant.getChild("id").getText());
        }
    }
    
    public static Gestionnaire login(String id, String password){
        Gestionnaire.chargerAdmins();
        for (int i=0;i<adminsId.size();i++) if (adminsId.get(i).equals(id)) {
            if (adminsPass.get(i).equals(password)) return new Gestionnaire(id, password);
        }
        return null;
    }
    
    public String toString(){
        return id+" - "+password;
    }
    
    public Boolean enregistrerAdherent(Adherent e){
        return e.enregistrerAdherent();
    }
    
    public boolean enregistrerSouscription(Adherent e, int nbreParts, int idf) throws SQLException{
        return e.ajouterPart(nbreParts, idf, id);
    }
    
    public int enregistrerEmprunt(Adherent e, long s, int idFond){
        return e.Emprunter(s, idFond, id);
    }
    
    public boolean enregistrerRetour(Adherent e, long somme, int idEmprunt){
        return e.effectuerRetour(somme, idEmprunt, id);
    }
    
    public boolean exclure(Adherent e){
        return e.exclure(id);
    }
    
    public boolean suspendre(Adherent e){
        return e.suspendre(id);
    }
    
    public ArrayList getListEmprunt(int idFond){
        return new Fond(idFond).getListAllEmprunt();
    }
    
    public ArrayList getListSouscription(int idFond){
        return new Fond(idFond).getListeParts();
    }
    
    public ArrayList getListExclus(int idFond){
        return new Fond(idFond).getListeExclusion();
    }
    
    public ArrayList getListSuspensions(int idF){
        return new Fond(idF).getListeTouteSuspension();
    }
   
}
