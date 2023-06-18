package src.model;

public class Duravel extends TipoCarga {
    private String setor;
    private String material;
    private double impostoIndustrializado;

    public Duravel(int numero, String s, String m, double i, String desc) {
        super(numero, desc);
        this.setor = s;
        this.material = m;
        this.impostoIndustrializado = i;
    }

    public String getSetor() {
        return setor;
    }

    public String getMaterial() {
        return material;
    }

    public double getImpostoIndustrializado() {
        return impostoIndustrializado;
    }
}

