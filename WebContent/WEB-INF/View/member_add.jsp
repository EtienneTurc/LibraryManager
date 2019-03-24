<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<title>Library Management</title>
	<meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet"
		type="text/css" />
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/0.98.0/css/materialize.min.css">
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<link href="assets/css/custom.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<jsp:include page='menu.jsp'></jsp:include>
	<main>
		<section class="content">
			<div class="page-announce valign-wrapper">
				<a href="#" data-activates="slide-out" class="button-collapse valign hide-on-large-only"><i
						class="material-icons">menu</i></a>
				<h1 class="page-announce-text valign">Fiche membre</h1>
			</div>
			<div class="row">
				<div class="container">
					<h5>Création d'un nouveau membre</h5>
					<div class="row">
						<form action="member_add" method="post" class="col s12">
							<div class="row">
								<div class="input-field col s6">
									<input id="last_name" type="text" name="last_name">
									<label for="last_name">Nom</label>
								</div>
								<div class="input-field col s6">
									<input id="first_name" type="text" name="first_name">
									<label for="first_name">Prénom</label>
								</div>
							</div>
							<div class="row">
								<div class="input-field col s12">
									<input id="address" type="text" name="address">
									<label for="address">Adresse</label>
								</div>
							</div>
							<div class="row">
								<div class="input-field col s6">
									<input id="mail" type="text" name="mail">
									<label for="mail">E-mail</label>
								</div>
								<div class="input-field col s6">
									<input id="phone" type="tel" name="phone">
									<label for="phone">Téléphone</label>
								</div>
							</div>
							<div class="row center">
								<button class="btn waves-effect waves-light" type="submit">Enregistrer</button>
								<button class="btn waves-effect waves-light orange" type="reset">Annuler</button>
							</div>
						</form>
					</div>

				</div>
			</div>
		</section>
	</main>
	<jsp:include page='footer.jsp'></jsp:include>
</body>

</html>
