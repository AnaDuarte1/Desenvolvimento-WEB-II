package edu.ifsp.loja.web.login;

import java.io.IOException;
import edu.ifsp.loja.web.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginProcessCommand implements Command {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usuario = request.getParameter("usuario");
        String senha = request.getParameter("senha");

        if ("scott".equals(usuario) && "tiger".equals(senha)) {
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogado", usuario);
            response.sendRedirect(request.getContextPath() + "/home");
        } else {
            request.setAttribute("error", "Usuário ou senha inválidos.");
            request.getRequestDispatcher("/paginas/login/login.jsp").forward(request, response);
        }
    }
}