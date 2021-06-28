<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Identification</title>

<!-- CSS -->
<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet">
</head>
<body>
<header>
<h1><a href="${pageContext.request.contextPath }/ServletAccueil">ENI-Enchères</a></h1>
</header>

<p>${messageErreur}</p>

<!--  Formulaire de connexion -->

<form method="POST" action="${pageContext.request.contextPath }/ServletIdentification">
	<div>
		<label for="identifiant">Identifiant : </label>
		<input type="text" id="identifiant" name="identifiant" placeholder="monIdentifiant" autofocus >
	</div>
	
	<div>
		<label for="mdp">Mot de passe : </label>
		<input type="password" id="mdp" name="mdp" placeholder="monMotDePasse" >
	</div>
	
	<div>
		<input type="checkbox" name="seSouvenir" id="seSouvenir">
		<label for="seSouvenir">Se souvenir de moi</label>
	</div>
	
	<div>
		<button type="submit">Connexion</button>
	</div>
</form>

	<a href="${pageContext.request.contextPath }/ServletCreationProfil">Créer un compte</a>



</body>
</html>