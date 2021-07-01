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
	
	<div class="card mb-3">
	<div class="card-body">
			<p class="text-dark container d-flex justify-content-center">${resultat}</p>
			
			<div class="h2 container d-flex justify-content-center">Nouvelle vente</div> 


	<form action="${pageContext.request.contextPath }/NouvelleVente" method="post">
		<div>
			<label for="nom_article">Article :</label> <input type="text"
				id="nom_article" name="nom_article">
				<span>${MapErreurs['nomArticle'] }</span>
		</div>

		<div>
			<label for="description">Description :</label>
			<textarea id="description" name="description" rows="5" cols="15" placeholder="Description de l'article"></textarea>
			<span>${MapErreurs['description'] }</span>
		</div>

		<div>
			<label for="categories">Catégorie :</label> <select id="categories"
				name="categorie">
				<option value="0" selected>--</option>
			
				<c:forEach items="${listeCategories }" var="categorie">
					<option value="${categorie.noCategorie }">${categorie.libelle }</option>
				</c:forEach>
			</select>
			<span>${MapErreurs['categorie'] }</span>
		</div>

		<div>
			<label for="prix_initial">Mise à prix :</label> <input type="number"
				id="prix_initial" name="prix_initial" required >
				<span>${MapErreurs['prixInitial'] }</span>
		</div>
	<div>
		<div>
			<label for="debutenchere">Début de l'enchère :</label> <input
				type="date" id="debutenchere" name="debutenchere">
		</div>
		<div>
			<label for="finenchere">Fin de l'enchère :</label> <input type="date"
				id="finenchere" name="finenchere">
				<span>${MapErreurs['dateEnchere'] }</span>
		</div>
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
			<span>${MapErreurs['adresseRetrait'] }</span>
		</fieldset>
		
		

		<button class="btn btn-info btn-block mb-3"type="submit">Enregistrer</button>
		

			</form>
			
			<div  class="btn btn-info btn-block mb-3"><a href="${pageContext.request.contextPath }/AccueilConnecte">Annuler</button>
		</div>
		</div>
		</div>
		</main>
		<div class="col-md-3 align-items-end"></div>
		</div>
		
</body>
</html>