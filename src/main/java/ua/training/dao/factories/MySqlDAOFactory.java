package ua.training.dao.factories;


import ua.training.dao.implementations.MySqlCourseDAO;
import ua.training.dao.implementations.MySqlUserDAO;
import ua.training.dao.implementations.MySqlUserStoreDAO;
import ua.training.dao.implementations.MySqlUserTypeDAO;
import ua.training.dao.interfaces.*;

public class MySqlDAOFactory extends DAOFactory {


    @Override
    public CourseDAO getCourseDAO() {
        return MySqlCourseDAO.getInstance();
    }

    @Override
    public UserDAO getUserDAO() {
        return MySqlUserDAO.getInstance();
    }

    @Override
    public UserStoreDAO getUserStoreDAO() {
        return MySqlUserStoreDAO.getInstance();
    }

    @Override
    public UserTypeDAO getUserTypeDAO() {
        return MySqlUserTypeDAO.getInstance();
    }
}
