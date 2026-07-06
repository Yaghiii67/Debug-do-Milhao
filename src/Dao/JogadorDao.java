package Dao;

import Model.Jogador;
import Util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JogadorDao implements Dao<Jogador> {

    @Override
    public List<Jogador> listarTodos() {
        List<Jogador> jogadores = new ArrayList<>();
        String sql = "SELECT * FROM jogadores ORDER BY id_jogador";

        try (Connection conexao = Conexao.getConnection();
             Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                jogadores.add(mapearJogador(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar jogadores: " + e.getMessage(), e);
        }
        return jogadores;
    }

    @Override
    public Jogador buscarPorId(int id) {
        String sql = "SELECT * FROM jogadores WHERE id_jogador = ?";

        try (Connection conexao = Conexao.getConnection();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? mapearJogador(rs) : null;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao buscar jogador por id: " + e.getMessage(), e);
        }
    }

    @Override
    public void inserir(Jogador objeto) {
        String sql = "INSERT INTO jogadores (pontuacao) VALUES (?)";

        try (Connection conexao = Conexao.getConnection();
             PreparedStatement ps = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, objeto.getPontuacao());
            ps.executeUpdate();

            try (ResultSet chavesGeradas = ps.getGeneratedKeys()) {
                if (chavesGeradas.next()) {
                    objeto.setId(chavesGeradas.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao inserir jogador: " + e.getMessage(), e);
        }
    }

    @Override
    public void atualizar(Jogador objeto) {
        String sql = "UPDATE jogadores SET pontuacao = ? WHERE id_jogador = ?";

        try (Connection conexao = Conexao.getConnection();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, objeto.getPontuacao());
            ps.setInt(2, objeto.getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar jogador: " + e.getMessage(), e);
        }
    }

    @Override
    public void deletar(int id) {
        String sql = "DELETE FROM jogadores WHERE id_jogador = ?";

        try (Connection conexao = Conexao.getConnection();
             PreparedStatement ps = conexao.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao deletar jogador: " + e.getMessage(), e);
        }
    }

    private Jogador mapearJogador(ResultSet rs) throws SQLException {
        return new Jogador(rs.getInt("id_jogador"), rs.getInt("pontuacao"));
    }
}