package src.model;

public class Perecivel extends TipoCarga {
    private String origem;
    private int validadeMax;

    public Perecivel(int numero, String o, int val, String desc) {
        super(numero, desc);
        this.origem = o;
        this.validadeMax = val;
    }

    public String getOrigem() {
        return origem;
    }

    public int getValidadeMax() {
        return validadeMax;
    }

    @Override
    public String toString() {
        return getNumero() + ";"
                + getDescricao() + ";"
                + "PERECIVEL" + ";"
                + origem + ";"
                + validadeMax;
    }
}
