package com.it.netty.lenfield;


public class TcpLenFieldServerTest {
	
	public static void main(String[] args) {
		TcpLenFieldServer.start(MsgText.lenFieldDto, MsgTextEncoder.class, LenFieldServerHandler.class);
	}

}
