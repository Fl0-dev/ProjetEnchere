<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang=""fr>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>ENI-Enchères</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

<%-- <link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet"> --%>
</head>

<body>

	<header>
		<nav class="navbar navbar-expand-lg bg-dark navbar-dark mb-3">
			<h1><a class="navbar-brand text-info" href="${pageContext.request.contextPath }/Accueil">ENI-Enchères</a></h1>
			
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#liens">
				<span class="navbar-toggler-icon"></span>
			</button>
			
			<div class="collapse navbar-collapse justify-content-end" id="liens">
				<ul class="navbar-nav">
					<li class="nav-item active">
						<a class="nav-link text-info" href="${pageContext.request.contextPath }/Identification">S'inscrire - Se connecter</a>
					</li>
				</ul>
			</div>
		</nav>
	</header>
	<!-- LIGNE -->
	<div class="row d-flex justify-content-center">
		<!-- COLONNE VIDE 1 -->
	<div class="col-md-3 align-items-start"></div>
		
		<!-- COLONNE CENTRALE -->
	<main class="col-md-6 align-items-center">
	
		<p class="text-dark container d-flex justify-content-center">${resultat}</p>
		<p class="text-dark container d-flex justify-content-center">${messageDeconnexion}</p>
		
		<div class="h2 container d-flex justify-content-center">Listes des enchères</div>
		
			<form action="${pageContext.request.contextPath }/Accueil" method="post" class="recherche">
				<div class ="form-group">
					<label for="filtres">Filtres :</label>
					<input class="form-control" type="text" placeholder="Le nom de l'article contient..." name="contenuRecherche">
				</div>
	
					<div class="form-control ">
						<label for="categories">Catégorie :</label>
						<select id="categories" name="categorie" class="form-control">
							<option value="0" selected>Toutes</option>
							<c:forEach items="${listeCategories }" var="categorie">
							<option value="${categorie.libelle }">${categorie.libelle }</option>
							</c:forEach>
						</select>
					</div>
					
				<button class="btn btn-info btn-block my-3 "type="submit">Rechercher</button>
		
			</form>
		

<!-- Affichage des enchères en cours -->
		        
        <c:forEach var="articleVendu" items="${listeVentesEnCours }">	
			<div class="card mb-3">
				
	        	<div class="card-body">
		        	<h5 class="card-title">${articleVendu.nomArticle }<h5>
		        	<h6 class"card-subtitle mb-2 text-muted">Prix : ${articleVendu.enchereMax.montant_enchere } points<h6>
		        	<p>Date de fin de l'enchère : ${articleVendu.dateFinEncheres }</p>
		        	<p>Vendeur : ${articleVendu.utilisateur.pseudo  }<p>
	        	</div>
	        	
	        </div>	
        </c:forEach>
       
        
	</main> 

<div class="col-md-3 align-items-end"></div>

</div>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</body>
</html>