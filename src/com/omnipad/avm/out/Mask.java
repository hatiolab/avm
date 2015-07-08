package com.omnipad.avm.out;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.omnipad.avm.camera.Camera;

public class Mask {
	public static final float SHOWMASK = 0.1f;
	public static final float HIDEMASK = 1.0f;

	int width;
	int height;
	int len; // 바이트 길이
	Data[] data;

	class Data {
		byte r;
		byte g;
		byte b;
	}

	public Mask(int width, int height) {
		this.width = width;
		this.height = height;
		
		this.len = height * ((width * 24 / 8 + 3) & ~3);
		
		this.data = new Data[width * height];
	}
	
	public void store(String path) throws IOException {
		FileOutputStream fos = new FileOutputStream(path);
		DataOutputStream dos = new DataOutputStream(fos);

		dos.writeInt(width);
		dos.writeInt(height);
		dos.writeInt(len);
		
		for(int i = 0;i < data.length;i++) {
			dos.write(data[i].r);
			dos.write(data[i].g);
			dos.write(data[i].b);
		}
		
		dos.close();
	}
	
	void findMaskValue(Camera camera, int offset, VNT.Data vnt, Box box) {
		Mask.Data mask = this.data[offset];

		switch (camera) {
		case FRONT:
			if (mask.b == 0 && mask.g == 0 && mask.r == 0)
				box.maxY = Math.min(vnt.y, box.maxY);
			break;
		case RIGHT:
			if (mask.b == 0 && mask.g == 255 && mask.r == 0)
				box.maxX = Math.min(vnt.x, box.maxX);
			break;
		case LEFT:
			if (mask.b == 0 && mask.g == 0 && mask.r == 255)
				box.minX = Math.max(vnt.x, box.minX);
			break;
		case REAR:
			if (mask.b == 0 && mask.g == 255 && mask.r == 255)
				box.minY = Math.max(vnt.y, box.minY);
			break;
		default:
			break;
		}
	}

	/* TODO emptyFind 가 리턴 파라미터이다. */
	public static float maskValue(Mask mask, Camera camera, int offset, int emptyFind) {
		if(mask == null)
			return SHOWMASK;

		Mask.Data maskData = mask.data[offset];
		emptyFind = 0;
		
		switch(camera) {
		case FRONT:
			if(maskData.b == 0 && maskData.g == 0 && maskData.r == 0)
				return SHOWMASK;
			if(maskData.r == 0 && maskData.g == 255 && maskData.b >= 1 && maskData.b <= 255)
				return ((float)(255 - maskData.b)) / 255;
			if(maskData.r == 0 && maskData.g == 0 && maskData.b >= 1 && maskData.b <= 255)
				return ((float)(255 - maskData.b)) / 255;
			
			emptyFind = 1;
			
			break;
		case RIGHT:
			if(maskData.b == 0 && maskData.g == 255 && maskData.r == 0)
				return SHOWMASK;
			if(maskData.r == 0 && maskData.g == 255 && maskData.b >= 1 && maskData.b <= 255)
				return ((float)(maskData.b)) / 255;
			if(maskData.r == 255 && maskData.g == 255 && maskData.b >= 1 && maskData.b <= 255)
				return ((float)(maskData.b)) / 255;
			
			emptyFind = 2;
			
			break;
		case LEFT:
			if(maskData.b == 0 && maskData.g == 0 && maskData.r == 255)
				return SHOWMASK;
			if(maskData.r == 0 && maskData.g == 0 && maskData.b >= 1 && maskData.b <= 255)
				return ((float)(maskData.b)) / 255;
			if(maskData.r == 255 && maskData.g == 0 && maskData.b >= 1 && maskData.b <= 255)
				return ((float)(maskData.b)) / 255;

			emptyFind = 3;

			break;
		case REAR:
			if(maskData.b == 0 && maskData.g == 255 && maskData.r == 255)
				return SHOWMASK;
			if(maskData.r == 255 && maskData.g == 0 && maskData.b >= 1 && maskData.b <= 255)
				return ((float)(255 - maskData.b)) / 255;
			if(maskData.r == 255 && maskData.g == 0 && maskData.b >= 1 && maskData.b <= 255)
				return ((float)(255 - maskData.b)) / 255;

			emptyFind = 4;

			break;
		default:
			break;
		}

		return HIDEMASK;
	}
}
