package ua.training.dao.implementations;


import ua.training.connection.ConnectionPool;
import ua.training.dao.interfaces.CourseDAO;
import ua.training.entity.Course;
import ua.training.entity.User;
import ua.training.entity.UserType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class MySqlCourseDAO implements CourseDAO {

    private static MySqlCourseDAO instance;
    private static final Logger LOGGER = Logger.getLogger(MySqlCourseDAO.class.getSimpleName());

    private static final int COLUMN_COURSE_ID = 1;
    private static final int COLUMN_COURSE_NAME = 2;
    private static final int COLUMN_USER_TYPE = 2;
    private static final int USER_TYPE_STUDENT = 2;

    private MySqlCourseDAO() {}

    public static synchronized MySqlCourseDAO getInstance() {
        if (instance == null) {
            instance = new MySqlCourseDAO();
        }
        return instance;
    }

    @Override
    public List<Course> findAll() {
        List<Course> courses = new ArrayList<>();
        final String sql = "SELECT * FROM COURSE;";
        Connection connection = ConnectionPool.getInstance().getConnection();
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                courses.add(createCourse(resultSet));
            }
            LOGGER.info("List of all courses was found");
        } catch (SQLException e) {
            LOGGER.warning("exception" + e);
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().putBackConnection(connection);
        }
        return courses;
    }



    @Override
    public Long insertCourse(Course course) {
        final String sql = "INSERT INTO COURSE(course_name) values(?)";
        Connection connection = ConnectionPool.getInstance().getConnection();
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, course.getNameCourse());
            int courseId = preparedStatement.executeUpdate();
            LOGGER.info("Course was inserted " + course.getNameCourse());
            return (long) courseId;
        } catch (SQLException e) {
            LOGGER.warning("exception" + e);
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().putBackConnection(connection);
        }
        return -1L;
    }


    @Override
    public Course findCourseWithCourseId(Long courseId) {
        final String sql = "SELECT * FROM COURSE WHERE course_id=?";
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, courseId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    LOGGER.info("Course was found with id " + courseId);
                    return createCourse(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.warning("exception" + e);
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().putBackConnection(connection);
        }
        return null;
    }

    @Override
    public Course findCourseWithCourseName(String courseName) {
        final String sql = "SELECT * FROM COURSE WHERE course_name=?";
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, courseName);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                if (resultSet.next()) {
                    LOGGER.info("Course was found with name " + courseName);
                    return createCourse(resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.warning("exception" + e);
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().putBackConnection(connection);
        }
        return null;
    }

    @Override
    public boolean updateCourse(Course course) {
        long courseId = course.getCourseId();
        final String sql = "UPDATE COURSE SET course_name=? WHERE course_id=?;";
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, course.getNameCourse());
            preparedStatement.setLong(2, courseId);
            int i = preparedStatement.executeUpdate();
            if (i != -1) {
                LOGGER.info("Course was updated " + course.getNameCourse());
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
    public List<User> findWhereUserIsStudent(Long courseId) {
        List<User> users = new ArrayList<>();
        final String sql = "SELECT * FROM USER WHERE user_id IN " +
                "(SELECT user_id FROM USER_STORE WHERE course_id=?) AND user_type_id=?;";
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setLong(1, courseId);
            preparedStatement.setLong(2, USER_TYPE_STUDENT);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    users.add(createUser(resultSet));
                }
                LOGGER.info("Students were found for courseId " + courseId);
            }
        } catch (SQLException e) {
            LOGGER.warning("exception" + e);
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().putBackConnection(connection);
        }
        return users;
    }



    @Override
    public List<Course> findStudentCourses(Long userId) {
        List<Course> courses = new ArrayList<>();
        final String sql = "SELECT * FROM COURSE WHERE course_id IN " +
                "(SELECT course_id FROM USER_STORE WHERE user_id=?);";
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setLong(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()) {
                    courses.add(createCourse(resultSet));
                }
                LOGGER.info("Courses were found for userId " + userId);
            }
        } catch (SQLException e) {
            LOGGER.warning("exception" + e);
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().putBackConnection(connection);
        }
        return courses;
    }



    private Course createCourse(ResultSet resultSet) {
        Course course = new Course();
        try {
            course.setCourseId(resultSet.getLong(COLUMN_COURSE_ID));
            course.setNameCourse(resultSet.getString(COLUMN_COURSE_NAME));
            setUserListToCourse(resultSet.getLong(COLUMN_COURSE_ID), course);
            LOGGER.info("Course was created " + course.getNameCourse());
        } catch (SQLException e) {
            LOGGER.warning("exception" + e);
            e.printStackTrace();
        }
        return course;
    }

    private void setUserListToCourse(Long courseId, Course course) {
        List<User> users = new ArrayList<>();
        final String sql = "SELECT * FROM USER WHERE user_id IN " +
                "(SELECT user_id FROM USER_STORE WHERE course_id=?);";
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql);) {
            preparedStatement.setLong(1, courseId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    users.add(createUser(resultSet));
                }
                LOGGER.info("Users were added for course " + course.getNameCourse());
            }
        } catch (SQLException e) {
            LOGGER.warning("exception" + e);
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().putBackConnection(connection);
        }
        course.setUserStore(users);
    }

    private User createUser(ResultSet resultSet) {
        User user = new User();
        try {
            user.setUserId(resultSet.getLong(1));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            user.setName(resultSet.getString("name"));
            user.setSurname(resultSet.getString("surname"));
            setUserType(resultSet.getLong(COLUMN_USER_TYPE), user);
            LOGGER.info("User was created " + user.getLogin());
        } catch (SQLException e) {
            LOGGER.warning("exception" + e);
            e.printStackTrace();
        }
        return user;
    }


    private void setUserType(Long userTypeId, User user) {
        String sql = "SELECT user_type FROM USER_TYPE WHERE user_type_id=?;";
        Connection connection = ConnectionPool.getInstance().getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, userTypeId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                UserType userType = new UserType();
                if (resultSet.next()) {
                    userType.setUserTypeName(resultSet.getString("user_type"));
                    user.setUserType(userType);
                    LOGGER.info("User type was added for user " + user.getLogin());
                }
            }
        } catch (SQLException e) {
            LOGGER.warning("exception" + e);
            e.printStackTrace();
        } finally {
            ConnectionPool.getInstance().putBackConnection(connection);
        }
    }

}

