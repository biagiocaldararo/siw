<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ page import="com.bgsshop.model.*" %>
<%
   Utente utente = (Utente) session.getAttribute("utente");
   boolean autorizzato = false;
   
   if (utente!=null)
       autorizzato = utente.getRuolo().equals("admin");
   
   if (!autorizzato) {
	   RequestDispatcher rd = application.getRequestDispatcher("/loginFallito.jsp");
   	   rd.forward(request, response);
   }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>BGS-Shop/evasioneOrdine</title>
	</head>
	<body>
		<p><i>${utente.username} [${utente.ruolo}]</i>
		   <a href="logout.do">Logout</a></p>
		<h1>Segnalazione Evasione Ordine</h1>
		<form action="evadiOrdine.do" method="post">
			<p>Codice ordine <input type="text" name="id"/>
			   <input type="submit" name="submit" value="Vai"/></p>
		    <font color="red">${errori["id"]}</font></p>
		    <font color="red">${notFound}</font></p>	   
		</form>
		<p><a href="homeAdmin.jsp">Home</a>
	</body>
</html>