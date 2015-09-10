/*
 * HelloWorld.java
 *
 * Created on January 5, 2006, 6:12 PM
 *
 * From "Multiprocessor Synchronization and Concurrent Data Structures",
 * by Maurice Herlihy and Nir Shavit.
 * Copyright 2006 Elsevier Inc. All rights reserved.
 */

/**
 * Illustrates threads and anonymous classes. Test by running main().
 * @author Maurice Herlihy
 */
public class HelloWorld {
  
    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[8];
        // create threads
        for (int i = 0; i < threads.length; i++) {
            final String message = "Hello world from thread " + i;
            // You can directly subclass Thread instead of using a Runnable.
            threads[i] = new Thread() {
                    public void run() {
                        System.out.println(message);
                    }
                };
        }
        // start threads
        for (Thread thread : threads) {
            thread.start();
        }
        // wait for them to finish
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println("done!");
    }  
}
