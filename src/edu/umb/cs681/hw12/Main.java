package edu.umb.cs681.hw12;

import java.util.LinkedList;
import java.util.stream.IntStream;

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
        System.out.printf("\tStarting %d parallel instances of RequestHandler...\n",
                          numThreads);
        threads.forEach((t) -> t.start());
        try {
            Thread.sleep(1000);            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        runnables.forEach( (r) -> r.gracefulStop());
    }

}
