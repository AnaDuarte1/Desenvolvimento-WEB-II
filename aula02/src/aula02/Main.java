package aula02;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
	public static void main(String[] args) {
		
		// Sempre que tentarmos conectar no banco, temos que tratar com try/catch
		try {
			try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/loja?" +
			                           "user=root&password=root")){	// Se conecta com o Banco de Dados MySql loja
			
			 
			String inicial = "T";
			Statement stmt = conn.createStatement(); // Objeto que representa a consulta SQL
			ResultSet rs = stmt.executeQuery("SELECT id, nome FROM produtos " //PRECISA dar espaço quando concatenamos com
											+ "WHERE nome LIKE '" + inicial + "%'");			
			
			while(rs.next()) { //Imprime os dados da tabela, enquanto o rs.next() retornar true
				int id = rs.getInt("id");
				String descricao = rs.getString("nome");
				
				System.out.println(id + " " + descricao);
			}
			
				// Fecha as conexões
				rs.close();
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
