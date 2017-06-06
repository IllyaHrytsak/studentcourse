package ua.training.dao.implementations;


import ua.training.connection.ConnectionPool;
import ua.training.dao.interfaces.UserStoreDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class MySqlUserStoreDAO implements UserStoreDAO {

    private static MySqlUserStoreDAO instance;
    private static final int COLUMN_MARK = 1;
    private static final Logger LOGGER = Logger.getLogger(MySqlUserStoreDAO.class.getSimpleName());

    private MySqlUserStoreDAO() {}

    public static synchronized MySqlUserStoreDAO getInstance() {
        if (instance == null) {
            instance = new MySqlUserStoreDAO();
        }
        return instance;
    }

    @Override
    public boolean insertMark(Long courseId, Long userId, Integer mark) {
        final String sql = "UPDATE USER_STORE SET mark=? WHERE course_id=? AND user_id=?;";
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, mark);
            preparedStatement.setLong(2, courseId);
            preparedStatement.setLong(3, userId);
            int code = preparedStatement.executeUpdate();
            if (code != -1) {
                LOGGER.info("Mark was inserted to user with id " + userId);
                return true;
            }
        } catch (SQLException e) {
            LOGGER.warning("exception" + e);
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().putBackConnection(connection);
        }
        return false;
    }

    @Override
    public Long insertStudentIntoCourse(Long courseId, Long userId) {
        final String sql = "INSERT INTO USER_STORE(course_id, user_id) VALUES(?,?);";
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, courseId);
            preparedStatement.setLong(2, userId);
            int code = preparedStatement.executeUpdate();
            LOGGER.info("Student was inserted into course with id " + courseId);
            return (long) code;
        } catch (SQLException e) {
            LOGGER.warning("exception" + e);
            e.printStackTrace();
        }  finally {
            ConnectionPool.getInstance().putBackConnection(connection);
        }
        return  -1L;
    }

    @Override
    public Integer findMark(Long courseId, Long userId) {
        String sql = "SELECT mark FROM USER_STORE WHERE course_id=? AND user_id=?";
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, courseId);
            preparedStatement.setLong(2, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    LOGGER.info("Mark was found for user with id " + userId);
                    return resultSet.getInt(COLUMN_MARK);
                }
            }
        } catch (SQLException e) {
            LOGGER.warning("exception" + e);
            e.printStackTrace();
        }  finally {
            ConnectionPool.getInstance().putBackConnection(connection);
        }
        return null;
    }

    public boolean removeStudent(Long courseId, Long userId) {
        String sql = "DELETE FROM USER_STORE WHERE course_id=? AND user_id=?";
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, courseId);
            preparedStatement.setLong(2, userId);
            int code = preparedStatement.executeUpdate();
            if (code != -1) {
                LOGGER.info("Student was removed from the course with id " + courseId);
                return true;
            }
        } catch (SQLException e) {
            LOGGER.warning("exception" + e);
            e.printStackTrace();
        }  finally {
            ConnectionPool.getInstance().putBackConnection(connection);
        }
        return false;
    }



}
