<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>BGS-Shop/store</title>
	</head>
	<body>
		<h1>Catalogo Prodotti</h1>
			<table border = "4">
				<tr>
					<th><b>Codice Prodotto</b></th>
					<th><b>Nome</b></th>
					<th></th>
				</tr>			
			<c:forEach var="prodotto" items ="${prodotti}">
				<tr>
					<td>${prodotto.id}</td>
					<td>${prodotto.nome}</td>
					<td><a href="dettagliProdotto.do?id=${prodotto.id}">Dettagli</a></td>
			   	</tr>
			</c:forEach>
			</table>
		<a href="home.jsp"><input type="button" value ="Home"/></a>
	</body>
</html>