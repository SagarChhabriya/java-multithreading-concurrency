# Concurrency and Multitheading in Java

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
### Using the Runnable Interface to Create and Run a Thread
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

### Limitations of using basic APIs
While the basic APIs like Thread and Runnable are essential, they have limitations in terms of flexibility, scalability, and advanced features for managing concurrent tasks.

## Executor Framework
### Features of Executor Framework
The Executor Framework in Java provides a higher-level abstraction for managing and controlling thread execution. It offers features like thread pooling, task scheduling, and better resource management.

### Thread Pool
Thread pools are a key component of the Executor Framework, allowing the reuse of threads to execute multiple tasks. This helps in avoiding the overhead of thread creation and destruction.

```java
ExecutorService executorService = Executors.newFixedThreadPool(5);

// Submitting tasks to the thread pool
executorService.submit(new MyRunnable());
executorService.submit(new MyCallable());

// Shutting down the thread pool
executorService.shutdown();
```

### Callables and Furtures
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

