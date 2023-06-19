package src.view;

import src.model.Carga;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class ConsultarCarga extends JPanel {
    class Events implements ActionListener { // Inner class para tratamento de eventos
        @Override
        public void actionPerformed(ActionEvent ev) {
            if (ev.getSource() == voltar) {
                screen.changePanel(0);
            }
        }
    }

    private Screen screen;
    private LinkedList<Carga> cargas;
    private JButton voltar;

    public ConsultarCarga(Screen screen, LinkedList<Carga> cargas) {
        super(new BorderLayout());
        this.screen = screen;
        this.cargas = cargas;
    }

    public void createUIComponents() {
        createButtons();
        JPanel header = new JPanel(new FlowLayout());
        header.add(new JLabel("LISTA DE CARGAS"));
        this.add(header, BorderLayout.NORTH);

        DefaultListModel<String> listaCarga = new DefaultListModel<>();
        listaCarga.addElement("Código;Peso;ValorDeclarado;TempoMáx;Situação;TipoCarga;Cliente;Origem;Destino");
        for (Carga c : cargas) {
            listaCarga.addElement(c.toString());
        }

        JList<String> consulta = new JList<>(listaCarga);
        JScrollPane scroll = new JScrollPane(consulta);

        this.add(scroll, BorderLayout.CENTER);
    }

    public void createButtons() {
        JPanel painelButtons = new JPanel(new FlowLayout()); // Configura o layout

        voltar = new JButton("Voltar");
        voltar.setForeground(Color.WHITE);
        voltar.setBackground(new Color(126, 32, 32));
        voltar.addActionListener(new Events());
        painelButtons.add(voltar);

        this.add(painelButtons, BorderLayout.SOUTH);
    }
}
