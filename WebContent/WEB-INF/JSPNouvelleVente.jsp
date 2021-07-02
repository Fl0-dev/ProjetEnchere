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
	<main class="col-md-6 align-items-center">
		<p class="text-dark container d-flex justify-content-center">${resultat}</p>
			
		<div class="h2 container d-flex justify-content-center">Nouvelle vente</div> 

			<form action="${pageContext.request.contextPath }/NouvelleVente" method="post">
				<div class="form-group ">
					<label for="nom_article">Article :</label> 
					<input class="form-control" type="text" id="nom_article" name="nom_article">
					<span>${MapErreurs['nomArticle'] }</span>
	
					<label for="description">Description :</label>
					<textarea class="form-control" id="description" name="description"  placeholder="Description de l'article"></textarea>
					<span>${MapErreurs['description'] }</span>
					
					<div class="form-control mt-2">
						<label for="categories">Catégorie :</label> 
						<select id="categories" name="categorie" class="form-control">	
							<option value="0" selected>--</option>
							<c:forEach items="${listeCategories }" var="categorie">
								<option value="${categorie.noCategorie }">${categorie.libelle }</option>
							</c:forEach>
						</select>
						<span>${MapErreurs['categorie'] }</span>
					</div>
			
					<div>
						<label for="prix_initial">Mise à prix :</label> 
						<input class="form-control" type="number" id="prix_initial" name="prix_initial" required >
						<span>${MapErreurs['prixInitial'] }</span>
					</div>
				
					<div>
						<label for="debutenchere">Début de l'enchère :</label>
						<input class="form-control" type="date" id="debutenchere" name="debutenchere" value="<c:out value="${today }"/>">
					</div>
					<div>
						<label for="finenchere">Fin de l'enchère :</label> 
						<input class="form-control"type="date" id="finenchere" name="finenchere">
						<span>${MapErreurs['dateEnchere'] }</span>
					</div>
				</div>
			
				<div class ="card">	
					<div class="card-body">
						<h5 class="card-title">Retrait</h5>
						<label for="rue">Rue :</label> 
						<input class="form-control"type="text" id="rue" name="rue" value="<c:out value="${utilisateurSession.rue }"/>">
						<label for="codePostal">Code postal :</label> 
						<input class="form-control" type="text" id="codePostal" name="codePostal" value="<c:out value="${utilisateurSession.codePostal }"/>">
						<label for="ville">Ville :</label> 
						<input class="form-control" type="text" id="ville" name="ville" value="<c:out value="${utilisateurSession.ville }"/>">
						<span>${MapErreurs['adresseRetrait'] }</span>
					</div>
				</div>
					
				<button class="btn btn-info btn-block my-3"type="submit">Enregistrer</button>
				
			</form>
					
			<a class="btn btn-secondary text-white btn-block mb-3" href="${pageContext.request.contextPath }/AccueilConnecte">Annuler</a>
		</div>
				
	
	</main>
		
		
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
		
</body>
</html>