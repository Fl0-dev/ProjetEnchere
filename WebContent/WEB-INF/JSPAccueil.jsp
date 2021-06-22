<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ENI-Enchères</title>

<!-- CSS -->
<link href="${pageContext.request.contextPath }/css/accueil.css" rel="stylesheet">
</head>

<body>

<header>
<h1>ENI-Enchères</h1>
<nav>
<a href="${pageContext.request.contextPath }/ServletIdentification">S'inscrire - Se connecter</a>
</nav>
</header>

<main>
<h2>Listes des enchères</h2>

<div>
<form action="/ServletAccueil" method="post">
	<div>
	<label for="filtres">Filtres :</label>
	<input type="text" placeholder="Le nom de l'article contient...">
	</div>
	
	<div>
	<label for="categories">Catégorie :</label>
	<select id="categories" name="categorie">
	</select>
	</div>
	
	<div>
	<button type="submit">Rechercher</button>
	</div>
</form>
</div>

<!-- Affichage des enchères en cours -->
<div>
<ul>
        	<c:forEach var="enchere" items="${listeEncheresEnCours }">
        	<!-- TODO: afficher nom de l'article, prix, date de fin d'enchère, nom de l'utilisateur-vendeur -->
	        	<li>${enchere }
	                </li>
        	</c:forEach>
        </ul>
</div>
</main>



</body>
</html>