package edu.ifsp.loja.factory;

import edu.ifsp.loja.persistencia.ClienteDAO;
import edu.ifsp.loja.persistencia.ProdutoDAO;

public abstract class DAOFactory {
    public abstract ProdutoDAO createProdutoDAO();
    public abstract ClienteDAO createClienteDAO();

    public static DAOFactory getFactory(String banco) {
        if ("mysql".equalsIgnoreCase(banco)) {
            return new MySQLDAOFactory();
        } else if ("oracle".equalsIgnoreCase(banco)) {
            return new OracleDAOFactory();
        } else {
            throw new RuntimeException("Banco não suportado: " + banco);
        }
    }
}
