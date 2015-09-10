/*
 * ThreadID.java
 *
 * Created on January 5, 2006, 6:18 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */


/**
 * Illustrates use of thread-local storage. Test by running main().
 * @author Maurice Herlihy
 */
public class ThreadID {
    /**
     * The next thread ID to be assigned
     **/
    private static volatile int nextID = 0;
    /**
     * My thread-local ID.
     **/
    private static ThreadLocalID threadID = new ThreadLocalID();
    public static int get() {
        return threadID.get();
    }
    /**
     * When running multiple tests, reset thread id state
     **/
    public static void reset() {
        nextID = 0;
    }
    public static void set(int value) {
        threadID.set(value);
    }
    private static class ThreadLocalID extends ThreadLocal<Integer> {
        protected synchronized Integer initialValue() {
            return nextID++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[5];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread() {
                    public void run() {
                        for (int i = 0; i < 5; i++) {
                            System.out.println("This is thread " + ThreadID.get() + ", take " + i + ".");
                        }
                    }
                };
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            thread.join();
        }
    }
}
