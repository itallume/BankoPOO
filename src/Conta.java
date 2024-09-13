import java.util.ArrayList;

public class Conta {
    private int id;
    private String data;
    private double saldo;
    private ArrayList<Correntista> correntistas = new ArrayList<>();

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

    public void setCorrentistas(Correntista correntista) {
        this.correntistas.add(correntista);
    }
}
