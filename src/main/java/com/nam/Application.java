package com.nam;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Properties;
import java.util.Scanner;
import java.util.Set;

public class Application {
    private String mailto;

    public Data init() throws Exception{

        Properties prp = new Properties();
        prp.load(new FileInputStream("src/main/resources/config.properties"));
        mailto = prp.getProperty("app.mailto");

        Data data = new Data();

        Scanner in = new Scanner(System.in);

        System.out.println("Enter count of yesterday's pages");
        int yesterdayN = in.nextInt();

        System.out.println("Enter Url & file with content");

        for (int i = 0; i < yesterdayN; i++){
            data.getYesterdayTable().put(in.next(), new String(Files.readAllBytes(Paths.get(in.next()))));
        }

        System.out.println("Enter count of today's pages");
        int todayN = in.nextInt();

        System.out.println("Enter Url & file with content");

        for (int i = 0; i < todayN; i++){
            data.getTodayTable().put(in.next(), new String(Files.readAllBytes(Paths.get(in.next()))));
        }

        System.out.println("Yesterday size:\n" + data.getYesterdayTable().size());
        System.out.println("Today size:\n" + data.getTodayTable().size());

        return data;
    }

    public Result process(Data data){
        Result result = new Result();

        Set<String> keys = data.getYesterdayTable().keySet();

        for (String key: keys){
            if (data.getTodayTable().containsKey(key)){ //key exist
                if (!data.getTodayTable().get(key).equals(data.getYesterdayTable().get(key))){ //changed value
                    result.getChangedPages().add(key);
                }
            } else { //key not exist
                result.getRemovedPages().add(key);
            }
        }

        if (result.getRemovedPages().size() + data.getTodayTable().size() > data.getYesterdayTable().size()){ //for performance purposes
            Set<String> todayKeys = data.getTodayTable().keySet();

            for (String key: todayKeys){
                if (!data.getYesterdayTable().containsKey(key)){

                    result.getAddedPages().add(key);
                }
            }
        }

        return result;
    }

    public void sendMail(Result result){
        System.out.println("Sending mail to: " + mailto + "\n" + result.toString());

        String from = "*****";
        String host = "****";

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.debug", "true");

        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(from, "****");
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailto));
            message.setSubject(LocalDate.now().toString());
            message.setText(result.toString());

            Transport.send(message);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
