# Concurrency and Multithreading in Java

# Table of Contents

- [Concurrency and Multithreading in Java](#concurrency-and-multithreading-in-java)
  - [Section 1: Basics of Concurrency](#section-1-basics-of-concurrency)
    - [1. What is Concurrency?](#1-what-is-concurrency)
    - [2. Process vs Thread](#2-process-vs-thread)
    - [3. Using the Thread Class to Create and Run a Thread](#3-using-the-thread-class-to-create-and-run-a-thread)
    - [4. Using the Runnable Interface to Create and Run a Thread](#4-using-the-runnable-interface-to-create-and-run-a-thread)
    - [5. Limitations of using basic APIs](#5-limitations-of-using-basic-apis)
  - [Section 2: Executor Framework](#executor-framework)
    - [6. Features of Executor Framework](#6-features-of-executor-framework)
    - [7. Executor Interface](#7-executor-interface)
    - [8. ExecutorService Interface](#8-executorservice-interface)
    - [9. Thread Pool](#9-thread-pool)
    - [10. Callables and Futures](#10-callables-and-futures)
    - [11. Different Thread Pools](#11-different-thread-pools)
    - [12. ScheduledExecutorService](#12-scheduledexecutorservice)
    - [13. ThreadFactory API](#13-threadfactory-api)
    - [14. ThreadPoolExecutor](#14-threadpoolexecutor)


## Section 1: Basics of Concurrency

### 1. What is Concurrency?
Concurrency is the ability of a program to execute multiple tasks simultaneously. In Java, it involves managing the execution of multiple threads to achieve parallelism and better utilization of system resources.

### 2. Process vs Thread
A process is an independent program with its own memory space, while a thread is a lightweight unit of execution within a process, sharing the same memory space. Threads enable concurrent execution and communication between different parts of a program.

### 3. Using the Thread Class to Create and Run a Thread
In Java, the `Thread` class is a fundamental way to create and manage threads. You can extend the `Thread` class and override its `run` method to define the code executed by the thread.

```java
class MyThread extends Thread {
    public void run() {
        // Code to be executed concurrently
    }
}

// Creating and starting the thread
MyThread myThread = new MyThread();
myThread.start();
```
### 4. Using the Runnable Interface to Create and Run a Thread
The Runnable interface provides an alternative way to create threads by implementing the run method. This allows better flexibility as a class can implement multiple interfaces.

```java
class MyRunnable implements Runnable {
    public void run() {
        // Code to be executed concurrently
    }
}

// Creating a thread using Runnable
Thread myThread = new Thread(new MyRunnable());
myThread.start();
```

### 5. Limitations of using basic APIs
While the basic APIs like Thread and Runnable are essential, they have limitations in terms of flexibility, scalability, and advanced features for managing concurrent tasks.

## Section 2: Executor Framework
### 6. Features of Executor Framework
The Executor Framework in Java provides a higher-level abstraction for managing and controlling thread execution. It offers features like thread pooling, task scheduling, and better resource management.

### 7. Executor Interface
The Executor interface is at the core of the framework, defining a single method:

```java
public interface Executor {
    void execute(Runnable command);
}
```
Implementations of this interface provide a way to execute a Runnable task asynchronously.

### 8. ExecutorService Interface
The ExecutorService interface extends Executor and provides a more complete set of methods for managing the lifecycle of threads and tasks. Some key methods include:

```java
public interface ExecutorService extends Executor {
    void shutdown();
    List<Runnable> shutdownNow();
    boolean isShutdown();
    boolean isTerminated();
    <T> Future<T> submit(Callable<T> task);
    // ... and more
}
```

### 9. Thread Pool
Thread pools are a key component of the Executor Framework, allowing the reuse of threads to execute multiple tasks. This helps in avoiding the overhead of thread creation and destruction.

```java
ExecutorService executorService = Executors.newFixedThreadPool(5);

// Submitting tasks to the thread pool
executorService.submit(new MyRunnable());
executorService.submit(new MyCallable());

// Shutting down the thread pool
executorService.shutdown();
```

### 10. Callables and Futures
The Callable interface is an enhancement over Runnable that allows threads to return values. Futures represent the result of asynchronous computations, providing a way to retrieve the output of a Callable task.

```java
class MyCallable implements Callable<String> {
    public String call() {
        // Code to be executed concurrently with a return value
        return "Task completed successfully";
    }
}

// Using Callable with ExecutorService
Future<String> futureResult = executorService.submit(new MyCallable());

// Retrieving the result
String result = futureResult.get();

```

### 11. Different Thread Pools
Thread pools are managed collections of worker threads that execute tasks. The Executors class provides factory methods to create different types of thread pools, such as:

newFixedThreadPool(int nThreads)
newCachedThreadPool()
newSingleThreadExecutor()
newScheduledThreadPool(int corePoolSize)

```java
ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
```


### 12. ScheduledExecutorService

The ScheduledExecutorService interface extends ExecutorService and provides methods to schedule tasks with specified delays or at fixed rates. It's useful for tasks that need to be executed periodically.

```java
ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
scheduledExecutorService.scheduleAtFixedRate(
    () -> System.out.println("Task executed at fixed rate"),
    0, 1, TimeUnit.SECONDS
);
```

### 13. ThreadFactory API

The ThreadFactory interface allows you to customize the creation of threads in a thread pool. This can be useful for setting thread names, priorities, or other thread properties.

```java
ThreadFactory customThreadFactory = new ThreadFactory() {
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setName("CustomThread-" + thread.getId());
        return thread;
    }
};

ExecutorService customThreadPool = Executors.newFixedThreadPool(5, customThreadFactory);
```

### 14. ThreadPoolExecutor

The ThreadPoolExecutor class is a powerful and customizable implementation of the ExecutorService interface. It allows fine-tuning of core and maximum pool sizes, keep-alive times, and other parameters.

```java
ThreadPoolExecutor customThreadPoolExecutor = new ThreadPoolExecutor(
    5, 10, 60, TimeUnit.SECONDS,
    new LinkedBlockingQueue<>(),
    new CustomThreadFactory()
);
```
