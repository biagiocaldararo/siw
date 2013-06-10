<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.bgsshop.model.Ordine" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>BGS-Shop/Carrello</title>
		</head>
	<body>
		<h1>Carrello</h1>
		<table border="4">
			<tr>
				<th><b>Codice Prodotto</b></th>
				<th><b>Prodotto</b></th>
				<th><b>Quantità</b></th>
				<th><b>Costo</b></th>
				<th></th>
			</tr>
			<c:forEach var="rigaOrdine" items ="${ordineCorrente.righeOrdine}">
				<tr>
					<td>${rigaOrdine.prodotto.id}</td>
					<td>${rigaOrdine.prodotto.nome}</td>
					<td>${rigaOrdine.quantita}</td>
					<td>${rigaOrdine.costo}</td>
					<td><a href="rimuoviDalCarrello.do?id=${rigaOrdine.prodotto.id}">Rimuovi dal Carrello</a></td>
			   	</tr>
			</c:forEach>
			<th colspan="4">Totale:${ordineCorrente.importo}</th>
		</table>
		<p></p>
			<p><a href="nuovoOrdine.jsp">Indietro</a></p>
			<% 
			   Ordine ordine = (Ordine) request.getSession().getAttribute("ordineCorrente");
			   String s = "";
			   if(ordine.getRigheOrdine().size()!=0) 
				   s = "Procedi all'Acquisto";
			 %>  
			<p><a href="confermaOrdine.jsp"><%= s %></a></p>
		</body>
</html>