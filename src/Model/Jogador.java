package Model;

public class Jogador {

    private int id;
    private int pontuacao;

    public Jogador(String nomesJogadore, int pontuacao){
        this.pontuacao = pontuacao;
    }

    public Jogador(int id, int pontuacao){
        this.id = id;
        this.pontuacao = pontuacao;
    }

    public int getId(){
        return id;
    }
    public int getPontuacao(){
        return pontuacao;
    }
    public void setId(int id){
        this.id = id;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }

    @Override
    public String toString(){
        return "Jogador{id =" + id + ", pontuacao =" + pontuacao + "}";
    }
}
