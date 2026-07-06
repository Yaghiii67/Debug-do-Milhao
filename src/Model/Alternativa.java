package Model;

public class Alternativa {

    private final int id;
    private final int idPergunta;
    private final char letra;
    private final String texto;
    private final boolean correta;

    public Alternativa(int id, int idPergunta, char letra, String texto, boolean correta){
        this.id = id;
        this.idPergunta = idPergunta;
        this.letra = letra;
        this.texto = texto;
        this.correta = correta;
    }
    public int getId(){
        return id;
    }
    public int getIdPergunta(){
        return idPergunta;
    }
    public char getLetra(){
        return letra;
    }
    public String getTexto(){
        return texto;
    }
    public boolean isCorreta(){
        return correta;
    }

    @Override
    public String toString(){
        return letra + ") " + texto;
    }
}
