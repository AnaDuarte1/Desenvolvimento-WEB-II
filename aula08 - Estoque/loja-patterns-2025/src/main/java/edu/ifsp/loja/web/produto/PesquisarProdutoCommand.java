package edu.ifsp.loja.web.produto;

import java.io.IOException;
import java.util.List;

import edu.ifsp.loja.modelo.Produto;
import edu.ifsp.loja.service.ProdutoService; // Importa o Service
import edu.ifsp.loja.web.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class PesquisarProdutoCommand implements Command {

	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String query = request.getParameter("query");

		List<Produto> produtos = null;
		
		if (query != null) {
			ProdutoService service = new ProdutoService();
			produtos = service.pesquisarProdutos(query);
		}

		request.setAttribute("produtos", produtos);

		RequestDispatcher rd = request.getRequestDispatcher("/paginas/produto/pesquisar.jsp");
		rd.forward(request, response);
	}

}