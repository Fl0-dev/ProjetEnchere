<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Accueil</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<%-- <link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet"> --%>
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-lg bg-dark navbar-dark mb-3">
			<h1><a class="navbar-brand text-info" href="${pageContext.request.contextPath }/AccueilConnecte">ENI-Enchères</a></h1>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#liens">
				<span class="navbar-toggler-icon"></span>
			</button>
			
			<div class="collapse navbar-collapse justify-content-end" id="liens">
				<ul class="navbar-nav">
					<li class="nav-item active">	
						<a class="nav-link text-info" href="${pageContext.request.contextPath }/NouvelleVente">Vendre un article</a>
					</li>
      				<li class="nav-item">	 
						<a class="nav-link text-info" href="${pageContext.request.contextPath }/MonProfil">Mon profil</a> 
					</li>
      				<li class="nav-item">	 
						<a class="nav-link text-info" href="${pageContext.request.contextPath }/deconnexion">Déconnexion</a>
					</li>
				</ul>		
			</div>
		</nav> 
	</header>
<div class="row d-flex justify-content-center">


<div class="col-md-3 align-items-start"></div>

	<main class="col-md-6 align-items-center">


	<p class="text-dark container d-flex justify-content-center">${succes}</p>

		<p class="text-dark container d-flex justify-content-center">${resultat}Bienvenue ${ sessionScope.utilisateurSession.pseudo }!</p> 
		<div class="h2 container d-flex justify-content-center">Listes des enchères</div>
	
			<form action="${pageContext.request.contextPath }/recherche" method="post" class="recherche">	
			<div class ="form-group">						
				<label for="filtres">Filtres :</label> 
				<input class="form-control" type="text" placeholder="Le nom de l'article contient..." name="contenuRecherche">
			</div>
				
				<div class="form-control">
						<label for="categories">Catégorie :</label>
						<select id="categories" name="categorie" class="form-control">
							<option value="0" selected>Toutes</option>
							<c:forEach items="${listeCategories }" var="categorie">
							<option value="${categorie.noCategorie }">${categorie.libelle }</option>
							</c:forEach>
						</select>
					</div>
				<br>
			
		
				<!-- boutons radio achats -->
				<div class="container d-flex flex-row justify-content-around mb-3">
					<div>	
						<div class="font-weight-bold">
							<input type="radio" name="choixAchatVente" value="achats" id="achats" checked> 
							<label for="achats">Achats</label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="encheres" checked value="encheresOuvertes" id="encheresOuvertes"> 
							<label class="form-check-label" for="encheresOuvertes">Enchères ouvertes</label>
						</div>	
						<div class="form-check">
							<input class="form-check-input" type="radio" name="encheres" value="mesEncheres" id="mesEncheres"> 
							<label class="form-check-label" for="mesEncheres">Mes enchères</label> 
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="encheres" value="mesEncheresRemportees" id="mesEncheresRemportees"> 
							<label class="form-check-label" for="mesEncheresRemportees">Mes enchères remportées</label> 
						</div>
					</div>
	
				
					<!-- boutons radio mes ventes -->
					<div>
						<div class="font-weight-bold">	
							<input type="radio" name="choixAchatVente" value="MesVentes" id="MesVentes"> 
							<label for="MesVentes">Mes ventes</label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="ventes" value="mesVentesEnCours" id="mesVentesEnCours"> 
							<label class="form-check-label" for="mesVentesEnCours">Mes ventes en cours</label>
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="ventes" value="ventesNonDebutees" id="ventesNonDebutees"> 
							<label class="form-check-label" for="ventesNonDebutees">Ventes non débutées</label> 
						</div>
						<div class="form-check">
							<input class="form-check-input" type="radio" name="ventes" value="ventesTerminees" id="ventesTerminees"> 
							<label class="form-check-label" for="ventesTerminees">Ventes terminées</label> 
						</div>
					</div>
				</div>
            	<div>
					<button class="btn btn-info btn-block mb-3"type="submit">Rechercher</button>
				</div>
			
		</form>

			<!-- Affichage des enchères en cours -->
			<div>
				<c:forEach var="articleVendu" items="${listeAafficher }">
					<div class="card mb-3">
					
						<div class="card-body">
<!-- passer dans le lien le noArticle dans l'URL-->	
							<h5 class="card-title"><a href="${pageContext.request.contextPath }/encherir?article=${articleVendu.noArticle }" class="card-link text-info">${articleVendu.nomArticle }</a><h5>
							<h6 class"card-subtitle mb-2 text-muted">Prix : ${articleVendu.enchereMax.montant_enchere } points<h6>
							<p>Date de fin de l'enchère : ${articleVendu.dateFinEncheres }</p>
<!-- passer dans le lien le noUtilisateur dans l'URL-->							
							<p>Vendeur : <a href="${pageContext.request.contextPath }/voirprofil?id=${articleVendu.utilisateur.noUtilisateur }" class="card-link text-info">${articleVendu.utilisateur.pseudo }</a></p>
						</div>	
					
					</div>
			</c:forEach>	
		</div>
	</main>
	
	<div class="col-md-3 align-items-end"></div>
	</div>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</body>
</html>