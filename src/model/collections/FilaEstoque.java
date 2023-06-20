package src.model.collections;

import src.model.Carga;

import java.util.LinkedList;
import java.util.Queue;

public class FilaEstoque {
    private Queue<Carga> fila;
    private static FilaEstoque instance;

    public FilaEstoque() {
        fila = new LinkedList<>();
    }

    public Queue<Carga> getFila() {
        return fila;
    }

    public Carga getFirstCarga() {
        return fila.peek();
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

    public Carga removeCarga() {
        return fila.poll();
    }
}


