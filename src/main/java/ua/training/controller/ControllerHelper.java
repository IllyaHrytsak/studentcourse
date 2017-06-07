package ua.training.controller;


import ua.training.command.implementation.*;
import ua.training.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ControllerHelper {

    private static ControllerHelper instance = null;
    private Map<String, Command> commands = new HashMap<>();

    private ControllerHelper() {
        commands.put("login", new LoginCommand());
        commands.put("register", new RegisterCommand());
        commands.put("updateCourse", new NameCourseCommand());
        commands.put("inputMark", new InputMarkCommand());
        commands.put("removeUser", new RemoveCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("back", new MissingCommand());
        commands.put("joinCourse", new JoinCourseCommand());
        commands.put("changeLanguage", new ChangeLanguageCommand());
        commands.put("insertCourse", new InsertCourseCommand());
        commands.put("deleteCourse", new DeleteCourseCommand());
    }

    public Command getCommand(HttpServletRequest request, HttpServletResponse response) {
        Command command = commands.get(request.getParameter("command"));
        if (command == null) {
            command = new MissingCommand();
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
