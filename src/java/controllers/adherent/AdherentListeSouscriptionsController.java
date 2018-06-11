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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Adherent;

/**
 *
 * @author Véronne
 */
@WebServlet(name = "AdherentListeSouscriptionsController", urlPatterns = {"/adherent/listeSouscriptions"})
public class AdherentListeSouscriptionsController extends BaseController {
    
    private static final long serialVersionUID = 1L;
    private String url = "/WEB-INF/views/adherent/listeSouscriptions.jsp";
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Adherent adherent2 = new Adherent();
        adherent2 = this.adherent;
        this.getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
