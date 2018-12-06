package com.nam;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App {
    private static Data data;
    private static Result result;

    public static void main(String[] args) {
        Application application = new Application();

        try {
            data = application.init(args);
        }catch(Exception e){
            e.printStackTrace();
        }

        result = application.process(data);
        application.sendMail(result);
    }
/*
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
    }*/
}