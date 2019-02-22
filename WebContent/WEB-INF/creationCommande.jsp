<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Création d'une commande</title>
    <link type="text/css" rel="stylesheet" href="<c:url value="inc/style.css"/>"  />
</head>
<body>
	<div>
		<form method="post" action="<c:url value="creationCommande" />" >
			<c:import url="/inc/menu.jsp" />
			
			<c:if test="${!empty requestScope.commande.client }">
				<c:set value="${requestScope.commande.client }" var="client" scope="request" />
			
			</c:if>
			<fieldset>
				<c:if test="${!empty sessionScope.listClient }">
					<label>Nouveau Client <span class="requis">*</span> </label>
				 	<input type="radio" name="choixNouveauClient" value="nouveauClient" checked> Oui
	  				<input type="radio" name="choixNouveauClient" value="ancienClient"> Non
	  				<br />
	  				<br />
	  			</c:if>
	  			
	  			
	  			
	  			
	  			<div id="nouveauClient">
					<c:import url="/inc/formClient.jsp"></c:import>
				</div>	
				
				<div id="ancienClient">
					<c:if test="${!empty sessionScope.listClient }">
						
							<select name="nomAncienClient" id="nomAncienClient">
							<c:forEach items="${sessionScope.listClient}" var="client">
							
							  <option value="${client.value['id'] }">${client.value['nom']  } ${client.value['prenom']  }</option>
							  </c:forEach>
							</select>
							
						
					</c:if>
				</div>
			</fieldset>
			
			<fieldset>
			
				<legend>Informations commande</legend>
				
				
				<label for="date">Date <span class="requis">*</span></label>
				<input type="text" name="date" id="date" disabled />
				<span class="erreur" >${requestScope.erreur.date }</span>
				<br />
				
				<label for="montant">Montant <span class="requis">*</span></label>
				<input type="text" name="montant" id="montant" value="<c:out value="${commande.montant }" />" size="20" maxlength="20" />
				<span class="erreur" >${requestScope.erreur['montant'] }</span>
				<br />
				
				<label for="modePaiement">Mode de paiement <span class="requis">*</span></label>
				<input type="text" name="modePaiement" id="modePaiement" value="<c:out value="${requestScope.commande.modePaiement }" />" size="20" maxlength="20" />
				<span class="erreur" >${requestScope.erreur['modePaiement'] }</span>
				<br />
				
				<label for="statutPaiement">Statut de paiement </label>
				<input type="text" name="statutPaiement" id="statutPaiement" value="<c:out value="${requestScope.commande.statutPaiement }" />" size="20" maxlength="20" />
				<span class="erreur" >${requestScope.erreur['statutPaiement'] }</span>
				<br />
				
				<label for="modeLivraison">Mode de livraison <span class="requis">*</span></label>
				<input type="text" name="modeLivraison" id="modeLivraison" value="<c:out value="${requestScope.commande.modeLivraison }" />" size="20" maxlength="20" />
				<span class="erreur" >${requestScope.erreur['modeLivraison'] }</span>
				<br />
				
				<label for="statutlivraison">Statut de livraison </label>
				<input type="text" name="statutlivraison" id="statutlivraison" value="<c:out value="${requestScope.commande.statutLivraison }" />" size="20" maxlength="20" />
				
			
			<c:if test="${!empty erreur }">
			
				<p class="info"> Erreur lors de la création de la commande</p>
			</c:if>
			
			</fieldset>
		
			<input type="submit" value="valider"/>
			<input type="reset" value="Vider le formulaire" />
		
		</form>
	
	
	
	</div>


 <%-- Inclusion de la bibliothèque jQuery. Vous trouverez des cours sur JavaScript et jQuery aux adresses suivantes :
               - http://www.siteduzero.com/tutoriel-3-309961-dynamisez-vos-sites-web-avec-javascript.html 
               - http://www.siteduzero.com/tutoriel-3-659477-un-site-web-dynamique-avec-jquery.html 
               
             Si vous ne souhaitez pas télécharger et ajouter jQuery à votre projet, vous pouvez utiliser la version fournie directement en ligne par Google :
             <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script> 
        --%>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.8.0/jquery.min.js"></script> 
        
        <%-- Petite fonction jQuery permettant le remplacement de la première partie du formulaire par la liste déroulante, au clic sur le bouton radio. --%>
        <script>
        	jQuery(document).ready(function(){
        		/* 1 - Au lancement de la page, on cache le bloc d'éléments du formulaire correspondant aux clients existants */
        		$("div#ancienClient").hide();
        		/* 2 - Au clic sur un des deux boutons radio "choixNouveauClient", on affiche le bloc d'éléments correspondant (nouveau ou ancien client) */
                jQuery('input[name=choixNouveauClient]:radio').click(function(){
                	$("div#nouveauClient").hide();
                	$("div#ancienClient").hide();
                    var divId = jQuery(this).val();
                    $("div#"+divId).show();
                });
            });
        </script>
</body>
</html>