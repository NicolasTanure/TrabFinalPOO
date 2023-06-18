package src.view;

import src.model.collections.Clientela;
import src.model.collections.Portos;
import src.model.collections.Tipos;

import javax.swing.*;

public class CadastroCarga extends JPanel {
    private Screen screen;
    private Tipos tipos;
    private Portos portos;
    private Clientela clientes;

    public CadastroCarga(Screen screen, Tipos tipos, Portos portos, Clientela clientes) {
        this.screen = screen;
        this.tipos = tipos;
        this.portos = portos;
        this.clientes = clientes;
    }
}
