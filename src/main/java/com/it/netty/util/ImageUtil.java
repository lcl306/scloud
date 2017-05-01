package com.it.netty.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class ImageUtil {
	
	public static byte[] file2bytes(File file){
		byte[] rtn = null;
		if(file!=null){
			InputStream in = null;
			ByteArrayOutputStream out = null;
			try{
				in = new FileInputStream(file);
				out = new ByteArrayOutputStream();
				byte[] buffer = new byte[1024];
				int len = 0;
				while((len=in.read(buffer))!=-1){
					out.write(buffer, 0, len);
				}
				rtn = out.toByteArray();
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try{
					if(in!=null) in.close();
					if(out!=null) out.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
		return rtn;
	}
	
	public static void bytes2file(File file, byte[] src){
		if(file!=null && src!=null){
			OutputStream os = null;
			BufferedOutputStream bos = null;
			try{
				os = new FileOutputStream(file);
				bos = new BufferedOutputStream(os);
				os.write(src);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				try{
					if(bos!=null) bos.close();
					if(os!=null) os.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	
	public static byte[] file2bytes(String filename){
		return file2bytes(new File(filename));
	}
	
	public static void bytes2file(String path, String filename, byte[] src){
		File dir = new File(path);
		if(dir.isDirectory() && !dir.exists()){
			dir.mkdir();
		}
		File file = new File(path+File.separator+filename);
		bytes2file(file, src);
	}

}
