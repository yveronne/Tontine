package controllers;

import java.io.IOException;
import java.sql.*;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class IndexController
 */
public class IndexController extends BaseController {
    private static final long serialVersionUID = 1L;
    private String url="/WEB-INF/views/welcome.jsp";
    private Connection connexion;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        this.getServletContext().getRequestDispatcher(url).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        if(connect(request, response)){
            if(gestionnaire!=null)
                response. sendRedirect( request. getContextPath( ) + "/gestionnaire/home" ) ;
            else
                response. sendRedirect( request. getContextPath( ) + "/adherent/home" ) ;
        }else{
                request.setAttribute("error", "Login or password incorrect");
                this.getServletContext().getRequestDispatcher(url).forward(request, response);
        }
        
    }
	

}
