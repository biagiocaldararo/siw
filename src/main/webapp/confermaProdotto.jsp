<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>BGS-Shop/confermaProdotto</title>
	</head>
	<body>
		<form action="confermaProdotto" method="get">
			<b> Sicuro di voler inserire questo prodotto? </b>
			<p> Nome: ${nome}</p>
			<p> Descrizione: ${descrizione}</p>
			<p> Prezzo: ${prezzo}</p>
			<input type="submit" name="risp" value="si" />
			<input type="submit" name="risp" value="no" />
		</form>
	</body>
</html>