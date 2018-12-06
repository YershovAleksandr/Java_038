package com.nam;

public class App {
    private static Data data;
    private static Result result;

    public static void main(String[] args) throws Exception{
        Application application = new Application();
        data = application.init();
        result = application.process(data);
        application.sendMail(result);
    }
}