package edu.umb.cs681.hw12;

import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.DirectoryStream;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;


abstract class GracefullyStoppingRunnable implements Runnable {

    private boolean done = false;
    private ReentrantLock lock;


    GracefullyStoppingRunnable() {
        lock = new ReentrantLock();
    }

    public void run() {
        while (true) {
            setLock();
            try {
                if (done) {
                    System.out.printf("\tRequestHandler %d: stopping\n", getThread().getId());
                    break;
                }
            } finally {
                unsetLock();
            };
            this.getMethodToRun().run();
            try {
                Thread.sleep(100);            
            } catch (InterruptedException e) {
                continue;
            }
        }
    }

    protected abstract Runnable getMethodToRun();
    
    
    protected void setLock() {
        lock.lock();
    }

    protected void unsetLock() {
        lock.unlock();
    }
    

    public Thread getThread() {
        return Thread.currentThread();
    }

    public void gracefulStop() {
        setLock();
        try {
            done = true;
            getThread().interrupt();
        } finally {
            unsetLock();
        }
    }
    
}
