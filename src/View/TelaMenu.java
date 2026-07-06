package View;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.LayoutManager;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class TelaMenu extends TelaBase {
    public TelaMenu() {
        super("Debug do Milhão - Menu Principal");
    }

    protected void montarConteudo() {
        JPanel painel = new JPanel();
        painel.setOpaque(false);
        painel.setLayout((LayoutManager)null);
        ImageIcon logoOriginal = new ImageIcon(this.getClass().getResource("/imagens/logo.png"));
        Image logo = logoOriginal.getImage().getScaledInstance(340, 180, 4);
        JLabel titulo = new JLabel(new ImageIcon(logo));
        titulo.setBounds(60, 10, 380, 170);
        painel.add(titulo);
        ImageIcon professoraOriginal = new ImageIcon(this.getClass().getResource("/imagens/professora.png"));
        Image professora = professoraOriginal.getImage().getScaledInstance(220, 320, 4);
        JLabel lblProfessora = new JLabel(new ImageIcon(professora));
        lblProfessora.setBounds(520, 130, 270, 340);
        painel.add(lblProfessora);
        JButton btnJogar = new JButton("Jogar");
        JButton btnCreditos = new JButton("Créditos");
        JButton btnSair = new JButton("Sair");

        for(JButton botao : new JButton[]{btnJogar, btnCreditos, btnSair}) {
            botao.setFont(FONTE_BOTAO);
        }

        btnJogar.setBounds(170, 220, 180, 45);
        btnCreditos.setBounds(170, 280, 180, 45);
        btnSair.setBounds(170, 340, 180, 45);
        painel.add(btnJogar);
        painel.add(btnCreditos);
        painel.add(btnSair);
        btnJogar.addActionListener((e) -> this.abrirTelaModoJogo());
        btnCreditos.addActionListener((e) -> this.abrirTelaCreditos());
        btnSair.addActionListener((e) -> System.exit(0));
        painel.add(titulo);
        painel.add(Box.createRigidArea(new Dimension(0, 50)));
        painel.add(btnJogar);
        painel.add(Box.createRigidArea(new Dimension(0, 15)));
        painel.add(btnCreditos);
        painel.add(Box.createRigidArea(new Dimension(0, 15)));
        painel.add(btnSair);
        this.add(painel);
    }

    private void abrirTelaModoJogo() {
        this.dispose();
        SwingUtilities.invokeLater(() -> (new TelaModo()).setVisible(true));
    }

    private void abrirTelaCreditos() {
        this.dispose();
        SwingUtilities.invokeLater(() -> (new TelaCreditos()).setVisible(true));
    }
}
