<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>BGS-Shop/ordineDaEvadere</title>
	</head>
	<body>
		<h1>Ordine:</h1>
		<p>Codice: ${ordine.id}</p>
		<p>Data: ${ordine.data}</p>
		<p>Stato: ${ordine.stato}</p>
		<p>Importo: ${ordine.importo}</p>
		<h2>Dettagli ordine:</h2>   
		<table border = "4">
			<tr>
				<th>Codice Riga</th>
				<th>Codice Prodotto</th>
				<th>Quantita</th>
				<th>Costo</th>
				<th></th>
			</tr>			
			<c:forEach var="rigaOrdine" items ="${ordine.righeOrdine}">
				<tr>
					<td>${rigaOrdine.id}</td>
					<td>${rigaOrdine.prodotto.id}</td>
					<td>${rigaOrdine.quantita}</td>
					<td>${rigaOrdine.costo}</td>
					<td><a href="dettagliProdotto.do?id=${rigaOrdine.prodotto.id}">Dettagli Prodotto</a></td>
			   	</tr>
			</c:forEach>
		</table>
		<p></p>
		<a href="evasioneOrdine.jsp">Indietro</a>
	</body>
</html>