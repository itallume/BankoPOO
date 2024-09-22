package testes;

/**
 * SI - POO - Prof. Fausto Ayres
 * Teste da regras_de_negocio.Fachada
 * 
 */

import regras_de_negocio.Fachada;
import modelos.*;

public class Apagar {

	public Apagar() {
		try {
			Fachada.apagarConta(2);
			System.out.println("apagou conta");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("--->"+e.getMessage());
		}	
	}

	public static void main(String[] args) {
		new Apagar();
	}
}
