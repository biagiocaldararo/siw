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
		<title>BGS-Shop/inserisciProdotto</title>
	</head>
	<body>
		<p><i>${utente.username} [${utente.ruolo}]</i>
		   <a href="logout.do">Logout</a></p>
		<form action="inserisciProdotto.do" method="post">
			<h1>Inserimento Prodotto</h1>
			
			<p> Nome* <input type="text" name="nome"/>
			<font color="red">${errori["nome"]}</font></p>
				                    	
			<p> Descrizione* <input type="text" name="descrizione"/>
			<font color="red">${errori["descrizione"]}</font></p>             				
			
			<p> Prezzo* <input type="text" name="prezzo"/>
			<font color="red">${errori["prezzo"]}</font></p>
			
			<p><input type="submit" name="submit" value="Inserisci" /></p>
			
			<p><i> * Campo obligatorio </i></p>
		</form>
		<a href="homeAdmin.jsp">Home</a>
	</body>
</html>