package ua.training.tag;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

public class ShowAuthorTag extends TagSupport {


    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().print("Hrytsak 2017");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }
}
