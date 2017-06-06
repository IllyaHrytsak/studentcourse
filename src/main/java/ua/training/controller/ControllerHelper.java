package ua.training.controller;


import ua.training.command.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ControllerHelper {

    private static ControllerHelper instance = null;
    private Map<String, ICommand> commands = new HashMap<>();

    private ControllerHelper() {
        commands.put("login", new CommandLogin());
        commands.put("register", new CommandRegister());
        commands.put("updateCourse", new CommandNameCourse());
        commands.put("showMark", new CommandShowMark());
        commands.put("inputMark", new CommandInputMark());
        commands.put("removeUser", new CommandRemove());
        commands.put("logout", new CommandLogout());
        commands.put("back", new CommandMissing());
        commands.put("joinCourse", new CommandJoinCourse());
        commands.put("changeLanguage", new CommandChangeLanguage());
    }

    public ICommand getCommand(HttpServletRequest request, HttpServletResponse response) {
        ICommand command = commands.get(request.getParameter("command"));
        if (command == null) {
            command = new CommandMissing();
        }
        return command;
    }


    public synchronized static ControllerHelper getInstance() {
        if (instance == null) {
            instance = new ControllerHelper();
        }
        return instance;
    }


}
