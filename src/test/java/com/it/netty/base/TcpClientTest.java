package com.it.netty.base;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TcpClientTest {
	
	static Integer clientNumber = 5;
    static final CountDownLatch countDownLatch = new CountDownLatch(clientNumber);
	static ExecutorService threadPool = Executors.newFixedThreadPool(clientNumber);
	
	public static void exec(final TcpClientIn clientIn){
		for(int index=0; index<clientNumber; index++, countDownLatch.countDown()) {
			threadPool.execute(new Runnable(){
            	
				public void run() {
					try{
						clientIn.client(countDownLatch);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
            });
        }
		threadPool.shutdown();
	}
	
	public static interface TcpClientIn{
		
		void client(CountDownLatch countDownLatch) throws Exception;
	}

}
