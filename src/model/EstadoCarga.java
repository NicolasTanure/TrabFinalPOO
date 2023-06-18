package src.model;

public enum EstadoCarga {
    PENDENTE("Pendente"),
    LOCADO("Locado"),
    CANCELADO("Cancelado"),
    FINALIZADO("Finalizado");

    private String estado;

    EstadoCarga(String estado) {
        this.estado = estado;
    }
}
