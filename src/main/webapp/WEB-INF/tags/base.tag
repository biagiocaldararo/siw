<%@tag description="Pagina di base" pageEncoding="UTF-8"%>
<%@attribute name="title" %>
<%@attribute name="head" fragment="true" %>
<%@attribute name="content" fragment="true" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>BGSShop / ${ title }</title>
	<jsp:invoke fragment="head" />
</head>
<body>
	<ul id="menu">
		<li><a href="prodotti">Catalogo</a></li>
		<li><a href="carrello">Carrello</a></li>
		<li><a href="ordini">Ordini</a></li>
	</ul>
	<div id="content">
		<jsp:doBody />
	</div>
</body>
</html>