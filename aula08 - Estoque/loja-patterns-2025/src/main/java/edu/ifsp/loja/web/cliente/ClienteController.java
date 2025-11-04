package edu.ifsp.loja.web.cliente;

import java.io.IOException;

import edu.ifsp.loja.web.Command;
import edu.ifsp.loja.web.PageNotFound;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cliente/*")
public class ClienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Command cmd = switch (request.getPathInfo()) {
		case "/pesquisar" -> new PesquisarClienteCommand();
		default -> PageNotFound.getInstance();
		};

		cmd.execute(request, response);
	}

}
