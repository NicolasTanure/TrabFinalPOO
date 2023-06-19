package src.view;

import src.model.collections.Portos;
import src.model.Porto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroPorto extends JPanel {
    private final Portos colecaoPortos = new Portos();
    private JTextField idField;
    private JTextField nomeField;
    private JTextField paisField;
    private JButton confirmar;
    private JButton limpar;
    private JButton voltar;
    private JLabel mensagemOK;
    private JLabel mensagemErro;
    private JLabel areaTexto;
    private Screen screen;


    public CadastroPorto(Screen screen) {
        super();
        this.screen = screen;
        this.setBorder(BorderFactory.createEmptyBorder(20,10,20,10));

        JPanel title = new JPanel(new FlowLayout());
        JLabel header = new JLabel("CADASTRO DE PORTO");
        header.setFont(new Font("Arial", Font.BOLD, 16));
        title.add(header);

        GridLayout gridCampos = new GridLayout(3, 2,4,5);
        JPanel painelCampos = new JPanel(gridCampos);
        JLabel idLabel = new JLabel("ID:");
        JLabel nomeLabel = new JLabel("Nome:");
        JLabel paisLabel = new JLabel("País:");
        areaTexto = new JLabel("Informações:");
        idField = new JTextField();
        nomeField = new JTextField();
        paisField = new JTextField();
        painelCampos.add(idLabel);
        painelCampos.add(idField);
        painelCampos.add(nomeLabel);
        painelCampos.add(nomeField);
        painelCampos.add(paisLabel);
        painelCampos.add(paisField);

        confirmar = new JButton("Confirmar");
        confirmar.setForeground(Color.WHITE);
        confirmar.setBackground(new Color(70, 136, 36));
        limpar = new JButton("Limpar");
        voltar = new JButton("Voltar");
        voltar.setForeground(Color.WHITE);
        voltar.setBackground(new Color(126, 32, 32));
        mensagemOK = new JLabel();
        mensagemErro = new JLabel();

        // Tratamento de evento dos botões
        confirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mensagemErro.setText("");
                mensagemOK.setText("");
                mensagemOK.setForeground(Color.GREEN);
                mensagemErro.setForeground(Color.RED);
                String nome = nomeField.getText();
                if (nome.equals("")) {
                    mensagemErro.setText("Erro: Formato Inválido! Preencha os campos com informações válidas");
                }
                int id = -1;
                try {
                    id = Integer.parseInt(idField.getText());
                }catch (Exception e1) {
                    mensagemErro.setText("Erro: Formato Inválido! Preencha os campos com informações válidas");
                }
                String pais = paisField.getText();
                if (pais.equals("")) {
                    mensagemErro.setText("Erro: Formato Inválido! Preencha os campos com informações válidas");
                }

                Porto p = new Porto(id, nome, pais);
                if (p.getId() != -1) {
                    if (colecaoPortos.addOrdenado(p)) {
                        mensagemOK.setText("Porto cadastrado com sucesso ");
                    } else {
                        mensagemErro.setText("Erro: ID indisponível");
                    }
                }
            }
        });

        limpar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idField.setText("");
                nomeField.setText("");
                paisField.setText("");
                mensagemOK.setText("");
                mensagemErro.setText("");
            }
        });

        voltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                screen.changePanel(0);
            }
        });



        GridLayout grid = new GridLayout(5, 1,0,8);
        JPanel painel = new JPanel(grid);
        painel.add(title);
        painel.add(painelCampos);

        JPanel confirmarPainel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        confirmarPainel.add(voltar);
        confirmarPainel.add(limpar);
        confirmarPainel.add(confirmar);
        painel.add(confirmarPainel);

        painel.add(areaTexto);

        JPanel areaTextoPainel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        areaTextoPainel.add(mensagemErro);
        areaTextoPainel.add(mensagemOK);
        painel.add(areaTextoPainel);

        this.add(painel);
    }

    public Portos getPortos() {
        return colecaoPortos;
    }
}
