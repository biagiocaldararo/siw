<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:base title="Gestione Prodotto">
  <form  action="" method="post">
    <div>
    <label for="prodotto-nome">Nome</label>
    <input type="text" id="prodotto-nome" name="nome" value="${ prodotto.nome }"/>
    ${ errori['nome'] }

    <label for="prodotto-desc">Descrizione</label>
    <textarea rows="15" cols="60" style="width:auto" name="descrizione">${ prodotto.descrizione }</textarea>
    ${ errori['descrizione'] }

    <label for="prodotto-prezzo">Prezzo</label>
    <input type="text" name="prezzo" class="input-large" value="${ prodotto.prezzo }"/>
    ${ errori['prezzo'] }
    </div>
    <button type="submit" class="btn btn-large btn-primary">Salva</button>
  </form>
</t:base>
