<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang=""fr>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>ENI-Enchères</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">


<title>Enchérir</title>
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
	
	<!-- LIGNE -->
	<div class="row d-flex justify-content-center">
		<!-- COLONNE VIDE 1 -->
	<div class="col-md-3 align-items-start"></div>
		
		<!-- COLONNE CENTRALE -->
	<main class="col-md-6 align-items-center">


<div class="card mb-3">
				
	<div class="card-body">
	<div>${resultat }</div>
	<div class="h2 container d-flex justify-content-center">Détail vente</div>

	<h5 class="card-title">${articleSelected.nomArticle }</h5>
	<table>
  
    <tbody>
		
         <tr>
            <td>Description :</td>
            <td>${articleSelected.description }</td>
        </tr>
       
         <tr>
            <td>Catégorie :</td>
            <td>${articleSelected.categorieArticle.libelle }</td>
        </tr>
         <tr>
            <td>Meilleure offre :</td>
            <td>${articleSelected.enchereMax.montant_enchere == 0 ? "Pas encore d'offre effectuée" : articleSelected.enchereMax.montant_enchere += " points"} </td>
        </tr>
         <tr>
            <td>Mise à prix :</td>
            <td>${articleSelected.miseAPrix } points</td>
        </tr>
         <tr>
            <td>Fin de l'enchère :</td>
            <td>${articleSelected.dateFinEncheres }</td>
        </tr>
         <tr>
            <td>Retrait :</td>
            <td>${articleSelected.lieuRetrait.rue_retrait } <br />
            ${articleSelected.lieuRetrait.code_postal_retrait } ${articleSelected.lieuRetrait.ville_retrait }</td>
        </tr>
        <tr>
            <td>Vendeur :</td>
            <td>${articleSelected.utilisateur.pseudo }</td>
        </tr>
 
    </tbody>
</table>
	<form name="enchere" method="POST" action="${pageContext.request.contextPath }/encherir">
	
			<label for="montant_enchere">Ma proposition :</label>
			
			<input type="number" id="montant_enchere" name="montant_enchere" required step="1" min="${articleSelected.enchereMax.montant_enchere == 0 ? articleSelected.miseAPrix + 1 : articleSelected.enchereMax.montant_enchere + 1 }" placeholder="${articleSelected.enchereMax.montant_enchere == 0 ? articleSelected.miseAPrix + 1 : articleSelected.enchereMax.montant_enchere + 1 }">
			
			
			<input id="article" name="article" type="hidden" value="${articleSelected.noArticle}" readonly>
			
			<button id="submitEnchere" name="submitEnchere" class="btn btn-info btn-block mb-3" type="submit">Enchérir</button>
	
	</form>
			</div>
			
	</div>
	</main>


<div class="col-md-3 align-items-end"></div>
</div>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	
</body>
</html>