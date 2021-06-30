<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Création Profil</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet">
</head>
<body>
	<header>
		<h1><a href="${pageContext.request.contextPath }/Accueil">ENI-Enchères</a></h1>
	</header>
	<center>
		<h1>Mon Profil</h1>
	</center>
	<form
		action="${pageContext.request.contextPath }/CreationProfil"
		method="POST">

		<center>

			<label for="pseudo">Pseudo <span>*</span> </label> 
			<input type="text" id="pseudo"name="pseudo" value="<c:out value="${param.pseudo }"/>" autofocus > 
			<span>${MapErreurs['pseudo']}</span>
			 <br> <br> 
			<label for="nom">Nom <span>*</span> </label> 
			<input type="text" id="nom" name="nom" value="<c:out value="${param.nom }"/>" >
			<span>${MapErreurs['nom']}</span>
			<br> <br> 
			<label for="prenom">Prénom <span>*</span> </label> 
			<input type="text" id="prenom" name="prenom" value="<c:out value="${param.prenom }"/>"  > 
			<span>${MapErreurs['prenom']}</span>
			 <br><br> 
			<label for="email">Email <span>*</span> </label> 
			<input type="mail" id="email" name="email" value="<c:out value="${param.email }"/>"  >
			<span>${MapErreurs['email']}</span>
			<br> <br> 
			<label for="telephone">Téléphone : </label> 
			<input type="tel" id="telephone" name="telephone" value="<c:out value="${param.telephone }"/>">
			 <span>${MapErreurs['tel']}</span>
			 <br><br> 
			<label for="rue">Rue <span>*</span> </label> 
			<input type="text" id="rue" name="rue" value="<c:out value="${param.rue }"/>"  >
			<span>${MapErreurs['rue']}</span>
			<br> <br> 
			<label for="codePostal">Code Postal <span>*</span> </label> 
			<input type="text" id="codePostal" name="codePostal" value="<c:out value="${param.codePostal }"/>"  >
			<span>${MapErreurs['codePostal']}</span> 
			 <br><br> 
			<label for="ville">Ville <span>*</span> </label> 
			<input type="text" id="ville" name="ville" value="<c:out value="${param.ville }"/>"  > 
			<span>${MapErreurs['ville']}</span>
			<br> <br> 
			<label for="motDePasse">Mot de passe <span>*</span> </label> 
			<input type="password" id="motDePasse" name="motDePasse"  > 
			<span>${MapErreurs['motDePasse']}</span>
			 <br><br> 
			<label for="confirmation">Confirmation <span>*</span> </label> 
			<input type="password"id="confirmation" name="confirmation"  > 
			<span>${MapErreurs['confirmation']}</span> 
			<br> <br>
		</center>

		<center>
			<br><br>
			<button type="submit">Créer</button>
			<br>
			<br>
			<p>${resultat}</p>
		</center>
	</form>
	<center>
	<a href="${pageContext.request.contextPath }/Accueil">Annuler</a>
	</center>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>