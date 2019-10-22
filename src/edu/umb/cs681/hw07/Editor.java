package edu.umb.cs681.hw07;

import java.util.concurrent.locks.ReentrantLock;

public class Editor implements Runnable {

    private File openFile;
    private boolean done = false;
    private ReentrantLock lock;

    Editor(File f) {
        openFile = f;
        lock = new ReentrantLock();
    }

    public void run() {
        
        while(true){
            lock.lock();
            try {
                if(done){ 
                    System.out.println("\tEditor: interrupted!");
                    break;
                }
                String newContents = RandomString.generate();
                System.out.printf("\tEditor: changing file contents to %s...\n",
                                  newContents);
                openFile.change(newContents);
                System.out.printf("\tEditor: saving...\n");
                openFile.save();
                Thread.sleep(1000);            
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
    
    public void setDone() {
        done = true;
    }

}
