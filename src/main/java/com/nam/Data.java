package com.nam;

import java.util.Hashtable;

public class Data {
    private String email;
    private Hashtable<String, String> yesterdayTable = new Hashtable<>();
    private Hashtable<String, String> todayTable = new Hashtable<>();

//    public Data(){}

    public void setEmail(String email){
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    public Hashtable<String, String> getYesterdayTable(){
        return yesterdayTable;
    }

    public Hashtable<String, String> getTodayTable(){
        return todayTable;
    }
}
