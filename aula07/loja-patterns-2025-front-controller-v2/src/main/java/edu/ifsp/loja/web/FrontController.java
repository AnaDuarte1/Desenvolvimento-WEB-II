package edu.ifsp.loja.web;

import java.io.IOException;

import edu.ifsp.loja.web.cliente.PesquisarClienteCommand;
import edu.ifsp.loja.web.home.HomeCommand;
import edu.ifsp.loja.web.login.LoginProcessCommand;
import edu.ifsp.loja.web.login.LogoutCommand;
import edu.ifsp.loja.web.produto.CadastrarInitCommand;
import edu.ifsp.loja.web.produto.CadastrarProcessCommand;
import edu.ifsp.loja.web.produto.PesquisarProdutoCommand;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/")
public class FrontController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Command cmd = resolveCommand(request);
		cmd.execute(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Command cmd = resolveCommand(request);
		cmd.execute(request, response);
	}

	private Command resolveCommand(HttpServletRequest request) {
		System.out.println("[FrontController] %s %s".formatted(request.getMethod(), request.getRequestURI()));
		
		String path = request.getRequestURI().substring(request.getContextPath().length());
		
		if (path.isEmpty() || path.equals("/")) {
			path = "/home";
		}

		Command cmd = null;
		
		if ("GET".equals(request.getMethod())) {
			switch (path) {
			case "/login":
				cmd = new ForwardCommand("/paginas/login/login.jsp");
				break;
			case "/logout":
				cmd = new LogoutCommand();
				break;
			case "/home":
				cmd = new HomeCommand();
				break;
			case "/produto/pesquisar":
				cmd = new PesquisarProdutoCommand();
				break;
			case "/produto/editar":
				cmd = new CadastrarInitCommand();
				break;
			case "/cliente/pesquisar":
				cmd = new PesquisarClienteCommand();
				break;
			}
		}
		
		if ("POST".equals(request.getMethod())) {
			switch (path) {
			case "/login":
				cmd = new LoginProcessCommand();
				break;
			case "/produto/editar":
				cmd = new CadastrarProcessCommand();
				break;
			}
		}
		
		if (cmd == null) {
			throw new IllegalArgumentException("Unknown destination: " + path);
		}
		
		return cmd;
	}
}