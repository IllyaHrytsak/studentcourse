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


public class NameCourseCommand implements Command {

    private static final String COURSE_ID = "courseId";
    private static final String NEW_NAME_COURSE = "nameCourse";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        String nameCourse = request.getParameter(NEW_NAME_COURSE);
        String courseId = request.getParameter(COURSE_ID);
        User user = (User) request.getSession().getAttribute("user");
        CourseService courseService = Service.getInstance().getCourseService();
        LoginService loginService = Service.getInstance().getLoginService();
        if (user != null) {
            String login = user.getLogin();
            String password = user.getPassword();
            HelperCommand commandHelper = HelperCommand.getInstance();
            if (courseService.changeNameCourse(nameCourse, courseId)) {
                user = loginService.findLecturer(login, password);
                commandHelper.setLecturerPage(request.getSession(), user);
                page = Config.getInstance().getProperty(Config.LECTURER_MAIN);
            } else {
                request.setAttribute("error",
                        Message.getInstance().getProperty(Message.COURSE_NAME_ERROR));
                page = Config.getInstance().getProperty(Config.ERROR);
            }
        } else {
            page = Config.getInstance().getProperty(Config.LOGIN);
        }
        return page;
    }
}
