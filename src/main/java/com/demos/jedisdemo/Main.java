package com.demos.jedisdemo;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String args[]) {
        String userKey = "user#1";
        RedisDao redisDao = new RedisDao();
        if (redisDao.getValue(userKey) == null) {
            System.out.println("setting user for first time");
            redisDao.setKey(userKey, "Anwesh");
        }
        System.out.println("User is " + redisDao.getValue(userKey));

        String userDetailsKey = userKey + "#details";
        if (redisDao.getAllHashValues(userDetailsKey) == null || redisDao.getAllHashValues(userDetailsKey).keySet().isEmpty()) {
            System.out.println("setting user details for first time");
            redisDao.setHashValue(userDetailsKey, "name", "Anwesh");
            redisDao.setHashValue(userDetailsKey, "age", "27");
        }
        redisDao.getAllHashValues(userDetailsKey).forEach((k, v) -> {
           System.out.println(k + " : " + v);
        });

        String fruitsKey = "fruits";
        if (redisDao.getList(fruitsKey) == null || redisDao.getList(fruitsKey).isEmpty()) {
            System.out.println("putting fruits for the first time");
            List<String> fruitsList = new ArrayList<String>() {{
                add("apple");
                add("pear");
                add("pineapple");
                add("mango");
                add("lime");
            }};
            redisDao.addListToRedisList(fruitsKey, fruitsList);
        }
        redisDao.getList(fruitsKey).stream().filter(word -> word.endsWith("apple")).forEach(System.out::println);
    }
}
