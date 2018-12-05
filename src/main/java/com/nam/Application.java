package com.nam;

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

    public void process(Data data, Result result){
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
    }

    public void sendMail(Result result){
        System.out.println(result.toString());
    }
}
