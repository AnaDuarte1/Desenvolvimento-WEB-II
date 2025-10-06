package edu.ifsp.loja.web.cliente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.ifsp.loja.modelo.Cliente;
import edu.ifsp.loja.persistencia.ClienteDAO;
import edu.ifsp.loja.web.Command;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PesquisarClienteCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paramId = request.getParameter("id");
		String paramName = request.getParameter("name");

		ClienteDAO dao = new ClienteDAO();
		List<Cliente> clientes = null;

		if (paramId != null && !paramId.isBlank()) {
			int id = Integer.parseInt(paramId);
			Cliente cliente = dao.findById(id);
			if (cliente != null) {
				clientes = new ArrayList<>();
				clientes.add(cliente);
			}
			
		} else if (paramName != null && !paramName.isBlank()) {
			clientes = dao.findByName(paramName);
			
		}
		
		request.setAttribute("clientes", clientes);		
		RequestDispatcher rd = request.getRequestDispatcher("/paginas/cliente/pesquisar.jsp");
		rd.forward(request, response);
	}
}