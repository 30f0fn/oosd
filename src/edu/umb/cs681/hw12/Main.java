package edu.umb.cs681.hw12;

import java.util.LinkedList;
import java.util.stream.IntStream;

// Creates 10 instances of RequestHandler and use 10 threads to execute
// RequestHandler’s run().
// • Implement 2-step thread termination in RequestHandler.
// – Have the main thread terminate those 10 extra threads in 2 steps. 

public class Main {
    
    public static void main(String[] args) {
        System.out.println("HW 12...");
        int numThreads = 10;
        LinkedList<Thread> threads = new LinkedList<Thread>();
        LinkedList<RequestHandler> runnables = new LinkedList<RequestHandler>();
        IntStream.range(0, numThreads).forEach((i) -> {
                RequestHandler handler = new RequestHandler();
                Thread thread = new Thread(handler);
                runnables.add(handler);
                threads.add(thread);
            });
        System.out.println("\tStarting 10 parallel instances of RequestHandler...");
        threads.forEach((t) -> t.start());
        try {
            Thread.sleep(1000);            
        } catch (InterruptedException e) {
            
        }
        runnables.forEach( (r) -> r.gracefulStop());
        // runnables.forEach( (r) -> r.setDone());
        // threads.forEach( (t) -> t.interrupt());
    }

}
