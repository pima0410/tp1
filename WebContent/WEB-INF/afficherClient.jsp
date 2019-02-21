<%@ page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Affichage du client</title>
	<link type="text/css" rel="stylesheet" href="<c:url value="inc/style.css"/>"  />
	
</head>

<body>
<c:import url="/inc/menu.jsp"></c:import>

<p class="info">${requestScope.message }</p>


	<p>Nom : <c:out value="${requestScope.client.nom }" /> </p>
	<p>Prénom : <c:out value="${requestScope.client.prenom }" /></p>
	<p>Adresse : <c:out value="${requestScope.client.adresse }" /></p>
	<p>Téléphone : <c:out value="${requestScope.client.telephone }" /></p>
	<p>Email : <c:out value="${requestScope.client.email }" /></p>
	

	<p class="info"> Client créer avec Succes</p>
	
	
	




</body>

</html>