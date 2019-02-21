<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Création d'un client</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="inc/style.css"/>"  />
</head>
<body>
	<div>
		<form method="post" action="<c:url value="creationClient"/>">
		<c:import url="/inc/menu.jsp"></c:import>
			<fieldset>
				<c:import url="/inc/formClient.jsp"></c:import>
				<c:if test="${!empty requestScope.erreur }">
					<p class="info">Echec de la création du client </p>
				</c:if>
			</fieldset>
		
			<input type="submit" value="valider"/>
			<input type="reset" value="Vider le formulaire" />
		
		</form>
	
	
	
	</div>
	

</body>
</html>