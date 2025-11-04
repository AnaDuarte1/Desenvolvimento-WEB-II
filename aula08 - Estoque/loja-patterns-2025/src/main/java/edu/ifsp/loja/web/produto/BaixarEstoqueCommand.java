package edu.ifsp.loja.web.produto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.ifsp.loja.modelo.Produto;
import edu.ifsp.loja.service.EstoqueInsuficienteException;
import edu.ifsp.loja.service.ProdutoService;
import edu.ifsp.loja.web.Command;
import edu.ifsp.loja.web.ViewCommand;
import edu.ifsp.loja.persistencia.DataAccessException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class BaixarEstoqueCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String paramId = request.getParameter("id");
        String paramQuantidade = request.getParameter("quantidade");
        
        ProdutoService service = new ProdutoService();
        List<String> errors = new ArrayList<>();
        int produtoId = 0;
        
        try {
            // 1. Tenta obter e validar o ID e a Quantidade
            if (paramId == null || paramId.isBlank() || paramQuantidade == null || paramQuantidade.isBlank()) {
                errors.add("ID e Quantidade são obrigatórios.");
                throw new NumberFormatException(); 
            }

            produtoId = Integer.parseInt(paramId);
            int quantidade = Integer.parseInt(paramQuantidade);

            // 2. Chama a lógica de negócio na camada de serviço
            service.baixarEstoque(produtoId, quantidade);
                
            // 3. Sucesso: Redireciona para a página de estoque atualizada (novo requisito)
            new ViewCommand("redirect:/produto/estoque?id=" + produtoId).execute(request, response);
            return;
            
        } catch (NumberFormatException e) {
            errors.add("ID e Quantidade devem ser números inteiros válidos.");
        } catch (IllegalArgumentException | EstoqueInsuficienteException e) {
            // Captura erros de regra de negócio (quantidade <= 0 ou estoque insuficiente)
            errors.add(e.getMessage());
        } catch (DataAccessException e) {
            // Captura erro de acesso a dados (e.g., produto não encontrado, erro de DB)
            errors.add("Erro de operação: " + e.getMessage());
        }
        
        // --- Tratamento de Erro (Se o fluxo chegou aqui, houve erro) ---

        // Recarrega o produto para que a página de estoque possa exibir o formulário e o erro.
        Produto produto = null;
        try {
            if (produtoId != 0) {
                produto = service.buscarProdutoPorId(produtoId);
            }
        } catch (DataAccessException ignore) {
            // Se o produto não puder ser carregado, a exceção original é o suficiente.
        }
        
        // Se o produto não pôde ser carregado (ou se o ID inicial for inválido/0), redireciona para pesquisa
        if (produto == null) {
            errors.add("Não foi possível carregar o produto para gerenciar o estoque. Redirecionando para a pesquisa.");
            request.setAttribute("errors", errors);
            new ViewCommand("redirect:/produto/pesquisar").execute(request, response);
            return;
        }

        // 4. Encaminha para a nova página de estoque com os erros e dados atuais do produto
        request.setAttribute("errors", errors);
        request.setAttribute("produto", produto);
        request.getRequestDispatcher("/paginas/produto/baixarEstoque.jsp").forward(request, response);
    }
}