package edu.ifsp.loja.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import edu.ifsp.loja.modelo.Produto;

public class ProdutoDAO {
	
	
	public List<Produto> findByDescricao(String descricao) {
		List<Produto> produtos = new ArrayList<>();

		try {
			Connection conn = ConnectionFactory.getConnection();

			PreparedStatement ps = conn.prepareStatement("SELECT id, descricao, preco, estoque FROM produto WHERE descricao LIKE ?");
			ps.setString(1, "%" + descricao + "%");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Produto produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setPreco(rs.getDouble("preco"));
				produto.setEstoque(rs.getInt("estoque"));

				produtos.add(produto);
			}

			rs.close();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}

		return produtos;
	}
	
	public void updateEstoque(int id, int novaQuantidade) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            PreparedStatement ps = conn.prepareStatement("UPDATE produto SET estoque = ? WHERE id = ?");
            ps.setInt(1, novaQuantidade);
            ps.setInt(2, id);
            ps.executeUpdate();
            
            ps.close();
            conn.close();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }

	
	public Produto findById(int id) {
		Produto produto = null;
		
		try {
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement ps = conn.prepareStatement("SELECT id, descricao, preco, estoque FROM produto WHERE id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			if (rs.next()) {
				produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setDescricao(rs.getString("descricao"));
				produto.setPreco(rs.getDouble("preco"));	
				produto.setEstoque(rs.getInt("estoque"));
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
        
        PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO produto (descricao, preco, estoque) VALUES (?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, produto.getDescricao());
        ps.setDouble(2, produto.getPreco());
        ps.setInt(3, produto.getEstoque()); // OK
        ps.executeUpdate();
        
        ResultSet rs = ps.getGeneratedKeys();            
        if (rs.next()) {
            int id = rs.getInt(1);
            produto.setId(id);
        } else {
            throw new DataAccessException("PK faltando");
        }
        
        
        ps.close();
        conn.close();
    }

	
	private void update(Produto produto) throws SQLException {
        Connection conn = ConnectionFactory.getConnection();
        
        PreparedStatement ps = conn.prepareStatement("UPDATE produto SET descricao = ?, preco = ?, estoque = ? WHERE id = ?");
        ps.setString(1, produto.getDescricao());
        ps.setDouble(2, produto.getPreco());
        ps.setInt(3, produto.getEstoque()); 
        ps.setInt(4, produto.getId());     
        
        ps.executeUpdate();
        
        ps.close();
        conn.close();
    }
	
}
