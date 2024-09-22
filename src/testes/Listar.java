package testes;
/**
 * SI - POO - Prof. Fausto Ayres
 * Teste da regras_de_negocio.Fachada
 * 
 */


import regras_de_negocio.Fachada;
import modelos.*;
public class Listar {

	public Listar() {
		try {
			System.out.println("\n---------listagem de contas-----");
			for(Conta p : Fachada.listarContas()) 
				System.out.println(p);

			System.out.println("\n---------listagem de correntistas ----");
			for(Correntista e : Fachada.listarCorrentistas()) 
				System.out.println(e);
			
		} catch (Exception e) {
			System.out.println("--->"+e.getMessage());
		}	
	}

	public static void main (String[] args) 
	{
		new Listar();
	}
}


