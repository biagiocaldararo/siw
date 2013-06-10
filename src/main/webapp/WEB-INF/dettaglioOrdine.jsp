<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:base title="Dettaglio ordine">
    <h1>Dettaglio dell'ordine #${ ordine.id }</h1>
    <ul>
    <c:forEach var="riga" items="${ ordine.righe }">
      <li><a href="prodotti/${ riga.prodotto.id }">${ riga.prodotto.nome }</a>, Quantità: ${ riga.quantita }</li>
    </c:forEach>
    </ul>
    <p>Totale ordine: ${ ordine.totale }€</p>
</t:base>
