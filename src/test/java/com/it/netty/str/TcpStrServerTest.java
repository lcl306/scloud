package com.it.netty.str;

public class TcpStrServerTest {

	public static void main(String[] args) {
		TcpStrServer.start(new StrServerHandler());
	}

}
