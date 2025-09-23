<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="edu.ifsp.loja.modelo.Cliente" %>


<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Pesquisa de Clientes</title>
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
	<div class="container">
		<h1>Pesquisa de Clientes</h1>
		
		<form action="cliente/pesquisar" method="get">
			<label for="id">ID: </label>
			<input type="text" name="id" id="id">
		
			<label for="name">Nome: </label>
			<input type="text" name="name" id="name">
			
			<button type="submit">Pesquisar</button>
		</form>

		<%
		List<Cliente> clientes = (List<Cliente>)request.getAttribute("clientes");
		if (clientes != null && !clientes.isEmpty()) {
		%>
		<table>
				<tr>
						<th>ID</th>
						<th>Nome</th>
						<th>Email</th>
						<th>Ação</th>
				</tr>
				
				<%
				for (Cliente c : clientes) {
				%>
				<tr>
						<td><%= c.getId() %></td>
						<td><%= c.getNome() %></td>
						<td><%= c.getEmail() %></td>
						<td><a href="cliente/editar?id=<%= c.getId() %>">Editar</a></td>
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
	</div>
</body>
</html>