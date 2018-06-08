/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.gestionnaire;

import controllers.BaseController;
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author GodLove
 */
public class GestionnaireHomeController extends BaseController {
    private static final long serialVersionUID = 1L;
    private String url="/WEB-INF/views/gestionnaire/home.jsp";
    private Connection connexion;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        redirect(request, response);
        if(!redirected)
            this.getServletContext().getRequestDispatcher(url).forward(request, response);
    }
    
}
