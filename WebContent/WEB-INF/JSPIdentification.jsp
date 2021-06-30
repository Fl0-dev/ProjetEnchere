<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Identification</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet">
</head>
<body>
<header>
<h1><a href="${pageContext.request.contextPath }/Accueil">ENI-Enchères</a></h1>
</header>

<p>${messageErreur}</p>

<!--  Formulaire de connexion -->

<form method="POST" action="${pageContext.request.contextPath }/Identification">
	<div>
		<label for="identifiant">Identifiant : </label>
		<input type="text" id="identifiant" name="identifiant" placeholder="monIdentifiant" autofocus >
	</div>
	
	<div>
		<label for="mdp">Mot de passe : </label>
		<input type="password" id="mdp" name="mdp" placeholder="monMotDePasse" >
	</div>
	
	<!-- <div>
		<input type="checkbox" name="seSouvenir" id="seSouvenir">
		<label for="seSouvenir">Se souvenir de moi</label>
	</div>
	-->
	
	
	<div>
		<button type="submit">Connexion</button>
	</div>
</form>

	<button><a href="${pageContext.request.contextPath }/CreationProfil">Créer un compte</a></button>


<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</body>
</html>