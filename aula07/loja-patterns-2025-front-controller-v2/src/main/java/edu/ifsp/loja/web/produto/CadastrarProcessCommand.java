package edu.ifsp.loja.web.produto;

import java.io.IOException;
import java.util.List;

import edu.ifsp.loja.modelo.Produto;
import edu.ifsp.loja.persistencia.ProdutoDAO;
import edu.ifsp.loja.web.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CadastrarProcessCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paramId = request.getParameter("id");
		String descricao = request.getParameter("descricao");
		String preco = request.getParameter("preco");
		
		
		ProdutoValidator validator = new ProdutoValidator();
		List<String> errors = validator.validate(descricao, preco);		
		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/paginas/produto/editar.jsp").forward(request, response);
			return;
		}
		
				
		Produto produto = new Produto();
		int id = Integer.parseInt(paramId);
		produto.setId(id);
		produto.setDescricao(descricao);
		double precoDouble = Double.parseDouble(preco);
		produto.setPreco(precoDouble);
		
		ProdutoDAO dao = new ProdutoDAO();
		dao.save(produto);
		
		response.sendRedirect(request.getContextPath() + "/produto/editar?id=" + produto.getId());
	}

}
