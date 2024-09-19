import java.util.ArrayList;

public class Conta {
    private int id;
    private String data;
    private double saldo;
    private ArrayList<Correntista> correntistas = new ArrayList<>();

    public Conta(int id, String data, double saldo) {
        this.id = id;
        this.data = data;
        this.saldo = saldo;
    }

    public void creditar(double valor){

    }
    public void debitar(double valor){

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

    public ArrayList<Correntista> getCorrentistas() {
        return correntistas;
    }

    public void adicionar(Correntista correntista) {
        this.correntistas.add(correntista);
    }
    public void remover(Correntista correntista){
        this.correntistas.remove(correntista);
    }
}
