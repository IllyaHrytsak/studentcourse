package ua.training.service;


import org.junit.Assert;
import org.junit.Test;

public class RegisterServiceTest {

    @Test
    public void testWrongInputLogin() {
        UserService userService = UserService.getInstance();
        String login = "1234";
        String password = "12345";
        String name = "Illya";
        String surname = "Hrytsak";
        boolean result = userService.inputStudent(login, password, name, surname);
        Assert.assertTrue(!result);
    }

    @Test
    public void testWrongInputPassword() {
        UserService userService = UserService.getInstance();
        String login = "ivanpetrovich";
        String password = "1";
        String name = "Illya";
        String surname = "Hrytsak";
        boolean result = userService.inputStudent(login, password, name, surname);
        Assert.assertTrue(!result);
    }

    @Test
    public void testWrongInputName() {
        UserService userService = UserService.getInstance();
        String login = "admin";
        String password = "1345";
        String name = "illya";
        String surname = "Hrytsak";
        boolean result = userService.inputStudent(login, password, name, surname);
        Assert.assertTrue(!result);
    }

    @Test
    public void testWrongInputSurname() {
        UserService userService = UserService.getInstance();
        String login = "admin";
        String password = "1345";
        String name = "Illya";
        String surname = "hrytsak";
        boolean result = userService.inputStudent(login, password, name, surname);
        Assert.assertTrue(!result);
    }

}
