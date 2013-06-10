<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:base title="Inserimento Prodotto">
  <h1>Conferma Inserimento</h1>
  <form action="" method="post">
    <ul>
      <li>Nome: ${prodotto.nome}</li>
      <li>Descrizione: ${prodotto.descrizione}</li>
      <li>Prezzo: ${prodotto.prezzo}</li>
    </ul>
    <input type="submit" value="Conferma" />
  </form>
 </t:base>
