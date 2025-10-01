package edu.ifsp.loja.persistencia.oracle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ifsp.loja.modelo.Produto;
import edu.ifsp.loja.persistencia.DataAccessException;
import edu.ifsp.loja.persistencia.ProdutoDAO;
import oracle.jdbc.OraclePreparedStatement;

public class ProdutoDAOOracle implements ProdutoDAO {
	
	
	public List<Produto> findByDescricao(String descricao) {
	    List<Produto> produtos = new ArrayList<>();

	    String sql = "SELECT id, descricao, preco FROM produto WHERE UPPER(descricao) LIKE UPPER(?)";

	    try (Connection conn = ConnectionFactory.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        ps.setString(1, "%" + descricao + "%");

	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                Produto produto = new Produto();
	                produto.setId(rs.getInt("id"));
	                produto.setDescricao(rs.getString("descricao"));
	                produto.setPreco(rs.getDouble("preco"));
	                produtos.add(produto);
	            }
	        }

	    } catch (SQLException e) {
	        throw new DataAccessException(e);
	    }

	    return produtos;
	}

	
	public Produto findById(int id) {
		Produto produto = null;
		
		try {
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT id, descricao, preco FROM produto WHERE id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setPreco(rs.getDouble("preco"));				
			}
			
			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	
		return produto;		
	}
	
	
	public Produto save(Produto produto) {
		try {
			if (isNew(produto)) {
				insert(produto);
			} else {
				update(produto);
			}
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
		
		return produto;
	}

	private boolean isNew(Produto produto) {
		return produto.getId() == 0;
	}


	private void insert(Produto produto) throws SQLException {
	    Connection conn = ConnectionFactory.getConnection();

	    String sql = "INSERT INTO produto (id, descricao, preco) " +
	                 "VALUES (seq_produto.NEXTVAL, ?, ?) RETURNING id INTO ?";

	    OraclePreparedStatement ps = (OraclePreparedStatement) conn.prepareStatement(sql);

	    ps.setString(1, produto.getDescricao());
	    ps.setDouble(2, produto.getPreco());

	    ps.registerReturnParameter(3, java.sql.Types.INTEGER);

	    ps.executeUpdate();

	    try (ResultSet rs = ps.getReturnResultSet()) {
	        if (rs.next()) {
	            int id = rs.getInt(1);
	            produto.setId(id);
	        } else {
	            throw new DataAccessException("PK faltando");
	        }
	    }

	    ps.close();
	    conn.close();
	}


	
	private void update(Produto produto) throws SQLException {
		Connection conn = ConnectionFactory.getConnection();
		
		PreparedStatement ps = conn.prepareStatement("UPDATE produto SET descricao = ?, preco = ? WHERE id = ?");
		ps.setString(1, produto.getDescricao());
		ps.setDouble(2, produto.getPreco());
		ps.setInt(3, produto.getId());
		ps.executeUpdate();
		
		ps.close();
		conn.close();
	}
	
}
