package by.it.academy.project.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DAO <E> {

        Long create(E e) throws SQLException;
        Optional<E> read(Long id) throws SQLException;
        int update(E e) throws SQLException;
        int delete(Long id) throws SQLException;
        List<E> getAll() throws SQLException;

}
