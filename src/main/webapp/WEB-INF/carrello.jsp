<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<t:base title="Carrello">

<jsp:attribute name="script">
  <script>
    $(function() {
      $(".riga-carrello input[type='number']").change(function() {
        var self = $(this),
            current = self.data("value"),
            button = self.siblings("button"),
            hidden = self.siblings("input[type='hidden']");

        hidden.val(self.val() - current);

        if (self.val() != current)
          button.show();
        else
          button.hide();
      });
    });
  </script>
</jsp:attribute>

<jsp:body>
<c:choose>
<c:when test="${ utente.carrello.numProdotti > 0 }">
  <div class="row">
    <div class="span9">
      Prodotto
    </div>
    <div class="span2">
      Prezzo
    </div>
    <div class="span1">
      Quantità
    </div>
  </div>

  <c:forEach var="riga" items="${ utente.carrello.righe }">

    <div class="row riga-carrello">
      <div class="span9">
        <a class="pull-left" href="/bgsshop/prodotti/${ riga.prodotto.id }">
          <img src="http://ecx.images-amazon.com/images/I/41l-6v-9tdL._AA160_.jpg" class="img-rounded">
        </a>
        <div>
          <h4><a href="/bgsshop/prodotti/${ riga.prodotto.id }">${ riga.prodotto.nome }</a></h4>
          <span><a href="/bgsshop/carrello/rimuovi/${ riga.prodotto.id }">Rimuovi</a></span>
        </div>
      </div>
      <div class="span2">
        ${ riga.prodotto.prezzo } €
      </div>
      <div class="span1">
        <form action="/bgsshop/carrello/aggiungi/${ riga.prodotto.id }?next=carrello" method="POST">
          <input type="number" min="0" autocomplete="off" data-value="${ riga.quantita }" value="${ riga.quantita}">
          <input type="hidden" name="qty" />
          <button class="btn btn-small" type="submit">Aggiorna</button>
        </form>
      </div>
    </div>

  </c:forEach>

  <div class="row">
    <div class="span3 offset9">
      <p><strong>Totale: ${ utente.carrello.totale } €</strong></p>
      <a class="btn btn-large btn-primary" href="carrello/checkout">Conferma ordine!</a>
    </div>
  </div>

</c:when>
<c:otherwise>
  <p>Il carrello è vuoto :(</p>
</c:otherwise>
</c:choose>
</jsp:body>


</t:base>
