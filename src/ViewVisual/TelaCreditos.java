package ViewVisual;

import javax.swing.*;
import java.awt.*;

public class TelaCreditos extends TelaBase {

    public TelaCreditos() {
        super("Debug do Milhão - Créditos");
    }

    @Override
    protected void montarConteudo() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));

        JLabel titulo = new JLabel("Créditos");
        titulo.setFont(FONTE_TITULO);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea texto = new JTextArea(
                "Criadores:\n\n" +
                        "Fabio Henrique Santos da Silva\n" +
                        "Henrique Dourado da Silva\n" +
                        "Hugo Yaghi do Nascimento Marreiros\n" +
                        "Victor Gabriel Freitas de Carvalho\n"
        );
        texto.setFont(FONTE_TEXTO);
        texto.setEditable(false);
        texto.setFocusable(false);
        texto.setLineWrap(true);
        texto.setWrapStyleWord(true);
        texto.setBackground(getBackground());
        texto.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(FONTE_BOTAO);
        btnVoltar.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnVoltar.setMaximumSize(new Dimension(200, 40));
        btnVoltar.addActionListener(e -> {
            dispose();
            SwingUtilities.invokeLater(() -> new TelaMenu().setVisible(true));
        });

        painel.add(titulo);
        painel.add(Box.createRigidArea(new Dimension(0, 20)));
        painel.add(texto);
        painel.add(Box.createRigidArea(new Dimension(0, 30)));
        painel.add(btnVoltar);

        add(painel);
    }
}