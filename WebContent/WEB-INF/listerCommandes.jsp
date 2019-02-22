<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="joda" uri="http://www.joda.org/joda/time/tags" %>




<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Liste des clients</title>
	<link type="text/css" rel="stylesheet" href="<c:url value="inc/style.css"/>"  />
	
</head>

<body>
<c:import url="/inc/menu.jsp" />
<c:choose>
	<c:when test="${empty sessionScope.listCommande }">
		<p class="erreur"> Aucune commande enregistré</p>
	</c:when>
	
	<c:otherwise>
		
		<table>
			<tr>
				<th>Client</th>
				<th>Date</th>
				<th>Montant</th>
				<th>Mode de paiement</th>
				<th>Statut de paiement</th>
				<th>Mode de livraison</th>
				<th>Statut de livraison</th>
				<th class="action">Action</th>
			</tr>
		
		<c:forEach items="${ sessionScope.listCommande}" var="listCommande" varStatus="statut">
		
			<tr class="${statut.count %2 == 0 ? 'pair' :'impair'}">
			
			  
			
				<td><c:out value="${listCommande.value.client.nom } ${listCommande.value.client.prenom }" /></td>
				<td><joda:format value="${listCommande.value.date }"  pattern="dd/MM/yyyy HH:mm:ss" /></td>		
				<td><c:out value="${listCommande.value.montant }" /></td>
				<td><c:out value="${listCommande.value.modePaiement }" /></td>
				<td><c:out value="${listCommande.value.statutPaiement }" /></td>
				<td><c:out value="${listCommande.value.modeLivraison }" /></td>
				<td><c:out value="${listCommande.value.statutLivraison }" /></td>
				
				
				<td class="action"><a href="<c:url value="/suppressionCommandes" ><c:param name="idCommande" value="${listCommande.value['id'] }"></c:param></c:url>"><img alt="Supprimer" src="<c:url value="/inc/supprimer.png"/>"></a></td>
				
			</tr>
			
		</c:forEach>
		
		
		</table>
	
	
	</c:otherwise>

</c:choose>


</body>
</html>