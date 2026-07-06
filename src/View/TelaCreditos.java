package View;

import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class TelaCreditos extends TelaBase {
    public TelaCreditos() {
        super("Debug do Milhão - Créditos");
    }

    protected void montarConteudo() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, 1));
        painel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        JLabel titulo = new JLabel("Créditos");
        titulo.setFont(FONTE_TITULO);
        titulo.setAlignmentX(0.5F);
        JTextArea texto = new JTextArea("Criadores:\n\nFabio Henrique Santos da Silva\nHenrique Dourado da Silva\nHugo Yaghi do Nascimento Marreiros\nVictor Gabriel Freitas de Carvalho\n");
        texto.setFont(FONTE_TEXTO);
        texto.setEditable(false);
        texto.setFocusable(false);
        texto.setLineWrap(true);
        texto.setWrapStyleWord(true);
        texto.setBackground(this.getBackground());
        texto.setAlignmentX(0.5F);
        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(FONTE_BOTAO);
        btnVoltar.setAlignmentX(0.5F);
        btnVoltar.setMaximumSize(new Dimension(200, 40));
        btnVoltar.addActionListener((e) -> {
            this.dispose();
            SwingUtilities.invokeLater(() -> (new TelaMenu()).setVisible(true));
        });
        painel.add(titulo);
        painel.add(Box.createRigidArea(new Dimension(0, 20)));
        painel.add(texto);
        painel.add(Box.createRigidArea(new Dimension(0, 30)));
        painel.add(btnVoltar);
        this.add(painel);
    }
}
