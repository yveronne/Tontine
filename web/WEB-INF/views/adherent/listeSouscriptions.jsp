<%-- 
    Document   : listeSouscriptions
    Created on : 10 juin 2018, 19:38:20
    Author     : Véronne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            <div class="container-fluid row">
                <div class="container-fluid row ariane">
                    <div class="col-md-12">
                        <a href="#">Accueil</a> &gt; <span>Mes souscriptions</span>
                    </div>
                </div>
                <div class="container-fluid row espace_blanc">

                </div>
                <div class="container-fluid row" id="espace_jaune">
                    <div class="col-md-12">
                        <strong>MES SOUSCRIPTIONS</strong>
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
                <div class="container-fluid row net_percevoir">
                    NET A PERCEVOIR POUR L'EXECICE EN COURS : <label>50000</label>
                </div>
                <table class="table table-bordered"> 

                    <thead> 
                        <tr> 
                            <td>Date d'achat</td> 
                            <td>Nombre de parts</td>
                            <td>Montant</td>
                            <td>Intérêt</td>
                            <td>Net à percevoir</td>
                        </tr> 
                    </thead> 
                    <tbody> 
                        <tr> 
                            <td>16/02/2018</td> 
                            <td>3</td>
                            <td>30000</td>
                            <td>10000</td>
                            <td>40000</td> 
                        </tr> 
                        <tr> 
                            <td>16/02/2018</td> 
                            <td>3</td>
                            <td>30000</td>
                            <td>10000</td>
                            <td>40000</td> 
                        </tr> 
                        <tr> 
                            <td>16/02/2018</td> 
                            <td>3</td>
                            <td>30000</td>
                            <td>10000</td>
                            <td>40000</td> 
                        </tr> 
                        <tr> 
                            <td>16/02/2018</td> 
                            <td>3</td>
                            <td>30000</td>
                            <td>10000</td>
                            <td>40000</td> 
                        </tr> 
                    </tbody> 
                </table>
                <div class="container-fluid row pagination_space">
                    <div class="pagination2">
                        <strong><a href="#">Première page </a>   </strong>
                        <strong class="glyphicon glyphicon-triangle-left left
                                "></strong>
                        <strong>Page </strong>
                        <strong><a href="#">1</a></strong>
                        <strong><a href="#">2</a></strong>
                        <strong><a href="#">3</a></strong>
                        <strong><a href="#">4</a></strong>
                        <strong><a href="#">5</a></strong>
                        <strong class="glyphicon glyphicon-triangle-right left
                                "></strong>
                    </div>
                </div>

            </div>
            <footer class="container-fluid row footer">
                <span>Copyright</span>   <b>4GI Promotion 2019</b>
            </footer>


            <script type="text/javascript" src="jquery-3.3.1.js"></script>
            <script type="text/javascript" src="bootstrap-3.3.7\dist\js\bootstrap.min.js"></script>

        </div>   
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </body>
</html>
