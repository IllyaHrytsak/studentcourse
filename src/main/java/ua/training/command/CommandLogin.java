package ua.training.command;


import ua.training.entity.User;
import ua.training.manager.Config;
import ua.training.manager.Message;
import ua.training.service.LoginService;
import ua.training.service.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CommandLogin implements ICommand {

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        session.setMaxInactiveInterval(30);
        String page = null;
        User user = null;
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        LoginService loginService = Service.getInstance().getLoginService();
        CommandHelper commandHelper = CommandHelper.getInstance();
        if ((user = loginService.findLecturer(login, password)) != null) {
            commandHelper.setLecturerPage(session, user);
            page = Config.getInstance().getProperty(Config.LECTURER_MAIN);
        } else if ((user = loginService.findStudent(login, password)) != null) {
            commandHelper.setStudentPage(session, user);
            page = Config.getInstance().getProperty(Config.STUDENT_MAIN);
        } else {
            request.setAttribute("error", Message.getInstance().getProperty(Message.LOGIN_ERROR));
            page = Config.getInstance().getProperty(Config.ERROR);
        }

        return page;
    }
}
