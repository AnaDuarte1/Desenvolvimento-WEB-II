<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="edu.ifsp.loja.modelo.Cliente" %>


<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Produtos - pesquisar</title>
	<base href="<%= request.getContextPath() %>/">
</head>
<body>
	<h1>Pesquisa de Clientes</h1>
	
	<form action="cliente/pesquisar" method="get">
		<label>ID: </label>
		<input type="text" name="id">
		<br>
	
		<label>Nome: </label>
		<input type="text" name="name">
		<br>
		<button type="submit">Pesquisar</button>
	</form>
	<br>


	<%
	List<Cliente> clientes = (List<Cliente>)request.getAttribute("clientes");
	if (clientes != null && !clientes.isEmpty()) {
	%>
	<br>
	<table border="1">
	        <tr>
	                <th>ID</th>
	                <th>Nome</th>
	                <th>Email</th>
	        </tr>
	        
	        <%
	        for (Cliente c : clientes) {
	        %>
	        <tr>
	                <td><%= c.getId() %></td>
	                <td><%= c.getNome() %></td>
	                <td><%= c.getEmail() %></td>
	        </tr>
	        <%
	        }
	        %>
	</table>
	<%
	} 
	if (clientes != null && clientes.isEmpty()) {
	%>
	<p>Nenhum cliente encontrado.</p>
	<%
	}
	%>
	
</body>
</html>