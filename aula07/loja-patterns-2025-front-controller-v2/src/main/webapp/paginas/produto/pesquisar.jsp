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