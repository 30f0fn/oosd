package edu.umb.cs681.threads.basics;

public class DepositRunnable implements Runnable{
	private ThreadUnsafeBankAccount account;
	
	public DepositRunnable(ThreadUnsafeBankAccount account) {
		this.account = account;
	}
	
	public void run(){
		try{
			for(int i = 0; i < 10; i++){
				account.deposit(100);
				Thread.sleep(2);
			}
		}catch(InterruptedException exception){}
	}
}
