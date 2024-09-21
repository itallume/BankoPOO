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

    public static void criarConta(String cpf) throws Exception {

        for( Conta cr : repositorio.getContas()){
            if (cr.getCorrentistas().getFirst().getCpf().equals(cpf)){
                throw new Exception("Correntista só pode ser titular de apenas uma conta");
            }
        }

        Correntista correntista = procurarCorrentista(cpf);

        Conta conta = new Conta(repositorio.gerarIdConta(), repositorio.gerarData());
        conta.adicionar(correntista);
        correntista.adicionar(conta);
        repositorio.adicionar(conta);
        repositorio.salvarObjetos();
    }

    public static void criarContaEspecial (String cpf,double limite ) throws Exception {
        if (limite < 50){
            throw new Exception("Limite da conta especial deve ser no mínimo 50");
        }

        for( Conta cr : repositorio.getContas()){
            if (cr.getCorrentistas().getFirst().getCpf().equals(cpf)){
                throw new Exception("Correntista só pode ser titular de apenas uma conta");
            }
        }

        Correntista correntista = procurarCorrentista(cpf);

        ContaEspecial contaE = new ContaEspecial(repositorio.gerarIdConta(), repositorio.gerarData(), 0, 50);
        contaE.adicionar(correntista);
        correntista.adicionar(contaE);
        repositorio.adicionar(contaE);
        repositorio.salvarObjetos();
    }

    public static void inserirCorrentista (int id, String cpf) throws Exception {
        Conta conta = procurarConta(id);
        Correntista correntista = procurarCorrentista(cpf);

        conta.adicionar(correntista);
        correntista.adicionar(conta);
    }

    public static void removerCorrentistaConta(int id, String cpf) throws Exception {
        Conta conta = procurarConta(id);
        Correntista correntista = procurarCorrentista(cpf);

        if (conta.getCorrentistas().getFirst().getCpf().equals(cpf)){
            throw new Exception("Correntista titular não pode ser removido");
        }

        conta.remover(correntista);
        correntista.remover(conta);
    }

    public static void apagarConta(int id) throws Exception {

        Conta conta = procurarConta(id);

        if(conta.getSaldo() != 0){
            throw new Exception("Conta só pode ser excluída se o saldo for 0");
        }

        for(Correntista c : conta.getCorrentistas()){
            c.remover(conta);
            conta.remover(c);
        }

        repositorio.remover(conta);
    }

    public static void creditarValor(int id,String cpf, String senha,double valor) throws Exception {
        Conta conta = procurarConta(id);
        Correntista correntista = procurarCorrentista(cpf);

        if (!correntista.getSenha().equals(senha)){
            throw new Exception("Senha incorreta");
        }

        conta.creditar(valor);
    }

    public static void debitarValor(int id, String cpf, String senha,double valor) throws Exception {
        Conta conta = procurarConta(id);
        Correntista correntista = procurarCorrentista(cpf);

        if (!correntista.getSenha().equals(senha)){
            throw new Exception("Senha incorreta");
        }

        conta.debitar(valor);
    }

    public static void transferirValor(int id1, String cpf, String senha,double valor, int id2) throws Exception {
        Conta c1 = procurarConta(id1);
        Conta c2 = procurarConta(id2);
        Correntista correntista = procurarCorrentista(cpf);

        if (!correntista.getSenha().equals(senha)){
            throw new Exception("Senha incorreta");
        }

        c1.debitar(valor);
        c2.creditar(valor);
    }

    private static Conta procurarConta(int id) throws Exception {
        Conta conta = repositorio.localizar(id);
        if (conta == null){
            throw new Exception("Conta não encontrada, verifique o id");
        }
        return conta;
    }

    private static Correntista procurarCorrentista(String cpf) throws Exception {
        Correntista correntista = repositorio.localizar(cpf);
        if (correntista == null){
            throw new Exception("Correntista não encontrado, verifique o cpf");
        }
        return correntista;
    }
}
