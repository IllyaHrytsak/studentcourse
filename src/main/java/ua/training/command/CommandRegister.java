package ua.training.command;


import ua.training.manager.Config;
import ua.training.manager.Message;
import ua.training.service.RegisterService;
import ua.training.service.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandRegister implements ICommand {

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = null;
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASSWORD);
        String name = request.getParameter(NAME);
        String surname= request.getParameter(SURNAME);
        RegisterService registerService = Service.getInstance().getRegisterService();
        if (registerService.inputStudent(login, password, name, surname)) {
            page = Config.getInstance().getProperty(Config.LOGIN);
        } else {
            request.setAttribute("error",
                    Message.getInstance().getProperty(Message.REGISTER_ERROR));
            page = Config.getInstance().getProperty(Config.REGISTER_ERROR);
        }

        return page;
    }
}
