<%@ page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Affichage de la commande</title>
	<link type="text/css" rel="stylesheet" href="<c:url value="inc/style.css"/>"  />
</head>

<body>
<c:import url="/inc/menu.jsp"> </c:import>

	
	
	
	
<p class="info">${requestScope.message }</p>



	<h2>Client</h2>
	
	<p>Nom : <c:out value="${requestScope.commande.client.nom }" /> </p>
	<p>Prénom : <c:out value="${requestScope.commande.client.prenom }" /></p>
	<p>Adresse : <c:out value="${requestScope.commande.client.adresse }" /></p>
	<p>Téléphone : <c:out value="${requestScope.commande.client.telephone }" /></p>
	<p>Email : <c:out value="${requestScope.commande.client.email }" /></p>
	
	<h2>Commande</h2>
	
	<p>Date : <c:out value="${requestScope.commande.date }" /></p>
	<p>Montant : <c:out value="${requestScope.commande.montant }" /></p>
	<p>Mode de paiement : <c:out value="${requestScope.commande.modePaiement }" /></p>
	<p>Statut du paiement : <c:out value="${requestScope.commande.statutPaiement }" /></p>
	<p>Mode de livraison : <c:out value="${requestScope.commande.modeLivraison }" /></p>
	<p>Statut de la livraison : <c:out value="${requestScope.commande.statutLivraison }" /></p>
	


<p class="info"> Commande crée avec Succes</p>

</body>

</html>