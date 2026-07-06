package View;

import Controller.QuizController;
import Model.Pergunta;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class TelaQuiz extends TelaBase {
    private static final String[] ROTULOS = new String[]{"A", "B", "C", "D"};
    private final QuizController controller;
    private JLabel lblProgresso;
    private JLabel lblPontuacao;
    private JTextArea txtEnunciado;
    private JButton[] botoesAlternativas;

    public TelaQuiz(QuizController controller) {
        super("Debug do Milhão - Jogando");
        this.controller = controller;
        this.atualizarTela();
    }

    protected void montarConteudo() {
        JPanel painelTopo = new JPanel(new BorderLayout());
        painelTopo.setOpaque(false);
        this.lblProgresso = new JLabel();
        this.lblPontuacao = new JLabel();
        this.lblProgresso.setFont(FONTE_TEXTO);
        this.lblPontuacao.setFont(FONTE_TEXTO);
        painelTopo.add(this.lblProgresso, "West");
        painelTopo.add(this.lblPontuacao, "East");
        painelTopo.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        this.txtEnunciado = new JTextArea();
        this.txtEnunciado.setFont(FONTE_TITULO.deriveFont(20.0F));
        this.txtEnunciado.setLineWrap(true);
        this.txtEnunciado.setWrapStyleWord(true);
        this.txtEnunciado.setEditable(false);
        this.txtEnunciado.setFocusable(false);
        this.txtEnunciado.setOpaque(false);
        this.txtEnunciado.setForeground(Color.WHITE);
        this.txtEnunciado.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
        JScrollPane scrollEnunciado = new JScrollPane(this.txtEnunciado);
        scrollEnunciado.setBorder(BorderFactory.createEmptyBorder());
        scrollEnunciado.setOpaque(false);
        scrollEnunciado.getViewport().setOpaque(false);
        scrollEnunciado.setVerticalScrollBarPolicy(20);
        JPanel painelAlternativas = new JPanel(new GridLayout(4, 1, 10, 10));
        painelAlternativas.setOpaque(false);
        painelAlternativas.setBorder(BorderFactory.createEmptyBorder(10, 30, 30, 30));
        this.botoesAlternativas = new JButton[4];

        for(int i = 0; i < 4; ++i) {
            JButton botao = new JButton();
            botao.setFont(FONTE_BOTAO);
            botao.setHorizontalAlignment(2);
            botao.addActionListener((e) -> this.selecionarAlternativa(i));
            this.botoesAlternativas[i] = botao;
            painelAlternativas.add(botao);
        }

        this.setLayout(new BorderLayout());
        this.add(painelTopo, "North");
        this.add(scrollEnunciado, "Center");
        this.add(painelAlternativas, "South");
    }

    private void atualizarTela() {
        if (!this.controller.existeProximaPergunta()) {
            this.finalizarQuiz();
        } else {
            Pergunta atual = this.controller.obterPerguntaAtual();
            JLabel var10000 = this.lblProgresso;
            int var10001 = this.controller.getNumeroPerguntaAtual();
            var10000.setText("Pergunta " + var10001 + " de " + this.controller.getTotalPerguntas());
            this.lblPontuacao.setText(this.montarTextoPontuacao());
            this.txtEnunciado.setText(atual.getEnunciado());
            String[] alternativas = atual.getAlternativas();
            int quantidade = Math.min(4, alternativas.length);

            for(int i = 0; i < quantidade; ++i) {
                JButton var6 = this.botoesAlternativas[i];
                String var7 = ROTULOS[i];
                var6.setText(var7 + ")  " + alternativas[i]);
                this.botoesAlternativas[i].setEnabled(true);
                this.botoesAlternativas[i].setVisible(true);
            }

            for(int i = quantidade; i < 4; ++i) {
                this.botoesAlternativas[i].setVisible(false);
            }

        }
    }

    private String montarTextoPontuacao() {
        if (this.controller.getNumeroJogadores() == 1) {
            return "Pontuação: " + this.controller.getPontuacao(0);
        } else {
            int vez = this.controller.getJogadorDaVez();
            return String.format("Vez: %s  |  %s: %d   %s: %d", this.controller.getNomeJogador(vez - 1), this.controller.getNomeJogador(0), this.controller.getPontuacao(0), this.controller.getNomeJogador(1), this.controller.getPontuacao(1));
        }
    }

    private void selecionarAlternativa(int indice) {
        this.setBotoesHabilitados(false);
        Pergunta perguntaRespondida = this.controller.obterPerguntaAtual();
        boolean acertou = this.controller.responder(indice);
        String explicacao = perguntaRespondida.getExplicacao(indice);
        String titulo = acertou ? "Você acertou!" : "Você errou.";
        JTextArea areaMensagem = new JTextArea(explicacao != null ? explicacao : "");
        areaMensagem.setEditable(false);
        areaMensagem.setFocusable(false);
        areaMensagem.setLineWrap(true);
        areaMensagem.setWrapStyleWord(true);
        areaMensagem.setFont(FONTE_TEXTO);
        areaMensagem.setBackground(this.getBackground());
        areaMensagem.setPreferredSize(new Dimension(320, 100));
        JOptionPane.showMessageDialog(this, areaMensagem, titulo, 1);
        this.atualizarTela();
    }

    private void setBotoesHabilitados(boolean habilitado) {
        for(JButton botao : this.botoesAlternativas) {
            botao.setEnabled(habilitado);
        }

    }

    private void finalizarQuiz() {
        int total = this.controller.getTotalPerguntas();
        StringBuilder resultado = new StringBuilder("Quiz finalizado!\n");
        if (this.controller.getNumeroJogadores() == 1) {
            resultado.append("Você acertou ").append(this.controller.getPontuacao(0)).append(" de ").append(total).append(" perguntas.");
        } else {
            for(int i = 0; i < this.controller.getNumeroJogadores(); ++i) {
                resultado.append(this.controller.getNomeJogador(i)).append(": ").append(this.controller.getPontuacao(i)).append(" pontos\n");
            }
        }

        this.controller.salvarResultado();
        JOptionPane.showMessageDialog(this, resultado.toString(), "Fim de jogo", 1);
        this.dispose();
        SwingUtilities.invokeLater(() -> (new TelaMenu()).setVisible(true));
    }
}
