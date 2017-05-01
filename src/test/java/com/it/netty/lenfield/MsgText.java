package com.it.netty.lenfield;

import io.netty.buffer.ByteBuf;

import com.it.netty.util.BufUtil;

public class MsgText {
	
	//类型  系统编号 0xAB 表示A系统，0xBC 表示B系统  
    private byte type;  
      
    //信息标志  0xAB 表示心跳包    0xBC 表示超时包  0xCD 业务信息包  
    private byte flag;  
      
    //主题信息的长度  
    private int length;  
	//主题信息  
    private String body;
    //主题信息  
    private byte[] image;

	public byte getType() {
		return type;
	}

	public void setType(byte type) {
		this.type = type;
	}

	public byte getFlag() {
		return flag;
	}

	public void setFlag(byte flag) {
		this.flag = flag;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "MsgText [type=" + type + ", flag=" + flag + ", length="
				+ length + ", body=" + body + "]";
	} 
    
	private static final int HEAD_SIZE = 6; //byte+byte+int = 1+1+4 = 6
	
	public static MsgText trans(ByteBuf in)throws Exception{
		if(in==null) return null;
		if(in.readableBytes()<HEAD_SIZE) throw new Exception("可读信息段比头部信息小。");
		MsgText text = new MsgText();
		text.setType(in.readByte());
		text.setFlag(in.readByte());
		int length = in.readInt();
		text.setLength(length);
		if(in.readableBytes()<length) throw new Exception("body的实际长度小于length");
		int bodylen = in.readInt();
		int imagelen = in.readInt();
		if(bodylen>0) text.setBody(new String(BufUtil.buf2bytes(in, bodylen), BufUtil.CODE));
		if(imagelen>0) text.setImage(BufUtil.buf2bytes(in, imagelen));
		return text;
	}
	
	public static LenFieldDto lenFieldDto = new LenFieldDto(2, 4, 0, 0);
    

}
