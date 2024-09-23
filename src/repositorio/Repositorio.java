package repositorio;

import modelos.Conta;
import modelos.ContaEspecial;
import modelos.Correntista;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;

public class Repositorio {
    private LinkedList<Conta> contas = new LinkedList<>();
    private LinkedList<Correntista> correntistas = new LinkedList<>();

    public Repositorio(){
        carregarDados();
    }

    public void adicionar(Correntista c) {
		correntistas.add(c);
		correntistas.sort(Comparator.comparing(Correntista::getCpf));
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

    public LinkedList<Correntista> getCorrentistas() {
		return correntistas;
	}
	public LinkedList<Conta> getContas() {
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

	public String gerarData(){
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate data = LocalDate.now();
		return formato.format(data);
	}

	public void carregarDados(){

		try{
			File arquivoContas = new File(".\\contas.csv");
			File arquivoCorrentistas = new File(".\\correntistas.csv");
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
			File f = new File( new File(".\\contas.csv").getCanonicalPath() )  ;
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
			File f = new File( new File(".\\correntistas.csv").getCanonicalPath() )  ;
			Scanner arquivo2 = new Scanner(f);
			while(arquivo2.hasNextLine()) 	{
				linha = arquivo2.nextLine().trim();
				partes = linha.split(";");
				cpf = partes[0];
				nome = partes[1];
				senha = partes[2];

				correntista = new Correntista(cpf, nome, senha);
				adicionar(correntista);

				if (partes.length > 3) {
					ids = partes[3];
					for (String id : ids.split(",")){
						Conta c = this.localizar(Integer.parseInt(id));
						c.adicionar(correntista);
						correntista.adicionar(c);
					}
				}

			}
			arquivo2.close();
		} catch(Exception ex)		{
			throw new RuntimeException("Erro na leitura arquivo de modelos.Correntista:"+ex.getMessage());
		}}

	public void	salvarObjetos()  {
			//gravar nos arquivos csv os objetos que est�o no repositorio
		try	{
			File f = new File( new File(".\\contas.csv").getCanonicalPath());
			FileWriter arquivo1 = new FileWriter(f);
			for(Conta c : contas) 	{
				if (c instanceof ContaEspecial ce){
					arquivo1.write("CONTAESPECIAL" + ";" +c.getId()+";"+c.getData()+";"+c.getSaldo()+";"+ce.getLimite()+"\n");
				}
				else {
					arquivo1.write("CONTA" + ";" + c.getId() + ";" + c.getData() + ";" + c.getSaldo() + "\n");
				}
			}
			arquivo1.close();
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
