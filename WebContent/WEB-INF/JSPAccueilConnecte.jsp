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
		<nav> 
			<a href="${pageContext.request.contextPath }/NouvelleVente">Vendre un article</a> 
			<a href="${pageContext.request.contextPath }/MonProfil">Mon profil</a> 
			<a href="${pageContext.request.contextPath }/deconnexion">Déconnexion</a>
		</nav> 
	</header>

	<main>
	<h2>Listes des enchères</h2>

		<form action="${pageContext.request.contextPath }/recherche" method="post" class="recherche">	
			<div>
				<div>						
					<label for="filtres">Filtres :</label> <input type="text"placeholder="Le nom de l'article contient..." name="contenuRecherche">
				</div>
				
				<div>
					<label for="categories">Catégorie :</label> 
					<select id="categories" name="categorie">					
						<option value="0" selected>Toutes</option>
							<c:forEach items="${listeCategories }" var="categorie">
								<option value="${categorie.libelle }">${categorie.libelle }</option>
							</c:forEach>
					</select>
				</div>
				<br>
			
		
				<!-- boutons radio achats -->
				<div>	
					<input type="radio" name="choixAchatVente" value="achats" checked> 
					<label>Achats</label>
					<div>
						<ul>
		                	<li>
								<input type="radio" name="encheres" checked value="encheresOuvertes"> <label>Enchères ouvertes</label>
							</li>
							<li>
								<input type="radio" name="encheres" value="mesEncheres"> <label>Mes enchères</label> 
							</li>
							<li>
								<input type="radio" name="encheres" value="mesEncheresRemportees"> <label>Mes enchères remportées</label> 
							</li>
						</ul>
					</div>	
				</div>
	
				
				<!-- boutons radio mes ventes -->
				<div>	
					<input type="radio" name="choixAchatVente" value="MesVentes"> 
					<label>Mes ventes</label>
					<div>
						<ul>
		                	<li>
								<input type="radio" name="ventes" value="mesVentesEnCours"> <label>Mes ventes en cours</label>
							</li>
							<li>
								<input type="radio" name="ventes" value="mesEncheres"> <label>Ventes non débutées</label> 
							</li>
							<li>
								<input type="radio" name="ventes" value="mesEncheresRemportees"> <label>Ventes terminées</label> 
							</li>
						</ul>
					</div>
				</div>
            	<div>
					<button type="submit">Rechercher</button>
				</div>
			</div> 
		</form>
				
			<!-- Affichage des enchères en cours -->
			<div class="encheres">

				<c:forEach var="articleVendu" items="${listeAafficher }">

					<ul class="carte-enchere">
						<li>${articleVendu.nomArticle }</li>
						<li>Prix : ${articleVendu.enchereMax.montant_enchere } points</li>
						<li>Date de fin de l'enchère : ${articleVendu.dateFinEncheres }</li>
						<li>Vendeur : ${articleVendu.utilisateur.pseudo }</li>
						
					</ul>

				</c:forEach>

			</div>
			
		
	</main>

</body>
</html>