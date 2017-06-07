package ua.training.command.implementation;


import ua.training.command.Command;
import ua.training.command.HelperCommand;
import ua.training.entity.User;
import ua.training.manager.Config;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChangeLanguageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        String language = request.getParameter("languageKey");
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            HelperCommand commandHelper = HelperCommand.getInstance();
            request.getSession().setAttribute("language", language);
            page = commandHelper.checkUser(request.getSession(), user);
        } else {
            request.getSession().setAttribute("language", language);
            page = Config.getInstance().getProperty(Config.LOGIN);
        }
        return page;
    }
}
