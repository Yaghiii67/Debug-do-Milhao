package Controller;

import Dao.JogadorDao;
import Dao.PerguntaDao;
import Model.Jogador;
import Model.Pergunta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizController {

    private final PerguntaDao perguntaDao;
    private final JogadorDao jogadorDao;

    private final int numeroJogadores;
    private final String[] nomesJogadores;
    private List<Pergunta> perguntas;
    private int indiceAtual;
    private final int[] pontuacoes;
    private int jogadorDaVez;

    public QuizController(int idTema, String dificuldade, int numeroJogadores) {
        this(idTema, dificuldade, numeroJogadores, gerarNomesPadrao(numeroJogadores));
    }

    public QuizController(int idTema, String dificuldade, int numeroJogadores, String[] nomesJogadores) {
        if (numeroJogadores != 1 && numeroJogadores != 2) {
            throw new IllegalArgumentException("Número de jogadores deve ser 1 ou 2.");
        }
        if (nomesJogadores == null || nomesJogadores.length != numeroJogadores) {
            throw new IllegalArgumentException("Quantidade de nomes não corresponde ao número de jogadores.");
        }

        this.perguntaDao = new PerguntaDao();
        this.jogadorDao = new JogadorDao();
        this.numeroJogadores = numeroJogadores;
        this.nomesJogadores = nomesJogadores;
        this.pontuacoes = new int[numeroJogadores];
        this.jogadorDaVez = 0;
        carregarPerguntas(idTema, dificuldade);
    }

    private static String[] gerarNomesPadrao(int numeroJogadores) {
        String[] nomes = new String[numeroJogadores];
        for (int i = 0; i < numeroJogadores; i++) {
            nomes[i] = "Jogador " + (i + 1);
        }
        return nomes;
    }

    private void carregarPerguntas(int idTema, String dificuldade) {
        List<Pergunta> resultado = perguntaDao.buscarPorTemaEDificuldade(idTema, dificuldade);
        this.perguntas = (resultado != null) ? new ArrayList<>(resultado) : new ArrayList<>();
        Collections.shuffle(this.perguntas);
        this.indiceAtual = 0;
    }

    public boolean existeProximaPergunta() {
        return indiceAtual < perguntas.size();
    }

    public Pergunta obterPerguntaAtual() {
        if (!existeProximaPergunta()) {
            throw new IllegalStateException("Não há mais perguntas.");
        }
        return perguntas.get(indiceAtual);
    }

    public boolean responder(int indiceAlternativaEscolhida) {
        Pergunta atual = obterPerguntaAtual();

        if (indiceAlternativaEscolhida < 0 || indiceAlternativaEscolhida > 3) {
            throw new IllegalArgumentException("Índice de alternativa inválido: " + indiceAlternativaEscolhida);
        }

        boolean acertou = atual.verificarResposta(indiceAlternativaEscolhida);

        if (acertou) {
            pontuacoes[jogadorDaVez]++;
        }

        indiceAtual++;

        if (numeroJogadores == 2) {
            jogadorDaVez = (jogadorDaVez + 1) % 2;
        }

        return acertou;
    }

    public int getJogadorDaVez() {
        return jogadorDaVez + 1;
    }

    public int getPontuacao(int jogador) {
        return pontuacoes[jogador];
    }

    public String getNomeJogador(int jogador) {
        return nomesJogadores[jogador];
    }

    public int getNumeroJogadores() {
        return numeroJogadores;
    }

    public int getTotalPerguntas() {
        return perguntas.size();
    }

    public int getNumeroPerguntaAtual() {
        return Math.min(indiceAtual + 1, perguntas.size());
    }

    public void salvarResultado() {
        for (int i = 0; i < numeroJogadores; i++) {
            jogadorDao.inserir(new Jogador(nomesJogadores[i], pontuacoes[i]));
        }
    }
}