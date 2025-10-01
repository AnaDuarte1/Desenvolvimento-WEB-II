package edu.ifsp.loja.web.home;

import java.io.IOException;

import edu.ifsp.loja.factory.DAOFactory;
import edu.ifsp.loja.persistencia.ProdutoDAO;
import edu.ifsp.loja.persistencia.ClienteDAO;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private ProdutoDAO produtoDAO;
    private ClienteDAO clienteDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("/paginas/home/home.jsp");
        rd.forward(request, response);
    }
}
