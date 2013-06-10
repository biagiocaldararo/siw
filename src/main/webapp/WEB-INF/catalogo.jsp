<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:base title="Catalogo">
<div class="row">
  <c:forEach var="prodotto" items="${ prodotti }" varStatus="stat">
    <div class="span4">
      <a class="pull-left" href="/bgsshop/prodotti/${ prodotto.id }">
        <img style="padding-right: 5px" src="http://ecx.images-amazon.com/images/I/41l-6v-9tdL._AA160_.jpg" class="img-rounded">
      </a>
      <div style="padding-left: 5px">
        <h4><a href="/bgsshop/prodotti/${ prodotto.id }">${ prodotto.nome }</a></h4>
        <span>${ prodotto.prezzo } €</span>
      </div>
    </div>
    <c:if test="${ stat.count % 3 == 0 }"></div><div class="row"></c:if>
  </c:forEach>
</div>
</t:base>
