package edu.ifsp.loja.web.cliente;

import java.io.IOException;
import java.util.List;

import edu.ifsp.loja.modelo.Cliente;
import edu.ifsp.loja.persistencia.ClienteDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cliente/editar")
public class CadastrarClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		final Cliente cliente;
		
		String paramId = request.getParameter("id");
		if (paramId != null && !paramId.isBlank()) {
			ClienteDAO dao = new ClienteDAO();
			int id = Integer.parseInt(paramId);
			cliente = dao.findById(id);
		} else {
			cliente = new Cliente();
		}
		
		String success = request.getParameter("success");
		if (success != null && success.equals("true")) {
		    request.setAttribute("success", "Cliente salvo com sucesso!");
		}

		request.setAttribute("cliente", cliente);
		RequestDispatcher rd = request.getRequestDispatcher("/paginas/cliente/editar.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String paramId = request.getParameter("id");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		
		
		ClienteValidator validator = new ClienteValidator();
		List<String> errors = validator.validate(nome, email);		
		if (!errors.isEmpty()) {
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("/paginas/cliente/editar.jsp").forward(request, response);
			return;
		}
		
				
		Cliente cliente = new Cliente();
		int id = Integer.parseInt(paramId);
		cliente.setId(id);
		cliente.setNome(nome);
		cliente.setEmail(email);
		
		ClienteDAO dao = new ClienteDAO();
		dao.save(cliente);
		
		response.sendRedirect(request.getContextPath() + "/cliente/editar?id=" + cliente.getId() + "&success=true");
	}
}