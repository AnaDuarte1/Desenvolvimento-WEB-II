package edu.ifsp.loja.persistencia.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class ConnectionFactory {
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver").getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public static Connection getConnection() throws SQLException {
		Connection conn = DriverManager
				.getConnection("jdbc:oracle:thin:@localhost:1539:free", "system", "root");
				
		return conn;
	}
}
