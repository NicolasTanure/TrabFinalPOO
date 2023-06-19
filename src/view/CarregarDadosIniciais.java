package src.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class CarregarDadosIniciais extends JPanel {
    private Screen screen;
    private JTextField nomeArquivoTextField;
    private JTextArea mensagemTextArea;
    private Queue<String> filaCargasPendentes;

    public CarregarDadosIniciais(Screen screen) {
        super();
        this.screen = screen;
        this.filaCargasPendentes = new LinkedList<>();

        setLayout(new BorderLayout());

        JLabel nomeArquivoLabel = new JLabel("Nome do arquivo:");
        nomeArquivoTextField = new JTextField();
        JButton carregarButton = new JButton("Carregar");

        carregarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeArquivo = nomeArquivoTextField.getText();
                carregarDados(nomeArquivo);
            }
        });

        mensagemTextArea = new JTextArea();
        mensagemTextArea.setEditable(false);

        JPanel panel = new JPanel(new GridLayout(3, 1));
        panel.add(nomeArquivoLabel);
        panel.add(nomeArquivoTextField);
        panel.add(carregarButton);

        JButton voltar = new JButton("Voltar");
        voltar.setForeground(Color.WHITE);
        voltar.setBackground(new Color(126, 32, 32));
        voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.changePanel(0); // Troca para a tela anterior (número 0)
            }
        });
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(voltar);
        
        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(mensagemTextArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void carregarDados(String nomeArquivo) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(nomeArquivo + ".txt"));
            String linha;

            // Carregar os dados do arquivo para o sistema
            while ((linha = reader.readLine()) != null) {
                filaCargasPendentes.add(linha);
            }

            reader.close();

            // Mostrar os dados de portos, navios, clientes, cargas
            mensagemTextArea.setText("");
            for (String carga : filaCargasPendentes) {
                mensagemTextArea.append(carga + "\n");
            }

        } catch (IOException e) {
            mensagemTextArea.setText("Erro: arquivo não encontrado.");
        } catch (Exception e) {
            mensagemTextArea.setText("Erro durante a carga de dados: " + e.getMessage());
        }
    }
}

