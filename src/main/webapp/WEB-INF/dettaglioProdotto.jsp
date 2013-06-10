<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:base title="Catalogo">
<h2>${prodotto.nome}</h2>
<p>${prodotto.descrizione}</p>
<p>Prezzo: ${prodotto.prezzo}€</p>
<form action="/bgsshop/carrello/aggiungi/${ prodotto.id }" method="POST">
  <label for="qty">Quantità</label>
  <input type="number" name="qty" value="1" style="width: 4em;" />
  <input type="submit" value="Aggiungi al Carrello" />
</form>
</t:base>
