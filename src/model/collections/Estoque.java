package src.model.collections;

import src.model.Carga;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class Estoque {
    // Inner class que reordena a fila de cargas
    class OrderCarga implements Comparator<Carga> {
        @Override
        public int compare(Carga one, Carga second) {
            return one.getIdentificador() - second.getIdentificador();
        }
    }

    private Queue<Carga> cargas;

    public Estoque() {
        cargas = new LinkedList<>();
    }

    public Carga getCarga(int id) {
        for (Carga c : cargas) {
            if (c.getIdentificador() == id) {
                return c;
            }
        }
        return null;
    }

    public boolean adicionarCarga(Carga c) {
        if (!consultar(c.getIdentificador())) {
            return cargas.add(c);
        }
        return false;
    }

    public boolean consultar(int id) {
        for (Carga c : cargas) {
            if (c.getIdentificador() == id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("Código;Cliente;Origem;Destino;Peso;ValorDeclarado;TempoMáximo;TipoCarga;Prioridade;Situação\n");

        for (Carga c : cargas) {
            s.append(c.getIdentificador() + ";"
                    + c.getCliente().getCod() + ";"
                    + c.getOrigem().getId() + ";"
                    + c.getDestino().getId() + ";"
                    + c.getPeso() + ";"
                    + c.getValorDeclarado() + ";"
                    + c.getTempoMaximo() + ";"
                    + c.getTipoCarga().getNumero() + ";"
                    + c.getPrioridade().getNome() + ";"
                    + c.getEstado().getNome() + "\n"
            );
        }
        return s.toString();
    }

}
