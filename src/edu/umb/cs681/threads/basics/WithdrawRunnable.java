package edu.umb.cs681.threads.basics;

public class WithdrawRunnable implements Runnable{
	private ThreadUnsafeBankAccount account;
	
	public WithdrawRunnable(ThreadUnsafeBankAccount account) {
		this.account = account;
	}
	
	public void run(){
		try{
			for(int i = 0; i < 10; i++){
				account.withdraw(100);
				Thread.sleep(2);
			}
		}catch(InterruptedException exception){}
	}
}
