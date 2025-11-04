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
	
	<h2>Dados do Produto</h2>
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
		
        <label for="estoque">Estoque Inicial/Total: </label>
		<input type="number" name="estoque" id="estoque" required value="<%= produto.getEstoque() %>">
		<br>
		
		<br>
		<button type="submit">Salvar</button>
	</form>

    <hr>
    
    <% if (produto.getId() != 0) { %>
        <h2>Gerenciamento de Estoque</h2>
        <p>Estoque Atual: <strong><%= produto.getEstoque() %></strong></p>
        <p><a href="produto/estoque?id=<%= produto.getId() %>">Realizar Baixa de Estoque</a></p>
    <% } %>
    
    <br>
    <a href="produto/pesquisar">Voltar para Pesquisa de Produtos</a>

</body>
</html>