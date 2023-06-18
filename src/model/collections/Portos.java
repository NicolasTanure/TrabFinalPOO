package src.model.collections;

import src.model.*;

import java.util.ArrayList;

public class Portos {
    private ArrayList<Porto> listaPorto;

    public Portos() {
        this.listaPorto = new ArrayList<>();
    }

    public ArrayList<Porto> getListaPorto() {
        return listaPorto;
    }

    public boolean checaID(Porto p) {
        for(Porto pp : listaPorto) {
            if (pp.getId() == p.getId())
                return false;
        }
        return true;
    }

    public boolean addOrdenado(Porto p) {
        if (listaPorto.isEmpty()) {
            listaPorto.add(p);
            return true;
        } else {
            if (checaID(p)) {
                for (int i = 0; i < listaPorto.size(); i++) {
                    if (p.getId() < listaPorto.get(i).getId()) {
                        listaPorto.add(i, p);
                        return true;
                    } else {
                        listaPorto.add(p);
                        return true;
                    }

                }
            }
            return false;
        }
    }
}

