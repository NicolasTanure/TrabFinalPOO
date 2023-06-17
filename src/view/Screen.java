package src.view;

import javax.swing.*;

public class Screen extends JFrame {
    private JPanel home;
    private JPanel tipo;

    public Screen() {
        super("Sistema ACMEHandelsschifffahrtsgesellschaft");
        this.pack();
        this.setSize(550,650);
        this.setLocationRelativeTo(null);
        home = new Home(this);
        this.setContentPane(home); // Tela padrão
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        tipo = new Tipo(this);
    }

    /**
     * Alterna os paineis da janela
     * @param panel número do painel para a troca
     */
    public void changePanel(int panel) {
        switch(panel) {
            case 0:
                this.setContentPane(home);
                this.pack();
                this.setSize(550,600);
                break;
            case 1:
                this.setContentPane(tipo);
                this.pack();
                this.setSize(550,600);
                break;
        }
    }
}
