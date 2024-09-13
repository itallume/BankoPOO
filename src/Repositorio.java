import java.util.ArrayList;

public class Repositorio {
    private ArrayList<Conta> contas = new ArrayList<>();
    private ArrayList<Correntista> correntistas = new ArrayList<>();

    public Repositorio(){
        
    }

    public void adicionarcorrentista(Correntista c) {
        correntistas.add(c);
    }
    
	public void remover(Correntista c){
		correntistas.remove(c);
	}

    public Correntista localizarProduto(String cpf) {
        for(Correntista correntista : correntistas){
            if (correntista.getCpf().equals(cpf)){
                return correntista;
            }
        }
        return null;
    }

    public void adicionarConta(Conta c) {
        contas.add(c);
    }
    
	public void remover(Conta c){
		contas.remove(c);
	}

    public Conta localizarProduto(int id) {
        for(Conta conta : contas){
            if (conta.getId() == id){
                return conta;
            }
        }
        return null;
    }

    public ArrayList<Correntista> getCorrentistas() {
		return correntistas;
	}
	public ArrayList<Conta> getContas() {
		return contas;
	}
	public int getTotalCorrentista(){
		return correntistas.size();
	}
	public int getTotalContas(){
		return contas.size();
	}
	public void apagarCorrentistas() {
		correntistas.clear();
	}
	public void apagarContas() {
		contas.clear();
	}
	public int gerarIdConta() {
		if (contas.isEmpty())
			return 1;
		else {
			Conta ultimo = contas.get(contas.size()-1);
			return ultimo.getId() + 1;
		}
	}


}
