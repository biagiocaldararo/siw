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
		<title>BGS-Shop/visualizzaOrdini</title>
	</head>
	<body>
	<p><i>Ciao, ${utente.username}!</i>
	   <a href="logout.do">Logout</a></p>
	<h1>Lista Ordini effettuati</h1>
		<table border = "4">
			<tr>
				<th>Codice Ordine</th>
				<th>Data</th>
				<th>Stato</th>
				<th>Importo</th>
				<th></th>
			</tr>			
			<c:forEach var="ordine" items ="${ordini}">
				<tr>
					<td>${ordine.id}</td>
					<td>${ordine.data}</td>
					<td>${ordine.stato}</td>
					<td>${ordine.importo}</td>
					<td><a href="dettagliOrdine.do?id=${ordine.id}">Dettagli</a></td>
			   	</tr>
			</c:forEach>
		</table>
		<a href="homeCustomer.jsp">Indietro</a>
	</body>
</html>