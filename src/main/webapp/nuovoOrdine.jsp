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
		<p><i>Carrello: (${numeroProdotti})</i></p>
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
				<td> ${prodotto.cod} </td>
				<td> ${prodotto.nome} </td>
				<form action="dettagliProdotto.do" method="post">
					<td><input type="submit" name="${prodotto.cod}" value="Dettagli"/></td>
				</form>
				<form action="aggiungiAlCarrello.do" method="post">
				    <td><input type="text" name="quantità" value="1"/></td>
					<td><input type="submit" name ="${prodotto.cod}" value="Aggiungi al Carrello"/></td>
				</form>
			</tr>
		</c:forEach>
		</table>
		<p></p>
		<a href="carrello.jsp"><input type="button" value ="Il tuo Carrello"/></a>
	</body>
</html>