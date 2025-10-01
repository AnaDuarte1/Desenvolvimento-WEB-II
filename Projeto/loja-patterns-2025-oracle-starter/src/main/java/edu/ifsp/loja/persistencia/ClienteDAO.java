package edu.ifsp.loja.persistencia;

import java.util.List;

import edu.ifsp.loja.modelo.Cliente;

public interface ClienteDAO {		
		public List<Cliente> findByName(String name);
		public Cliente findById(int id);
}
