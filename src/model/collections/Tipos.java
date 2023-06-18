package src.model.collections;

import src.model.Duravel;
import src.model.Perecivel;
import src.model.TipoCarga;

import java.util.ArrayList;

public class Tipos {
    private ArrayList<TipoCarga> tipos;

    public Tipos() {
        tipos = new ArrayList<>();
    }

    public ArrayList<TipoCarga> getTipos() {
        return tipos;
    }

    public boolean adicionarTipo(TipoCarga tipo) {
        if (!consultar(tipo.getNumero())) {
            return tipos.add(tipo);
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
        s.append("ID | Descrição\n");
        s.append(">> PERECÍVEL: Origem | Validade Máx\n");
        s.append(">> DURÁVEL: Setor| Material Principal | Imposto IPI\n");

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
