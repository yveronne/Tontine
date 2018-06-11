<%-- 
    Document   : listeEmprunts
    Created on : 10 juin 2018, 19:37:46
    Author     : Véronne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Association-Accueil</title>

        <!-- css-->
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap_1.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/connexion.css">
    </head>
    <body>
        <div class="container-fluid">
            <header class="container-fluid row header">           
                <div class="img-responsive col-md-2 logo">
                    <img src="${pageContext.request.contextPath}/images/LogoFinal.png" class="image">
                </div>
                <nav class="container-fluid col-md-7 col-md-offset-5 menu navbar navbar-default">
                    <!-- Navbar Container -->
                    <div class="container-fluid">

                        <!-- Navbar Header [contains both toggle button and navbar brand] -->
                        <div class="navbar-header">
                            <!-- Toggle Button [handles opening navbar components on mobile screens]-->
                            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#exampleNavComponents" aria-expanded="false">
                                <span class="sr-only">Toggle navigation</span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                                <span class="icon-bar"></span>
                            </button>
                        </div>

                        <!-- Navbar Collapse [contains navbar components such as navbar menu and forms ] -->
                        <div class="collapse navbar-collapse small_menu" id="exampleNavComponents">

                            <!-- Navbar Menu -->
                            <ul class="nav navbar-nav navbar-right sou-menu">
                                <li>
                                    <a href="${pageContext.request.contextPath}/adherent/home" class="lien">Accueil</a>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/adherent/listeEmprunts" class="lien">Mes emprunts</a>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/adherent/listeSouscriptions" class="lien">Mes souscriptions</a>
                                </li>
                                <!-- Navbar link with a dropdown menu -->
                                <li class="dropdown">
                                    <a href="#" class="dropdown-toggle lien" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Bienvenue 
                                        <span class="caret"></span>
                                    </a>
                                    <ul class="dropdown-menu">
                                        <li><a href="${pageContext.request.contextPath}/adherent/details">Détails de mon compte</a></li>
                                        <li><a href="${pageContext.request.contextPath}/deconnexion">Déconnexion</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                </nav>
            </header>
            <div class="container-fluid row ariane">
                <div class="col-md-12">
                    <a href="${pageContext.request.contextPath}/adherent/home">Accueil</a> &gt; <span>Mes emprunts</span>
                </div>
            </div>
            <div class="container-fluid row espace_blanc">

            </div>
            <div class="container-fluid row" id="espace_jaune">
                <div class="col-md-12">
                    <strong>MES EMPRUNTS</strong>
                </div>

            </div>
            <div class="container-fluid row" id="tableau_listEmprunts">
              
                <form>
                    <div class="container-fluid espace_blanc"></div>
                    <div class="form-group row">
                        <label for="staticEmail" class="col-md-2 col-form-label">Filtrer</label> 
                    </div>
                    <div class="form-group row">
                        <div  class="col-md-2 col-form-label"><label for="inputPassword">Exercice en cours</label></div>
                        <div class="col-md-1 form-check">
                            <input type="checkbox" class="form-check-input" id="checkbox100">
                        </div>
                    </div>

                </form>
            </div>
            <table class="table table-bordered"> 

                <thead> 
                    <tr> 
                        <td>Date emprunt</td> 
                        <td>Montant</td>
                        <td>Intérêts générés</td>
                        <td>Total</td>
                        <td>Etat</td>
                        <td>Remboursé le</td>
                    </tr> 
                </thead> 
                <tbody> 
                    <c:forEach items="${emprunts}" var="emprunt">
                        <tr>
                            <td>${emprunt.getDateEmprunt()}</td>
                            <td>${emprunt.getMontant()}</td>
                            <td>${emprunt.calculerMontantRetour()}</td>
                            <td>${emprunt.isRembourse()}</td>
                        </tr>
                    </c:forEach>>

                </tbody> 
            </table>
            <div class="container-fluid row pagination_space">

            </div>

            <footer class="container-fluid row footer">
                <span>Copyright</span>   <b>4GI Promotion 2019</b>
            </footer>
        </div>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </body>
</html>
