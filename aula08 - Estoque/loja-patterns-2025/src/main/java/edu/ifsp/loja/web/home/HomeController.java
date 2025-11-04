package edu.ifsp.loja.web.home;

import java.io.IOException;

import edu.ifsp.loja.web.ViewCommand;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/home")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ViewCommand homeCommand = new ViewCommand("forward:/paginas/home/home.jsp");


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		homeCommand.execute(request, response);	
	}
}
