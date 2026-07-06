package Dao;

import Model.Tema;
import Util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TemaDao implements Dao<Tema> {

    @Override
    public List<Tema> listarTodos() {
        List<Tema> temas = new ArrayList<>();
        String sql = "SELECT * FROM temas ORDER BY nome";

        try (Connection conexao = Conexao.getConnection();
             Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                temas.add(mapearTema(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar temas: " + e.getMessage(), e);
        }
        return temas;
    }

    @Override
    public Tema buscarPorId(int id) {
        String sql = "SELECT * FROM temas WHERE id_tema = ?";

        try (Connection conexao = Conexao.getConnection();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapearTema(rs) : null;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar tema por id: " + e.getMessage(), e);
        }
    }

    @Override
    public void inserir(Tema objeto) {
        String sql = "INSERT INTO temas (nome, descricao) VALUES (?, ?)";

        try (Connection conexao = Conexao.getConnection();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, objeto.getNome());
            ps.setString(2, objeto.getDescricao());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir tema: " + e.getMessage(), e);
        }
    }

    @Override
    public void atualizar(Tema objeto) {
        String sql = "UPDATE temas SET nome = ?, descricao = ? WHERE id_tema = ?";

        try (Connection conexao = Conexao.getConnection();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setString(1, objeto.getNome());
            ps.setString(2, objeto.getDescricao());
            ps.setInt(3, objeto.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar tema: " + e.getMessage(), e);
        }
    }

    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM temas WHERE id_tema = ?";

        try (Connection conexao = Conexao.getConnection();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar tema: " + e.getMessage(), e);
        }
    }

    private Tema mapearTema(ResultSet rs) throws SQLException {
        return new Tema(rs.getInt("id_tema"), rs.getString("nome"), rs.getString("descricao"));
    }
}