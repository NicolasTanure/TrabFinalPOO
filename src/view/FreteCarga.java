package src.view;

import src.model.Carga;
import src.model.EstadoCarga;
import src.model.Navio;
import src.model.collections.FilaEstoque;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Queue;

public class FreteCarga extends JPanel {
    private Screen screen;
    private JTextArea mensagemTextArea;
    private FilaEstoque filaEstoque;
    private CadastroNavio cadastro;

    public FreteCarga(Screen screen, FilaEstoque filaEstoque,CadastroNavio cadastro) {
        super();
        this.screen = screen;
        this.filaEstoque = filaEstoque;
        this.cadastro = cadastro;
        setLayout(new BorderLayout());

        mensagemTextArea = new JTextArea();
        mensagemTextArea.setEditable(false);

        JButton fretarButton = new JButton("Fretar Cargas");
        fretarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fretarCargas();
            }
        });

        JButton voltarButton = new JButton("Voltar");
        voltarButton.setForeground(Color.WHITE);
        voltarButton.setBackground(new Color(126, 32, 32));
        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.changePanel(0); // Troca para a tela anterior (número 0)
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(fretarButton);
        buttonPanel.add(voltarButton);

        add(new JScrollPane(mensagemTextArea), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void fretarCargas() {
        Queue<Carga> cargasPendentes = filaEstoque.getCargasPendentes();
        Queue<Navio> filaNaviosDisponiveis = cadastro.getNaviosDisponiveis();

        if (cargasPendentes.isEmpty()) {
            mensagemTextArea.setText("Erro: Não há cargas pendentes para fretar.");
            return;
        }

       

        for (Carga carga : cargasPendentes) {
            boolean navioDesignado = false;

            for (Navio navio : filaNaviosDisponiveis) {
                
                    carga.setEstado(EstadoCarga.LOCADO);
                    navioDesignado = true;
                    mensagemTextArea.append("Carga " + carga.getIdentificador() + " fretada pelo navio " + navio.getNome() + "\n");
                    break;
                }
            }

    }
}


