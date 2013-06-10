<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.*" %>
<%@ page import="com.bgsshop.model.*" %>
<%
   Utente utente = (Utente) session.getAttribute("utente");
   boolean autorizzato = false;
   
   if (utente!=null)
       autorizzato = utente.getRuolo().equals("customer");
   
   if (!autorizzato) {
	   RequestDispatcher rd = application.getRequestDispatcher("/loginFallito.jsp");
   	   rd.forward(request, response);
   }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>BGS-Shop/dettagliOrdine</title>
	</head>
	<body>
		<p><i>Ciao, ${utente.username}!</i>
		   <a href="logout.do">Logout</a></p>	
		<h1>Dettagli ordine</h1>
		<table border = "4">
			<tr>
				<th>Codice Riga</th>
				<th>Codice Prodotto</th>
				<th>Quantita</th>
				<th>Costo</th>
				<th></th>
			</tr>			
			<c:forEach var="rigaOrdine" items ="${righeOrdine}">
				<tr>
					<td>${rigaOrdine.id}</td>
					<td>${rigaOrdine.prodotto.id}</td>
					<td>${rigaOrdine.quantita}</td>
					<td>${rigaOrdine.costo}</td>
					<td><a href="dettagliProdotto.do?id=${rigaOrdine.prodotto.id}">Dettagli Prodotto</a></td>
			   	</tr>
			</c:forEach>
		</table>
		<a href="visualizzaOrdini.jsp">Indietro</a>
	</body>
</html>