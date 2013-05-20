package it.uniroma3.controller;

import it.uniroma3.model.*;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AutenticaCliente
 */
@WebServlet("/login")
public class AutenticaCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AutenticaCliente() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = getServletContext();
		HttpSession sessione = request.getSession();
		RequestDispatcher rd;
		
		FacadeCliente facade= new FacadeCliente();
		ClienteHelper helper = new ClienteHelper(request, facade.trovaUtente(request.getParameter("username")));
		
		String destinazione = "/home.jsp";
		
		if(helper.login()){
			Cliente c = helper.getCliente();
			if(c.getRuolo().equals("admin"))
				destinazione = "/homeAdmin.jsp";
			else
				destinazione = "/homeCustomer.jsp";
			sessione.setAttribute("cliente", c);
		}
		
		rd = application.getRequestDispatcher(destinazione);
		
		rd.forward(request, response);
	}

}
