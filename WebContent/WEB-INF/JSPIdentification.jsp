<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>ENI-Enchères</h1>

<form method="POST" action="${pageContext.request.contextPath }/ServletIdentification">
	<label for="identifiant">Identifiant : </label>
	<input type="text" id="identifiant" name="identifiant" placeholder="monIdentifiant" autofocus required>
	<br>
	<label for="mdp">Mot de passe : </label>
	<input type="password" id="mdp" name="mdp" placeholder="monMotDePasse" required>
	<br>
	<input type="checkbox" name="seSouvenir" id="seSouvenir">
	<label for="seSouvenir">Se souvenir de moi</label>
	<br>
	<button type="submit">Connexion</button>
</form>

	<a href="${pageContext.request.contextPath }/********AJOUTLIENSERVLETCREATIONPROFIL**********">Créer un compte</a>



</body>
</html>