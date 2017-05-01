package com.it.netty.lenfield;

import java.util.concurrent.CountDownLatch;

import com.it.netty.base.TcpClientTest;
import com.it.netty.base.TcpClientTest.TcpClientIn;

public class TcpLenFieldClientTest {
	
	public static void main(String[] args){
		TcpClientTest.exec(new TcpClientIn() {
			@Override
			public void client(CountDownLatch countDownLatch) throws Exception {
				countDownLatch.await();
				TcpLenFieldClient.connect(MsgText.lenFieldDto, MsgTextEncoder.class, LenFieldClientHandler.class);		
			}
		});
	}

}
