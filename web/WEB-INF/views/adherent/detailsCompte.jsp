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
                      <a href="#" class="lien">Accueil</a>
                  </li>
                  <li>
                      <a href="#" class="lien">Mes emprunts</a>
                  </li>
                  <li>
                      <a href="#" class="lien">Mes souscriptions</a>
                  </li>
                <!-- Navbar link with a dropdown menu -->
                  <li class="dropdown">
                      <a href="#" class="dropdown-toggle lien" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Bienvenu 
                        <span class="caret"></span>
                      </a>
                    <ul class="dropdown-menu">
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                        <li><a href="#">Separated link</a></li>
                        <li><a href="#">One more separated link</a></li>
                    </ul>
                </li>
            </ul>
          </div>
        </nav>
        </header>
        <div class="container-fluid row ariane">
        	<div class="col-md-12">
        		<a href="#">Accueil</a> &gt; <span>Mon compte</span>
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
             <div class="form-group row">
               <label for="staticEmail" class="col-md-2 col-form-label">MATRICULE</label>
               <div class="col-sm-10">
                    <label for="staticEmail" class="col-md-6 col-form-label">16P219</label>
                 </div>
             </div>
             <div class="form-group row">
                 <label for="inputPassword" class="col-md-2 col-form-label">NOM</label>
                 <div class="col-sm-10">
                    <label for="staticEmail" class="col-md-6 col-form-label">NYASSA NOAH Pascal Damien</label>
                 </div>
             </div>
             <div class="form-group row">
                 <label for="inputPassword" class="col-sm-2 col-form-label">NUMERO CNI</label>
                 <div class="col-sm-10">
                     <label for="staticEmail" class="col-sm-3 col-form-label">115040084</label>
                 </div>
             </div>
             <div class="form-group row">
                 <label for="inputPassword" class="col-sm-2 col-form-label">ADRESSE</label>
                 <div class="col-sm-10">
                    <label for="staticEmail" class="col-sm-3 col-form-label">Santa Barbara</label>
                 </div>
             </div>
             <div class="form-group row">
                 <label for="inputPassword" class="col-sm-2 col-form-label">TELEPHONE</label>
                 <div class="col-sm-10">
                     <label for="staticEmail" class="col-sm-2 col-form-label">690604642</label>
                 </div>
             </div>
             <div class="form-group row">
                 <label for="inputPassword" class="col-sm-2 col-form-label">ADRESSE MAIL</label>
                 <div class="col-sm-10">
                     <label for="staticEmail" class="col-sm-5 col-form-label">pascalnyabich@polytechnique.cm</label>
                 </div>
             </div>
             <div class="form-group row">
                  <label for="inputPassword" class="col-sm-2 col-form-label">DATE D'ADHESION</label>
                  <div class="col-sm-10">
                      <label for="staticEmail" class="col-sm-2 col-form-label">16/3/2001</label>
                  </div>
             </div>
             <div class="form-group row">
                  <label for="inputPassword" class="col-sm-2 col-form-label">Password</label>
                  <div class="col-sm-2">
                      <input type="password" value="papa va au marché et achète des tomates" class=" form-control" id="inputPassword" placeholder="Password">
                  </div>
                  <div class="col-md-2">
                  	<a href="" class="modification_detail">Modifier le mot de passe</a>
                  </div>
             </div>
        
    </div>
        <footer class="container-fluid row footer">
        <span>Copyright</span>   <b>4GI Promotion 2019</b>
    </footer>
    </div>
     <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>

       <script type="text/javascript" src="${pageContext.request.contextPath}/js/compiled.min.js"></script>
    </body>
  </html>
