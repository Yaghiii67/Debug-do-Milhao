package View;

import javax.swing.*;
import java.awt.*;

public class TelaMenu extends TelaBase {

    public TelaMenu() {
        super("Debug do Milhão - Menu Principal");
    }

    @Override
    protected void montarConteudo() {
        JPanel painel = new JPanel();
        painel.setOpaque(false);
        painel.setLayout(null);

        ImageIcon logoOriginal = new ImageIcon(
                getClass().getResource("/imagens/logo.png"));

        Image logo = logoOriginal.getImage().getScaledInstance(
                340, 180, Image.SCALE_SMOOTH);

        JLabel titulo = new JLabel(new ImageIcon(logo));
        titulo.setBounds(60, 10, 380, 170);
        painel.add(titulo);

        ImageIcon professoraOriginal = new ImageIcon(
                getClass().getResource("/imagens/professora.png"));

        Image professora = professoraOriginal.getImage().getScaledInstance(
                220, 320, Image.SCALE_SMOOTH);

        JLabel lblProfessora = new JLabel(new ImageIcon(professora));
        lblProfessora.setBounds(520, 130, 270, 340);
        painel.add(lblProfessora);


        JButton btnJogar = new JButton("Jogar");
        JButton btnCreditos = new JButton("Créditos");
        JButton btnSair = new JButton("Sair");

        for (JButton botao : new JButton[]{btnJogar, btnCreditos, btnSair}) {
            botao.setFont(FONTE_BOTAO);
        }

        btnJogar.setBounds(170, 220, 180, 45);
        btnCreditos.setBounds(170, 280, 180, 45);
        btnSair.setBounds(170, 340, 180, 45);

        painel.add(btnJogar);
        painel.add(btnCreditos);
        painel.add(btnSair);

        btnJogar.addActionListener(e -> abrirTelaModoJogo());
        btnCreditos.addActionListener(e -> abrirTelaCreditos());
        btnSair.addActionListener(e -> System.exit(0));

        painel.add(titulo);
        painel.add(Box.createRigidArea(new Dimension(0, 50)));
        painel.add(btnJogar);
        painel.add(Box.createRigidArea(new Dimension(0, 15)));
        painel.add(btnCreditos);
        painel.add(Box.createRigidArea(new Dimension(0, 15)));
        painel.add(btnSair);

        add(painel);
    }

    private void abrirTelaModoJogo() {
        dispose(); // fecha o menu
        SwingUtilities.invokeLater(() -> new TelaModo().setVisible(true));
    }

    private void abrirTelaCreditos() {
        dispose(); // fecha o menu
        SwingUtilities.invokeLater(() -> new TelaCreditos().setVisible(true));
    }
}
