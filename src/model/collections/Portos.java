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

    public Porto getPorto(int id) {
        for (Porto p : listaPorto) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public boolean checaID(Porto p) {
        for(Porto pp : listaPorto) {
            if (pp.getId() == p.getId()) {
                return false;
            }
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
                        System.out.println("Porto Cadastrado id: " + p.getId());
                        adicionaDistancia(p);
                        System.out.println("Distancia cadastradas");
                        return true;
                    } else {
                        listaPorto.add(p);
                        adicionaDistancia(p);
                        return true;
                    }

                }
            }
            return false;
        }
    }

    public void adicionaDistancia(Porto p) {
        if (!listaPorto.isEmpty()) {
            for (Porto porto : listaPorto) {
                porto.getDistancia().put(p.getId(), 100);
                p.getDistancia().put(porto.getId(), 100);
            }
        }
    }
}

