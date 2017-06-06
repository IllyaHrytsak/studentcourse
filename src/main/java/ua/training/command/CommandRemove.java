package ua.training.command;


import ua.training.entity.User;
import ua.training.manager.Config;
import ua.training.manager.Message;
import ua.training.service.CourseService;
import ua.training.service.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommandRemove implements ICommand {

    private static final String COURSE_ID = "courseId";
    private static final String STUDENT_ID = "studentId";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        String courseId = request.getParameter(COURSE_ID);
        String userId = request.getParameter(STUDENT_ID);
        User user = (User) request.getSession().getAttribute("user");
        CourseService courseService = Service.getInstance().getCourseService();
        if (user != null) {
            CommandHelper commandHelper = CommandHelper.getInstance();
            if (courseService.removeStudent(courseId, userId)) {
                page = commandHelper.checkUser(request.getSession(), user);
            } else {
                request.setAttribute("error",
                        Message.getInstance().getProperty(Message.REMOVE_ERROR));
                page = Config.getInstance().getProperty(Config.ERROR);
            }
        } else {
            page = Config.getInstance().getProperty(Config.LOGIN);
        }
        return page;
    }
}
