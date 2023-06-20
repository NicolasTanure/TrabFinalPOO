package src.view;
import java.util.*;

import src.model.Carga;
import src.model.Navio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroNavio extends JPanel {
    // Inner class que reordena lista de Navios
    class OrderNavios implements Comparator<Navio> {
        @Override
        public int compare(Navio one, Navio second) {
            return one.getNome().compareTo(second.getNome());
        }
    }

    private JTextField nomeTextField;
    private JTextField velocidadeTextField;
    private JTextField autonomiaTextField;
    private JTextField custoTextField;
    private JTextArea mensagemTextArea;
    private JButton confirmar;
    private JButton limpar;
    private JButton imprimir;
    private JButton voltar;
    private SortedMap<String, Navio> navios;
    private Screen screen;

    public CadastroNavio(Screen screen) {
        super(new BorderLayout());
        this.screen = screen;

        JPanel inputPanel = new JPanel(new GridLayout(5, 2));
        inputPanel.add(new JLabel("Nome:"));
        nomeTextField = new JTextField();
        inputPanel.add(nomeTextField);
        inputPanel.add(new JLabel("Velocidade:"));
        velocidadeTextField = new JTextField();
        inputPanel.add(velocidadeTextField);
        inputPanel.add(new JLabel("Autonomia:"));
        autonomiaTextField = new JTextField();
        inputPanel.add(autonomiaTextField);
        inputPanel.add(new JLabel("Custo por Milha Básico:"));
        custoTextField = new JTextField();
        inputPanel.add(custoTextField);
        this.add(inputPanel, BorderLayout.NORTH);

        mensagemTextArea = new JTextArea();
        this.add(new JScrollPane(mensagemTextArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        voltar = new JButton("Voltar");
        voltar.setForeground(Color.WHITE);
        voltar.setBackground(new Color(126, 32, 32));
        voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.changePanel(0); // Troca para a tela anterior (número 0)
                limparCampos();
            }
        });
        buttonPanel.add(voltar);

        limpar = new JButton("Limpar");
        limpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });
        buttonPanel.add(limpar);

        confirmar = new JButton("Confirmar");
        confirmar.setForeground(Color.WHITE);
        confirmar.setBackground(new Color(70, 136, 36));
        confirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarNavio();
            }
        });
        buttonPanel.add(confirmar);

        imprimir = new JButton("Imprimir");
        imprimir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imprimirNavios();
            }
        });
        buttonPanel.add(imprimir);

        this.add(buttonPanel, BorderLayout.SOUTH);

        navios = new TreeMap<>();
    }

    private void cadastrarNavio() {
        String nome = nomeTextField.getText().trim();
        String velocidadeStr = velocidadeTextField.getText().trim();
        String autonomiaStr = autonomiaTextField.getText().trim();
        String custoStr = custoTextField.getText().trim();

        if (nome.isEmpty() || velocidadeStr.isEmpty() || autonomiaStr.isEmpty() || custoStr.isEmpty()) {
            exibirMensagemErro("Por favor, preencha todos os campos.");
            return;
        }

        if (navios.containsKey(nome)) {
            exibirMensagemErro("Já existe um navio cadastrado com esse nome.");
            return;
        }

        double velocidade;
        double autonomia;
        double custo;

        try {
            velocidade = Double.parseDouble(velocidadeStr);
        } catch (NumberFormatException e) {
            exibirMensagemErro("Valores inválidos para velocidade.");
            return;
        }

        try {
            autonomia = Double.parseDouble(autonomiaStr);
        } catch (NumberFormatException e) {
            exibirMensagemErro("Valores inválidos para autonomia.");
            return;
        }

        try {
            custo = Double.parseDouble(custoStr);
        } catch (NumberFormatException e) {
            exibirMensagemErro("Valores inválidos para custo por milha básico.");
            return;
        }

        Navio navio = new Navio(nome, velocidade, autonomia, custo);
        navios.put(nome, navio);
        exibirMensagem("Navio cadastrado com sucesso: " + navio.toString());
    }

    private void exibirMensagem(String mensagem) {
        mensagemTextArea.append(mensagem + "\n");
    }

    private void exibirMensagemErro(String mensagem) {
        JOptionPane.showMessageDialog(
                this,
                mensagem,
                "ERRO",
                JOptionPane.ERROR_MESSAGE
        );
    }

    private void limparCampos() {
        nomeTextField.setText("");
        velocidadeTextField.setText("");
        autonomiaTextField.setText("");
        custoTextField.setText("");
    }

    private void imprimirNavios() {
        mensagemTextArea.setText("");
        for (Map.Entry<String, Navio> entry : navios.entrySet()) {
            mensagemTextArea.append(entry.getValue().toString() + "\n");
        }
    }

    public LinkedList<Navio> getNaviosDisponiveis() {
        LinkedList<Navio> disponiveis = new LinkedList<>();

        for (Navio n : navios.values()) {
            if (n.getCarga() == null) {
                disponiveis.add(n);
            }
        }
        Collections.sort(disponiveis, new OrderNavios());
        return disponiveis;
    }

    public LinkedList<Navio> getNaviosIndisponiveis() {
        LinkedList<Navio> indisponiveis = new LinkedList<>();

        for (Navio n : navios.values()) {
            if (n.getCarga() != null) {
                indisponiveis.add(n);
            }
        }
        Collections.sort(indisponiveis, new OrderNavios());
        return indisponiveis;
    }
}

