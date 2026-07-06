package View;

import Controller.QuizController;
import Dao.TemaDao;
import Model.Tema;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TelaTemas extends TelaBase {

    private final int numeroJogadores;
    private JComboBox<Tema> comboTemas;
    private JComboBox<String> comboDificuldade;

    public TelaTemas(int numeroJogadores) {
        super("Debug do Milhão - Tema e Dificuldade");
        this.numeroJogadores = numeroJogadores;
    }

    @Override
    protected void montarConteudo() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setBorder(BorderFactory.createEmptyBorder(45, 60, 45, 60));

        JLabel titulo = new JLabel("Escolha o tema e a dificuldade");
        titulo.setFont(FONTE_TITULO.deriveFont(20f));
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblTema = new JLabel("Tema:");
        lblTema.setFont(FONTE_TEXTO);
        lblTema.setAlignmentX(Component.CENTER_ALIGNMENT);

        List<Tema> temas = new TemaDao().listarTodos();
        comboTemas = new JComboBox<>(temas.toArray(new Tema[0]));
        comboTemas.setFont(FONTE_TEXTO);
        comboTemas.setMaximumSize(new Dimension(380, 32));
        comboTemas.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblDificuldade = new JLabel("Dificuldade:");
        lblDificuldade.setFont(FONTE_TEXTO);
        lblDificuldade.setAlignmentX(Component.CENTER_ALIGNMENT);

        comboDificuldade = new JComboBox<>(new String[]{"Facil", "Medio", "Dificil"});
        comboDificuldade.setFont(FONTE_TEXTO);
        comboDificuldade.setMaximumSize(new Dimension(380, 32));
        comboDificuldade.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btnComecar = new JButton("Começar");
        JButton btnVoltar = new JButton("Voltar");
        for (JButton botao : new JButton[]{btnComecar, btnVoltar}) {
            botao.setFont(FONTE_BOTAO);
            botao.setAlignmentX(Component.CENTER_ALIGNMENT);
            botao.setMaximumSize(new Dimension(200, 40));
        }

        btnComecar.addActionListener(e -> iniciarJogo());
        btnVoltar.addActionListener(e -> voltar());

        painel.add(titulo);
        painel.add(Box.createRigidArea(new Dimension(0, 35)));
        painel.add(lblTema);
        painel.add(Box.createRigidArea(new Dimension(0, 8)));
        painel.add(comboTemas);
        painel.add(Box.createRigidArea(new Dimension(0, 25)));
        painel.add(lblDificuldade);
        painel.add(Box.createRigidArea(new Dimension(0, 8)));
        painel.add(comboDificuldade);
        painel.add(Box.createRigidArea(new Dimension(0, 35)));
        painel.add(btnComecar);
        painel.add(Box.createRigidArea(new Dimension(0, 12)));
        painel.add(btnVoltar);

        add(painel);
    }

    private void iniciarJogo() {
        Tema temaSelecionado = (Tema) comboTemas.getSelectedItem();
        String dificuldadeSelecionada = (String) comboDificuldade.getSelectedItem();

        if (temaSelecionado == null) {
            JOptionPane.showMessageDialog(this,
                    "Nenhum tema cadastrado no banco de dados.",
                    "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        QuizController controller = new QuizController(
                temaSelecionado.getId(), dificuldadeSelecionada, numeroJogadores);

        if (!controller.existeProximaPergunta()) {
            JOptionPane.showMessageDialog(this,
                    "Não há perguntas cadastradas para o tema \"" + temaSelecionado.getNome() +
                            "\" na dificuldade \"" + dificuldadeSelecionada + "\".",
                    "Sem perguntas", JOptionPane.WARNING_MESSAGE);
            return;
        }

        dispose();
        SwingUtilities.invokeLater(() -> new TelaQuiz(controller).setVisible(true));
    }

    private void voltar() {
        dispose();
        SwingUtilities.invokeLater(() -> new TelaModo().setVisible(true));
    }
}
    private void selecionarAlternativa(int indice) {
        setBotoesHabilitados(false);

        Pergunta perguntaRespondida = controller.obterPerguntaAtual();
        boolean acertou = controller.responder(indice);
        String explicacao = perguntaRespondida.getExplicacao(indice);

        String titulo = acertou ? "Você acertou!" : "Você errou.";

        JTextArea areaMensagem = new JTextArea(explicacao != null ? explicacao : "");
        areaMensagem.setEditable(false);
        areaMensagem.setFocusable(false);
        areaMensagem.setLineWrap(true);
        areaMensagem.setWrapStyleWord(true);
        areaMensagem.setFont(FONTE_TEXTO);
        areaMensagem.setBackground(getBackground());
        areaMensagem.setPreferredSize(new Dimension(320, 100));

        JOptionPane.showMessageDialog(this, areaMensagem, titulo, JOptionPane.INFORMATION_MESSAGE);

        atualizarTela();
    }
