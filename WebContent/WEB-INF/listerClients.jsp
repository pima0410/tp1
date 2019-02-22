<%@ page pageEncoding="utf-8" %>
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
	<c:when test="${empty sessionScope.listClient }">
		<p class="erreur"> Aucun client enregistré</p>
	</c:when>
	
	<c:otherwise>
		
		<table>
			<tr>
				<th>Nom</th>
				<th>Prénom</th>
				<th>Adresse</th>
				<th>Telephone</th>
				<th>Email</th>
				<th class="action">Action</th>
			</tr>
		
		<c:forEach items="${ sessionScope.listClient}" var="listClient" varStatus="statut">
		
			<tr class="${statut.count %2 == 0 ? 'pair' :'impair'}">
			
			  
			
				<td><c:out value="${listClient.value.nom }" /></td>
				<td><c:out value="${listClient.value.prenom }" /></td>
				<td><c:out value="${listClient.value.adresse }" /></td>
				<td><c:out value="${listClient.value.telephone }" /></td>
				<td><c:out value="${listClient.value.email }" /></td>
				
				<td class="action"><a href="<c:url value="/suppressionClients" ><c:param name="nomClient" value="${listClient.value['id'] }"></c:param></c:url>"><img alt="Supprimer" src="<c:url value="/inc/supprimer.png"/>"></a></td>
				
				
				
			</tr>
			
		</c:forEach>
		
		
		</table>
	
	
	</c:otherwise>

</c:choose>


</body>
</html>