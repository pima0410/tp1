

	<legend>Informations client</legend>
	<label for="nom">Nom <span class="requis">*</span></label>
	<input type="text" name="nom" id="nom" value="<c:out value="${requestScope.client.nom }" />" size="20" maxlength="20" />
	<span class="erreur">${erreur['nom'] }</span>
	<br />
	
	<label for="prenom">Prénom</label>
	<input type="text" name="prenom" id="prenom" value="<c:out value="${requestScope.client.prenom }" />" size="20" maxlength="20" />
	<span class="erreur">${erreur['prenom'] }</span>
	<br />
	
	<label for="adresse">Adresse de livraison <span class="requis">*</span></label>
	<input type="text" name="adresse" id="adresse" value="<c:out value="${requestScope.client.adresse }" />" size="20" maxlength="20" />
	<span class="erreur">${erreur['adresse'] }</span>
	<br />
	
	<label for="telephone">Numéro de téléphone <span class="requis">*</span></label>
	<input type="text" name="telephone" id="telephone" value="<c:out value="${requestScope.client.telephone }" />" size="20" maxlength="10" />
	<span class="erreur">${erreur['telephone'] }</span>
	<br />
	
	<label for="email">Email </label>
	<input type="text" name="email" id="email" value="<c:out value="${requestScope.client.email }" />" size="20" maxlength="60" />
	<span class="erreur">${erreur['email'] }</span>
	<br />
