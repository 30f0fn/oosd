package edu.umb.cs681.hw14;

import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantLock;

class Cart {
    private LinkedList<Product> inCartItems = new LinkedList<Product>();
    private ReentrantLock lock = new ReentrantLock();
    public LinkedList<Product> getItems() {
        lock.lock();
        try {
            return inCartItems;
        } finally {
            lock.unlock();            
        }
    }
    public void addItem(Product item){
        lock.lock();
        try {
            inCartItems.add(item);
        } finally {
            lock.unlock();            
        }
    }
    public void removeItem(int productIndex){
        lock.lock();
        try {
            inCartItems.remove(productIndex);
        } finally {
            lock.unlock();
        }
    }
    public void clear(){
        lock.lock();
        try {
            inCartItems.clear();
        } finally {
            lock.unlock();
        }
    }


}
