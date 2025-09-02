package ex01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DAO {
	public List<Pais> BuscarPorAno(int ano){
		List<Pais> paises = new ArrayList<Pais>();
		
		try {
			try(Connection conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3307/world?" +
			        "user=root&password=root")) {
				
				Statement stmt = conn.createStatement();
				
				ResultSet rs = stmt.executeQuery(
						"SELECT code, name FROM country " + 
						"WHERE indepyear >= " + ano
						);
				
				while (rs.next()) {
					String codigo = rs.getString("code");
					String descricao = rs.getString("name");
					paises.add(new Pais(codigo, descricao));
				}
				
				rs.close();
				stmt.close();
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return paises;
	}
	
}
