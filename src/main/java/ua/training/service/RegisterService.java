package ua.training.service;


import ua.training.dao.factories.DAOFactory;
import ua.training.dao.interfaces.UserDAO;
import ua.training.entity.User;


public class RegisterService {

    private static RegisterService instance;

    private static final String loginRegex = "^[a-zA-Z][a-zA-Z0-9-_\\.]{1,34}$";
    private static final String passwordRegex = "^[\\w-\\.]{2,34}$";
    private static final String nameAndSurnameRegex = "^[A-Z][a-z\\s]{1,34}|[А-ЯІЇЁ][а-яіїё\\s]{1,34}$";
    private DAOFactory daoFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
    private UserDAO userDao = daoFactory.getUserDAO();

    private RegisterService() {
    }

    public static RegisterService getInstance() {
        if (instance == null) {
            instance = new RegisterService();
        }
        return instance;
    }

    public boolean inputStudent(String login, String password, String name, String surname) {
        User user = new User();
        if (!checkLoginAndPassword(login, password)) {
            return false;
        }
        user.setLogin(login);
        user.setPassword(password);

        if (userDao.findWhereUserLoginEquals(login) != null) {
            return false;
        }

        if (!checkNameAndSurname(name, surname)) {
            return false;
        }

        user.setName(name);
        user.setSurname(surname);
        return (userDao.insertUser(user) != -1);
    }

    private boolean checkLoginAndPassword(String login, String password) {
        return (login.matches(loginRegex)
                && password.matches(passwordRegex));
    }

    private boolean checkNameAndSurname(String name, String surname) {
        return (name.matches(nameAndSurnameRegex)
                && surname.matches(nameAndSurnameRegex));
    }



}
