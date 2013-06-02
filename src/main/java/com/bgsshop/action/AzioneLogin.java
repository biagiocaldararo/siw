package com.bgsshop.action;

import javax.servlet.http.HttpSession;

import com.bgsshop.facade.*;
import com.bgsshop.model.*;
import com.bgsshop.mvc.Azione;

public class AzioneLogin extends Azione {


	private static final String LOGIN_TEMPLATE = "login.jsp";
	private static final String LOGIN_REDIRECT_URL = "/bgsshop/";

	public int get() {
		return ok(LOGIN_TEMPLATE);
	}

	public int post() {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String next = request.getParameter("next");

		FacadeAutenticazione facade = new FacadeAutenticazione();
		Cliente cliente = facade.autentica(username, password);

		if (cliente != null) {
			request.setAttribute("errore", "Username o password errati");
			return forbidden(LOGIN_TEMPLATE);
		}

		login(cliente);

		if (next == null || "".equals(next)) {
			redirect(LOGIN_REDIRECT_URL);
		}

		return redirect(next);


	}

	private void login(Cliente cliente) {
		HttpSession sessione = request.getSession();
		sessione.setAttribute("cliente", cliente);
	}
}
