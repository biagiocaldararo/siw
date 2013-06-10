<%@tag description="Pagina di base" pageEncoding="UTF-8"%>
<%@attribute name="title" %>
<%@attribute name="head" fragment="true" %>
<%@attribute name="script" fragment="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <title>BGSShop / ${ title }</title>
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link href="/bgsshop/css/bootstrap.min.css" rel="stylesheet" media="screen">
  <link href="/bgsshop/css/bgsshop.css" rel="stylesheet" media="screen">
  <link href="/bgsshop/css/bootstrap-responsive.css" rel="stylesheet">
  <jsp:invoke fragment="head" />
</head>
<body>
    <div class="container">
      <div class="masthead">
        <h3 class="muted">BGSShop / ${ title }</h3>
        <div class="navbar">
          <div class="navbar-inner">
            <div class="container">
              <ul class="nav">
                <li class="active"><a href="/bgsshop/"><i class="icon-home"></i> Home</a></li>
                <li><a href="/bgsshop/prodotti"><i class="icon-th-list"></i> Catalogo</a></li>
              <c:choose>
              <c:when test="${ user != null }">
                <li><a href="/bgsshop/ordini"><i class="icon-list-alt"></i> Ordini</a></li>
                <li><a href="/bgsshop/carrello"><i class="icon-shopping-cart"></i> Carrello (${ user.carrello.numProdotti })</a></li>
              <c:if test="${ user.isAdmin() }">
                <li><a href="/bgsshop/admin"><i class="icon-plus-sign"></i> Amministrazione</a></li>
              </c:if>
                <li><a href="/bgsshop/logout">Logout</a></li>
              </c:when>
              <c:otherwise>
                <li><a href="/bgsshop/login">Login</a></li>
              </c:otherwise>
              </c:choose>
              </ul>
            </div>
          </div>
        </div><!-- /.navbar -->
      </div>

      <jsp:doBody />
    </div>
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="/bgsshop/js/bootstrap.min.js"></script>
    <jsp:invoke fragment="script" />
</body>
</html>
