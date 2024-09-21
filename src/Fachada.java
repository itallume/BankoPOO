import java.util.ArrayList;

public class Fachada {
    private static Repositorio repositorio = new Repositorio();
    public static ArrayList<Correntista> listarCorrentistas(){
        return repositorio.getCorrentistas();
    }

    public static ArrayList<Conta> listarContas(){

        return repositorio.getContas();
    }

    public static void criarCorrentista(String cpf, String nome,String senha) throws Exception {
        if (!senha.matches("^\\d{4}$")){
            throw new Exception("Senha deve ter apenas 4 caracteres numéricos.");
        }
        Correntista correntista = new Correntista(cpf, nome, senha);
        repositorio.adicionar(correntista);
        repositorio.salvarObjetos();
    }

    public static void criarConta(String cpf){

    }

    public static void criarContaEspecial (String cpf,double limite ){

    }

    public static void inserirCorrentista (int id, String cpf){

    }

    public static void removerCorrentistaConta(int id, String cpf){

    }

    public static void apagarConta(int id){

    }

    public static void creditarValor(int id,String cpf, String senha,double valor){

    }

    public static void debitarValor(int id,String cpf, String senha,double Valor){

    }

    public static void transferirValor(int id1, String cpf, String senha,int id2){
        
    }


}
