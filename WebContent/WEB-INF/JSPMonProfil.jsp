<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Mon Profil</title>
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
<main>

 <c:if test="${ !empty sessionScope.utilisateurSession }">
        <p>Votre crédit ${ sessionScope.utilisateurSession.credit } !</p>
    </c:if>
    
<!--  TODO: récupérer les informations de l'utilisateur connecté -->
<table>
    <thead>
        <tr>
            <th colspan="2">Mon profil</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td>Pseudo :</td>
            <td>${ sessionScope.utilisateurSession.pseudo }</td>
        </tr>
         <tr>
            <td>Nom :</td>
            <td>${ sessionScope.utilisateurSession.nom }</td>
        </tr>
         <tr>
            <td>Prénom :</td>
            <td>${ sessionScope.utilisateurSession.prenom }</td>
        </tr>
         <tr>
            <td>Email :</td>
            <td>${ sessionScope.utilisateurSession.email }</td>
        </tr>
         <tr>
            <td>Téléphone :</td>
            <td>${ sessionScope.utilisateurSession.telephone }</td>
        </tr>
         <tr>
            <td>Rue</td>
            <td>${ sessionScope.utilisateurSession.rue }</td>
        </tr>
         <tr>
            <td>Code postal</td>
            <td>${ sessionScope.utilisateurSession.codePostal }</td>
        </tr>
         <tr>
            <td>Ville</td>
            <td>${ sessionScope.utilisateurSession.ville }</td>
        </tr>
    </tbody>
</table>

<a href="${pageContext.request.contextPath }/ModifProfil">Modifier</a>

</main>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</body>

</html>