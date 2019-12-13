package edu.umb.cs681.hw18;

import java.util.LinkedList;
import java.util.stream.IntStream;

import edu.umb.cs681.hw12.RequestHandler;
import edu.umb.cs681.hw12.AccessCounterInterface;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("HW 18...");
        AccessCounterInterface counter = new ThreadSafeAccessCounter();
        int numThreads = 10;
        LinkedList<Thread> threads = new LinkedList<Thread>();
        LinkedList<RequestHandler> runnables = new LinkedList<RequestHandler>();
        IntStream.range(0, numThreads).forEach((i) -> {
                RequestHandler handler = new RequestHandler(counter);
                Thread thread = new Thread(handler);
                runnables.add(handler);
                threads.add(thread);
            });
        System.out.printf("\tMain.main: starting %d parallel instances of RequestHandler...\n",
                          numThreads);
        threads.forEach((t) -> t.start());
        try {
            Thread.sleep(1000);            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        runnables.forEach( (r) -> r.gracefulStop());
        threads.forEach((t) -> {try {t.join();} catch (InterruptedException e) {}});
        System.out.printf("\tMain.main: done\n");
        counter.printData();
    }

}


