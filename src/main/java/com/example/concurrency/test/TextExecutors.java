package com.example.concurrency.test;

import com.example.concurrency.beans.User;
import com.example.concurrency.dao.UserDao;
import com.example.concurrency.runnables.UserProcessor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TextExecutors {
    public static void main(String[] args) {
        //ExecutorService service= Executors.newSingleThreadExecutor(); // single thread
        //ExecutorService service= Executors.newCachedThreadPool(); // expandable thread pool
        ExecutorService service= Executors.newFixedThreadPool(3); // fixed thread pool
        //Above all are factory methods
        List<String> users = getUsersFromFile("D:\\Concurrency & Multithreading\\Ex_Files_Java_EE_Concurrency\\Exercise Files\\Chapter3\\03_07\\begin\\javaseconcurrency\\new_users.txt");
        UserDao userDao=new UserDao();
        for(String user:users){
            Future<Integer> future=service.submit(new UserProcessor(user,userDao));
            try {
                System.out.println("Result of the operation is "+future.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        service.shutdown();
        System.out.println("Main execution over");
    }

    public static List<String> getUsersFromFile(String fileName){
        List<String> users = new ArrayList<>();

        try(BufferedReader reader=new BufferedReader(new FileReader(new File(fileName)))){
            String line=null;
            while((line=reader.readLine())!=null){
                users.add(line);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Exception");
        } catch (IOException e) {
            System.out.println("Exception");
        }
        return users;
    }
}
