package edu.umb.cs681.hw13;

import java.util.concurrent.locks.ReentrantLock;

public class Customer {
    private Address address;
    private ReentrantLock lock;

    public Customer(Address addr) {
        this.address = addr;
        this.lock = new ReentrantLock();
    }
    
    public void setAddress(Address addr) {
        lock.lock();
        try {
            address = addr;
        } finally {
            lock.unlock();
        }        
    }

    public void setAddressNoisily(Address addr) {
        lock.lock();
        try {
            address = addr;
            System.out.printf("\tSetting address to \n\t\t%s\n", addr);
        } finally {
            lock.unlock();
        }        
    }


    public Address getAddress() {
        Address addr;
        lock.lock();
        addr = this.address;
        lock.unlock();
        return addr;
    }
}
