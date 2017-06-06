package ua.training.dao.interfaces;


import ua.training.entity.User;
import java.util.List;

public interface UserDAO {

    List<User> findAll();
    Long insertUser(User user);
    User findUserWithUserId(Long userId);
    boolean updateUser(User user);
    User findWhereUserLoginEquals(String login);
    User findLecturerWithLoginAndPassword(String login, String password);
    User findStudentWithLoginAndPassword(String login, String password);

}
