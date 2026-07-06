package Dao;

import Model.Pergunta;
import Util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PerguntaDao implements Dao<Pergunta> {

    private final Connection conexao;

    public PerguntaDao() {
        try {
            this.conexao = Conexao.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar ao banco de dados: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Pergunta> listarTodos() {
        List<Pergunta> perguntas = new ArrayList<>();
        String sql = "SELECT * FROM perguntas ORDER BY id_pergunta";

        try (Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                perguntas.add(mapearPergunta(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar perguntas: " + e.getMessage(), e);
        }
        return perguntas;
    }

    @Override
    public Pergunta buscarPorId(int id) {
        String sql = "SELECT * FROM perguntas WHERE id_pergunta = ?";

        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapearPergunta(rs);
                }
                return null;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar pergunta por id: " + e.getMessage(), e);
        }
    }

    public List<Pergunta> buscarPorTemaEDificuldade(int idTema, String dificuldade) {
        List<Pergunta> perguntas = new ArrayList<>();
        String sql = "SELECT * FROM perguntas WHERE id_tema = ? AND dificuldade = ?";

        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, idTema);
            ps.setString(2, dificuldade);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    perguntas.add(mapearPergunta(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar perguntas por tema e dificuldade: " + e.getMessage(), e);
        }
        return perguntas;
    }

    @Override
    public void inserir(Pergunta pergunta) {
        throw new UnsupportedOperationException(
                "Método 'inserir' precisa ser reescrito para o novo schema (tabela alternativas separada).");
    }

    @Override
    public void atualizar(Pergunta pergunta) {
        throw new UnsupportedOperationException(
                "Método 'atualizar' precisa ser reescrito para o novo schema (tabela alternativas separada).");
    }

    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM perguntas WHERE id_pergunta = ?";

        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar pergunta: " + e.getMessage(), e);
        }
    }

    private Pergunta mapearPergunta(ResultSet rs) throws SQLException {
        int idPergunta = rs.getInt("id_pergunta");
        String enunciado = rs.getString("enunciado");

        String[] alternativas = new String[4];
        String[] explicacoes = new String[4];
        int indiceCorreta = -1;

        String sqlAlternativas = "SELECT letra, texto, correta, explicacao FROM alternativas " +
                "WHERE id_pergunta = ? ORDER BY letra";

        try (PreparedStatement ps = conexao.prepareStatement(sqlAlternativas)) {
            ps.setInt(1, idPergunta);

            try (ResultSet rsAlt = ps.executeQuery()) {
                int i = 0;
                while (rsAlt.next() && i < 4) {
                    alternativas[i] = rsAlt.getString("texto");
                    explicacoes[i] = rsAlt.getString("explicacao");
                    if (rsAlt.getBoolean("correta")) {
                        indiceCorreta = i;
                    }
                    i++;
                }
            }
        }

        if (indiceCorreta == -1) {
            throw new IllegalStateException(
                    "Pergunta id=" + idPergunta + " não possui alternativa marcada como correta.");
        }

        return new Pergunta(idPergunta, enunciado, alternativas, explicacoes, indiceCorreta);
    }

    public void fecharConexao() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }

}