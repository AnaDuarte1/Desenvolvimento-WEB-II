package ex01;


import java.util.List;

public class Main {
	public static void main(String[] args) {
		
		DAO dao = new DAO();
		
		int ano = 1500;
		
		List<Pais> lista = dao.BuscarPorAno(ano);
		System.out.println("Países: ");
		
		for(Pais p: lista) {
			System.out.println("\n " + p);
		}
			
	}
}
