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
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(60, 60, 60, 60));

        JLabel titulo = new JLabel("Debug do Milhão");
        titulo.setFont(FONTE_TITULO);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnJogar = new JButton("Jogar");
        JButton btnCreditos = new JButton("Créditos");
        JButton btnSair = new JButton("Sair");

        for (JButton botao : new JButton[]{btnJogar, btnCreditos, btnSair}) {
            botao.setFont(FONTE_BOTAO);
            botao.setAlignmentX(Component.CENTER_ALIGNMENT);
            botao.setMaximumSize(new Dimension(200, 40));
        }

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