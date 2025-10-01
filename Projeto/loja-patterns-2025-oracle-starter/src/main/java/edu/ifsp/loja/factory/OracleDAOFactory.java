package edu.ifsp.loja.factory;

import edu.ifsp.loja.persistencia.ProdutoDAO;
import edu.ifsp.loja.persistencia.ClienteDAO;
import edu.ifsp.loja.persistencia.oracle.ProdutoDAOOracle; 
import edu.ifsp.loja.persistencia.oracle.ClienteDAOOracle;

public class OracleDAOFactory extends DAOFactory {

    @Override
    public ProdutoDAO createProdutoDAO() {
        return new ProdutoDAOOracle();
    }

    @Override
    public ClienteDAO createClienteDAO() {
        return new ClienteDAOOracle();
    }
}
