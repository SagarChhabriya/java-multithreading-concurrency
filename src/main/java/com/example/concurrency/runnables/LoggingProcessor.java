package com.example.concurrency.runnables;

import jdk.jshell.spi.ExecutionControlProvider;

import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingProcessor implements Callable<Boolean> {

    @Override
    public Boolean call() throws Exception{

        Logger.getLogger(LoggingProcessor.class.getName()).log(Level.INFO,"Logging Something");
        return true;

    }
}
