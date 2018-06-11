/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.adherent;

import controllers.BaseController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Adherent;
import models.Emprunt;
import models.Fond;

/**
 *
 * @author Véronne
 */
@WebServlet(name = "AdherentListeEmpruntsController", urlPatterns = {"/adherent/listeEmprunts"})
public class AdherentListeEmpruntsController extends BaseController {
    private static final long serialVersionUID = 1L;
    private String url = "/WEB-INF/views/adherent/listeEmprunts.jsp";

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Adherent adherent2 = new Adherent();
        Fond fondActuel = new Fond();
        ArrayList<Emprunt> emprunts = new ArrayList();
        adherent2 = this.adherent;
        emprunts = adherent2.getListEmprunt(fondActuel.trouverFondActuel().getId());
        request.setAttribute("emprunts", emprunts);
        request.setAttribute("adherent2", adherent2);
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
