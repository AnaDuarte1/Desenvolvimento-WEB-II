package exemplo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
	public static void main(String[] args) {
		try {
			try (Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3307/loja?" +
			        "user=root&password=root")) {
				
				Statement stmt = conn.createStatement();
				// Faz de conta
				String inicial = "T";
				ResultSet rs = stmt.executeQuery(
						"SELECT id, nome FROM produtos "
						+ "WHERE nome LIKE '" + inicial + "%'");
				
				while (rs.next()) {
					int id = rs.getInt("id");
					String descricao = rs.getString("nome");
					
					System.out.println(id + " " + descricao);
				}
				
				rs.close();
				stmt.close();
				
			}  // fim do try()
			// conn.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
