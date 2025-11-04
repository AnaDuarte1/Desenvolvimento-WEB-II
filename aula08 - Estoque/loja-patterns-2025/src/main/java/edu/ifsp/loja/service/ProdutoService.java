package edu.ifsp.loja.service;

import java.util.List;

import edu.ifsp.loja.modelo.Produto;
import edu.ifsp.loja.persistencia.ProdutoDAO;
import edu.ifsp.loja.persistencia.DataAccessException;


public class ProdutoService {

    private final ProdutoDAO dao;
    private final ProdutoValidator validator;

    public ProdutoService() {
        this.dao = new ProdutoDAO();
        this.validator = new ProdutoValidator(); 
    }

    public Produto salvarProduto(Produto produto, String precoString) {
        List<String> errors = validator.validate(produto.getDescricao(), precoString);
        
        if (!errors.isEmpty()) {
            return null; 
        }

        produto.setPreco(Double.parseDouble(precoString));
        
        return dao.save(produto);
    }
    
    public Produto buscarProdutoPorId(int id) {
        return dao.findById(id);
    }
    
    public List<Produto> pesquisarProdutos(String query) {
        if (query == null || query.isBlank()) {
            return List.of(); 
        }
        return dao.findByDescricao(query);
    }
    

    

    public void baixarEstoque(int produtoId, int quantidade) {
        
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade a ser baixada deve ser maior do que zero.");
        }
        
        Produto produto = dao.findById(produtoId);

        if (produto == null) {
            throw new DataAccessException("Produto com ID " + produtoId + " não encontrado.");
        }
        
        int estoqueAtual = produto.getEstoque();
        
        if (estoqueAtual < quantidade) {
            throw new EstoqueInsuficienteException(
                "Estoque insuficiente. Disponível: " + estoqueAtual + ", Solicitado: " + quantidade
            );
        }
        
        int novoEstoque = estoqueAtual - quantidade;
        
        dao.updateEstoque(produtoId, novoEstoque);
    }
}