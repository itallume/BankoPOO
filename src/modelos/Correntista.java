package modelos;

import modelos.Conta;

import java.util.ArrayList;
import java.util.LinkedList;

public class Correntista {
    private String cpf;
    private String nome;
    private String senha;
    private LinkedList<Conta> contas = new LinkedList<>();

    public Correntista(String cpf, String nome, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
    }

    public double getSaldo(){
        double saldo = 0;
        for(Conta conta: contas){
            saldo += conta.getSaldo();
        }
        return saldo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public LinkedList<Conta> getContas() {
        return contas;
    }

    public void adicionar(Conta conta) {
        this.contas.add(conta);
    }
    public void remover(Conta conta){
        this.contas.remove(conta);
    }

    @Override
    public String toString() {
        return "Correntista{" +
                "cpf='" + cpf + '\'' +
                ", nome='" + nome + '\'' +
                ", senha='" + senha +'}';
    }
}
