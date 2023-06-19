package src.model.collections;

import src.model.Carga;
import src.model.EstadoCarga;

import java.util.LinkedList;
import java.util.Queue;

public class FilaEstoque {
    private Queue<Carga> fila;

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
}
