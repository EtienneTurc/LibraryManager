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
				<h1 class="page-announce-text valign">Fiche livre</h1>
			</div>
			<div class="row">
				<div class="container">
					<h5>Détails du livre n°
						<c:out value="${book.getId()}" />
					</h5>
					<div class="row">
						<form action="book_details?id=${book.getId()}" method="post" class="col s12">
							<div class="row">
								<div class="input-field col s12">
									<input id="title" type="text" value="${book.getTitle()}" name="title">
									<label for="title">Titre</label>
								</div>
							</div>
							<div class="row">
								<div class="input-field col s6">
									<input id="author" type="text" value="${book.getAuthor()}" name="author">
									<label for="author">Auteur</label>
								</div>
								<div class="input-field col s6">
									<input id="isbn" type="text" value="${book.getIsbn()}" name="isbn">
									<label for="isbn">ISBN 13</label>
								</div>
							</div>
							<div class="row center">
								<button class="btn waves-effect waves-light" type="submit">Enregistrer</button>
								<button class="btn waves-effect waves-light orange" type="reset">Annuler</button>
							</div>
						</form>

						<form action="book_delete" method="get" class="col s12">
							<input type="hidden" value="${book.getId()}" name="id">
							<div class="row center">
								<button class="btn waves-effect waves-light red" type="submit">Supprimer le livre
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
										<th>Emprunteur</th>
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
