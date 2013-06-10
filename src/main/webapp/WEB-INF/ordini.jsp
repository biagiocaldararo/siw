<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:base title="I miei Ordini">
<c:set var="ordini" value="${ user.ordini }"></c:set>
<table class="table">
  <thead>
    <tr>
      <th>#</th>
      <th>Data</th>
      <th>Cliente</th>
      <th>Totale</th>
      <th>Stato</th>
    </tr>
  </thead>
  <tbody>
<c:forEach var="ordine" items="${ ordini }">
    <tr>
      <td>${ ordine.id }</td>
      <td>${ ordine.data }</td>
      <td>${ ordine.utente.username }</td>
      <td>${ ordine.totale }</td>
      <td>${ ordine.stato }</td>
    </tr>
</c:forEach>
  </tbody>
</table>
</t:base>
