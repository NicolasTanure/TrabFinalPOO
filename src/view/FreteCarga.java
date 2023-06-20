package src.view;

import src.model.Carga;
import src.model.EstadoCarga;
import src.model.Navio;
import src.model.Prioridade;
import src.model.collections.FilaEstoque;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Map;

public class FreteCarga extends JPanel {
    class Events implements ActionListener { // Inner class para tratamento de eventos
        @Override
        public void actionPerformed(ActionEvent ev) {
            if (ev.getSource() == fretar) {
                fretarCargas();
            } else if (ev.getSource() == voltar) {
                screen.changePanel(0);
            }
        }
    }

    private Screen screen;
    private FilaEstoque fila;
    private LinkedList<Navio> navios;
    private Carga current;
    private JButton fretar;
    private JButton voltar;
    private JRadioButton barato;
    private JRadioButton rapido;
    private JTextArea dadosCarga;
    private JLabel headerInformation;
    private JLabel information;

    public FreteCarga(Screen screen, FilaEstoque fila, LinkedList<Navio> navios) {
        super(new GridLayout(7, 1));
        this.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
        this.screen = screen;
        this.fila = fila;
        this.navios = navios;
        this.current = fila.getFirstCarga();
        createUIComponents();
    }

    public void createUIComponents() {
        // Cria campos de texto
        createTextFields();
        // Cria os botões
        createButtons();
        // Cria área de informação
        createInformationFields();
    }

    public void createButtons() {
        JPanel painelButtons = new JPanel(new FlowLayout()); // Configura o layout

        voltar = new JButton("Voltar");
        voltar.setForeground(Color.WHITE);
        voltar.setBackground(new Color(126, 32, 32));
        voltar.addActionListener(new Events());
        painelButtons.add(voltar);

        fretar = new JButton("Fretar");
        fretar.setForeground(Color.WHITE);
        fretar.setBackground(new Color(70, 136, 36));
        fretar.addActionListener(new Events());
        painelButtons.add(fretar);

        this.add(painelButtons);
    }

    public void createTextFields() {
        // Criação do header da tela
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel header = new JLabel("FRETAR CARGA");
        header.setFont(new Font("Arial", Font.BOLD, 16));
        painel.add(header);
        this.add(painel);

        JPanel currentCarga = new JPanel(new FlowLayout(FlowLayout.CENTER));
        dadosCarga = new JTextArea(current.toString()); // Referência da primeira Carga
        dadosCarga.setEditable(false);
        dadosCarga.setLineWrap(true);
        JScrollPane scroll = new JScrollPane(dadosCarga);
        scroll.setPreferredSize(new Dimension(570,40));
        currentCarga.add(scroll);
        this.add(currentCarga);

        ButtonGroup bg = new ButtonGroup();
        JPanel tipoFrete = new JPanel(new FlowLayout(FlowLayout.CENTER));
        barato = new JRadioButton("Barato", false);
        bg.add(barato);
        tipoFrete.add(barato);
        rapido = new JRadioButton("Rápido", false);
        bg.add(rapido);
        tipoFrete.add(rapido);
        this.add(tipoFrete);
    }

    public void createInformationFields() {
        // Área que mostra as informações linha 5
        JPanel painelText5 = new JPanel(new FlowLayout(FlowLayout.LEADING));
        JLabel textInformation = new JLabel("Informações:");
        textInformation.setFont(new Font("Arial", Font.BOLD, 13));
        painelText5.add(textInformation);
        headerInformation = new JLabel();
        painelText5.add(headerInformation);
        this.add(painelText5);
        // Área que mostra as informações linha 6
        JPanel painelText6 = new JPanel(new FlowLayout(FlowLayout.LEADING));
        information = new JLabel();
        painelText6.add(information);
        this.add(painelText6);
    }

    private void fretarCargas() {
        boolean frete = false;
        String info = "";

        Map<Integer, Integer> destino = current.getDestino().getDistancia();
        int id = current.getDestino().getId();
        System.out.println(destino.containsKey(0));
        int distancia = destino.get(id); // >>>> ERRO

        if (!navios.isEmpty()) {
            for (Navio n : navios) {
                double tempo = Math.ceil(distancia / n.getVelocidade()); // >>>> Onde dá a exceção
                if (n.getAutonomia() >= distancia && tempo <= current.getTempoMaximo()) {
                    if (n.getCarga() == null) {
                        if (barato.isSelected()) {
                            current.setPrioridade(Prioridade.BARARTO);
                            current.setEstado(EstadoCarga.LOCADO);
                            n.setCarga(current);

                            frete = true;
                            info = "Carga alocada!";
                        } else if (rapido.isSelected()) {
                            current.setPrioridade(Prioridade.RAPIDO);
                            current.setEstado(EstadoCarga.LOCADO);
                            n.setCarga(current);

                            frete = true;
                            info = "Carga alocada!";
                        } else {
                            JOptionPane.showMessageDialog(
                                    this,
                                    "Informe a prioridade do frete!",
                                    "ERRO",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    } else {
                        Carga c = fila.removeCarga();
                        fila.addCarga(c);

                        info = "Carga adicionada novamente na fila!";
                    }
                } else {
                    Carga c = fila.removeCarga();
                    c.setEstado(EstadoCarga.CANCELADO);

                    info = "Carga removida da fila, impossível transportar!";
                }
            }
        } else {
            info = "ERRO: Não há navios cadastrados!";
        }

        if (frete) { // Se foi possível fretar
            headerInformation.setForeground(Color.GREEN);
            headerInformation.setText("CARGA ALOCADA");
            information.setText("Carga em transporte..."); // >>>> Printa aqui o valor do frete

            if (!fila.getFila().isEmpty()) {
                fila.removeCarga(); // Remove a carga cadastrada da fila
                current = fila.getFirstCarga();
                dadosCarga.setText(current.toString()); // Atualiza as informações na tela
            } else {
                dadosCarga.setText("");
                headerInformation.setForeground(Color.RED);
                headerInformation.setText("ERRO");
                information.setText("Não há mais cargas a serem fretadas!");
            }
        } else { // Senão foi
            headerInformation.setForeground(Color.RED);
            headerInformation.setText("NÃO FOI POSSÍVEL ALOCAR CARGA");
            information.setText(info);
        }
    }
}


