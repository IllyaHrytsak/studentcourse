package ua.training.service;


import org.junit.Assert;
import org.junit.Test;
import ua.training.entity.User;


public class LoginServiceTest {

    @Test
    public void testFindUser() {
       UserService userService = UserService.getInstance();
       User user = userService.findLecturer("ivan1", "admin");
       Assert.assertNotNull(user);
    }

    @Test
    public void testNotFindUser() {
        UserService userService = UserService.getInstance();
        User user = userService.findLecturer("", "");
        Assert.assertNull(user);
    }


}
