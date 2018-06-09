<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <h1>Je suis un Adherent</h1>
        <p>FFF<a href="${pageContext.request.contextPath}/adherent/details">Details</a></p>
        
        <h2><a href="${pageContext.request.contextPath}/deconnexion">Me déconnecter</a></h2>
    </body>
</html>