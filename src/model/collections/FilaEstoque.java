package src.model.collections;

import src.model.Carga;
import src.model.EstadoCarga;

import java.util.LinkedList;
import java.util.Queue;

public class FilaEstoque {
    private Queue<Carga> fila;
    private static FilaEstoque instance;

    public FilaEstoque() {
        fila = new LinkedList<>();
    }

    public Carga getCarga() {
        return fila.poll();
    }

    public boolean addCarga(Carga c) {
        if (!consultar(c.getIdentificador())) {
            return fila.offer(c);
        }
        return false;
    }

    public boolean consultar(int id) {
        for (Carga c : fila) {
            if (c.getIdentificador() == id) {
                return true;
            }
        }
        return false;
    }
    public Queue<Carga> getCargasPendentes() {
        Queue<Carga> cargasPendentes = new LinkedList<>();
        for (Carga carga : fila) {
            if (carga.getEstado() == EstadoCarga.PENDENTE) {
                cargasPendentes.offer(carga);
            }
        }
        return cargasPendentes;
    }

    public static FilaEstoque getInstance() {
        if (instance == null) {
            instance = new FilaEstoque();
        }
        return instance;
    }
}


