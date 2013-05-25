<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>BGS-Shop/confermaOrdine</title>
	</head>
	<body>
		<form action="confermaOrdine.do" method="post">
			<p>Sei sicuro di voler confermare il tuo Ordine?</p>
			<input type="submit" name="risp" value="Si"/>
			<input type="submit" name="risp" value="No"/>
		</form>
	</body>
</html>