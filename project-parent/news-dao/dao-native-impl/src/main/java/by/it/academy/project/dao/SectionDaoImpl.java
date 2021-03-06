package by.it.academy.project.dao;

import by.it.academy.project.model.Section;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SectionDaoImpl extends AbstractDao implements SectionDao {

    private static final SectionDaoImpl INSTANCE = new SectionDaoImpl();

    public static SectionDao getINSTANCE() {
        return INSTANCE;
    }

    private final static String SELECT_ALL = "SELECT * FROM section;";
    private final static String SELECT_BY_ID = "SELECT * FROM section WHERE S_id=?";


    protected SectionDaoImpl() {
        super(LoggerFactory.getLogger(SectionDaoImpl.class));
    }


    @Override
    public Long create(Section section) throws SQLException {
        return null;
    }

    @Override
    public Optional<Section> read(Long id) throws SQLException {

        ResultSet resultSet = null;
        Optional<Section> result = Optional.empty();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {

            statement.setLong(1, id);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                result = Optional.of(Mapper.mapSection(resultSet));
            }

        } finally {
            closeQuietly(resultSet);
        }
        return result;
    }

    @Override
    public int update(Section section) throws SQLException {
        return 0;
    }

    @Override
    public int delete(Long id) throws SQLException {
        return 0;
    }

    @Override
    public List<Section> getAll() throws SQLException {

        ResultSet resultSet = null;
        List<Section> result = new ArrayList<>();

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(SELECT_ALL)) {

            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                result.add(Mapper.mapSection(resultSet));
            }

        } finally {
            closeQuietly(resultSet);
        }
        return result;
    }

}
