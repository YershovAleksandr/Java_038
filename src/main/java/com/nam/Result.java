package com.nam;

import java.util.ArrayList;
import java.util.List;

public class Result {
    private List<String> removedPages = new ArrayList<>();
    private List<String> addedPages = new ArrayList<>();
    private List<String> changedPages = new ArrayList<>();

    public List<String> getRemovedPages() {
        return removedPages;
    }

    public List<String> getAddedPages(){
        return addedPages;
    }

    public List<String> getChangedPages(){
        return changedPages;
    }

    @Override
    public String toString(){
        return String.format("Здравствуйте, дорогая и.о. секретаря\n" +
                "За последние сутки во вверенных Вам сайтах произошли следующие изменения:\n" +
                "\n" +
                "\t1. Исчезли следующие страницы: %s\n" +
                "\t2. Появились следующие новые страницы: %s\n" +
                "\t3. Изменились следующие страницы: %s\n" +
                "\n" +
                "С уважением,\n" +
                "автоматизированная система\n" +
                "мониторинга.", removedPages.toString(), addedPages.toString(), changedPages.toString());
    }
}
