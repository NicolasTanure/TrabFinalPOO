package src.model.collections;

import src.model.Duravel;
import src.model.Perecivel;
import src.model.TipoCarga;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Tipos {
    // Inner class para reordenação da lista de tipo de cargas
    class OrderTipos implements Comparator<TipoCarga> {
        @Override
        public int compare(TipoCarga one, TipoCarga second) {
            return one.getNumero() - second.getNumero();
        }
    }

    private ArrayList<TipoCarga> tipos;

    public Tipos() {
        tipos = new ArrayList<>();
    }

    public TipoCarga getTipo(int num) {
        for (TipoCarga t : tipos) {
            if (t.getNumero() == num) {
                return t;
            }
        }
        return null;
    }

    public boolean adicionarTipo(TipoCarga tipo) {
        if (!consultar(tipo.getNumero())) {
            if (tipos.add(tipo)) {
                Collections.sort(tipos, new OrderTipos());
            }
            return true;
        }
        return false;
    }

    public boolean consultar(int num) {
        for (TipoCarga t : tipos) {
            if (t.getNumero() == num) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("ID;Descrição\n");
        s.append("> PERECÍVEL: Origem;Validade Máx\n");
        s.append("> DURÁVEL: Setor;Material Principal;Imposto IPI\n");

        for (TipoCarga t : tipos) {
            if (t instanceof Perecivel p) {
                s.append(
                        p.getNumero()
                                + ";" + p.getDescricao()
                                + ";" + p.getOrigem()
                                + ";" + p.getValidadeMax()
                                + "\n"
                );

            } else if (t instanceof Duravel d) {
                s.append(
                        d.getNumero()
                                + ";" + d.getDescricao()
                                + ";" + d.getSetor()
                                + ";" + d.getMaterial()
                                + ";" + d.getImpostoIndustrializado()
                                + "\n"
                );
            }
        }
        return s.toString();
    }
}
