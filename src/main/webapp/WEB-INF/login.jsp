<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:base title="Login">
	<form action="?next=/" method="post">
		Username: <input type="text" name="username" /><br>
		Password: <input type="password" name="password" />
		<input type="submit" value="Accedi" />
	</form>
</t:base>
</html>