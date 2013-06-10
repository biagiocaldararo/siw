<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:base title="Login">


      <form class="form-signin" method="POST">
        

        <h2 class="form-signin-heading">Entra in BGSShop</h2>
        <input type="text" name="username" class="input-block-level" placeholder="Username">
        <input type="password" name="password" class="input-block-level" placeholder="Password">
        <label class="checkbox">
          <input type="checkbox" value="remember-me"> Ricordami
        </label>
        <button class="btn btn-large btn-primary" type="submit">Login</button>
      </form>

</t:base>
</html>
