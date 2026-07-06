package Dao;

import java.util.List;

public interface Dao<T> {

    List<T> listarTodos();

    T buscarPorId(int id);

    void inserir(T objeto);

    void atualizar(T objeto);

    void deletar(int id);
}