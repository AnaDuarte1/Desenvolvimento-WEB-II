package edu.ifsp.loja.factory;

import edu.ifsp.loja.persistencia.ProdutoDAO;
import edu.ifsp.loja.persistencia.ClienteDAO;
import edu.ifsp.loja.persistencia.mysql.ProdutoDAOMySQL; 
import edu.ifsp.loja.persistencia.mysql.ClienteDAOMySQL;

public class MySQLDAOFactory extends DAOFactory {

    @Override
    public ProdutoDAO createProdutoDAO() {
        return new ProdutoDAOMySQL();
    }

    @Override
    public ClienteDAO createClienteDAO() {
        return new ClienteDAOMySQL();
    }
}
