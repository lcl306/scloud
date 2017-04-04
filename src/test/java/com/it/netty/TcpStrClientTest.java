package com.it.netty;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TcpStrClientTest {
	
	static Integer clientNumber = 5;
    static final CountDownLatch countDownLatch = new CountDownLatch(clientNumber);
	static ExecutorService threadPool = Executors.newFixedThreadPool(clientNumber);
	
	public static void main(String[] args){	
		for(int index=0; index<clientNumber; index++, countDownLatch.countDown()) {
			threadPool.execute(new Runnable(){
            	
				public void run() {
					TcpStrClient client = new TcpStrClient();
			        try {
						client.connect(new TcpStrClientTestHandler(countDownLatch));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
            });
        }
		threadPool.shutdown();
	}

}
