package edu.umb.cs681.hw15;

import edu.umb.cs.threads.basics.DepositRunnable; 
import edu.umb.cs.threads.basics.WithdrawRunnable; 

public class Main {

    public static void main(String[] args) {
        System.out.println("HW 15...");

        TwoStepBankAccount bankAccount = new TwoStepBankAccount();        

        System.out.println("\tMain.main: attempting withdrawal on empty account...");
        Thread t = new Thread(() -> bankAccount.withdraw(100));
        t.start();
        t.interrupt();
        try {
            t.join();
        } catch (InterruptedException e) {}


        System.out.println("\tMain.main: attempting deposit to funds limit");
        bankAccount.deposit(300);

        Thread t1 = new Thread(() -> {
                bankAccount.deposit(100);
        });
        Thread t2 = new Thread(() -> {

                bankAccount.deposit(100);
        });
        System.out.printf("\tMain.main: attempting deposits on threads %d and %d\n",
                          t1.getId(), t2.getId());
        t1.start();
        t2.start();
        t1.interrupt();
        try {
            t1.join();
        } catch (InterruptedException e) {}

        System.out.printf("\tMain.main: attempting withdrawal\n");
        bankAccount.withdraw(100);


    }
    
}
