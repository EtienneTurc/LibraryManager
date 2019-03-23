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
					<h5>Détails du membre n°
						<c:out value="${member.getId()}" />
					</h5>
					<div class="row">
						<form action="member_details?id=${member.getId()}" method="post" class="col s12">
							<div class="row">
								<div class="input-field col s4">
									<input id="last_name" type="text" value="${member.getLastName()}" name="last_name">
									<label for="last_name">Nom</label>
								</div>
								<div class="input-field col s4">
									<input id="first_name" type="text" value="${member.getFirstName()}"
										name="first_name">
									<label for="first_name">Prénom</label>
								</div>
								<div class="input-field col s4">
									<select name="subs" class="browser-default">
										<!-- TODO : faire en sorte que l'option correspondant � l'abonnement du membre soit s�lectionn�e par d�faut -->
										<!-- Pour cela, vous devez rajouter l'attribut selecter sur la balise <option> concern�e -->
										<option value="BASIC" ${(member.getSubscription()=="BASIC" ) ? "selected" : ""
											}>
											Abonnement BASIC</option>
										<option value="PREMIUM" ${(member.getSubscription()=="PREMIUM" ) ? "selected"
											: "" }>
											Abonnement PREMIUM</option>
										<option value="VIP" ${(member.getSubscription()=="VIP" ) ? "selected" : "" }>
											Abonnement VIP</option>
									</select>
								</div>
							</div>
							<div class="row">
								<div class="input-field col s12">
									<input id="address" type="text" value="${member.getAddress()}" name="address">
									<label for="address">Adresse</label>
								</div>
							</div>
							<div class="row">
								<div class="input-field col s6">
									<input id="mail" type="text" value="${member.getMail()}" name="mail">
									<label for="mail">E-mail</label>
								</div>
								<div class="input-field col s6">
									<input id="phone" type="tel" value="${member.getPhone()}" name="phone">
									<label for="phone">Téléphone</label>
								</div>
							</div>
							<div class="row center">
								<button class="btn waves-effect waves-light" type="submit">Enregistrer</button>
								<button class="btn waves-effect waves-light orange" type="reset">Annuler</button>
							</div>
						</form>

						<form action="member_delete" method="get" class="col s12">
							<input type="hidden" value="${member.getId()}" name="id">
							<div class="row center">
								<button class="btn waves-effect waves-light red" type="submit">Supprimer le membre
									<i class="material-icons right">delete</i>
								</button>
							</div>
						</form>
					</div>
					<div class="row">
						<div class="col s12">
							<table class="striped">
								<thead>
									<tr>
										<th>Livre</th>
										<th>Date d'emprunt</th>
										<th>Retour</th>
									</tr>
								</thead>
								<tbody id="results">
									<c:forEach items="${borrows}" var="item">
										<tr>
											<td>
												<c:out value="${item.getMember().getFirstName()}" />
												<c:out value="${item.getMember().getLastName()}" />
											</td>
											<td>
												<c:out value="${item.getStartBorrow()}" />
											</td>
											<td>
												<a href="borrow_return?id=${item.getId()}">
													<ion-icon class="table-item" name="log-in">
												</a>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</section>
	</main>
	<jsp:include page='footer.jsp'></jsp:include>
</body>

</html>
