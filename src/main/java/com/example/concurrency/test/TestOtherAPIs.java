package com.example.concurrency.test;

import com.example.concurrency.runnables.LoggingProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class TestOtherAPIs {
    public static void main(String[] args) {
        ExecutorService executorService= Executors.newFixedThreadPool(2);
        List<Callable<Boolean>> callables = new ArrayList<>();

        callables.add(new LoggingProcessor());
        callables.add(new LoggingProcessor());
        callables.add(new LoggingProcessor());
        callables.add(new LoggingProcessor());
        callables.add(new LoggingProcessor());

        try {
            List<Future<Boolean>> futures = executorService.invokeAll(callables);

            for(Future<Boolean> future:futures){
                System.out.println("Operation Result :"+future.get());
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }

        executorService.shutdown();
        try {
            System.out.println("Service shutdown? "+executorService.awaitTermination(30,TimeUnit.SECONDS));
        } catch (InterruptedException e) {
            executorService.shutdown();
            System.out.println("Exception");
        }


    }
}
