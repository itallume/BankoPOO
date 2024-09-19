public class ContaEspecial extends Conta{
    public double limite;



    public ContaEspecial(int id, String data, double saldo, double limite) {
        super(id, data, saldo);
        this.limite = limite;
    }

    @Override
    public void debitar(double valor) {
        super.debitar(valor);
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

}
