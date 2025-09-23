<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="edu.ifsp.loja.modelo.Produto" %>


<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Produtos</title>
	<base href="<%= request.getContextPath() %>/">
	<style>
		body {
			font-family: Arial, sans-serif;
			background-color: #f0f2f5;
			color: #333;
			margin: 0;
			padding: 20px;
			display: flex;
			justify-content: center;
			align-items: center;
			flex-direction: column;
		}
		.container {
			background-color: #ffffff;
			padding: 40px;
			border-radius: 8px;
			box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
			width: 400px;
			text-align: left;
		}
		h1 {
			color: #1c1e21;
			margin-bottom: 20px;
			text-align: center;
		}
		form {
			display: flex;
			flex-direction: column;
		}
		label {
			margin-bottom: 5px;
			font-weight: bold;
			color: #606770;
		}
		input[type="text"],
		input[type="email"],
		input[type="number"] {
			padding: 10px;
			margin-bottom: 15px;
			border: 1px solid #dddfe2;
			border-radius: 6px;
			font-size: 1em;
		}
		input[type="text"][disabled] {
			background-color: #f5f6f7;
			color: #bec3c9;
		}
		button {
			background-color: #007bff;
			color: white;
			padding: 12px;
			border: none;
			border-radius: 6px;
			font-size: 1.1em;
			cursor: pointer;
			transition: background-color 0.3s ease;
		}
		button:hover {
			background-color: #0056b3;
		}
		ul {
			list-style: none;
			padding: 0;
			margin-bottom: 20px;
			background-color: #fffbe6;
			border: 1px solid #ffeeba;
			border-radius: 6px;
			padding: 15px;
		}
		li {
			color: #856404;
		}
		br {
			display: none;
		}
	</style>
</head>
<body>
	<h1>Cadastro de Produtos</h1>
	
	<%
	List<String> errors = (List<String>)request.getAttribute("errors");
	if (errors != null && !errors.isEmpty()) {
	%>
	<ul>
	<% for (String erro : errors) { %>
		<li><%= erro %></li>
	<% } %>
	</ul>	
	<%
	}
	%>
	
	<%
	Produto produto = (Produto)request.getAttribute("produto");
	%>
	
	<form action="produto/editar" method="post">		
		<label>ID: </label>
		<input type="text" disabled value="<%= produto.getId() %>">
		<input type="hidden" name="id" value="<%= produto.getId() %>">
		<br>
		
		<label for="descricao">Descrição: </label>
		<input type="text" name="descricao" id="descricao" required value="<%= produto.getDescricao() %>">
		<br>
	
		<label for="preco">Preço: </label>
		<input type="number" name="preco" id="preco" required value="<%= produto.getPreco() %>">
		<br>
		
		<br>
		<button type="submit">Salvar</button>
	</form>

</body>
</html>