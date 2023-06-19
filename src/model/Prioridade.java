package src.model;

public enum Prioridade {
    BARARTO("BARATO"),
    RAPIDO("RAPIDO");

    private String prioridade;

    Prioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getNome() {
        return prioridade;
    }
}
