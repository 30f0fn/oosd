package edu.umb.cs681.hw16;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;


public class AdmissionMonitor {

    private int currentVisitors = 0;
    private ReentrantLock lock = new ReentrantLock();
    private Condition subMaxOccupancyCondition = lock.newCondition();

    private static final int MAX_OCCUPANCY = 3;

    public void enter() {
        lock.lock();
        // System.out.println("\tAdmissionMonitor.enter: secured lock!");
        try {
            while(currentVisitors >= MAX_OCCUPANCY) {
                try {
                    System.out.printf("\tAdmissionMonitor.enter (thread %d): too many visitors. Please wait...\n",
                                      Thread.currentThread().getId()
                                      );
                    subMaxOccupancyCondition.await();
                } catch (InterruptedException e) {
                    System.out.printf("\tAdmissionMonitor.enter (thread %d): interrupted!\n",
                                      Thread.currentThread().getId());
                    return;
                }
            }
            currentVisitors++; 
            System.out.printf("\tAdmissionMonitor.entrance (thread %d): success! num visitors is now %d\n",
                              Thread.currentThread().getId(),
                              currentVisitors);
        } finally {
            lock.unlock();
            // System.out.println("\tAdmissionMonitor.enter: lock released!");
        }
    }

    public void exit() {
        lock.lock();
        // System.out.println("\texit: lock secured!");
        try {
            currentVisitors--;
            System.out.printf("\tAdmissionMonitor.exit (thread %d): success! num visitors is now %d\n",
                              Thread.currentThread().getId(),
                              currentVisitors);

            subMaxOccupancyCondition.signalAll();
        } finally {
            lock.unlock();
            // System.out.println("\texit: lock released!");
        }

    }

    public int countCurrentVisitors() {
        lock.lock();
        try {
            return currentVisitors;
        } finally {
            lock.unlock();
        }
    }

}
