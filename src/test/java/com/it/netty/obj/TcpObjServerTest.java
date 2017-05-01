package com.it.netty.obj;


public class TcpObjServerTest {
	
	public static void main(String[] args) {
		TcpObjServer.start(ObjServerHandler.class);
	}

}
