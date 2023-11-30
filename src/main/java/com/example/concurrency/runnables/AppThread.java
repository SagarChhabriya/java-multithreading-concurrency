package com.example.concurrency.runnables;

import java.io.*;

public class AppThread extends Thread{

    @Override
    public void run(){
        try(BufferedReader reader=new BufferedReader(new FileReader(new File("D:\\Concurrency & Multithreading\\Ex_Files_Java_EE_Concurrency\\Exercise Files\\Chapter2\\02_03\\begin\\sample.txt")))){
             String line=null;
             while((line=reader.readLine())!=null){
                 System.out.println(Thread.currentThread().getName()+" reading the line:"+line);
             }
        } catch (FileNotFoundException e) {
            System.out.println("Exception");
        } catch (IOException e) {
            System.out.println("Exception");
        }
    }
}
