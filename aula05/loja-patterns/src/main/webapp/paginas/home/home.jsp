<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Loja Patterns</title>
	<base href="<%= request.getContextPath() %>/">
	<style>
		body {
			font-family: Arial, sans-serif;
			background-color: #f0f2f5;
			color: #333;
			margin: 0;
			padding: 0;
			display: flex;
			justify-content: center;
			align-items: center;
			height: 100vh;
		}
		.container {
			background-color: #ffffff;
			padding: 40px;
			border-radius: 8px;
			box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
			width: 300px;
			text-align: center;
		}
		h1 {
			color: #1c1e21;
			margin-bottom: 10px;
		}
		h2 {
			color: #606770;
			font-weight: normal;
			font-size: 1.2em;
			margin-top: 0;
			margin-bottom: 30px;
			border-bottom: 1px solid #dddfe2;
			padding-bottom: 20px;
		}
		ul {
			list-style: none;
			padding: 0;
			text-align: left;
		}
		ul ul {
			padding-left: 20px;
		}
		li {
			margin-bottom: 12px;
			font-size: 1.1em;
			color: #4b4f56;
		}
		a {
			text-decoration: none;
			color: #007bff;
			font-weight: bold;
			font-size: 0.9em;
			transition: color 0.3s ease;
		}
		a:hover {
			color: #0056b3;
			text-decoration: underline;
		}
	</style>
</head>
<body>

	<div class="container">
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
					<li><a href="cliente/editar">Novo</a></li>
				</ul>
			</li>
		</ul>
	</div>
	
</body>
</html>