package Model;

public class Pergunta {

    private final int id;
    private final String enunciado;
    private final String[] alternativas;
    private final int indiceRespostaCorreta;

    public Pergunta(int id, String enunciado, String[] alternativas, int indiceRespostaCorreta) {
        if (alternativas.length != 4) {
            throw new IllegalArgumentException("Uma pergunta deve ter exatamente 4 alternativas.");
        }
        this.id = id;
        this.enunciado = enunciado;
        this.alternativas = alternativas;
        this.indiceRespostaCorreta = indiceRespostaCorreta;
    }

    public int getId() {
        return id;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public String[] getAlternativas() {
        return alternativas;
    }

    public String getAlternativa(int indice) {
        return alternativas[indice];
    }

    public boolean verificarResposta(int indiceEscolhido) {
        return indiceEscolhido == indiceRespostaCorreta;
    }

    public int getIndiceRespostaCorreta() {
        return indiceRespostaCorreta;
    }

    @Override
    public String toString() {
        return "Pergunta{id=" + id + ", enunciado='" + enunciado + "'}";
    }
}