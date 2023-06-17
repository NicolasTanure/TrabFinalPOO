package src.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JPanel {
    // Tnner class de tratamento de eventos dos JButtons
    class Events implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == finalizar) {
                screen.setVisible(false);
                Runtime.getRuntime().exit(0);
            } else if (e.getSource() == tipos) {
                screen.changePanel(1);
            }
        }
    }

    private Screen screen;
    private JButton cargas;
    private JButton tipos;
    private JButton portos;
    private JButton navios;
    private JButton finalizar;

    public Home(Screen screen) {
        super(new GridLayout(6,1,5,8));
        this.setBorder(BorderFactory.createEmptyBorder(20,20,10,20));
        createUIComponents();
        this.screen = screen;
    }

    public void createUIComponents() {
        cargas = new JButton("Cadastrar Carga");
        this.add(cargas);

        tipos = new JButton("Cadastrar Tipo de Carga");
        tipos.addActionListener(new Events());
        this.add(tipos);

        portos = new JButton("Cadastrar Portos");
        this.add(portos);

        navios = new JButton("Cadastrar Navios");
        this.add(portos);

        finalizar = new JButton("Finalizar");
        finalizar.setForeground(Color.WHITE);
        finalizar.setBackground(new Color(126, 32, 32));
        finalizar.addActionListener(new Events());
        this.add(finalizar);
    }
}
