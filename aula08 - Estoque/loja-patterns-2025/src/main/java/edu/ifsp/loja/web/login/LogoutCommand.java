package edu.ifsp.loja.web.login;

import java.io.IOException;

import edu.ifsp.loja.web.Command;
import edu.ifsp.loja.web.ViewCommand;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LogoutCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		new ViewCommand("redirect:/home").execute(request, response);
	}

}
