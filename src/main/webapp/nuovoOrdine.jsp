<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>BGS-Shop/nuovoOrdine</title>
	</head>
	<body>
		<h1>Catalogo Prodotti</h1>
		<p>
			<a href="carrello.jsp"><input type="button" value ="Il tuo Carrello (${numeroProdotti})"/></a>
		</p>
		<p><font color="red">${errori["quantita"]}</font></p>
		<table border = "4">
			<tr>
				<th><b>Codice Prodotto</b></th>
				<th><b>Nome</b></th>
				<th></th>
				<th><b>Quantità</b></th>
				<th></th>
			</tr>			
		<c:forEach var="prodotto" items ="${prodotti}">
			<tr>
				<td> ${prodotto.id} </td>
				<td> ${prodotto.nome} </td>
				<td><a href="dettagliProdotto.do?cod=${prodotto.id}">Dettagli</a></td>
				<form action="aggiungiAlCarrello.do" method="post">
				    <td><input type="text" name="quantita" value="1"/>
				    	<input type="hidden" name="cod" value="${prodotto.id}" /></td>
					<td><input type="submit" value="Aggiungi al Carrello"/></td>
				</form>
			</tr>
		</c:forEach>
		</table>
	</body>
</html>