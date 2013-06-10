<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:base title="Amministrazione Ordini">

<table class="table">
  <thead>
    <tr>
      <th>#</th>
      <th>Data</th>
      <th>Cliente</th>
      <th>Totale</th>
      <th>Stato</th>
      <th>Imposta come evaso</th>
    </tr>
  </thead>
  <tbody>
<c:forEach var="ordine" items="${ ordini }">
    <tr>
      <td><!-- non ancora <a href="/bgsshop/admin/ordini/${ ordine.id }"> -->${ ordine.id }</td>
      <td>${ ordine.data }</td>
      <td><a href="/bgsshop/admin/utenti/${ ordine.utente.id }">${ ordine.utente.username }</a></td>
      <td>${ ordine.totale }</td>
      <td>${ ordine.stato }</td>
      <td><a href="/bgsshop/admin/ordini/${ ordine.id }/spedisci" class="btn">Evaso!</a></td>
    </tr>
</c:forEach>
  </tbody>
</table>

</t:base>
