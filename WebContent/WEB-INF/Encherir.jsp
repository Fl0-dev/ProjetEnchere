<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Accueil</title>
<!-- CSS -->
<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet">
</head>
<body>

	<header>
	<h1><a href="${pageContext.request.contextPath }/AccueilConnecte">ENI-Enchères</a></h1>
	<p>${resultat}Bienvenue ${ sessionScope.utilisateurSession.pseudo }!</p>
	<nav> <a
		href="${pageContext.request.contextPath }/NouvelleVente">Vendre
		un article</a> <a
		href="${pageContext.request.contextPath }/MonProfil">Mon
		profil</a> <a href="${pageContext.request.contextPath }/deconnexion">Déconnexion</a>
	</nav> </header>
	
	<table>
    <thead>
        <tr>
            <th colspan="2">Détail vente</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            
            <td>${ articleSelected.nomArticle }</td>
        </tr>
         <tr>
            <td>Description :</td>
            <td></td>
        </tr>
         <tr>
            <td>Catégorie :</td>
            <td></td>
        </tr>
         <tr>
            <td>Meilleure offre :</td>
            <td></td>
        </tr>
         <tr>
            <td>Mise à prix :</td>
            <td></td>
        </tr>
         <tr>
            <td>Fin de l'enchère :</td>
            <td></td>
        </tr>
         <tr>
            <td>Code postal</td>
            <td></td>
        </tr>
         <tr>
            <td>Ville</td>
            <td></td>
        </tr>
    </tbody>
</table>
	

</body>
</html>