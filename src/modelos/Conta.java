package modelos;

import java.util.ArrayList;
import java.util.LinkedList;

public class Conta {
    protected int id;
    protected String data ;
    protected double saldo;
    private LinkedList<Correntista> correntistas = new LinkedList<>();

    public Conta(int id, String data, double saldo) {
        this.id = id;
        this.data = data;
        this.saldo = saldo;
    }

    public Conta(int id, String data) {
        this.id = id;
        this.data = data;
    }

    public void creditar(double valor) throws Exception {
        if (valor <= 0){
            throw new Exception("Valor não pode ser negativou ou zero");
        }
        saldo += valor;
    }
    public void debitar(double valor) throws Exception {
        if (valor <= 0){
            throw new Exception("Valor não pode ser negativou ou zero");
        }

        if (valor > saldo){
            throw new Exception("Saldo insuficiente");
        }
        saldo -= valor;
    }

    public void transferir (double valor, Conta destino) throws Exception {
        debitar(valor);
        destino.creditar(valor);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public LinkedList<Correntista> getCorrentistas() {
        return correntistas;
    }

    public void adicionar(Correntista correntista) {
        this.correntistas.add(correntista);
    }
    public void remover(Correntista correntista){
        this.correntistas.remove(correntista);
    }

    @Override
    public String toString() {
        return "Conta{" +
                "id=" + id +
                ", data='" + data + '\'' +
                ", saldo=" + saldo +'}';
    }
}
