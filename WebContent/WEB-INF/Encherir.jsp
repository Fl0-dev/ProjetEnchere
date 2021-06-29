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
		un articles</a> <a
		href="${pageContext.request.contextPath }/MonProfil">Mon
		profil</a> <a href="${pageContext.request.contextPath }/deconnexion">Déconnexion</a>
	</nav> </header>
	<h2>Détail vente</h2>
	

</body>
</html>