package edu.umb.cs681.hw14;

import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util. LinkedList;

public class Main {
    static float REMOVAL_FREQ = .1f;
    static int NUM_THREADS = 100;
    static int NUM_ACTIONS = 100;
    static float addFreq = 1 - REMOVAL_FREQ;

    static Cart cart = new Cart();
    static Random rnd = new Random();

    static Runnable addProduct = () -> {
        cart.addItem(new Product());
        // System.out.printf("added item, cart size now %d\n",
        //                   cart.getItems().size());
    };
    static Runnable removeRandomProduct = () -> {
        int numItems = cart.getItems().size();
        if (numItems == 0) {
            // System.out.println("nothing to remove from empty cart!");
            return;
        };
        int cartPosition = (int) (rnd.nextFloat() * numItems - 1);
        cart.removeItem(cartPosition);
        // System.out.println("removed item");
    };

    static Function<Integer, Runnable> randomCartActions =
            (Integer numActions) -> () ->
            {
                IntStream.rangeClosed(1, numActions)
                .forEach((i) -> {
                        if (rnd.nextFloat() > REMOVAL_FREQ) {
                            addProduct.run();
                        } else {
                            removeRandomProduct.run();
                        };
                    });
                // System.out.println("thread finished");
            };

    public static void main(String[] args) {
        System.out.println("HW 14...");
        sequential();
        parallel();
    }

    static void sequential() {
        randomCartActions.apply(NUM_ACTIONS).run();
        System.out.printf("\tMain.sequential: cart now contains %d items, should be ~ %d\n",
                          cart.getItems().size(),
                          (int) (NUM_ACTIONS * (addFreq - REMOVAL_FREQ)));
        cart.clear();
    }

    static void parallel() {
        LinkedList<Thread> threads = new LinkedList<Thread>();
        IntStream.rangeClosed(1,NUM_THREADS)
            .forEach((i) -> {
                    Thread t = new Thread(randomCartActions.apply(NUM_ACTIONS));
                    threads.add(t);
                    t.start();
                });

        threads.forEach((t) -> {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    System.out.printf("\tMain.parallel: thread %d interrupted\n", t.getId());
                    // e.printStackTrace();
                }
            });
        
        System.out.printf("\tMain.parallel: cart now contains %d items, should be ~ %d\n",
                          cart.getItems().size(),
                          (int) (NUM_THREADS * NUM_ACTIONS * (addFreq - REMOVAL_FREQ)));
        
        cart.clear();
    }

}
