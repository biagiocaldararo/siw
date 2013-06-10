<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<t:base title="Modifica Utente">
  <form  action="" method="post">
    <div>
    <label for="utente-username">Username</label>
    <input type="text" id="utente-username" name="username" value="${ utente.username }"/>
    ${ errori['username'] }

    <label for="utente-ruolo">Ruolo</label>
    <input type="text" name="ruolo" value="${ utente.ruolo }"/>
    ${ errori['ruolo'] }
    </div>
    <button type="submit" class="btn btn-large btn-primary">Salva</button>
  </form>
</t:base>
