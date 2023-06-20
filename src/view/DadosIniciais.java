package src.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.Scanner;

public class DadosIniciais extends JDialog {
    class Events implements ActionListener { // Inner class para tratamento de eventos
        @Override
        public void actionPerformed(ActionEvent ev) {
            if (ev.getSource() == confirmar) {
                try {
                    readCSV();
                } catch (FileNotFoundException e) {
                    JOptionPane.showMessageDialog(
                            dados,
                            "Arquivos não encontrados!",
                            "ERRO",
                            JOptionPane.ERROR_MESSAGE
                    );
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(
                            dados,
                            "E/S interrompida!",
                            "ERRO",
                            JOptionPane.ERROR_MESSAGE
                    );
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(
                            dados,
                            "E/S inválida!",
                            "ERRO",
                            JOptionPane.ERROR_MESSAGE
                    );
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (ev.getSource() == voltar) {
                screen.changePanel(0);
            }
        }
    }

    private Screen screen;
    private DadosIniciais dados;
    private JButton confirmar;
    private JButton voltar;

    public DadosIniciais(Screen screen) {
        super(screen);
        this.screen = screen;
        this.dados = this;
    }

    public void createUIComponents() {
        this.setLayout(new BorderLayout());

    }

    public void createButtons() {
        JPanel painelButtons = new JPanel(new FlowLayout()); // Configura o layout

        voltar = new JButton("Voltar");
        voltar.setForeground(Color.WHITE);
        voltar.setBackground(new Color(126, 32, 32));
        voltar.addActionListener(new Events());
        painelButtons.add(voltar);

        confirmar = new JButton("Fretar");
        confirmar.setForeground(Color.WHITE);
        confirmar.setBackground(new Color(70, 136, 36));
        confirmar.addActionListener(new Events());
        painelButtons.add(confirmar);

        this.add(painelButtons);
    }

    public void createTextFields() {

    }


    public void readCSV() throws IOException {
        Path pasta = Path.of("src");

        Scanner sc = new Scanner(new BufferedReader(new FileReader("sla.csv", Charset.defaultCharset())));

        sc.nextLine(); // Pula header do arquivo
    }
}
