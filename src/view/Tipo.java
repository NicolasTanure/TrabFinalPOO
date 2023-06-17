package src.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tipo extends JPanel {
    class Events implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == teste) {
                screen.changePanel(0);
            }
        }
    }

    private Screen screen;
    private JButton teste;

    public Tipo(Screen screen) {
        super(new GridLayout(8,1));
        this.setBorder(BorderFactory.createEmptyBorder(20,20,10,20));
        this.screen = screen;

        teste = new JButton("TIPO");
        teste.addActionListener(new Events());
        this.add(teste);
    }

}
