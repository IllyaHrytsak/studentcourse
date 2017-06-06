package ua.training.dao.interfaces;


import ua.training.entity.User;
import java.util.List;

/**
 * Data access object for User table
 *
 * @author Illya Hrytsak
 */
public interface UserDAO {

    /**
     * Finds all users in database
     * @return list of all users
     */
    List<User> findAll();

    /**
     * Tries to insert user in database
     * @param user User
     * @return userId of inserted user
     */
    Long insertUser(User user);

    /**
     * Finds user by userId
     * @param userId userId of user
     * @return user
     */
    User findUserWithUserId(Long userId);

    /**
     * Tries to update user in database
     * @param user User
     * @return true if update was successful or false if not
     */
    boolean updateUser(User user);

    /**
     * Finds user by login
     * @param login Login of user
     * @return user
     */
    User findWhereUserLoginEquals(String login);

    /**
     * Finds lecturer by login and password
     * @param login Login of user
     * @param password Password of user
     * @return lecturer
     */
    User findLecturerWithLoginAndPassword(String login, String password);

    /**
     * Finds student by login and password
     * @param login Login of user
     * @param password Password of user
     * @return student
     */
    User findStudentWithLoginAndPassword(String login, String password);

}
