<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Nouvelle vente</title>
<!-- CSS -->
<link href="${pageContext.request.contextPath }/css/style.css"
	rel="stylesheet">
</head>
<body>
	<header>
	<h1>
		<a href="${pageContext.request.contextPath }/AccueilConnecte">ENI-Enchères</a>
	</h1>
	<p>${resultat}Bienvenue ${ sessionScope.utilisateurSession.pseudo }!</p>
	<nav> <a href="${pageContext.request.contextPath }/NouvelleVente">Vendre
		un article</a> <a href="${pageContext.request.contextPath }/MonProfil">Mon
		profil</a> <a href="${pageContext.request.contextPath }/deconnexion">Déconnexion</a>
	</nav> </header>


<p>${messageErreur}</p>
<p>${succès}</p>

	<form action="${pageContext.request.contextPath }/NouvelleVente" method="post">
		<div>
			<label for="nom_article">Article :</label> <input type="text"
				id="nom_article" name="nom_article">
		</div>

		<div>
			<label for="description">Description :</label>
			<textarea id="description" name="description" rows="5" cols="15" placeholder="Description de l'article"></textarea>
		</div>

		<div>
			<label for="categories">Catégorie :</label> <select id="categories"
				name="categorie">
				<option value="0" selected>--</option>
			
				<c:forEach items="${listeCategories }" var="categorie">
					<option value="${categorie.noCategorie }">${categorie.libelle }</option>
				</c:forEach>
			</select>
		</div>

		<div>
			<label for="prix_initial">Mise à prix :</label> <input type="number"
				id="prix_initial" name="prix_initial" required>
		</div>

		<div>
			<label for="debutenchere">Début de l'enchère :</label> <input
				type="date" id="debutenchere" name="debutenchere">
		</div>
		<div>
			<label for="finenchere">Fin de l'enchère :</label> <input type="date"
				id="finenchere" name="finenchere">
		</div>

		<fieldset>
			<legend>Retrait</legend>
			<div>
			<label for="rue">Rue :</label> 
			<input type="text" id="rue" name="rue" value="<c:out value="${utilisateurSession.rue }"/>">
			</div>
			<div>
			<label for="codePostal">Code postal :</label> 
			<input type="text" id="codePostal" name="codePostal" value="<c:out value="${utilisateurSession.codePostal }"/>">
			</div>
			<div>
			<label for="ville">Ville :</label> 
			<input type="text" id="ville" name="ville" value="<c:out value="${utilisateurSession.ville }"/>">
			</div>
		</fieldset>
		
		

		<div class="button">
			<input type="submit" value="Enregistrer">
		</div>
		

			</form>
			
			<div class="button">
			<button type="reset" ><a href="${pageContext.request.contextPath }/AccueilConnecte">Annuler</button>
		</div>
</body>
</html>