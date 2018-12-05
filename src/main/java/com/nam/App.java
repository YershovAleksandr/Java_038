package com.nam;

import java.util.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World Softaria!" );

        //todo fix this shit
        Hashtable<String, String> yesterday = new Hashtable<>();
        Hashtable<String, String> today = new Hashtable<>();

        setHashTable(yesterday, 1);
        setHashTable(today, 2);

        System.out.println("Yesterday");
        printHashTable(yesterday);
        System.out.println("Today");
        printHashTable(today);
        System.out.println("CheckTables");
        checkTables(yesterday, today);

/*        Hashtable<String, String> tables = new Hashtable<>();

        tables.put("1", "1001");
        tables.put("2", "1002");
        tables.put("3", "1003");
        tables.put("4", "1004");
        tables.put("5", "1005");

        String str = tables.get("2");

        if (str != null){
            System.out.println("Value of key 2 is: " + str);
        }

        Set<String> keys = tables.keySet();

        Iterator<String> it = keys.iterator();

        while(it.hasNext()){

            String s = it.next();

            System.out.println("Key: " + s + " Value: " + tables.get(s));
        }

        System.out.println("!!!!");

        for (String key : keys){
            System.out.println("Key: " + key + " Value: " + tables.get(key));
        }
*/
    }

    static void setHashTable(Hashtable<String, String> tab, int t){
        //todo fix this shit
        //Hashtable<String, String> tab = new Hashtable<>();

        if (t == 1) {
            tab.put("1", "1001");
            tab.put("2", "1002");
            tab.put("3", "1003");
            tab.put("4", "1004");
            tab.put("5", "1005");
            tab.put("6", "1006");
            tab.put("7", "1007");
        } else {
            tab.put("1", "2001");
            tab.put("2", "1002");
            tab.put("3", "2003");
            tab.put("4", "1004");
            tab.put("5", "2005");
            tab.put("6", "1006");
            tab.put("8", "2008");
        }

        //table = tab;

        //printHashTable(table);
    }

    static void printHashTable(Hashtable<String, String> table){
        Set<String> keys = table.keySet();

        for (String key: keys){
            System.out.println("Key: " + key + " Value:" + table.get(key));
        }

        //System.out.println(table.toString());
    }

    static void checkTables(Hashtable<String, String> yesterday, Hashtable<String, String> today){

        List<String> changeKeys = new ArrayList<>();
        List<String> removeKeys = new ArrayList<>();
        List<String> addKeys = new ArrayList<>();

        Set<String> keys = yesterday.keySet();

        System.out.println("Yesterday:");
        for (String key: keys){
            //System.out.println("Key: " + key + " Value:" + yesterday.get(key));

            if (today.containsKey(key)){ //exist key
                if (!today.get(key).equals(yesterday.get(key))){ //modification value
                    System.out.println("CHANGE Key: " + key + " Old Value: " + yesterday.get(key) + " New value: " + today.get(key));
                    changeKeys.add(key);
                }
            } else { //not exist key
                System.out.println("REMOVE key: " + key);
                removeKeys.add(key);
            }
        }

        if (removeKeys.size() + today.size() > yesterday.size()){

            Set<String> todayKeys = today.keySet();

            for (String key: todayKeys){
                if (!yesterday.containsKey(key)){
                    System.out.println("ADD Key: " + key);

                    addKeys.add(key);
                }
            }
        }

        System.out.println("Change " + changeKeys.size() + " values\n" + changeKeys.toString());
        for(String s : changeKeys){
            System.out.println("Key: " + s);
        }

        System.out.println("Remove " + removeKeys.size() + " values\n" + removeKeys.toString());
        for(String s : removeKeys){
            System.out.println("Key: " + s);
        }

        System.out.println("Add " + addKeys.size() + " values\n" + addKeys.toString());
        for(String s : addKeys){
            System.out.println("Key: " + s);
        }


    }
}
