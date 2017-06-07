package ua.training.tag;


import ua.training.entity.Course;
import ua.training.service.CourseService;
import ua.training.service.Service;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import java.io.IOException;
import java.util.logging.Logger;

public class ShowMarkTag extends BodyTagSupport {

    private String userId;
    private String courseId;
    private CourseService courseService = Service.getInstance().getCourseService();
    private static final Logger LOGGER = Logger.getLogger(ShowMarkTag.class.getSimpleName());

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }


    @Override
    public int doStartTag() throws JspException {
        Integer mark = courseService.findMark(courseId, userId);
        try {
            if (mark.equals(0)) {
                pageContext.getOut().print("No mark");
            } else {
                pageContext.getOut().print(mark);
            }
        } catch (IOException e) {
            LOGGER.warning("Exception: " + e.getMessage());
        }

        return super.doStartTag();
    }
}
