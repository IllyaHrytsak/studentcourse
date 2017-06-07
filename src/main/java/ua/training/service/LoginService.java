package ua.training.service;


import ua.training.dao.factories.DAOFactory;
import ua.training.dao.interfaces.CourseDAO;
import ua.training.dao.interfaces.UserDAO;
import ua.training.entity.Course;
import ua.training.entity.User;

import java.util.ArrayList;
import java.util.List;

public class LoginService {

    private static LoginService instance;

    private DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
    private UserDAO userDao = daoFactory.getUserDAO();
    private CourseDAO courseDAO = daoFactory.getCourseDAO();

    private LoginService() {
    }

    public static LoginService getInstance() {
        if (instance == null) {
            instance = new LoginService();
        }
        return instance;
    }

    public User findLecturer(String login, String password) {
        return userDao.findLecturerWithLoginAndPassword(login, password);
    }

    public User findStudent(String login, String password) {
        return userDao.findStudentWithLoginAndPassword(login, password);
    }

    public List<User> getStudentsWhereCourse(List<Course> courses) {
        if (courses.isEmpty()) {
            return new ArrayList<>();
        }
        return courseDAO.findWhereUserIsStudent(courses.get(0).getCourseId());
    }

    public List<Course> getAvailableCourses() {
        return courseDAO.findAll();
    }

    public List<Course> getStudentCourses(Long userId) {
        return courseDAO.findStudentCourses(userId);
    }


}
