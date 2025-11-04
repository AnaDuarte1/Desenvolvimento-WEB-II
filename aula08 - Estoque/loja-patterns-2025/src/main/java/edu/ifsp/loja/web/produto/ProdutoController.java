package edu.ifsp.loja.web.produto;

import java.io.IOException;

import edu.ifsp.loja.web.Command;
import edu.ifsp.loja.web.PageNotFound;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/produto/*")
public class ProdutoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Command cmd = switch (request.getPathInfo()) {
		case "/pesquisar" -> new PesquisarProdutoCommand();
		case "/editar" -> new CadastrarInitCommand();
        // NOVO: Mapeamento para a página de gerenciamento de estoque
        case "/estoque" -> new BaixarEstoqueInitCommand(); 
		default -> PageNotFound.getInstance();
		};

		cmd.execute(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Command cmd = switch (request.getPathInfo()) {
		case "/editar" -> new CadastrarProdutoCommand();
        // Mapeamento para a operação de baixa de estoque (mantido)
        case "/baixarEstoque" -> new BaixarEstoqueCommand(); 
		default -> PageNotFound.getInstance();
		};

		cmd.execute(request, response);
	}

}