package ua.training.command;


import ua.training.entity.User;
import ua.training.manager.Config;
import ua.training.service.LoginService;
import ua.training.service.Service;

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
        LoginService loginService = Service.getInstance().getLoginService();
        session.setAttribute("user", user);
        session.setAttribute("courses", user.getCourseStore());
        session.setAttribute("students", loginService.getStudentsWhereCourse(user.getCourseStore()));
    }

    public void setStudentPage(HttpSession session, User user) {
        LoginService loginService = Service.getInstance().getLoginService();
        session.setAttribute("user", user);
        session.setAttribute("availableCourses",
                loginService.getAvailableCourses());
        session.setAttribute("studentCourses",
                loginService.getStudentCourses(user.getUserId()));
    }

    public String checkUser(HttpSession session, User user) {
        String page = null;
        LoginService loginService = Service.getInstance().getLoginService();
        String login = user.getLogin();
        String password = user.getPassword();
        if ((user = loginService.findLecturer(login, password)) != null) {
            setLecturerPage(session, user);
            page = Config.getInstance().getProperty(Config.LECTURER_MAIN);
        } else {
            user = loginService.findStudent(login, password);
            setStudentPage(session, user);
            page = Config.getInstance().getProperty(Config.STUDENT_MAIN);
        }
        return page;
    }

}
