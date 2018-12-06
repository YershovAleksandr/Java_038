package com.nam;

import java.io.FileInputStream;
import java.util.Properties;

public class App {
    private static Data data;
    private static Result result;

    public static void main(String[] args) throws Exception{
        Application application = new Application();

        Properties prp = new Properties();
        prp.load(new FileInputStream("src/main/resources/config.properties"));
        application.setMailto(prp.getProperty("app.mailto"));

        data = application.init();
        result = application.process(data);
        application.sendMail(result);
    }
}