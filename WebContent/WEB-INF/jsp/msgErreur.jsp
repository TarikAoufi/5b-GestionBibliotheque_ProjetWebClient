	<%
		// recuperation du message et affichage si il existe
		String msgErreur = (String) request.getAttribute("msgErreur");
		if (msgErreur != null) {
	%>
			<p><%=msgErreur%></p>
			<hr />
	<%	} %>