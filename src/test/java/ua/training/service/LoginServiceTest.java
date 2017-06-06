package ua.training.service;


import org.junit.Assert;
import org.junit.Test;
import ua.training.entity.User;


public class LoginServiceTest {

    @Test
    public void testFindUser() {
       LoginService loginService = Service.getInstance().getLoginService();
       User user = loginService.findLecturer("ivan1", "admin");
       Assert.assertNotNull(user);
    }

    @Test
    public void testNotFindUser() {
        LoginService loginService = Service.getInstance().getLoginService();
        User user = loginService.findLecturer("", "");
        Assert.assertNull(user);
    }


}
