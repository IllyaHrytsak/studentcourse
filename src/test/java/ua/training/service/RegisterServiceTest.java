package ua.training.service;


import org.junit.Assert;
import org.junit.Test;

public class RegisterServiceTest {

    @Test
    public void testWrongInputLogin() {
        RegisterService registerService = Service.getInstance().getRegisterService();
        String login = "1234";
        String password = "12345";
        String name = "Illya";
        String surname = "Hrytsak";
        boolean result = registerService.inputStudent(login, password, name, surname);
        Assert.assertTrue(!result);
    }

    @Test
    public void testWrongInputPassword() {
        RegisterService registerService = Service.getInstance().getRegisterService();
        String login = "ivanpetrovich";
        String password = "1";
        String name = "Illya";
        String surname = "Hrytsak";
        boolean result = registerService.inputStudent(login, password, name, surname);
        Assert.assertTrue(!result);
    }

    @Test
    public void testWrongInputName() {
        RegisterService registerService = Service.getInstance().getRegisterService();
        String login = "admin";
        String password = "1345";
        String name = "illya";
        String surname = "Hrytsak";
        boolean result = registerService.inputStudent(login, password, name, surname);
        Assert.assertTrue(!result);
    }

    @Test
    public void testWrongInputSurname() {
        RegisterService registerService = Service.getInstance().getRegisterService();
        String login = "admin";
        String password = "1345";
        String name = "Illya";
        String surname = "hrytsak";
        boolean result = registerService.inputStudent(login, password, name, surname);
        Assert.assertTrue(!result);
    }

}
