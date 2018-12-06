package com.nam;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.time.LocalDate;
import java.util.Properties;
import java.util.Set;

public class Application {
    //private Data data;
    //private Result result;

    public Application(){
    }

    public Data init(String[] args){

        Data data = new Data();

        /*data.getTodayTable().put("1", "1001");
        data.getTodayTable().put("2", "1002");
        data.getTodayTable().put("3", "1003");
        data.getTodayTable().put("4", "1004");
        data.getTodayTable().put("5", "1005");
        data.getTodayTable().put("6", "1006");
        data.getTodayTable().put("7", "1007");

        data.getYesterdayTable().put("1", "2001");
        data.getYesterdayTable().put("2", "1002");
        data.getYesterdayTable().put("3", "2003");
        data.getYesterdayTable().put("4", "1004");
        data.getYesterdayTable().put("5", "2005");
        data.getYesterdayTable().put("6", "1006");
        data.getYesterdayTable().put("8", "2008");*/

        System.out.println("Yesterday:\n" + data.getYesterdayTable().toString());
        System.out.println("Today:\n" + data.getTodayTable().toString());

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
        System.out.println(result.toString());

        String to = "alebed42@74.ru";
        //String to = "namstudionsk@gmail.com";
        String from = "alebed42@74.ru";
        String host = "smtp.yandex.ru";

        Properties properties = new Properties();

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.debug", "true");

        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("alebed42@74.ru", "ofsdabmh");
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(LocalDate.now().toString());
            message.setText(result.toString());

            Transport.send(message);
            System.out.println("Sent message successfully....");

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
