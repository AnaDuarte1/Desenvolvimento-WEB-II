<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="edu.ifsp.loja.modelo.Produto" %>


<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Baixa de Estoque</title>
	<base href="<%= request.getContextPath() %>/">
</head>
<body>
	<h1>Gerenciamento de Estoque</h1>
	
	<%
	List<String> errors = (List<String>)request.getAttribute("errors");
 if (errors != null && !errors.isEmpty()) {
	%>
	<ul>
	<% for (String erro : errors) { %>
		<li style="color:red;"><%= erro %></li>
	<% } %>
	</ul>	
	<%
	}
	%>
	
	<%
	Produto produto = (Produto)request.getAttribute("produto");
 %>

    <% if (produto != null && produto.getId() != 0) { %>

    <h2>Produto: <%= produto.getDescricao() %> (ID: <%= produto.getId() %>)</h2>
    
    <p>Estoque atual: <strong><%= produto.getEstoque() %></strong></p>
    
    <hr>
    
    <form action="produto/baixarEstoque" method="post">
        <input type="hidden" name="id" value="<%= produto.getId() %>">
    
        <label for="quantidade">Quantidade a baixar: </label>
        <input type="number" name="quantidade" id="quantidade" required min="1">
        <br>
        
        <br>
        <button type="submit">Baixar Estoque</button>
    </form>
    
    <br>
    <a href="produto/editar?id=<%= produto.getId() %>">Voltar para Edição do Produto</a>

    <% } else { %>
        <p>Produto não encontrado ou ID inválido para gerenciamento de estoque.</p>
    <% } %>

    <br>
    <a href="produto/pesquisar">Voltar para Pesquisa de Produtos</a>
    
</body>
</html>