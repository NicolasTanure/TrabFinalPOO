package src.view;

import javax.swing.*;
import src.model.collections.*;
public class Screen extends JFrame {
    private JPanel home;
    private CadastroTipo tipo;
    private CadastroNavio navio;
    private CadastroPorto porto;
    private CadastroCliente cliente;
    private CadastroCarga carga;
    private ConsultarCarga consulta;
    private FreteCarga freteCarga;

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
                this.setLocationRelativeTo(null);
                break;
            case 1:
                this.setContentPane(tipo);
                this.pack();
                this.setSize(550,450);
                this.setLocationRelativeTo(null);
                break;
            case 2:
                this.setContentPane(navio);
                this.pack();
                this.setSize(550,600);
                this.setLocationRelativeTo(null);
                break;
            case 3:
                this.setContentPane(porto);
                this.pack();
                this.setSize(600,660);
                this.setLocationRelativeTo(null);
                break;
            case 4:
                this.setContentPane(cliente);
                this.pack();
                this.setSize(550,400);
                this.setLocationRelativeTo(null);
                break;
            case 5:
                this.setContentPane(carga);
                this.pack();
                this.setSize(650,450);
                this.setLocationRelativeTo(null);
                break;
            case 6:
                if (!carga.getEstoque().getCargas().isEmpty()) { // Se existem cargas cadastradas
                    consulta = new ConsultarCarga(this,carga.getEstoque().getCargas());
                    this.setContentPane(consulta);
                    this.pack();
                    this.setSize(800,400);
                    this.setLocationRelativeTo(null);
                } else {
                    JOptionPane.showMessageDialog(
                            this,
                            "Sem cargas existentes para consulta!",
                            "ERRO",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
                break;
            case 7:
                if (!carga.getFila().getFila().isEmpty()) {
                    freteCarga = new FreteCarga(this,carga.getFila(),navio.getNavios());
                    this.setContentPane(freteCarga);
                    this.pack();
                    this.setSize(650,450);
                    this.setLocationRelativeTo(null);
                } else {
                    JOptionPane.showMessageDialog(
                            this,
                            "Não há cargas para serem fretadas!",
                            "ERRO",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
                break;
        }
    }
}
