/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.adherent;

import controllers.BaseController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Adherent;

/**
 *
 * @author Véronne
 */
public class AdherentDetailController extends BaseController {
    
    private static final long serialVersionUID = 1L;
    private String url="/WEB-INF/views/adherent/detailsCompte.jsp";

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
            Adherent adherent2 = new Adherent();
            adherent2 = this.adherent;
            request.setAttribute("adherent2", adherent2);
            this.getServletContext().getRequestDispatcher(url).forward(request, response);
        
            
            
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String oldPass, newPass, message;
        Adherent adherent2 = new Adherent();
        oldPass = request.getParameter("oldPass").toString();
        newPass = request.getParameter("newPass").toString();
        if(oldPass.equals(this.adherent.getPassword())){
            if(this.adherent.setPassword(newPass)){
                message = "Vos modifications ont bien été enregistrées";
            }
            else{
                message = "Une erreur est survenue. Veuillez réessayer";
            }        
        }
        else{
            message = "L'ancien mot de passe saisi est erroné";
        }
        adherent2 = this.adherent;
        request.setAttribute("message", message);
        request.setAttribute("adherent2", adherent2);
        this.getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    

}
