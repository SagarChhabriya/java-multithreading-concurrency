package com.example.concurrency.test;

import com.example.concurrency.runnables.CleaningScheduler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestSchedulers {
    public static void main(String[] args) {
        ScheduledExecutorService service= Executors.newSingleThreadScheduledExecutor();
        service.schedule(new CleaningScheduler(),5, TimeUnit.SECONDS);

    }
}
