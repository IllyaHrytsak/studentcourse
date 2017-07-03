package ua.training.tag;


import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.logging.Logger;

public class ShowAuthorTag extends TagSupport {

    private static final String AUTHOR = "Hrytsak 2017";

    private static final Logger LOGGER = Logger.getLogger(ShowAuthorTag.class.getSimpleName());

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getOut().print(AUTHOR);
        } catch (IOException e) {
            LOGGER.warning("Exception: " + e.getMessage());
        }
        return SKIP_BODY;
    }
}
