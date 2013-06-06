<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
		<title>BGS-Shop/homeCustomer</title>
	</head>
	<body>
			<p><i>Ciao, ${utente.username}!</i></p>
		    <a href="nuovoOrdine.do">Nuovo Ordine</a>
	</body>
</html>