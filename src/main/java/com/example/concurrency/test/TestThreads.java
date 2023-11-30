package com.example.concurrency.test;

import com.example.concurrency.runnables.AppRunnable;
import com.example.concurrency.runnables.AppThread;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TestThreads {
    public static void main(String[] args) {
        //Using extending Thread class
//        AppThread thread1 = new AppThread();//State=NEW
//        AppThread thread2 = new AppThread();
//        AppThread thread3 = new AppThread();
//
//        thread1.start();//State=RUNNABLE
//        thread2.start();
//        thread3.start();

        //execute run //State=RUNNING
        //job completed // State=TERMINATED/DEAD

        //Using implementing runnable
//        Thread run1 = new Thread(new AppRunnable());
//        run1.start();
//
//        Thread run2 = new Thread(new AppRunnable());
//        run2.start();
//
//        Thread run3 = new Thread(new AppRunnable());
//        run3.start();

        //Executor API -Creates Thread Pools
        Executor executor= Executors.newSingleThreadExecutor();
        executor.execute(new AppRunnable());
    }
}
