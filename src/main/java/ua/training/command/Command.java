package ua.training.command;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Interface for realization GoF Pattern 'Command'
 * @author Illya Hrytsak
 */
public interface Command {

    /**
     * This method returns address of jsp pages, which will be forwarded by servlet
     * @param request gets http request from servlet
     * @param response gets http response from servlet
     * @return string which has address of jsp page
     * @throws ServletException notifies us that problem in servlet
     * @throws IOException notifies us that problem in input or output stream
     */
    String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
