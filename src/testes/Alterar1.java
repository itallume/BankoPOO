package testes;

/**
 * SI - POO - Prof. Fausto Ayres
 * Teste da regras_de_negocio.Fachada
 * 
 */

import regras_de_negocio.Fachada;
import repositorio.Repositorio;

public class Alterar1 {

	public Alterar1() {
		try {
			Fachada.inserirCorrentistaConta(2, "0001"); // cotitular
			Fachada.inserirCorrentistaConta(3, "0001"); // cotitular
			Fachada.inserirCorrentistaConta(1, "0002"); // cotitular
			Fachada.inserirCorrentistaConta(3, "0002"); // cotitular
			Fachada.inserirCorrentistaConta(1, "0003"); // cotitular
			Fachada.inserirCorrentistaConta(2, "0003"); // cotitular
			System.out.println("inseriu cotitulares nas contas");

			Fachada.removerCorrentistaConta(1, "0002"); // cotitular
			Fachada.removerCorrentistaConta(3, "0002"); // cotitular
			System.out.println("removeu cotitulares das contas");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		new Alterar1();
	}
}
