<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Loja Patterns</title>
	<base href="<%= request.getContextPath() %>/">
</head>
<body>
	<h1>Loja Patterns</h1>
	
	<h2>Menu</h2>
	<ul>
		<li>
			Produtos
			<ul>
				<li><a href="produto/pesquisar">Pesquisar</a></li>
				<li><a href="produto/editar">Novo</a></li>
			</ul>
			
		</li>
		
		<li>
			Clientes
			<ul>
				<li><a href="cliente/pesquisar">Pesquisar</a></li>
			</ul>
		</li>
		
	</ul>
	
</body>
</html>