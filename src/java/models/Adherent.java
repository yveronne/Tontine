/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;

/**
 *
 * @author Leeroy
 */
public class Adherent extends PersistantClass{
    
    private static final long serialVersionUID = 1L;

    private String matricule;
    private String nom;
    private String numeroCNI;
    private String adresse;
    private String phone;
    private String email;
    private Date date;
    private String password;

    public Adherent(){
        
    }

    public Adherent(String matricule) {
        this.matricule = matricule;
        this.copy(rechercherParMatricule(matricule));
    }

    public Adherent(String matricule, String nom, String numeroCNI, String adresse, String phone, String email, Date date, String password) {
        this.matricule = matricule;
        this.nom = nom;
        this.numeroCNI = numeroCNI;
        this.adresse = adresse;
        this.phone = phone;
        this.email = email;
        this.date = date;
        this.password = password;
    }
    
    public Adherent(String nom, String numeroCNI, String adresse, String phone, String email, String password) {
        this.matricule = matricule;
        this.nom = nom;
        this.numeroCNI = numeroCNI;
        this.adresse = adresse;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Adherent)) {
            return false;
        }
        Adherent other = (Adherent) object;
        if ((this.matricule == null && other.matricule != null) || (this.matricule != null && !this.matricule.equals(other.matricule))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Mr "+ nom +" [" + matricule + " ]";
    }
    
    public boolean enregistrerAdherent(){
        boolean b = true;//(String matricule, String nom, String numeroCNI, String adresse, String phone, String email, Date date, String password)
        String q = "INSERT INTO adherent (matricule, nom, numeroCNI, adresse, phone, email, password) VALUES('"+genererMatricule()+"', '"+nom+"', '"+numeroCNI+"', '"
                +adresse+"', "+phone+", '"+email+"', '"+password+"')"; 
        
        return executeQuerry(q);
    }
    
    public Adherent connect(String matricule,String password){
        Adherent ad=rechercherParMatricule(matricule);
        if(ad!=null && ad.password.equals(password)){
            return ad;
        }
        return null;
    }
    
    public String getPassword(){
        return password;
    }
    
    public boolean setPassword(String p){
        boolean b = executeQuerry("update adherent set password='"+p+"'");
        if (b) password = p;
        return b;
    }
    
    public String genererMatricule(){
        String de = getInformationFromDB("select count(matricule) from adherent");
        int d;
        if (de== null) d = 0;
        else d = Integer.parseInt(de);
        String e = (d+1<10 ? "0"+(d+1): (d+1)+"");
        this.matricule = e+""+nom.toUpperCase().substring(0, 4)+"2018";// format 07DANI2018
        return matricule;
    }
    
    public void copy(Adherent p){
        this.matricule = p.matricule;
        this.nom = p.nom;
        this.phone = p.phone;
        this.email = p.email;
        this.numeroCNI = p.numeroCNI;
        this.adresse = p.adresse;
        this.date = p.date;
        this.password = p.password;
    }
    
    Adherent rechercherParCNI(String numeroCNI){
        Adherent p = null;
        try{
            String sql;
            sql = "SELECT * FROM adherent WHERE numeroCNI='"+numeroCNI+"'";
            ResultSet rs = getFromDB(sql);
            
            while(rs.next()){
                p =  new Adherent(rs.getString("matricule")
                      , rs.getString("nom"), rs.getString("numeroCNI"), rs.getString("adresse")
                      , rs.getString("phone"), rs.getString("email"), rs.getDate("date"), rs.getString("password"));

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
    
    Adherent rechercherParMatricule(String matricule){
        Adherent p = null;
        try{
            String sql;
            sql = "SELECT * FROM adherent WHERE matricule='"+matricule+"'";
            ResultSet rs = getFromDB(sql);
            
            while(rs.next()){
                p =  new Adherent(rs.getString("matricule")
                      , rs.getString("nom"), rs.getString("numeroCNI"), rs.getString("adresse")
                      , rs.getString("phone"), rs.getString("email"), rs.getDate("date"), rs.getString("password"));

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
    
    public boolean suspendre(String idg){
        if (this.isSuspendu()) return false;
        Date d = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        java.util.Date kini = new java.util.Date();
        java.sql.Date d2 = new java.sql.Date(kini.getTime() + 31l*24l*60l*60l*1000l);
        Suspension s = new Suspension(matricule, d, d2, idg);
        return s.enregistrer();
    }
    
    public boolean isSuspendu(){
        int nbre = Integer.parseInt(getInformationFromDB("select count(id) from suspension where dateFin > '"
                +(new java.sql.Date(Calendar.getInstance().getTime().getTime()))+"' and matricule='"+matricule+"'"));
        if (nbre==1) return true;
        else if (nbre ==0) return false;
        else System.out.println("COMMENT EST CE POSSIBLE ?? PLUS D'UNE SUSPENSION EN MEME TEMPS ??!!");
        return false;
    }
    
    public boolean exclure(String idg){
        if (this.isExclu()) return false;
        Exclusion s = new Exclusion(matricule, idg);
        return s.enregistrer();
    }
    
    public boolean isExclu(){
        int nbre = Integer.parseInt(getInformationFromDB("select count(id) from exclusion where matricule='"+matricule+"'"));
        if (nbre>1) return true;
        else return false;
    }
    
    public boolean isNormal(){
        return (!isExclu() && !isSuspendu());
    }
    
    public int getPartID(int idf){
        String  en = getInformationFromDB("select max(id) from part where matricule='"+matricule+"' and IDFond="+idf);//==0 si aucune part...
        if (en==null || Integer.parseInt(en)==0) return 0;
        else return Integer.parseInt(getInformationFromDB("select id from part where matricule='"+matricule+"' and IDFond="+idf));
    }
    
    public boolean ajouterPart(int nbreParts, int idf, String idg) throws SQLException{
        if (!isNormal()) return false;
        int i = getPartID(idf);
        if (i!=0) {
            Part p = new Part(i);
            new Souscription(i, nbreParts, idg).enregistrer();
            return p.setNombre(p.getNombre()+nbreParts);
        }
        else new Part(nbreParts, matricule, idf, idg).enregistrer();
        return true;
    }
    
    boolean verifierEmpruntEnCours(int idf){
        return !((getListInformationFromDB("select id from emprunt where matricule='"+matricule+"' and rembourse = false and idFond ="+idf)).isEmpty());
    }
    
    public int rechercherIdEmpruntNonRembourse(int idf){
        if (verifierEmpruntEnCours(idf))
            return Integer.parseInt(getListInformationFromDB("select id from emprunt where matricule='"+matricule+"' and rembourse = false and idFond="+idf).get(0));
        else return 0;
    }
    
    public ArrayList<Emprunt> getListEmprunt(int idFond){
        ArrayList<Emprunt> le = new ArrayList();
        ArrayList<String> l = getListInformationFromDB("select id from emprunt where idFond="+idFond+" and matricule='"+matricule+"'");
        for(String s:l){
            le.add(new Emprunt(Integer.parseInt(s)));
        }
        return le;
    }
    
    public int Emprunter(long somme, int idf, String idg){
        if (!isNormal() || verifierEmpruntEnCours(idf)) return 0;
        return new Emprunt(somme, matricule, idf, idg).enregistrer();
    }
    
    public boolean effectuerRetour(long val, int ide, String idg){
        return new Retour(val, ide, idg).enregistrer();
    }
    
    public boolean isActionnaire(int idf){
        return (getPartID(idf)==0)? false:true;
    }
    
    public long calculerDividendes(int idf){
        int nbreTotalParts = 0;
        Fond f = new Fond(idf);
        int mesParts = new Part(this.getPartID(idf)).getNombre();
        for(Adherent e:f.getListAdherent()) if(e.isActionnaire(idf)) nbreTotalParts += new Part(e.getPartID(idf)).getNombre();

        long soldeDepart = Part.VALEUR*nbreTotalParts;
        long fondGestionTontine = Math.round(0.2*(soldeDepart - f.getSoldeDisponible()));
        long sommeAPartager = f.getSoldeDisponible() - fondGestionTontine;
        
        long mapart = Math.round((mesParts/nbreTotalParts)*sommeAPartager);
        return mapart;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumeroCNI() {
        return numeroCNI;
    }

    public void setNumeroCNI(String numeroCNI) {
        this.numeroCNI = numeroCNI;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
}


