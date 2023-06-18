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
    private JButton botao;
    private JButton limparCampos;
    private JButton fechar;
    private JLabel mensagemOK;
    private JLabel mensagemErro;
    private JLabel areaTexto;
    private Screen screen;


    public CadastroPorto(Screen screen) {
        super();
        this.screen = screen;

        JLabel formTitle = new JLabel("Digite os dados do porto que deseja cadastrar");
        formTitle.setFont(new Font("Tahoma", Font.PLAIN, 20));

        GridLayout gridCampos = new GridLayout(3, 2);
        JPanel painelCampos = new JPanel(gridCampos);
        JLabel idLabel = new JLabel("ID");
        JLabel nomeLabel = new JLabel("Nome");
        JLabel paisLabel = new JLabel("Pais");
        areaTexto = new JLabel("Mensagens ao Usuário:");
        idField = new JTextField();
        nomeField = new JTextField();
        paisField = new JTextField();
        painelCampos.add(idLabel);
        painelCampos.add(idField);
        painelCampos.add(nomeLabel);
        painelCampos.add(nomeField);
        painelCampos.add(paisLabel);
        painelCampos.add(paisField);

        botao = new JButton("Cadastrar");
        limparCampos = new JButton("Limpar todos os Campos");
        fechar = new JButton("Finalizar");
        mensagemOK = new JLabel();
        mensagemErro = new JLabel();

        // Tratamento de evento do botao
        botao.addActionListener(new ActionListener() {
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

        limparCampos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                idField.setText("");
                nomeField.setText("");
                paisField.setText("");
                mensagemOK.setText("");
                mensagemErro.setText("");
            }
        });

        fechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });



        GridLayout grid = new GridLayout(5, 1);
        JPanel painel = new JPanel(grid);
        painel.add(formTitle);
        painel.add(painelCampos);
        FlowLayout botaoLayout = new FlowLayout(FlowLayout.RIGHT);
        FlowLayout areaTextoLayout = new FlowLayout(FlowLayout.LEADING);
        JPanel botaoPainel = new JPanel(botaoLayout);
        JPanel areaTextoPainel = new JPanel(areaTextoLayout);
        botaoPainel.add(fechar);
        botaoPainel.add(limparCampos);
        botaoPainel.add(botao);
        areaTextoPainel.add(mensagemErro);
        areaTextoPainel.add(mensagemOK);
        painel.add(botaoPainel);
        painel.add(areaTexto);
        painel.add(areaTextoPainel);
        painel.setBorder(BorderFactory.createEmptyBorder(30,100,30,100));

        this.add(painel);
        this.setSize(650, 400);
        this.setVisible(true);
    }
}
