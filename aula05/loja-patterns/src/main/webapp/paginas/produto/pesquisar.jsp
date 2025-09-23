<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="edu.ifsp.loja.modelo.Produto" %>


<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Produtos - pesquisar</title>
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
			width: 80%;
			max-width: 800px;
		}
		h1 {
			color: #1c1e21;
			margin-bottom: 20px;
			text-align: center;
		}
		form {
			margin-bottom: 20px;
			display: flex;
			flex-direction: column;
			align-items: flex-start;
		}
		label {
			margin-bottom: 5px;
			font-weight: bold;
			color: #606770;
		}
		input[type="text"] {
			padding: 10px;
			margin-bottom: 10px;
			border: 1px solid #dddfe2;
			border-radius: 6px;
			font-size: 1em;
			width: calc(100% - 22px);
		}
		button {
			background-color: #007bff;
			color: white;
			padding: 10px 15px;
			border: none;
			border-radius: 6px;
			font-size: 1em;
			cursor: pointer;
			transition: background-color 0.3s ease;
		}
		button:hover {
			background-color: #0056b3;
		}
		table {
			width: 100%;
			border-collapse: collapse;
			margin-top: 20px;
		}
		th, td {
			padding: 12px;
			border: 1px solid #dddfe2;
			text-align: left;
		}
		th {
			background-color: #f5f6f7;
			font-weight: bold;
		}
		tr:nth-child(even) {
			background-color: #f9f9f9;
		}
		tr:hover {
			background-color: #f1f1f1;
		}
		a {
			color: #007bff;
			text-decoration: none;
			font-weight: bold;
		}
		a:hover {
			text-decoration: underline;
		}
		p {
			text-align: center;
			color: #606770;
			font-size: 1.1em;
		}
		br {
			display: none;
		}
	</style>
</head>
<body>
	<h1>Pesquisa de Produtos</h1>
	
	<form action="produto/pesquisar" method="get">
		<label>Produto: </label>
		<input type="text" name="query">
		<br>
		<button type="submit">Pesquisar</button>
	</form>
	<br>


	<%
	List<Produto> produtos = (List<Produto>)request.getAttribute("produtos");
	if (produtos != null && !produtos.isEmpty()) {
	%>
	<br>
	<table border="1">
	        <tr>
	                <th>ID</th>
	                <th>Produto</th>
	                <th>Preço</th>
	        </tr>
	        
	        <%
	        for (Produto p : produtos) {
	        %>
	        <tr>
	                <td><%= p.getId() %></td>
	                <td><%= p.getDescricao() %></td>
	                <td><%= p.getPreco() %></td>
	        </tr>
	        <%
	        }
	        %>
	</table>
	<%
	} 
	if (produtos != null && produtos.isEmpty()) {
	%>
	<p>Nenhum produto encontrado.</p>
	<%
	}
	%>
	
</body>
</html>