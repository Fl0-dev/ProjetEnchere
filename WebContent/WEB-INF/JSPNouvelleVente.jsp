<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Nouvelle vente</title>
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


<form action="/NouvelleVente" method="post">
   <div>
        <label for="nom_article">Article :</label>
        <input type="text" id="nom_article" name="nom_article">
    </div>
    
    <div>
        <label for="description">Description :</label>
        <textarea id="description" name="description" rows="5" cols="15">Description de l'article</textarea>
    </div>
    
    <div>
	<label for="categories">Catégorie :</label>
	<select id="categories" name="categorie">
		<c:forEach items="${listeCategories }" var="categorie">
		<option value="${categorie.noCategorie }">${categorie.libelle }</option>
		</c:forEach>
	</select>
	</div>
	
	<div>
	<label for="prix_initial">Mise à prix :</label>
<input type="number" id="prix_initial" name="prix_initial">
	</div>
	
	<div>
	<label for="debutenchere">Début de l'enchère :</label>
<input type="date" id="debutenchere" name="debutenchere">
	</div>
	<div>
	<label for="finenchere">Fin de l'enchère :</label>
<input type="date" id="finenchere" name="finenchere">
	</div>
	
	   <div class="button">
        <button type="submit">Enregistrer</button>
    </div>
       <div class="button">
        <button type="reset">Annuler</button>
    </div>
    
    <div>
        <label for="rue">Rue :</label>
        <input type="text" id="article" name="article">
    </div>
    
</form>
</body>
</html>