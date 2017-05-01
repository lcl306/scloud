package com.it.netty.lenfield;

/**
 * maxFrameLength：解码的帧的最大长度
 * lengthFieldOffset ：长度属性的起始位（偏移位）
 * lengthFieldLength：长度属性的长度，即存放整个大数据包长度的字节所占的长度
 * lengthAdjustmen：长度调节值，在总长被定义为包含包头长度时，修正信息长度。为负数时，Length表示的是整个帧的长度，为正数或0时，表示真实数据长度
 * initialBytesToStrip：跳过的字节数，根据需要我们跳过lengthFieldLength个字节，以便接收端直接接受到不含“长度属性”的内容
 http://blog.163.com/linfenliang@126/blog/static/127857195201210821145721/
 eg:
   engthFieldOffset    = 1 (= the length of HDR1)  
   lengthFieldLength   = 2 (长度是12，未包含HDR1，Length，HDR2，表示ActualConent的长度)
   lengthAdjustment    = 1 (= the length of HDR2)
   initialBytesToStrip = 3 (= the length of HDR1 + LEN)
   
   BEFORE DECODE (16 bytes)                       AFTER DECODE (13 bytes)
   +------+--------+------+----------------+      +------+----------------+
   | HDR1 | Length | HDR2 | Actual Content |      | HDR2 | Actual Content |
   | 0xCA | 0x000C | 0xFE | "HELLO, WORLD" |----->| 0xFE | "HELLO, WORLD" |
   +------+--------+------+----------------+      +------+----------------+
                  因为读取HDR2+ActualConent内容，所以长度是ActualConent的长度+HDR2的长度，所以长度要加1，即lengthAdjustment=1
  eg:
   engthFieldOffset    = 1 (= the length of HDR1)  
   lengthFieldLength   = 2 (长度是16，HDR1+Length+HDR2+ActualConent的长度)
   lengthAdjustment    =-3 (= the length of HDR2)
   initialBytesToStrip = 3 (= the length of HDR1 + LEN)
   
   BEFORE DECODE (16 bytes)                       AFTER DECODE (13 bytes)
   +------+--------+------+----------------+      +------+----------------+
   | HDR1 | Length | HDR2 | Actual Content |      | HDR2 | Actual Content |
   | 0xCA | 0x0010 | 0xFE | "HELLO, WORLD" |----->| 0xFE | "HELLO, WORLD" |
   +------+--------+------+----------------+      +------+----------------+
                  因为读取HDR2+ActualConent内容，所以长度是ActualConent的长度+HDR2的长度，所以长度要减3，即lengthAdjustment=-3
 * */
public class LenFieldDto {
	
	private int maxFrameLength = Integer.MAX_VALUE;
	
	private int lengthFieldOffset;
	
	private int lengthFieldLength;
	
	private int lengthAdjustment;
	
	private int initialBytesToStrip;
	
	public LenFieldDto(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustmen, int initialBytesToStrip) {
		super();
		this.maxFrameLength = maxFrameLength;
		this.lengthFieldOffset = lengthFieldOffset;
		this.lengthFieldLength = lengthFieldLength;
		this.lengthAdjustment = lengthAdjustmen;
		this.initialBytesToStrip = initialBytesToStrip;
	}
	
	public LenFieldDto(int lengthFieldOffset, int lengthFieldLength, int lengthAdjustmen, int initialBytesToStrip){
		this.lengthFieldOffset = lengthFieldOffset;
		this.lengthFieldLength = lengthFieldLength;
		this.lengthAdjustment = lengthAdjustmen;
		this.initialBytesToStrip = initialBytesToStrip;
	}
	
	public LenFieldDto(){
		
	}

	public int getMaxFrameLength() {
		return maxFrameLength;
	}

	public void setMaxFrameLength(int maxFrameLength) {
		this.maxFrameLength = maxFrameLength;
	}

	public int getLengthFieldOffset() {
		return lengthFieldOffset;
	}

	public void setLengthFieldOffset(int lengthFieldOffset) {
		this.lengthFieldOffset = lengthFieldOffset;
	}

	public int getLengthFieldLength() {
		return lengthFieldLength;
	}

	public void setLengthFieldLength(int lengthFieldLength) {
		this.lengthFieldLength = lengthFieldLength;
	}

	public int getLengthAdjustment() {
		return lengthAdjustment;
	}

	public void setLengthAdjustment(int lengthAdjustment) {
		this.lengthAdjustment = lengthAdjustment;
	}

	public int getInitialBytesToStrip() {
		return initialBytesToStrip;
	}

	public void setInitialBytesToStrip(int initialBytesToStrip) {
		this.initialBytesToStrip = initialBytesToStrip;
	}

	
}
