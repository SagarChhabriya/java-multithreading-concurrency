package com.example.concurrency.runnables;

import com.example.concurrency.beans.User;
import com.example.concurrency.dao.UserDao;

import java.util.StringTokenizer;
import java.util.concurrent.Callable;

public class UserProcessor implements Callable<Integer> {

    private String userRecord;
    private UserDao userDao;

    public UserProcessor(String userRecord,UserDao userDao){
        this.userRecord=userRecord;
        this.userDao=userDao;
    }

    @Override
    public Integer call() throws Exception{
        StringTokenizer  tokenizer =new StringTokenizer(userRecord,",");
        User user=null;
        int rows=0;
        System.out.println(Thread.currentThread().getName()+" processing record for "+userRecord);
        while(tokenizer.hasMoreTokens()){
            user=new User();
            user.setEmailAddress(tokenizer.nextToken());
            user.setName(tokenizer.nextToken());
            user.setId(Integer.valueOf(tokenizer.nextToken()));
            rows=userDao.saveUser(user);
        }
        return rows;
    }
}
