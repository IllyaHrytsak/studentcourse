package ua.training.command.implementation;


import ua.training.command.Command;
import ua.training.command.HelperCommand;
import ua.training.entity.User;
import ua.training.manager.Config;
import ua.training.manager.Message;
import ua.training.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginCommand implements Command {

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String SESSION_TIME = Config.getInstance().getProperty(Config.SESSION_TIME);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        int sessionTime = Integer.parseInt(SESSION_TIME);
        session.setMaxInactiveInterval(sessionTime);
        String page = null;
        User user = null;
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        UserService userService = UserService.getInstance();
        HelperCommand helperCommand = HelperCommand.getInstance();
        if ((user = userService.findLecturer(login, password)) != null) {
            helperCommand.setLecturerPage(session, user);
            page = Config.getInstance().getProperty(Config.LECTURER_MAIN);
        } else if ((user = userService.findStudent(login, password)) != null) {
            helperCommand.setStudentPage(session, user);
            page = Config.getInstance().getProperty(Config.STUDENT_MAIN);
        } else {
            request.setAttribute("error", Message.getInstance().getProperty(Message.LOGIN_ERROR));
            page = Config.getInstance().getProperty(Config.LOGIN);
        }

        return page;
    }
}
