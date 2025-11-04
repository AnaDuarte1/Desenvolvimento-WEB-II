package edu.ifsp.loja.web.login;

import java.io.IOException;

import edu.ifsp.loja.web.Command;
import edu.ifsp.loja.web.ViewCommand;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("user");
		String password = request.getParameter("password");
		
		// Usuário e senha corretos?
		if (!("scott".equals(user) && "tiger".equals(password))) {
			request.setAttribute("error", true);
			request.getRequestDispatcher("/paginas/login/login.jsp").forward(request, response);
			return;
		}
				
		HttpSession session = request.getSession();
		session.setAttribute("user", user);

		String path = request.getParameter("next");
		if (path == null) {
			path = "/home";
		}
		
		new ViewCommand("redirect:" + path).execute(request, response);
	}

}
