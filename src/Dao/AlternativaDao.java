package Dao;

import Model.Alternativa;
import Util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlternativaDao implements Dao<Alternativa> {

    @Override
    public List<Alternativa> listarTodos() {
        List<Alternativa> alternativas = new ArrayList<>();
        String sql = "SELECT * FROM alternativas ORDER BY id_pergunta, letra";

        try (Connection conexao = Conexao.getConnection();
             Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                alternativas.add(mapearAlternativa(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar alternativas: " + e.getMessage(), e);
        }
        return alternativas;
    }

    public List<Alternativa> listarPorPergunta(int idPergunta) {
        List<Alternativa> alternativas = new ArrayList<>();
        String sql = "SELECT * FROM alternativas WHERE id_pergunta = ? ORDER BY letra";

        try (Connection conexao = Conexao.getConnection();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, idPergunta);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    alternativas.add(mapearAlternativa(rs));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar alternativas da pergunta " + idPergunta + ": " + e.getMessage(), e);
        }
        return alternativas;
    }

    @Override
    public Alternativa buscarPorId(int id) {
        String sql = "SELECT * FROM alternativas WHERE id_alternativa = ?";

        try (Connection conexao = Conexao.getConnection();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapearAlternativa(rs) : null;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar alternativa por id: " + e.getMessage(), e);
        }
    }

    @Override
    public void inserir(Alternativa objeto) {
        String sql = "INSERT INTO alternativas (id_pergunta, letra, texto, correta) VALUES (?, ?, ?, ?)";

        try (Connection conexao = Conexao.getConnection();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, objeto.getIdPergunta());
            ps.setString(2, String.valueOf(objeto.getLetra()));
            ps.setString(3, objeto.getTexto());
            ps.setBoolean(4, objeto.isCorreta());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir alternativa: " + e.getMessage(), e);
        }
    }

    @Override
    public void atualizar(Alternativa objeto) {
        String sql = "UPDATE alternativas SET letra = ?, texto = ?, correta = ? WHERE id_alternativa = ?";

        try (Connection conexao = Conexao.getConnection();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, String.valueOf(objeto.getLetra()));
            ps.setString(2, objeto.getTexto());
            ps.setBoolean(3, objeto.isCorreta());
            ps.setInt(4, objeto.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar alternativa: " + e.getMessage(), e);
        }
    }

    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM alternativas WHERE id_alternativa = ?";

        try (Connection conexao = Conexao.getConnection();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar alternativa: " + e.getMessage(), e);
        }
    }

    private Alternativa mapearAlternativa(ResultSet rs) throws SQLException {
        return new Alternativa(
                rs.getInt("id_alternativa"),
                rs.getInt("id_pergunta"),
                rs.getString("letra").charAt(0),
                rs.getString("texto"),
                rs.getBoolean("correta")
        );
    }
}