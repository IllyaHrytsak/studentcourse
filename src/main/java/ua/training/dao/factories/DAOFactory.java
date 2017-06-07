package ua.training.dao.factories;


import ua.training.dao.interfaces.CourseDAO;
import ua.training.dao.interfaces.UserDAO;
import ua.training.dao.interfaces.UserStoreDAO;
import ua.training.dao.interfaces.UserTypeDAO;

public abstract class DAOFactory {

    public static final int MYSQL = 1;

    public abstract CourseDAO getCourseDAO();

    public abstract UserDAO getUserDAO();

    public abstract UserStoreDAO getUserStoreDAO();

    public abstract UserTypeDAO getUserTypeDAO();

    public static DAOFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case MYSQL:
                return new MySqlDAOFactory();
            default:
                return null;
        }
    }


}
