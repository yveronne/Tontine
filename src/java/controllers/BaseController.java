package controllers;

import models.Gestionnaire;
import java.io.IOException;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Adherent;


import models.Connecteur;

/**
 * Servlet implementation class BaseController
 */
public class BaseController extends HttpServlet {
	protected Gestionnaire gestionnaire;
        protected Adherent adherent;
	protected boolean redirected=false;
        protected boolean secured=false;
    /**
     * @throws IOException 
     * @see HttpServlet#HttpServlet()
     */
    public BaseController(){
        
    }
    public BaseController(boolean sec,HttpServletRequest request,HttpServletResponse response){
        secured=sec;
        
    }
    protected void goToHome(HttpServletRequest request,HttpServletResponse response) throws IOException{
    	response.sendRedirect(request.getContextPath( ) + "/home" ) ;
    }
    protected void redirect(HttpServletRequest request,HttpServletResponse response){
    	HttpSession session=request.getSession();
        Object o=session.getAttribute("user");
    	redirected= false;
        try{
            if(o==null){
                String login=getCookieValue(request, "login");
                String password=getCookieValue(request, "password");
                if(login==null || password==null){
                    redirected=true;
                    deconnexion(request, response);
                }else{
                    gestionnaire=Gestionnaire.login(login, password);
                    if(gestionnaire==null){
                        Adherent g=new Adherent();
                        adherent=g.connect(login, password);
                        if(adherent!=null){
                            session.setAttribute("user", adherent);
                            setCookie(response, "login", adherent.getMatricule(), 60*60*24);
                            setCookie(response, "password", adherent.getMatricule(), 60*60*24);
                        }else{
                            redirected=true;
                            try {
                                deconnexion(request, response);
                            } catch (IOException ex) {
                                Logger.getLogger(BaseController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                    }else{
                        session.setAttribute("user", gestionnaire);
                        setCookie(response, "login", gestionnaire.getId(), 60*60*24);
                        setCookie(response, "password", gestionnaire.getPassword(), 60*60*24);
                    }
                }
            }else{
                if(o instanceof Gestionnaire){
                    gestionnaire=(Gestionnaire)o;
                    session.setAttribute("user", gestionnaire);
                    setCookie(response, "login", gestionnaire.getId(), 60*60*24);
                    setCookie(response, "password", gestionnaire.getPassword(), 60*60*24);
                }else{
                    session.setAttribute("user", adherent);
                    setCookie(response, "login", adherent.getMatricule(), 60*60*24);
                    setCookie(response, "password", adherent.getMatricule(), 60*60*24);
                    adherent=(Adherent)o;
                }
            }
        }catch(Exception e){
            
        }
    }
    private static String getCookieValue( HttpServletRequest request, String nom ) {
        Cookie[] cookies = request.getCookies();
        if ( cookies != null ) {
            for ( Cookie cookie : cookies ) {
                if ( cookie != null && nom.equals( cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }
    
    private static void setCookie( HttpServletResponse response, String	nom, String valeur, int maxAge ) {
        Cookie cookie = new Cookie( nom, valeur );
        cookie.setMaxAge( maxAge );
        response.addCookie( cookie );
    }
    
    protected boolean connect(HttpServletRequest request,HttpServletResponse response){
        HttpSession session = request.getSession();
        String login=request.getParameter("login").toString();
        String password=request.getParameter("password").toString();
        String gest=request.getParameter("gestionnaire");
        if(gest!=null && gest.equals("true")){
            gestionnaire=Gestionnaire.login(login, password);
            if(gestionnaire!=null){
                session.setAttribute("user", gestionnaire);
                setCookie(response, "login", gestionnaire.getId(), 60*60*24);
                setCookie(response, "password", gestionnaire.getPassword(), 60*60*24);
                return true;
            }
        }else if(gest==null){
            Adherent adherent=new Adherent();
            this.adherent=adherent.connect(login, password);
            
            if(this.adherent!=null){
                session.setAttribute("user", this.adherent);
                setCookie(response, "login", this.adherent.getMatricule(), 60*60*24);
                setCookie(response, "password", this.adherent.getMatricule(), 60*60*24);
                return true;
            }else{
                return false;
            }
        }
        return false;
    }
    
    protected void deconnexion(HttpServletRequest request, HttpServletResponse response) throws IOException{
    	HttpSession session=request.getSession();
    	session.setAttribute("user", null);
    	setCookie(response, "login", "", 0);
        setCookie(response, "password", "", 0);
    	session.invalidate();
        gestionnaire=null;
        adherent=null;
    	goToHome(request, response);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException,NumberFormatException {
        deconnexion(request, response);
    }
}
