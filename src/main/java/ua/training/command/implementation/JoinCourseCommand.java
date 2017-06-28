package ua.training.command.implementation;


import ua.training.command.Command;
import ua.training.command.HelperCommand;
import ua.training.entity.User;
import ua.training.manager.Config;
import ua.training.manager.Message;
import ua.training.service.CourseService;
import ua.training.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JoinCourseCommand implements Command {

    private static final String COURSE_ID = "courseId";
    private static final String STUDENT_ID = "studentId";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        String courseId = request.getParameter(COURSE_ID);
        String userId = request.getParameter(STUDENT_ID);
        User user = (User) request.getSession().getAttribute("user");
        CourseService courseService = CourseService.getInstance();
        UserService userService = UserService.getInstance();
        if (user != null) {
            String login = user.getLogin();
            HelperCommand helperCommand = HelperCommand.getInstance();
            if (courseService.insertStudent(courseId, userId)) {
                user = userService.findStudentByLogin(login);
                helperCommand.setStudentPage(request.getSession(), user);
                page = Config.getInstance().getProperty(Config.STUDENT_MAIN);

            } else {
                request.setAttribute("error",
                        Message.getInstance().getProperty(Message.JOIN_COURSE_ERROR));
                page = Config.getInstance().getProperty(Config.ERROR);
            }
        } else {
            page = Config.getInstance().getProperty(Config.LOGIN);
        }
        return page;
    }
}
