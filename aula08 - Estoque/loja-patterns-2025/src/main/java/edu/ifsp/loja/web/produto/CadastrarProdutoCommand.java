package edu.ifsp.loja.web.produto;

import java.io.IOException;
import java.util.List;

import edu.ifsp.loja.modelo.Produto;
import edu.ifsp.loja.persistencia.ProdutoDAO;
import edu.ifsp.loja.service.ProdutoValidator;
import edu.ifsp.loja.web.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CadastrarProdutoCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paramId = request.getParameter("id");
		String descricao = request.getParameter("descricao");
		String preco = request.getParameter("preco");
        // Lendo o novo campo 'estoque'
        String estoque = request.getParameter("estoque"); 
		
		
		ProdutoValidator validator = new ProdutoValidator();
		List<String> errors = validator.validate(descricao, preco);		
        
        // Validação do campo estoque
        int estoqueInt = 0;
        try {
            if (estoque == null || estoque.isBlank()) {
                errors.add("O campo 'Estoque' é obrigatório para o cadastro/edição.");
            } else {
                estoqueInt = Integer.parseInt(estoque);
                if (estoqueInt < 0) {
                    errors.add("O estoque inicial não pode ser negativo.");
                }
            }
        } catch (NumberFormatException e) {
            errors.add("Estoque inválido. Deve ser um número inteiro.");
        }
        
        // Se houver erros, retorna à página de edição
		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
            // Prepara o objeto produto para exibir os dados submetidos na tela novamente
            Produto produtoComErros = new Produto();
            try {
                if (paramId != null && !paramId.isBlank()) {
                    produtoComErros.setId(Integer.parseInt(paramId));
                }
            } catch (NumberFormatException ignore) {}
            produtoComErros.setDescricao(descricao);
            try { produtoComErros.setPreco(Double.parseDouble(preco)); } catch (Exception ignore) {}
            produtoComErros.setEstoque(estoqueInt); 

            request.setAttribute("produto", produtoComErros);
			request.getRequestDispatcher("/paginas/produto/editar.jsp").forward(request, response);
			return;
		}
		
				
		Produto produto = new Produto();
		int id = 0;
		if (paramId != null && !paramId.isBlank()) {
		    try {
		        id = Integer.parseInt(paramId);
		    } catch (NumberFormatException ignore) {}
		}
		
		produto.setId(id);
		produto.setDescricao(descricao);
		double precoDouble = Double.parseDouble(preco);
		produto.setPreco(precoDouble);
        produto.setEstoque(estoqueInt); // Define o estoque a partir do campo preenchido.
		
		ProdutoDAO dao = new ProdutoDAO();
		dao.save(produto);
		
		response.sendRedirect(request.getContextPath() + "/produto/editar?id=" + produto.getId());
	}

}