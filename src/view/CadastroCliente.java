package src.view;

import src.model.Cliente;
import src.model.collections.Clientela;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CadastroCliente extends JPanel {
    class Events implements ActionListener { // Inner class para tratamento de eventos
        @Override
        public void actionPerformed(ActionEvent ev) {
            if (ev.getSource() == confirmar) {
                try {
                    createCliente();
                } catch (NumberFormatException e) {
                    System.err.format("Campo(s) inválido(s): %s%n", e);
                    headerInformation.setForeground(Color.RED);
                    headerInformation.setText("FALHA NO CADASTRO");
                    information.setText("Entrada de dado(s) inválido(s)");
                } catch (IllegalArgumentException e) {
                    System.err.format("E/S inválida: %s%n", e);
                    headerInformation.setForeground(Color.RED);
                    headerInformation.setText("FALHA NO CADASTRO");
                    information.setText("Entrada de dado(s) inválido(s)");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (ev.getSource() == limpar) {
                clearFields(cliente);
            } else if (ev.getSource() == voltar) {
                screen.changePanel(0);
            }
        }
    }

    private Screen screen;
    private CadastroCliente cliente;
    private Clientela clientes;
    private JButton confirmar;
    private JButton limpar;
    private JButton voltar;
    private JTextField textCodigo;
    private JTextField textNome;
    private JTextField textEmail;
    private JLabel headerInformation;
    private JLabel information;

    public CadastroCliente(Screen screen) {
        super(new GridLayout(7,1));
        this.setBorder(BorderFactory.createEmptyBorder(20,20,10,20));
        clientes = new Clientela();
        createUIComponents();
        this.screen = screen;
        this.cliente = this;
    }

    public Clientela getClientes() {
        return clientes;
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

        limpar = new JButton("Limpar");
        limpar.addActionListener(new Events());
        painelButtons.add(limpar);

        confirmar = new JButton("Confirmar");
        confirmar.setForeground(Color.WHITE);
        confirmar.setBackground(new Color(70, 136, 36));
        confirmar.addActionListener(new Events());
        painelButtons.add(confirmar);

        this.add(painelButtons);
    }

    public void createTextFields() {
        // Criação do header da tela
        JPanel painel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel header = new JLabel("CADASTRO DE CLIENTE");
        header.setFont(new Font("Arial", Font.BOLD, 16));
        painel.add(header);
        this.add(painel);

        // Áreas de texto linha 1
        JPanel painelText1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelText1.add(new JLabel("Código: "));
        textCodigo = new JTextField(17);
        painelText1.add(textCodigo);
        this.add(painelText1);

        // Áreas de texto linha 2
        JPanel painelText2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelText2.add(new JLabel("Nome: "));
        textNome = new JTextField(18);
        painelText2.add(textNome);
        this.add(painelText2);

        // Áreas de texto linha 3
        JPanel painelText3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelText3.add(new JLabel("Email: "));
        textEmail = new JTextField(18);
        painelText3.add(textEmail);
        this.add(painelText3);
    }

    public void createInformationFields() {
        // Área que mostra as informações linha 5
        JPanel painelText5 = new JPanel(new FlowLayout(FlowLayout.LEADING));
        JLabel textInformation = new JLabel("Informações:");
        textInformation.setFont(new Font("Arial", Font.BOLD,13));
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

    // Método limpa campos
    public void clearFields(Container box) {
        for (Component component : box.getComponents()) {
            if (component instanceof JTextField) {
                JTextField field = (JTextField) component;
                field.setText("");
            } else if (component instanceof Container) {
                clearFields((Container) component);
            }
        }
        headerInformation.setText("");
        information.setText("");
    }

    public void createCliente() throws NumberFormatException {
        boolean create = false;
        String info = "";

        String codigo = textCodigo.getText();
        String nome = textNome.getText();
        String email = textEmail.getText();


        if (!codigo.isEmpty() && !nome.isEmpty() && !email.isEmpty()) {
            int cod = Integer.parseInt(codigo);

            Cliente cliente = new Cliente(cod,nome,email);

            if (clientes.adicionarCliente(cliente)) {
                create = true;
            } else {
                info = "ERRO: " + clientes.getErro();
            }
        } else {
            info = "ERRO: Campos incompletos!";
        }

        if (create) { // Se foi realizado o cadastro
            headerInformation.setForeground(Color.GREEN);
            headerInformation.setText("CADASTRADO COM SUCESSO");
            information.setText("Novo cliente adicionado");
        } else { // Senão foi
            headerInformation.setForeground(Color.RED);
            headerInformation.setText("FALHA NO CADASTRO");
            information.setText(info);
        }
    }
}
