package edu.ifsp.loja.web.produto;

import java.io.IOException;

import edu.ifsp.loja.modelo.Produto;
import edu.ifsp.loja.web.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import edu.ifsp.loja.service.ProdutoService;
import java.util.List;

public class BaixarEstoqueInitCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Produto produto = new Produto();
		ProdutoService service = new ProdutoService();
		List<String> errors = null;
		
		String paramId = request.getParameter("id");
		if (paramId != null && !paramId.isBlank()) {
			try {
                int id = Integer.parseInt(paramId);
                // Busca o produto (retorna null se não encontrar)
                produto = service.buscarProdutoPorId(id); 
            } catch (NumberFormatException e) {
                errors = List.of("ID de produto inválido.");
            }
		} else {
            errors = List.of("ID de produto não especificado.");
		}

        if (produto == null) {
            // Se o ID for válido, mas o produto não for encontrado, tratamos como erro
            errors = List.of("Produto não encontrado.");
            produto = new Produto(); // Evita NullPointerException na JSP
        }
        
        if (errors != null) {
            request.setAttribute("errors", errors);
        }
		
		request.setAttribute("produto", produto);
		RequestDispatcher rd = request.getRequestDispatcher("/paginas/produto/baixarEstoque.jsp");
		rd.forward(request, response);
	}

}