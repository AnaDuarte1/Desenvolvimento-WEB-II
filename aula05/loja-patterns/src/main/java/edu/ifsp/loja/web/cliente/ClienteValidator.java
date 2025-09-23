package edu.ifsp.loja.web.cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteValidator {
	public List<String> validate(String nome, String email) {
		List<String> errors = new ArrayList<>();
		
		if (nome == null || nome.isBlank()) {
			errors.add("O nome é obrigatório.");
		}
		
		if (email == null || email.isBlank()) {
			errors.add("O email é obrigatório.");
		} else if (!email.contains("@")) {
			errors.add("Email inválido.");
		}

		return errors;
	}
}
