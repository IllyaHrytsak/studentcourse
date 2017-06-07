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


public class InputMarkCommand implements Command {

    private static final String COURSE_ID = "courseId";
    private static final String STUDENT_ID = "studentId";
    private static final String MARK = "mark";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        String courseId = request.getParameter(COURSE_ID);
        String userId = request.getParameter(STUDENT_ID);
        String mark = request.getParameter(MARK);
        User user = (User) request.getSession().getAttribute("user");
        CourseService courseService = Service.getInstance().getCourseService();
        LoginService loginService = Service.getInstance().getLoginService();
        if (user != null) {
            if (courseService.insertMark(courseId, userId, mark)) {
                String login = user.getLogin();
                String password = user.getPassword();
                HelperCommand commandHelper = HelperCommand.getInstance();
                user = loginService.findLecturer(login, password);
                commandHelper.setLecturerPage(request.getSession(), user);
                page = Config.getInstance().getProperty(Config.LECTURER_MAIN);
            } else {
            request.getSession().setAttribute("error",
                    Message.getInstance().getProperty(Message.INPUT_MARK_ERROR));
            page = Config.getInstance().getProperty(Config.ERROR);
            }
        } else {
            page = Config.getInstance().getProperty(Config.LOGIN);
        }
        return page;
    }
}
