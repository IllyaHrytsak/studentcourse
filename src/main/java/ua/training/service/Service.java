package ua.training.service;


public class Service {

    private static Service instance;

    private Service() {
    }

    public static Service getInstance() {
        if (instance == null) {
            instance = new Service();
        }
        return instance;
    }

    public LoginService getLoginService() {
        return LoginService.getInstance();
    }

    public RegisterService getRegisterService() {
        return RegisterService.getInstance();
    }

    public CourseService getCourseService() {
        return CourseService.getInstance();
    }
}
