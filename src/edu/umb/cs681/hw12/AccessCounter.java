package edu.umb.cs681.hw12;

import java.util.HashMap;
import java.nio.file.Path;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {
    HashMap<Path, Integer> data;
    private static ReentrantLock lock = new ReentrantLock();

    public AccessCounter() {
        data = new HashMap<Path, Integer>();
    }

    public void increment(Path path) {
        lock.lock();
        try {
            data.put(path, getCount(path) + 1);            
        } finally {
            lock.unlock();
        }
    }

    public Integer getCount(Path path) {
        lock.lock();
        try {
            return data.getOrDefault(path, 0);
        } finally {
            lock.unlock();
        }
    }

}
