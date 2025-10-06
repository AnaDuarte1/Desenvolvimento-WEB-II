<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<base href="<%= request.getContextPath() %>/">
</head>
<body>
    <h1>Login</h1>

    <%
    String error = (String)request.getAttribute("error");
    if (error != null) {
    %>
        <p style="color:red;"><%= error %></p>
    <%
    }
    %>

    <form action="login" method="post">
        <label for="usuario">Usuário:</label>
        <input type="text" id="usuario" name="usuario" value="scott"><br><br>
        
        <label for="senha">Senha:</label>
        <input type="password" id="senha" name="senha" value="tiger"><br><br>
        
        <button type="submit">Entrar</button>
    </form>
</body>
</html>