<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Modifier Profil</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet">
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
	<center>
		<h1>Mon Profil</h1>
	</center>
	<form
		action="${pageContext.request.contextPath }/ModifProfil"
		method="POST">

		<center>

			<label for="pseudo">Pseudo <span>*</span> </label> 
			<input type="text" id="pseudo"name="pseudo" value="<c:out value="${utilisateurSession.pseudo }"/>" autofocus > 
			<span>${MapErreurs['pseudo']}</span> 
			 <br> <br> 
			<label for="nom">Nom <span>*</span> </label> 
			<input type="text" id="nom" name="nom" value="<c:out value="${utilisateurSession.nom }"/>" >
			<span>${MapErreurs['nom']}</span>
			<br> <br> 
			<label for="prenom">Prénom <span>*</span> </label> 
			<input type="text" id="prenom" name="prenom" value="<c:out value="${utilisateurSession.prenom }"/>"  > 
			<span>${MapErreurs['prenom']}</span>
			 <br><br> 
			<label for="email">Email <span>*</span> </label> 
			<input type="mail" id="email" name="email" value="<c:out value="${utilisateurSession.email }"/>"  >
			<span>${MapErreurs['email']}</span>
			<br> <br> 
			<label for="telephone">Téléphone : </label> 
			<input type="tel" id="telephone" name="telephone" value="<c:out value="${utilisateurSession.telephone }"/>"> 
			<span>${MapErreurs['tel']}</span>
			 <br><br> 
			<label for="rue">Rue <span>*</span> </label> 
			<input type="text" id="rue" name="rue" value="<c:out value="${utilisateurSession.rue }"/>"  >
			<span>${MapErreurs['rue']}</span>
			<br> <br> 
			<label for="codePostal">Code Postal <span>*</span> </label> 
			<input type="text" id="codePostal" name="codePostal" value="<c:out value="${utilisateurSession.codePostal }"/>"  >
			<span>${MapErreurs['codePostal']}</span> 
			 <br><br> 
			<label for="ville">Ville <span>*</span> </label> 
			<input type="text" id="ville" name="ville" value="<c:out value="${utilisateurSession.ville }"/>"  > 
			<span>${MapErreurs['ville']}</span>
			<br> <br> 
			<label for="motDePasse">Mot de passe actuel <span>*</span> </label> 
			<input type="password" id="motDePasse" name="motDePasse" > 
			<span>${MapErreurs['VerifMdp']}</span> 
			 <br><br> 
			 <label for="motDePasseNew">Nouveau mot de passe <span>*</span> </label> 
			<input type="password" id="motDePasseNew" name="motDePasseNew"  > 
			<span>${MapErreurs['motDePasseNew']}</span> 
			 <br><br> 
			<label for="confirmation">Confirmation <span>*</span> </label> 
			<input type="password"id="confirmation" name="confirmation"  > 
			<span>${MapErreurs['confirmation']}</span> 
			<br> <br>
			<label for="credit">Crédit : </label>
			<label for="credit">${utilisateurSession.credit}</label>
			</center>

		<center>
			<span>${MapErreurs['champ']}</span> 
			<br><br>
			<button type="submit" value="modif" name="bouton" >Enregistrer</button>
			<br>
			<br>
			<button type="submit" value="suppr" name="bouton" >Supprimer mon compte</button>
			<br>
			<br>
			<p>${resultat}</p>
		</center>
	</form>
	<center>
	<a href="${pageContext.request.contextPath }/Accueil">Supprimer mon compte</a>
	</center>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
	
</body>
</html>