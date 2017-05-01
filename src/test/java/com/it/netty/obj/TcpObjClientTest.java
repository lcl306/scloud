package com.it.netty.obj;

import java.util.concurrent.CountDownLatch;

import com.it.netty.base.TcpClientTest;
import com.it.netty.base.TcpClientTest.TcpClientIn;

public class TcpObjClientTest {
	
	public static void main(String[] args){
		TcpClientTest.exec(new TcpClientIn() {
			@Override
			public void client(CountDownLatch countDownLatch) throws Exception {
				countDownLatch.await();
				TcpObjClient.connect(ObjClientHandler.class);		
			}
		});
	}

}
