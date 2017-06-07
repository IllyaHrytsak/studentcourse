package ua.training.command.implementation;


import ua.training.command.Command;
import ua.training.command.HelperCommand;
import ua.training.entity.User;
import ua.training.manager.Config;
import ua.training.manager.Message;
import ua.training.service.CourseService;
import ua.training.service.LoginService;
import ua.training.service.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class InsertCourseCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        String courseName = request.getParameter("newNameCourse");
        String userId = request.getParameter("userId");
        User user = (User) request.getSession().getAttribute("user");
        CourseService courseService = Service.getInstance().getCourseService();
        LoginService loginService = Service.getInstance().getLoginService();
        if (user != null) {
            HelperCommand commandHelper = HelperCommand.getInstance();
            String login = user.getLogin();
            String password = user.getPassword();
            if (courseService.insertCourse(courseName, userId)) {
                if ((user = loginService.findLecturer(login, password)) != null)
                commandHelper.setLecturerPage(request.getSession(), user);
                page = Config.getInstance().getProperty(Config.LECTURER_MAIN);
            } else {
                request.getSession().setAttribute("error",
                        Message.getInstance().getProperty(Message.COURSE_NAME_ERROR));
                page = Config.getInstance().getProperty(Config.ERROR);
            }
        } else {
            page = Config.getInstance().getProperty(Config.LOGIN);
        }
        return page;
    }

}
