package edu.umb.cs681.hw08;

import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentSingleton {

    private static ConcurrentSingleton instance = null;
    private static ReentrantLock lock = new ReentrantLock();
    private static int numConstructorCalls = 0;
    
    private ConcurrentSingleton() {
        numConstructorCalls += 1;
    }

    public static ConcurrentSingleton getInstance() {

        lock.lock();
        try {
            if (instance == null) {
                instance = new ConcurrentSingleton();
                // System.out.println("\t\tConcurrentSingleton: getInstance() called my contructor? yes!");
            // } else {
                // System.out.println("\t\tConcurrentSingleton: getInstance() called my contructor? no!");
            }
            return instance;
        } finally {
            lock.unlock();
        }
    }

    public static void printNumConstructorCalls() {
        System.out.printf("\tConcurrent Singleton: number of calls to my constructor is %d\n",
                          numConstructorCalls);
    }
}
