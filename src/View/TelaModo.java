package View;

import javax.swing.*;
import java.awt.*;

public class TelaModo extends TelaBase {

    public TelaModo() {
        super("Debug do Milhão - Modo de Jogo");
    }

    @Override
    protected void montarConteudo() {
        JPanel painel = new JPanel();
        painel.setOpaque(false);
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(60, 60, 60, 60));

        JLabel titulo = new JLabel("Escolha o modo de jogo");
        titulo.setFont(FONTE_TITULO.deriveFont(22f));
        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btn1Jogador = new JButton("1 Jogador");
        JButton btn2Jogadores = new JButton("2 Jogadores");
        JButton btnVoltar = new JButton("Voltar");

        for (JButton botao : new JButton[]{btn1Jogador, btn2Jogadores, btnVoltar}) {
            botao.setFont(FONTE_BOTAO);
            botao.setAlignmentX(Component.CENTER_ALIGNMENT);
            botao.setMaximumSize(new Dimension(220, 40));
        }

        btn1Jogador.addActionListener(e -> abrirSelecaoTema(1));
        btn2Jogadores.addActionListener(e -> abrirSelecaoTema(2));
        btnVoltar.addActionListener(e -> voltarMenu());

        painel.add(titulo);
        painel.add(Box.createRigidArea(new Dimension(0, 50)));
        painel.add(btn1Jogador);
        painel.add(Box.createRigidArea(new Dimension(0, 15)));
        painel.add(btn2Jogadores);
        painel.add(Box.createRigidArea(new Dimension(0, 35)));
        painel.add(btnVoltar);

        add(painel);
    }

    private void abrirSelecaoTema(int numeroJogadores) {
        dispose();
        SwingUtilities.invokeLater(() -> new TelaTemas(numeroJogadores).setVisible(true));
    }

    private void voltarMenu() {
        dispose();
        SwingUtilities.invokeLater(() -> new TelaMenu().setVisible(true));
    }
}
