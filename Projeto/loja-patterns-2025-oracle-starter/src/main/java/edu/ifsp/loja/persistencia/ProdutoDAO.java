package edu.ifsp.loja.persistencia;

import java.util.List;

import edu.ifsp.loja.modelo.Produto;

public interface ProdutoDAO {
		public List<Produto> findByDescricao(String descricao);
		public Produto findById(int id);
		public Produto save(Produto produto);
}
