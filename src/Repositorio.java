import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Repositorio {
    private ArrayList<Conta> contas = new ArrayList<>();
    private ArrayList<Correntista> correntistas = new ArrayList<>();

    public Repositorio(){
        
    }

    public void adicionar(Correntista c) {
        correntistas.add(c);
    }
    
	public void remover(Correntista c){
		correntistas.remove(c);
	}

    public Correntista localizar(String cpf) {
        for(Correntista correntista : correntistas){
            if (correntista.getCpf().equals(cpf)){
                return correntista;
            }
        }
        return null;
    }

    public void adicionar(Conta c) {
        contas.add(c);
    }
    
	public void remover(Conta c){
		contas.remove(c);
	}

    public Conta localizar(int id) {
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
			Conta ultimo = contas.getLast();
			return ultimo.getId() + 1;
		}
	}

	public void carregarDados(){

		try{
			File arquivoContas = new File("dados\\contas.csv");
			File arquivoCorrentistas = new File("dados\\correntistas.csv");
			if (!arquivoContas.exists() || !arquivoCorrentistas.exists()){
				FileWriter arquivo1 = new FileWriter(arquivoContas); arquivo1.close();
				FileWriter arquivo2 = new FileWriter(arquivoCorrentistas); arquivo2.close();
				return;
			}
		} catch (IOException e) {
            throw new RuntimeException(e);
        }

		String linha;
		String[] partes;
		Conta conta;
		Correntista correntista;

		try	{
			String tipo, id, data, saldo, limite;
			File f = new File( new File("dados\\contas.csv").getCanonicalPath() )  ;
			Scanner arquivo1 = new Scanner(f);
			while(arquivo1.hasNextLine()) 	{
				linha = arquivo1.nextLine().trim();
				partes = linha.split(";");
				//System.out.println(Arrays.toString(partes));
				tipo = partes[0];
				id = partes[1];
				data = partes[2];
				saldo = partes[3];

				if (tipo.toUpperCase().equals("CONTA")) {
					conta = new Conta(Integer.parseInt(id), data, Double.parseDouble(saldo));
					this.adicionar(conta);
				}
				else{
					limite = partes[4];
					conta = new ContaEspecial(Integer.parseInt(id), data, Double.parseDouble(saldo), Double.parseDouble(limite));
					this.adicionar(conta);
				}
			}
			arquivo1.close();
		} catch(Exception ex)		{
			throw new RuntimeException("Erro na leitura arquivo de Conta:"+ex.getMessage());
		}

		try	{
			String cpf, nome, senha, ids;
			File f = new File( new File("dados\\correntistas.csv").getCanonicalPath() )  ;
			Scanner arquivo2 = new Scanner(f);
			while(arquivo2.hasNextLine()) 	{
				linha = arquivo2.nextLine().trim();
				partes = linha.split(";");
				cpf = partes[0];
				nome = partes[1];
				senha = partes[2];
				ids = partes[3];

				correntista = new Correntista(cpf, nome, senha);
				this.adicionar(correntista);
				for (String id : ids.split(",")){
					Conta c = this.localizar(Integer.parseInt(id));
					c.adicionar(correntista);
					correntista.adicionar(c);

				}

			}
			arquivo2.close();
		} catch(Exception ex)		{
			throw new RuntimeException("Erro na leitura arquivo de Correntista:"+ex.getMessage());
		}}

	public void	salvarObjetos()  {
			//gravar nos arquivos csv os objetos que est�o no repositorio
		try	{
			File f = new File( new File(".\\contas.csv").getCanonicalPath())  ;
			FileWriter arquivo1 = new FileWriter(f);

			for(Conta c : contas) 	{
				if (c instanceof ContaEspecial ce){
					arquivo1.write(c.getId()+";"+c.getData()+";"+c.getSaldo()+";"+ce.getLimite()+"\n");
				}
				else {
					arquivo1.write(c.getId() + ";" + c.getData() + ";" + c.getSaldo() +"\n");
				}
				arquivo1.close();
			}
		}
		catch(Exception e){
			throw new RuntimeException("problema na escrita no arquivo eventos: "+e.getMessage());
		}

		try	{
			File f = new File( new File(".\\correntistas.csv").getCanonicalPath())  ;
			FileWriter arquivo2 = new FileWriter(f) ;
			ArrayList<String> lista ;
			String listaId;
			for(Correntista correntista : correntistas) {
				lista = new ArrayList<>();
				for (Conta c : correntista.getContas()) {
					lista.add(c.getId() + "");
				}
				listaId = String.join(",", lista);
				arquivo2.write(correntista.getCpf() + ";" + correntista.getNome() + ";" + correntista.getSenha() + ";" + listaId + "\n");
			}
			arquivo2.close();
		}
		catch (Exception e) {
			throw new RuntimeException("problema na escrita do arquivo correntistas: "+e.getMessage());
		}

	}

}
