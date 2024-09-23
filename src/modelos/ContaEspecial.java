package modelos;

import modelos.Conta;

public class ContaEspecial extends Conta {
    public double limite;

    public ContaEspecial(int id, String data, double saldo, double limite) {
        super(id, data, saldo);
        this.limite = limite;
    }

    @Override
    public void debitar(double valor) throws Exception {
        if (getSaldo() + limite - valor < 0){
            throw new Exception("Saldo insuficiente");
        }
        this.saldo -= valor;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    @Override
    public String toString() {
        return "ContaEspecial{" +
                "limite=" + limite +
                ", id=" + id +
                ", data='" + data + '\'' +
                ", saldo=" + saldo +
                '}';
    }
}
