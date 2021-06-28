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
	<h1><a href="${pageContext.request.contextPath }/ServletAccueilConnecte">ENI-Enchères</a></h1>
	<p>${resultat}Bienvenue ${ sessionScope.utilisateurSession.pseudo }!</p>
	<nav> <a
		href="${pageContext.request.contextPath }/ServletNouvelleVente">Vendre
		un articles</a> <a
		href="${pageContext.request.contextPath }/ServletMonProfil">Mon
		profil</a> <a href="${pageContext.request.contextPath }/deconnexion">Déconnexion</a>
	</nav> </header>

	<main>
	<h2>Listes des enchères</h2>

	


			<div>
				<form
					action="${pageContext.request.contextPath }/recherche"
					method="post" class="recherche">
					<div>
						<!--  TODO: Implémenter la recherche filtrée -->
						<label for="filtres">Filtres :</label> <input type="text"
							placeholder="Le nom de l'article contient..."
							name="contenuRecherche">
					</div>

					<div>

						<label for="categories">Catégorie :</label> <select
							id="categories" name="categorie">

							<!-- FIXME: l'option toutes affiche toutes les enchères -->
							<option value="0" selected>Toutes</option>

							<c:forEach items="${listeCategories }" var="categorie">
								<option value="${categorie.noCategorie }">${categorie.libelle }</option>
							</c:forEach>
						</select>
					</div>
					
					<div>
		
			<input type="radio" name="choixAchatVente" value="achats"> 
			<label>Achats</label>
			<div>
				<input type="checkbox" name="encheresOuvertes"
					value="encheresOuvertes"> <label>Enchères ouvertes</label>
				<br> <input type="checkbox" name="mesEncheres"
					value="mesEncheres"> <label>Mes enchères</label> <br>
				<input type="checkbox" name="mesEncheresRemportees"
					value="mesEncheresRemportees"> <label>Mes enchères
					remportées</label> <br>
			</div>

 

<div>
<input onclick="activer" type="radio" id="groupeVentes" name="typeEncheres" value="2">
<label for="groupeVentes">Ventes</label>
<br>
<input disabled="disabled" class="ck_ventes" id="ventesEnCours" name="ventesEnCours" type="checkbox" value="1">
<label for="ventesEnCours">Mes ventes en cours</label>
<br>
<input disabled="disabled" class="ck_ventes" id="ventesNonDebutees" name="ventesNonDebutees" type="checkbox" value="1">
<label for="ventesNonDebutees">Ventes non débutées</label>
<br>
<input disabled="disabled" class="ck_ventes" id="ventesTerminees" name="ventesTerminees" type="checkbox" value="1">
<label for="ventesTerminees">Ventes terminées</label>
</div>


			</div>

					<div>
						<button type="submit">Rechercher</button>
					</div>
				</form>
				
				
				
				
			</div>

			<!-- Affichage des enchères en cours -->
			<div class="encheres">

				<c:forEach var="enchere" items="${listeEncheresEnCours }">

					<ul class="carte-enchere">
						<li>${enchere.articleVendu.nomArticle }</li>
						<li>Prix : ${enchere.montant_enchere } points</li>
						<li>Date de fin de l'enchère :
							${enchere.articleVendu.dateFinEncheres }</li>
						<li>Vendeur : ${enchere.utilisateur.pseudo }</li>
						<li>Meilleur enchérisseur : ${articleVendu.utilisateur.pseudo }</li>
					</ul>

				</c:forEach>

			</div>
			
		
	</main>

</body>
</html>