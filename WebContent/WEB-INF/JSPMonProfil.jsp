<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Mon Profil</title>
<!-- CSS -->
<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet">
</head>
<body>
<header>
<h1><a href="${pageContext.request.contextPath }/ServletAccueilConnecte">ENI-Enchères</a></h1>
</header>
<main>

 <c:if test="${ !empty sessionScope.utilisateurSession }">
        <p>Vous êtes ${ sessionScope.utilisateurSession.pseudo } !</p>
    </c:if>
    
<!--  TODO: récupérer les informations de l'utilisateur connecté -->
<table>
    <thead>
        <tr>
            <th colspan="2">Mon profil</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>Pseudo :</td>
            <td>${ sessionScope.utilisateurSession.pseudo }</td>
        </tr>
         <tr>
            <td>Nom :</td>
            <td>${ sessionScope.utilisateurSession.nom }</td>
        </tr>
         <tr>
            <td>Prénom :</td>
            <td>${ sessionScope.utilisateurSession.prenom }</td>
        </tr>
         <tr>
            <td>Email :</td>
            <td>${ sessionScope.utilisateurSession.email }</td>
        </tr>
         <tr>
            <td>Téléphone :</td>
            <td>${ sessionScope.utilisateurSession.telephone }</td>
        </tr>
         <tr>
            <td>Rue</td>
            <td>${ sessionScope.utilisateurSession.rue }</td>
        </tr>
         <tr>
            <td>Code postal</td>
            <td>${ sessionScope.utilisateurSession.codePostal }</td>
        </tr>
         <tr>
            <td>Ville</td>
            <td>${ sessionScope.utilisateurSession.ville }</td>
        </tr>
    </tbody>
</table>

<a href="">Modifier</a>

</main>

</body>

</html>