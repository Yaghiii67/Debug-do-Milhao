package View;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class TelaModo extends TelaBase {
    public TelaModo() {
        super("Debug do Milhão - Modo de Jogo");
    }

    protected void montarConteudo() {
        JPanel painel = new JPanel();
        painel.setOpaque(false);
        painel.setLayout(new BoxLayout(painel, 1));
        painel.setBorder(BorderFactory.createEmptyBorder(60, 60, 60, 60));
        JLabel titulo = new JLabel("Escolha o modo de jogo");
        titulo.setFont(FONTE_TITULO.deriveFont(22.0F));
        titulo.setForeground(Color.WHITE);
        titulo.setAlignmentX(0.5F);
        JButton btn1Jogador = new JButton("1 Jogador");
        JButton btn2Jogadores = new JButton("2 Jogadores");
        JButton btnVoltar = new JButton("Voltar");

        for(JButton botao : new JButton[]{btn1Jogador, btn2Jogadores, btnVoltar}) {
            botao.setFont(FONTE_BOTAO);
            botao.setAlignmentX(0.5F);
            botao.setMaximumSize(new Dimension(220, 40));
        }

        btn1Jogador.addActionListener((e) -> this.abrirSelecaoTema(1));
        btn2Jogadores.addActionListener((e) -> this.abrirSelecaoTema(2));
        btnVoltar.addActionListener((e) -> this.voltarMenu());
        painel.add(titulo);
        painel.add(Box.createRigidArea(new Dimension(0, 50)));
        painel.add(btn1Jogador);
        painel.add(Box.createRigidArea(new Dimension(0, 15)));
        painel.add(btn2Jogadores);
        painel.add(Box.createRigidArea(new Dimension(0, 35)));
        painel.add(btnVoltar);
        this.add(painel);
    }

    private void abrirSelecaoTema(int numeroJogadores) {
        this.dispose();
        SwingUtilities.invokeLater(() -> (new TelaTemas(numeroJogadores)).setVisible(true));
    }

    private void voltarMenu() {
        this.dispose();
        SwingUtilities.invokeLater(() -> (new TelaMenu()).setVisible(true));
    }
}
