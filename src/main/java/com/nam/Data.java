package com.nam;

import java.util.Hashtable;

public class Data {
    private Hashtable<String, String> yesterdayTable = new Hashtable<>();
    private Hashtable<String, String> todayTable = new Hashtable<>();

    public Hashtable<String, String> getYesterdayTable(){
        return yesterdayTable;
    }

    public Hashtable<String, String> getTodayTable(){
        return todayTable;
    }
}
