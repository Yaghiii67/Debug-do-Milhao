package View;

import Controller.QuizController;
import Model.Pergunta;

import javax.swing.*;
import java.awt.*;

public class TelaQuiz extends TelaBase {

    private static final String[] ROTULOS = {"A", "B", "C", "D"};

    private final QuizController controller;

    private JLabel lblProgresso;
    private JLabel lblPontuacao;
    private JTextArea txtEnunciado;
    private JButton[] botoesAlternativas;

    public TelaQuiz(QuizController controller) {
        super("Debug do Milhão - Jogando");
        this.controller = controller;
        atualizarTela();
    }

    @Override
    protected void montarConteudo() {
        JPanel painelTopo = new JPanel(new BorderLayout());
        lblProgresso = new JLabel();
        lblPontuacao = new JLabel();
        lblProgresso.setFont(FONTE_TEXTO);
        lblPontuacao.setFont(FONTE_TEXTO);
        painelTopo.add(lblProgresso, BorderLayout.WEST);
        painelTopo.add(lblPontuacao, BorderLayout.EAST);
        painelTopo.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        txtEnunciado = new JTextArea();
        txtEnunciado.setFont(FONTE_TITULO.deriveFont(20f));
        txtEnunciado.setLineWrap(true);
        txtEnunciado.setWrapStyleWord(true);
        txtEnunciado.setEditable(false);
        txtEnunciado.setFocusable(false);
        txtEnunciado.setBackground(getBackground());
        txtEnunciado.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        JScrollPane scrollEnunciado = new JScrollPane(txtEnunciado);
        scrollEnunciado.setBorder(BorderFactory.createEmptyBorder());
        scrollEnunciado.setOpaque(false);
        scrollEnunciado.getViewport().setOpaque(false);
        scrollEnunciado.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JPanel painelAlternativas = new JPanel(new GridLayout(4, 1, 10, 10));
        painelAlternativas.setBorder(BorderFactory.createEmptyBorder(10, 30, 30, 30));

        botoesAlternativas = new JButton[4];
        for (int i = 0; i < 4; i++) {
            JButton botao = new JButton();
            botao.setFont(FONTE_BOTAO);
            botao.setHorizontalAlignment(SwingConstants.LEFT);
            final int indice = i;
            botao.addActionListener(e -> selecionarAlternativa(indice));
            botoesAlternativas[i] = botao;
            painelAlternativas.add(botao);
        }

        setLayout(new BorderLayout());
        add(painelTopo, BorderLayout.NORTH);
        add(scrollEnunciado, BorderLayout.CENTER);
        add(painelAlternativas, BorderLayout.SOUTH);
    }

    private void atualizarTela() {
        if (!controller.existeProximaPergunta()) {
            finalizarQuiz();
            return;
        }

        Pergunta atual = controller.obterPerguntaAtual();
        lblProgresso.setText("Pergunta " + controller.getNumeroPerguntaAtual() + " de " + controller.getTotalPerguntas());
        lblPontuacao.setText(montarTextoPontuacao());
        txtEnunciado.setText(atual.getEnunciado());

        String[] alternativas = atual.getAlternativas();
        int quantidade = Math.min(4, alternativas.length);
        for (int i = 0; i < quantidade; i++) {
            botoesAlternativas[i].setText(ROTULOS[i] + ")  " + alternativas[i]);
            botoesAlternativas[i].setEnabled(true);
            botoesAlternativas[i].setVisible(true);
        }
        for (int i = quantidade; i < 4; i++) {
            botoesAlternativas[i].setVisible(false);
        }
    }

    private String montarTextoPontuacao() {
        if (controller.getNumeroJogadores() == 1) {
            return "Pontuação: " + controller.getPontuacao(0);
        }

        int vez = controller.getJogadorDaVez(); // 1 ou 2
        return String.format("Vez: %s  |  %s: %d   %s: %d",
                controller.getNomeJogador(vez - 1),
                controller.getNomeJogador(0), controller.getPontuacao(0),
                controller.getNomeJogador(1), controller.getPontuacao(1));
    }

    private void selecionarAlternativa(int indice) {
        setBotoesHabilitados(false);

        boolean acertou = controller.responder(indice);

        String mensagem = acertou ? "Você acertou!" : "Você errou.";
        JOptionPane.showMessageDialog(this, mensagem, "Resultado", JOptionPane.INFORMATION_MESSAGE);

        atualizarTela();
    }

    private void setBotoesHabilitados(boolean habilitado) {
        for (JButton botao : botoesAlternativas) {
            botao.setEnabled(habilitado);
        }
    }

    private void finalizarQuiz() {
        int total = controller.getTotalPerguntas();
        StringBuilder resultado = new StringBuilder("Quiz finalizado!\n");

        if (controller.getNumeroJogadores() == 1) {
            resultado.append("Você acertou ").append(controller.getPontuacao(0))
                    .append(" de ").append(total).append(" perguntas.");
        } else {
            for (int i = 0; i < controller.getNumeroJogadores(); i++) {
                resultado.append(controller.getNomeJogador(i)).append(": ")
                        .append(controller.getPontuacao(i)).append(" pontos\n");
            }
        }

        controller.salvarResultado();

        JOptionPane.showMessageDialog(this, resultado.toString(), "Fim de jogo", JOptionPane.INFORMATION_MESSAGE);

        dispose();
        SwingUtilities.invokeLater(() -> new TelaMenu().setVisible(true));
    }
}