<%-- 
    Document   : detailsCompte
    Created on : 9 juin 2018, 19:43:40
    Author     : Véronne
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
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
                                    <a href="#" class="dropdown-toggle lien" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Bienvenu 
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
                    <a href="${pageContext.request.contextPath}/adherent/home">Accueil</a> &gt; <span>Mon compte</span>
                </div>
            </div>
            <div class="container-fluid row espace_blanc">

            </div>
            <div class="container-fluid row" id="espace_jaune">
                <div class="col-md-12">
                    <strong>DETAILS DE MON COMPTE</strong>
                </div>

            </div>
            <div class="container-fluid row" id="formulaire">

                <div class="container-fluid espace_blanc"></div>
                <h2>${message}</h2>
                <div class="form-group row">
                    <label for="staticEmail" class="col-md-2 col-form-label">MATRICULE</label>
                    <div class="col-sm-10">

                        <label for="staticEmail" class="col-md-6 col-form-label">${adherent2.matricule}</label>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputPassword" class="col-md-2 col-form-label">NOM</label>
                    <div class="col-sm-10">
                        <label for="staticEmail" class="col-md-6 col-form-label">${adherent2.nom}</label>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputPassword" class="col-sm-2 col-form-label">NUMERO CNI</label>
                    <div class="col-sm-10">
                        <label for="staticEmail" class="col-sm-3 col-form-label">${adherent2.numeroCNI}</label>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputPassword" class="col-sm-2 col-form-label">ADRESSE</label>
                    <div class="col-sm-10">
                        <label for="staticEmail" class="col-sm-3 col-form-label">${adherent2.adresse}</label>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputPassword" class="col-sm-2 col-form-label">TELEPHONE</label>
                    <div class="col-sm-10">
                        <label for="staticEmail" class="col-sm-2 col-form-label">${adherent2.phone}</label>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputPassword" class="col-sm-2 col-form-label">ADRESSE MAIL</label>
                    <div class="col-sm-10">
                        <label for="staticEmail" class="col-sm-5 col-form-label">${adherent2.email}</label>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputPassword" class="col-sm-2 col-form-label">DATE D'ADHESION</label>
                    <div class="col-sm-10">
                        <label for="staticEmail" class="col-sm-2 col-form-label">${adherent2.date}</label>
                    </div>
                </div>
                <div class="form-group row">
                    <label for="inputPassword" class="col-sm-2 col-form-label">Mot de passe</label>
                    <div class="col-sm-2">
                        <input type="password" value="${adherent2.password}" class=" form-control" id="inputPassword" placeholder="Password">
                    </div>
                    <div class="col-md-2">
                        <button class="btn btn-mine" data-toggle="modal" data-target="#changePassModal">Modifier le mot de passe</button>
                    </div>
                </div>

            </div>
                    <div class="modal fade" id="changePassModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalCenterTitle">Changer le mot de passe</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form method="POST" action="">
                            <div class="form-group">
                                <label for="oldPass">Ancien mot de passe</label>
                                <input type="password" class="form-control" id="oldPass" name="oldPass">
                            </div>
                            <div class="form-group">
                                <label for="newPass">Nouveau mot de passe</label>
                                <input type="password" class="form-control" id="newPass" name="newPass">
                            </div>
                            
                            <button type="submit" class="btn btn-mine">Se connecter</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
            <footer class="container-fluid row footer">
                <span>Copyright</span>   <b>4GI Promotion 2019</b>
            </footer>
        </div>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </body>
</html>
