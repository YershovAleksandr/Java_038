package com.nam;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;
import java.util.Random;

/**
 * Hello world!
 *
 */
public class App {
    private static Data data;
    private static Result result;

    public static void main(String[] args) {
        System.out.println("Hello World Softaria!");

        Random rnd = new Random();
        String str = "Fuck you!" + rnd.nextInt();

        //sendMail(str);

      //  checkMail(str);

        //System.exit(0);

        Application application = new Application();


        data = application.init(args);

        result = new Result();

        application.process(data, result);

        application.sendMail(result);

    }

    private static void sendMail(String str){
// Recipient's email ID needs to be mentioned.
        String to = "alebed42@74.ru";

        // Sender's email ID needs to be mentioned
        String from = "alebed42@74.ru";

        // Assuming you are sending email from localhost
        String host = "smtp.yandex.ru";

        int port = 465;

        // Get system properties
        Properties properties = new Properties();

        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.debug", "true");
        //properties.put("mail.user", "alebede42@74.ru");
        //properties.put("mail.password", "0fsdabmh");

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("alebed42@74.ru", "ofsdabmh");
            }
        });

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field


            message.setSubject(str);

            // Now set the actual message
            message.setText(str);

            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");

        } catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }

    private static void checkMail(String str){
        final String user = "alebed42@74.ru"; // имя пользователя
        final String pass = "ofsdabmh";    // пароль
        final String host = "imap.yandex.ru";     // адрес почтового сервера

        // Создание свойств
        Properties props = new Properties();

        //включение debug-режима
        props.put("mail.debug", "true");

        //Указываем протокол - IMAP с SSL
        props.put("mail.store.protocol", "imaps");
        Session session = Session.getInstance(props);
        try {
            Store store = session.getStore();

            //подключаемся к почтовому серверу
            store.connect(host, user, pass);

            //получаем папку с входящими сообщениями
            Folder inbox = store.getFolder("INBOX");

            //открываем её только для чтения
            inbox.open(Folder.READ_ONLY);

            //получаем последнее сообщение (самое старое будет под номером 1)
            Message m = inbox.getMessage(inbox.getMessageCount());

            //m.getSubject();
            //Multipart mp = (Multipart) m.getContent();
            //BodyPart bp = mp.getBodyPart(0);

            //Выводим содержимое на экран
            //System.out.println(bp.getContent());
            System.out.println("\n~" + m.getContent().toString() + "~");

        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}