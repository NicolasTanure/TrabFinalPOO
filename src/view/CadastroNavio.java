package src.view;

import src.model.Navio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class CadastroNavio extends JPanel {
    private JTextField nomeTextField, velocidadeTextField, autonomiaTextField, custoTextField;
    private JTextArea mensagemTextArea;
    private JButton cadastrarButton, limparButton, imprimirButton, voltarButton;
    private SortedMap<String, Navio> navios;
    private Screen screen;

    public CadastroNavio(Screen screen) {
        super();
        this.screen = screen;
        setSize(500, 400);
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

        imprimirButton = new JButton("Imprimir");
        imprimirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                imprimirNavios();
            }
        });
        buttonPanel.add(imprimirButton);

        voltarButton = new JButton("Voltar");
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.changePanel(0); // Troca para a tela anterior (número 0)
            }
        });
        buttonPanel.add(voltarButton);

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
        limparCampos();
    }

    private void exibirMensagem(String mensagem) {
        mensagemTextArea.append(mensagem + "\n");
    }

    private void exibirMensagemErro(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Erro", JOptionPane.ERROR_MESSAGE);
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
}
