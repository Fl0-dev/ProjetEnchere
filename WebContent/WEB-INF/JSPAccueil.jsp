<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ENI-Enchères</title>

<!-- CSS -->
<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet">
</head>

<body>

<header>
<h1>ENI-Enchères</h1>

<p>${resultat}</p>
<p>${messageDeconnexion}</p>

<nav>
<a href="${pageContext.request.contextPath }/Identification">S'inscrire - Se connecter</a>
</nav>
</header>

<main>
<h2>Listes des enchères</h2>

<div>
<form action="${pageContext.request.contextPath }/Accueil" method="post" class="recherche">
	<div>
	<!--  TODO: Implémenter la recherche filtrée -->
	<label for="filtres">Filtres :</label>
	<input type="text" placeholder="Le nom de l'article contient..." name="contenuRecherche">
	</div>
	
	<div>
	
	<label for="categories">Catégorie :</label>
	<select id="categories" name="categorie">
	
	<!-- FIXME: l'option toutes affiche toutes les enchères -->
		<option value="0" selected>Toutes</option>
	
		<c:forEach items="${listeCategories }" var="categorie">
		<option value="${categorie.noCategorie }">${categorie.libelle }</option>
		</c:forEach>
	</select>
	</div>
	
	<div>
	<button type="submit">Rechercher</button>
	</div>
</form>
</div>

<!-- Affichage des enchères en cours -->
<div class="encheres">

        	<c:forEach var="articleVendu" items="${listeVentesEnCours }">

	        	<ul class="carte-enchere">
	        	<li>${articleVendu.nomArticle }</li>
	        	<li>Prix : ${articleVendu.enchereMax.montant_enchere } points</li>
	        	<li>Date de fin de l'enchère : ${articleVendu.dateFinEncheres }</li>
	        	<li>Vendeur : ${articleVendu.utilisateur.pseudo  }</li>
	        	</ul>
	        	
        	</c:forEach>
        
</div>
</main>



</body>
</html>