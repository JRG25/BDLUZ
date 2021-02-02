package ps.ds.luz.modelo;

public class Luz {

    int numeroMed;
    String cliente;
    int lecAnt;
    int lecPre;
    int tipo;

    public Luz() {
        // TODO Auto-generated constructor stub
    }

    public Luz(int numeroMed, String cliente, int lecAnt, int lecPre, int tipo) {
        this.numeroMed = numeroMed;
        this.cliente = cliente;
        this.lecAnt = lecAnt;
        this.lecPre = lecPre;
        this.tipo = tipo;
    }

    public int consumoLuz() {

        return lecPre - lecAnt;
    }

    public String cTipo() {
        String v[] = {"", "Residencial", "Comercial", "Industrial"};
        return v[tipo];
    }

    public double pago() {
        double v[] = {0, 0.06, 0.85, 1.1};
        return v[tipo] * consumoLuz();
    }

    public double igv() {
        return pago() * 0.18;
    }

    public double total() {
        return pago() + igv();
    }

    public int getNumeroMed() {
        return numeroMed;
    }

    public void setNumeroMed(int numeroMed) {
        this.numeroMed = numeroMed;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public int getLecAnt() {
        return lecAnt;
    }

    public void setLecAnt(int lecAnt) {
        this.lecAnt = lecAnt;
    }

    public int getLecPre() {
        return lecPre;
    }

    public void setLecPre(int lecPre) {
        this.lecPre = lecPre;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

}
