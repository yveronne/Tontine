package models;

import java.sql.*;

/**
 *
 * @author UTILISATEUR
 */
public class Connecteur {
    
    private String driver="com.mysql.jdbc.Driver";
    private String login="root";
    private String mdp="";
    private String chaineConnexion="jdbc:mysql://localhost:3306/projetelectronique";
    private Connection connexion;
    
    public void AjoutDriver(String driver) throws SQLException
    {
        this.driver=driver;
        fermerConnexion();
    }
    
    public void AjoutChaineConnexion(String chaineConnexion) throws SQLException
    {
        this.chaineConnexion=chaineConnexion;
        fermerConnexion();
    }

    public void fermerConnexion() throws SQLException
    {
        if(this.connexion!=null && !this.connexion.isClosed())
            this.connexion.close();
    }
    
    public Connection SeConnecter() throws SQLException
    {
        try{
            if(this.connexion==null || this.connexion.isClosed()){
                Class.forName(driver);
                if(login!=null)
                    this.connexion=DriverManager.getConnection(this.chaineConnexion, this.login,this.mdp);
                else
                    this.connexion=DriverManager.getConnection(this.chaineConnexion);
            }
            System.out.println(">>>"+connexion);
            return this.connexion;
        }
        catch(ClassNotFoundException ex){
            throw new SQLException("Classe introuvable "+ex.getMessage());
        }
    }
   
}

