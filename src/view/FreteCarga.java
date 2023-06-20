package src.view;

import src.model.*;
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
    private LinkedList<Navio> ocupados;
    private Carga current;
    private JButton fretar;
    private JButton voltar;
    private JRadioButton barato;
    private JRadioButton rapido;
    private JTextArea dadosCarga;
    private JLabel headerInformation;
    private JLabel information;

    public FreteCarga(Screen screen, FilaEstoque fila, LinkedList<Navio> navios, LinkedList<Navio> ocupados) {
        super(new GridLayout(7, 1));
        this.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));
        this.screen = screen;
        this.fila = fila;
        this.navios = navios;
        this.ocupados = ocupados;
        this.current = fila.getFirstCarga();
        createUIComponents();
    }

    public void setCurrent(Carga current) {
        this.current = current;
    }

    public void setNavios(LinkedList<Navio> navios) {
        this.navios = navios;
    }

    public LinkedList<Navio> getOcupados() {
        return ocupados;
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
        Navio navio = null;
        boolean frete = false;
        String info = "";

        Map<Integer, Integer> destino = current.getDestino().getDistancia();
        int id = current.getOrigem().getId();
        int distancia = destino.get(id);

        if (!navios.isEmpty()) {
            for (Navio n : navios) {
                double tempo = Math.ceil(distancia / n.getVelocidade());
                if (n.getAutonomia() >= distancia && tempo <= current.getTempoMaximo()) {
                    if (barato.isSelected()) {
                        current.setPrioridade(Prioridade.BARARTO);
                        current.setEstado(EstadoCarga.LOCADO);
                        n.setCarga(current);
                        n.setHistorico(current);
                        navio = n;
                        navios.remove(n); // Remove o navio não mais disponível
                        ocupados.add(n); // Cataloga como indisponível

                        frete = true;
                        info = "Carga alocada!";
                    } else if (rapido.isSelected()) {
                        current.setPrioridade(Prioridade.RAPIDO);
                        current.setEstado(EstadoCarga.LOCADO);
                        n.setCarga(current);
                        n.setHistorico(current);
                        navio = n;
                        navios.remove(n);
                        ocupados.add(n);

                        frete = true;
                        info = "Carga alocada!";
                    } else {
                        headerInformation.setForeground(Color.RED);
                        headerInformation.setText("ERRO");
                        information.setText("Selecione a prioridade do frete!");
                    }
                } else {
                    // Navios disponíveis incapazes de transportar, verifica nos indisponíveis
                    info = verificaIndisponiveis(current, distancia);
                }
            }
        } else {
            // Não há mais navios disponíveis, apenas indisponíveis
            info = verificaIndisponiveis(current, distancia);
        }

        if (frete) { // Se foi possível fretar
            double preco = calculaFrete(navio, current); // Calcula o frete

            headerInformation.setForeground(Color.GREEN);
            headerInformation.setText("CARGA ALOCADA");
            information.setText("Carga em transporte... " + "Preço: " + preco);

            fila.removeCarga(); // Remove a carga cadastrada da fila
            if (!fila.getFila().isEmpty()) {
                current = fila.getFirstCarga(); // Senão está vazia, atualiza as informações na tela
                dadosCarga.setText(current.toString());
            } else {
                dadosCarga.setText("");
                JOptionPane.showMessageDialog(
                        this,
                        "Não há mais cargas a serem fretadas!",
                        "ATENÇÃO",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        } else { // Senão foi
            headerInformation.setForeground(Color.RED);
            headerInformation.setText("NÃO FOI POSSÍVEL ALOCAR CARGA");
            information.setText(info);
        }
    }

    public String verificaIndisponiveis(Carga current, int distancia) {
        String info = "";
        boolean adicionaFila = false;

        for (Navio n : ocupados) {
            double time = Math.ceil(distancia / n.getVelocidade());
            if (n.getAutonomia() >= distancia && time <= current.getTempoMaximo()) {
                Carga c = fila.removeCarga();
                fila.addCarga(c);

                adicionaFila = true;
                info = "Carga adicionada novamente na fila. Sem navios disponíveis no momento!";
            }
        }
        if (!adicionaFila) { // Nem mesmo os navios indisponíveis são capazes de transportar
            Carga c = fila.removeCarga();
            c.setEstado(EstadoCarga.CANCELADO);

            info = "Carga cancelada. Sem navios capazes de transportar!";
        }
        return info;
    }

    public double calculaFrete(Navio navio, Carga carga) {
        double precoFinal = 0;
        double precoRegiao = 0;
        double precoPeso = 0;
        double precoPrioridade = 0;

        int idDestino = carga.getDestino().getId();
        int distancia = carga.getOrigem().getDistancia().get(idDestino);

        // Preço por prioridade
        if(carga.getPrioridade() == Prioridade.RAPIDO) {
            precoPrioridade = distancia * (navio.getCustoPorMilhaBasico() * 2);
        }else {
            precoPrioridade = distancia * navio.getCustoPorMilhaBasico();
        }

        // Preço por regiao
        if(carga.getOrigem().getPais().toLowerCase().equals("brasil") && carga.getDestino().getPais().toLowerCase().equals("brasil")) {
            precoRegiao = 10000;
        } else {
            precoRegiao = 50000;
        }

        // Preço por peso
        if(carga.getTipoCarga() instanceof Duravel) {
            Duravel duravel = (Duravel) carga.getTipoCarga();
            precoPeso = carga.getPeso() * 1.5 + (carga.getValorDeclarado() * (duravel.getImpostoIndustrializado()/100));
        } else {
            precoPeso = carga.getPeso() * 2;
        }

        precoFinal = precoRegiao + precoPeso + precoPrioridade;
        return precoFinal;
    }
}


