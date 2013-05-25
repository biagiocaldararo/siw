<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
					<td> ${rigaOrdine.prodotto.cod} </td>
					<td> ${rigaOrdine.prodotto.nome} </td>
					<td> ${rigaOrdine.quantita} </td>
					<td> ${rigaOrdine.costo} </td>
					<form action="rimuoviDalCarrello.do" method="post">
						<td><input type="submit" name="${rigaOrdine.prodotto.cod}" value="Rimuovi dal Carrello"/></td>
					</form>
			   	</tr>
			</c:forEach>
			<th colspan="4">Totale:${ordineCorrente.importo}</th>
		</table>
		<p></p>
		<p>
			<a href="nuovoOrdine.jsp"><input type="button" value ="Indietro"/></a>
			<a href="confermaOrdine.jsp"><input type="button" value ="Procedi all'Acquisto"/></a>
		</p>
		</body>
</html>