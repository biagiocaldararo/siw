<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>BGS-Shop/homeAdmin</title>
	</head>
	<body>
		<p><i>${cliente.username} [${cliente.ruolo}]</i></p>
		<form action="vaiInserimentoProdotto" method="get">
			<input type="submit" name="submit" value="Inserisci Prodotto"/>
		</form>
	</body>
</html>