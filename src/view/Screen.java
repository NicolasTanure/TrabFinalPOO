package src.view;

import javax.swing.*;

public class Screen extends JFrame {
    private JPanel home;
    private CadastroTipo tipo;
    private CadastroNavio navio;
    private CadastroPorto porto;
    private CadastroCliente cliente;
    private JPanel carga;

    public Screen() {
        super("Sistema ACMEHandelsschifffahrtsgesellschaft");
        this.pack();
        this.setSize(550,650);
        this.setLocationRelativeTo(null);
        home = new Home(this);
        this.setContentPane(home); // Tela padrão
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        tipo = new CadastroTipo(this); // Painel recebe a tela PRINCIPAL (extends JFrame)
        navio = new CadastroNavio(this);
        porto = new CadastroPorto(this);
        cliente = new CadastroCliente(this);
        carga = new CadastroCarga(this,tipo.getTipos(),porto.getPortos(),cliente.getClientes());
    }

    /**
     * Alterna os paineis da janela
     * @param panel número do painel para a troca
     */
    public void changePanel(int panel) {
        switch(panel) {
            case 0:
                this.setContentPane(home);
                this.pack(); // Redimensiona a tela para receber todos componentes
                this.setSize(550,600);
                break;
            case 1:
                this.setContentPane(tipo);
                this.pack();
                this.setSize(550,500);
                break;
            case 2:
                this.setContentPane(navio);
                this.pack();
                this.setSize(550,600);
                break;
            case 3:
                this.setContentPane(porto);
                this.pack();
                this.setSize(550,600);
                break;
            case 4:
                this.setContentPane(cliente);
                this.pack();
                this.setSize(550,450);
                break;
            case 5:
                this.setContentPane(carga);
                this.pack();
                this.setSize(650,450);
                break;

        }
    }
}
