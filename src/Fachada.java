import java.util.ArrayList;

public class Fachada {
    private static Repositorio repositorio = new Repositorio();
    public static ArrayList<Correntista> listarCorrentistas(){
        ArrayList<Correntista> correntistas = repositorio.getCorrentistas();
        ArrayList<Correntista> correntistasOrdenados = new ArrayList<>();

        for (Correntista crnt : correntistas){
            if (correntistasOrdenados.isEmpty()){
                correntistasOrdenados.add(crnt);
                continue;
            }
            for(int i = 0; i < correntistasOrdenados.size(); i++){
                if (crnt.getCpf().compareTo(correntistasOrdenados.get(i).getCpf()) > 0){
                    correntistasOrdenados.add(i, crnt);
                    break;
                }
            }
        }
        return correntistasOrdenados;
    }


    public static ArrayList<Conta> listarContas(){
        return null;
    }

    public static void criarCorrentista(String cpf, String nome,String senha){
        
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
