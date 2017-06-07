package ua.training.command.implementation;


import ua.training.command.Command;
import ua.training.manager.Config;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class LogoutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        request.setAttribute("language", session.getAttribute("language"));
        session.invalidate();
        return Config.getInstance().getProperty(Config.LOGIN);
    }
}
