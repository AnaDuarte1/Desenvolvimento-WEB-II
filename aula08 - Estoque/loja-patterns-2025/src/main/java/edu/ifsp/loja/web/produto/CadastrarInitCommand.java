package edu.ifsp.loja.web.produto;

import java.io.IOException;

import edu.ifsp.loja.modelo.Produto;
import edu.ifsp.loja.service.ProdutoService; // Importa o Service
import edu.ifsp.loja.web.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CadastrarInitCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final Produto produto;
		
		String paramId = request.getParameter("id");
		if (paramId != null && !paramId.isBlank()) {
			// DELEGAÇÃO: Usa o Service em vez do DAO
			ProdutoService service = new ProdutoService(); 
			int id = Integer.parseInt(paramId);
			produto = service.buscarProdutoPorId(id);
		} else {
			produto = new Produto();
		}

		request.setAttribute("produto", produto);
		RequestDispatcher rd = request.getRequestDispatcher("/paginas/produto/editar.jsp");
		rd.forward(request, response);
	}

}