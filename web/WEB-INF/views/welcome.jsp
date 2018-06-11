<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="https://fonts.googleapis.com/css?family=Montserrat+Alternates" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/ourStyle.css">
        <title>Bienvenue sur La Caisse</title>
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-12 col-sm-12 col-xs-12" id="welcomeDiv">
                    <img src="${pageContext.request.contextPath}/images/LogoFinal.png">
                    <h1>Bienvenue sur la caisse</h1>
                    <h5>Empruntez en toute sérénité</h5>
                    <p style="font-weight: bold;color:red;text-align: center;">
                        <%
                            if (request.getAttribute("error") != null) {
                                out.print(request.getAttribute("error").toString());
                            }
                        %>
                    </p>
                    <button type="button" class="btn btn-mine" data-toggle="modal" data-target="#loginModal">
                        Se connecter
                    </button>
                </div>	
            </div>
        </div>
        <div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalCenterTitle">Connexion</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form method="POST" action="">
                            <div class="form-group">
                                <label for="login">Matricule / Nom d'utilisateur</label>
                                <input type="text" class="form-control" id="login" name="login">
                            </div>
                            <div class="form-group">
                                <label for="password">Mot de passe</label>
                                <input type="password" class="form-control" id="password" name="password">
                            </div>
                            <div class="form-check">
                                <input type="checkbox" class="form-check-input" id="adminCheck" name="gestionnaire" value="true"/>
                                <label class="form-check-label" for="adminCheck">Gestionnaire</label>
                            </div>
                            <button type="submit" class="btn btn-mine">Se connecter</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/js/compiled.min.js"></script>
    </body>
</html>