package com.nam;

import java.util.Hashtable;

public class Data {
    private final Hashtable<String, String> yesterdayTable = new Hashtable<>();
    private final Hashtable<String, String> todayTable = new Hashtable<>();

//    public Data(){}

    public Hashtable<String, String> getYesterdayTable(){
        return yesterdayTable;
    }

    public Hashtable<String, String> getTodayTable(){
        return todayTable;
    }
}
