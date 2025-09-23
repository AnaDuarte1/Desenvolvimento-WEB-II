package edu.ifsp.loja.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.ifsp.loja.modelo.Cliente;

public class ClienteDAO {
	
	public List<Cliente> findByName(String name) {
		List<Cliente> list = new ArrayList<>();
		
		try {
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT id, nome, email FROM cliente WHERE nome LIKE ?");
			ps.setString(1, "%" + name + "%");
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Cliente cliente = mapRow(rs);
				list.add(cliente);
			}
			
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
		
		return list;
	}
	
	public Cliente findById(int id) {
		Cliente cliente = null;
		try {
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT id, nome, email FROM cliente WHERE id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				cliente = mapRow(rs);
			}
			
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
		
		return cliente;
	}

	private Cliente mapRow(ResultSet rs) throws SQLException {
		Cliente cliente = new Cliente();
		cliente.setId(rs.getInt("id"));
		cliente.setNome(rs.getString("nome"));
		cliente.setEmail(rs.getString("email"));
		return cliente;
	}
	
	public Cliente save(Cliente cliente) {
		try {
			if (isNew(cliente)) {
				insert(cliente);
			} else {
				update(cliente);
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
		
		return cliente;
	}


	private boolean isNew(Cliente cliente) {
		return cliente.getId() == 0;
	}


	private void insert(Cliente cliente) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		
		PreparedStatement ps = conn.prepareStatement(
				"INSERT INTO cliente (nome, email) VALUES (?, ?)",
				Statement.RETURN_GENERATED_KEYS);
		ps.setString(1, cliente.getNome());
		ps.setString(2, cliente.getEmail());
		ps.executeUpdate();
		
		ResultSet rs = ps.getGeneratedKeys();			
		if (rs.next()) {
			int id = rs.getInt(1);
			cliente.setId(id);
		} else {
			throw new DataAccessException("PK faltando");
		}
		
		
		ps.close();
		conn.close();
	}

	
	private void update(Cliente cliente) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		
		PreparedStatement ps = conn.prepareStatement("UPDATE cliente SET nome = ?, email = ? WHERE id = ?");
		ps.setString(1, cliente.getNome());
		ps.setString(2, cliente.getEmail());
		ps.setInt(3, cliente.getId());
		ps.executeUpdate();
		
		ps.close();
		conn.close();
	}
}