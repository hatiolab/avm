package com.omnipad.avm;

import java.io.FileInputStream;
import java.io.IOException;

public class YUV420 {
	public static int size(int w, int h) {
		int lumaSize = w * h;
		int chromaSize = w * h / 2;
		return lumaSize + chromaSize;
	}
	
	public static byte[] load(String path, int w, int h) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		
		byte[] image = new byte[size(w, h)];
		
		fis.read(image);
		
		fis.close();
		
		return image;
	}
}
