<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Création Profil</title>
</head>
<body>
	<center>
		<h1>Mon Profil</h1>
	</center>
	<form
		action="${pageContext.request.contextPath }/ServletCreationProfil"
		method="POST">

		<center>

			<label for="pseudo">Pseudo : </label> 
			<input type="text" id="pseudo"name="pseudo" autofocus required> 
			<span class="erreur">${MapErreurs['pseudo']}</span> 
			 <br> <br> 
			<label for="nom">Nom : </label> 
			<input type="text" id="nom" name="nom" autofocus required>
			<br> <br> 
			<label for="prenom">Prénom : </label> 
			<input type="text" id="prenom" name="prenom" autofocus required> 
			 <br><br> 
			<label for="email">Email : </label> 
			<input type="mail" id="email" name="email" autofocus required>
			<span class="erreur">${MapErreurs['email']}</span> 
			<br> <br> 
			<label for="telephone">Téléphone : </label> 
			<input type="tel" id="telephone" name="telephone"> 
			 <br><br> 
			<label for="rue">Rue : </label> 
			<input type="text" id="rue" name="rue" autofocus required>
			<br> <br> 
			<label for="codePostal">Code Postal : </label> 
			<input type="text" id="codePostal" name="codePostal" autofocus required>
			 <br><br> 
			<label for="ville">Ville : </label> 
			<input type="text" id="ville" name="ville" autofocus required> 
			<br> <br> 
			<label for="motDePasse">Mot de passe : </label> 
			<input type="password" id="motDePasse" name="motDePasse" autofocus required> 
			 <br><br> 
			<label for="confirmation">Confirmation : </label> 
			<input type="password"id="confirmation" name="confirmation" autofocus required> 
			<span class="erreur">${MapErreurs['mot de passe']}</span> 
			<br> <br>
		</center>

		<center>
			<button type="submit">Créer</button>
			<br>
			<br>
			<p class="${empty erreurs ? 'succes' : 'erreur'}">${resultat}</p>
		</center>
	</form>
	<center>
	<a href="${pageContext.request.contextPath }/ServletAccueil">Annuler</a>
	</center>
</body>
</html>