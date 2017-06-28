package ua.training.command;


import ua.training.entity.User;
import ua.training.manager.Config;
import ua.training.service.UserService;

import javax.servlet.http.HttpSession;

public class HelperCommand {

    private static HelperCommand instance;

    private HelperCommand() {
    }

    public static HelperCommand getInstance() {
        if (instance == null) {
            instance = new HelperCommand();
        }
        return instance;
    }

    public void setLecturerPage(HttpSession session, User user) {
        UserService userService = UserService.getInstance();
        session.setAttribute("user", user);
        session.setAttribute("courses", user.getCourseStore());
        session.setAttribute("students", userService.getStudentsWhereCourse(user.getCourseStore()));
    }

    public void setStudentPage(HttpSession session, User user) {
        UserService userService = UserService.getInstance();
        session.setAttribute("user", user);
        session.setAttribute("availableCourses",
                userService.getAvailableCourses());
        session.setAttribute("studentCourses",
                userService.getStudentCourses(user.getUserId()));
    }

    public String checkUser(HttpSession session, User user) {
        String page = null;
        UserService userService = UserService.getInstance();
        String login = user.getLogin();
        String password = user.getPassword();
        if ((user = userService.findLecturer(login, password)) != null) {
            setLecturerPage(session, user);
            page = Config.getInstance().getProperty(Config.LECTURER_MAIN);
        } else {
            user = userService.findStudentByLogin(login);
            setStudentPage(session, user);
            page = Config.getInstance().getProperty(Config.STUDENT_MAIN);
        }
        return page;
    }

}
