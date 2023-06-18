package src.view;
import src.model.Navio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;



public class ScreenNavio extends JFrame {
    private JTextField nomeTextField, velocidadeTextField, autonomiaTextField, custoTextField;
    private JTextArea mensagemTextArea;
    private JButton cadastrarButton, limparButton, finalizarButton, imprimirButton;
    private SortedMap<String, Navio> navios;

    public ScreenNavio() {
        super();
        setTitle("Cadastro de Navios");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

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
        add(inputPanel, BorderLayout.NORTH);

        mensagemTextArea = new JTextArea();
        add(new JScrollPane(mensagemTextArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cadastrarNavio();
            }
        });
        buttonPanel.add(cadastrarButton);

        limparButton = new JButton("Limpar");
        limparButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limparCampos();
            }
        });
        buttonPanel.add(limparButton);

        finalizarButton = new JButton("Finalizar");
        finalizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                finalizar();
            }
        });
        buttonPanel.add(finalizarButton);

        imprimirButton = new JButton("Imprimir");
        imprimirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imprimirNavios();
            }
        });
        buttonPanel.add(imprimirButton);

        add(buttonPanel, BorderLayout.SOUTH);

        navios = new TreeMap<>();

        setVisible(true);
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

        double velocidade, autonomia, custo;

        try {
            velocidade = Double.parseDouble(velocidadeStr);
        } catch (NumberFormatException e) {
            exibirMensagemErro("Valores inválidos para velocidade");
            return;
        }

        try {
            autonomia = Double.parseDouble(autonomiaStr);
        } catch (NumberFormatException e) {
            exibirMensagemErro("Valores inválidos para autonomia");
            return;
        }

        try {
            custo = Double.parseDouble(custoStr);
        } catch (NumberFormatException e) {
            exibirMensagemErro("Valores inválidos para custo");
            return;
        }

        Navio navio = new Navio(nome, velocidade, autonomia, custo);
        navios.put(nome, navio);
        exibirMensagem("Navio cadastrado com sucesso: " + navio.toString());

        nomeTextField.setText("");
        velocidadeTextField.setText("");
        autonomiaTextField.setText("");
        custoTextField.setText("");
    }

    private void limparCampos() {
        nomeTextField.setText("");
        velocidadeTextField.setText("");
        autonomiaTextField.setText("");
        custoTextField.setText("");
        mensagemTextArea.setText("");
    }

    private void finalizar() {
        dispose();
    }

    private void imprimirNavios() {
        for (Map.Entry<String, Navio> entry : navios.entrySet()) {
            String nome = entry.getKey();
            Navio navio = entry.getValue();
            exibirMensagem(nome + ": " + navio.toString());
        }
    }

    private void exibirMensagem(String mensagem) {
        mensagemTextArea.append(mensagem + "\n");
    }

    private void exibirMensagemErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    
}
