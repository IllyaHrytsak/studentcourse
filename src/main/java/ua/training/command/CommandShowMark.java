package ua.training.command;


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

public class CommandShowMark implements ICommand {


    private static final String COURSE_ID = "courseId";
    private static final String STUDENT_ID = "studentId";
    private static final String STUDENT_NAME = "studentName";
    private static final String STUDENT_SURNAME = "studentSurname";
    private static final String COURSE_NAME = "courseName";


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = null;
        Integer mark = null;
        String courseId = request.getParameter(COURSE_ID);
        String userId = request.getParameter(STUDENT_ID);
        String studentName = request.getParameter(STUDENT_NAME);
        String studentSurname = request.getParameter(STUDENT_SURNAME);
        String courseName = request.getParameter(COURSE_NAME);
        User user = (User) request.getSession().getAttribute("user");
        CourseService courseService = Service.getInstance().getCourseService();
        LoginService loginService = Service.getInstance().getLoginService();
        if (user != null) {
            String login = user.getLogin();
            String password = user.getPassword();
            CommandHelper commandHelper = CommandHelper.getInstance();
            if ((mark = courseService.findMark(courseId, userId)) != null) {
                if ((user = loginService.findLecturer(login, password)) != null) {
                    commandHelper.setLecturerPage(request.getSession(), user);
                    request.setAttribute("mark", mark);
                    request.setAttribute("studentNameMark", studentName);
                    request.setAttribute("studentSurnameMark", studentSurname);
                    page = Config.getInstance().getProperty(Config.LECTURER_MAIN);
                } else if ((user = loginService.findStudent(login, password)) != null) {
                    commandHelper.setStudentPage(request.getSession(), user);
                    request.setAttribute("mark", mark);
                    request.setAttribute("courseNameMark", courseName);
                    page = Config.getInstance().getProperty(Config.STUDENT_MAIN);
                }
            } else {
                request.getSession().setAttribute("error",
                        Message.getInstance().getProperty(Message.SHOW_MARK_ERROR));
                page = Config.getInstance().getProperty(Config.ERROR);
            }
        } else {
            page = Config.getInstance().getProperty(Config.LOGIN);
        }
        return page;
    }
}
