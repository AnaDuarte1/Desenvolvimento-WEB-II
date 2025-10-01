package edu.ifsp.loja.web.produto;

import java.io.IOException;
import java.util.List;

import edu.ifsp.loja.factory.DAOFactory;
import edu.ifsp.loja.modelo.Produto;
import edu.ifsp.loja.persistencia.ProdutoDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/produto/editar")
public class CadastrarProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final Produto produto;
		
		String paramId = request.getParameter("id");
		if (paramId != null && !paramId.isBlank()) {
		    DAOFactory factory = (DAOFactory) getServletContext().getAttribute("DAOFactory");
		    ProdutoDAO dao = factory.createProdutoDAO();
			int id = Integer.parseInt(paramId);
			produto = dao.findById(id);
		} else {
			produto = new Produto();
		}

		request.setAttribute("produto", produto);
		RequestDispatcher rd = request.getRequestDispatcher("/paginas/produto/editar.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
	    DAOFactory factory = (DAOFactory) getServletContext().getAttribute("DAOFactory");
	    ProdutoDAO dao = factory.createProdutoDAO();
		dao.save(produto);
		
		response.sendRedirect(request.getContextPath() + "/produto/editar?id=" + produto.getId());
	}

}
