package com.omnipad.avm.model;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import com.omnipad.avm.TLVFormat;

public class MultiViewData {
	public int flags = 0;	// interpolation method (0: none, 1: bilinear, 2: bicubic)
	
	public int carWidth = 0;		// 자동차 전폭
	public int carLength = 0;		// 자동차 전장
	public int carHeight = 0;		// 자동차 높이 
	public int carWheelBase = 0;	// 축거
	public int carTread = 0;		// 윤거 (주차선폭)
	
	public float maskFfov = 0.0f;
	public float maskRfov = 0.0f;
	public float maskBlend = 0.0f;
	public int maskColor = 0;
	public int maskWidth = 0;
	public int maskType = 0;	// 0: user define, 1: blend, 2: 구분선 영역 12개, 3: 구분선 영역 4개
	
	// view offset
	public int offsetX = 0;
	public int offsetY = 0;
	
	public int dsizeWidth = 0;
	public int dsizeHeight = 0;
	public Point2D dsizeCenter = new Point2D();
	public Point2D cp = new Point2D();	// 카메라 이미지 좌표

	public float mmPerPixel = 0.0f;	// 출력 영상 단위 픽셀 당 공간해상도

	// 3D AVM
	public float[] options = new float[10];	// size 10, 0:BOWL_X, 1:BOWL_Y, 2:BOWL_Z, 3:BOWL_R, 4:Blend
	
	public CameraData[] cameraData;
	
	public MultiViewData() {
		dsizeCenter = new Point2D();
	}
	
	public MultiViewData(ByteBuffer buf) throws IOException {
		flags = buf.getInt();
		carWidth = buf.getInt();
		carLength = buf.getInt();
		carHeight = buf.getInt();
		carWheelBase = buf.getInt();
		carTread = buf.getInt();
		
		maskFfov = buf.getFloat();
		maskRfov = buf.getFloat();
		maskBlend = buf.getFloat();
		maskColor = buf.getInt();
		maskWidth = buf.getInt();
		maskType = buf.getInt();
		
		// mask car position
		options[0] = buf.getFloat(); // top
		options[1] = buf.getFloat(); // left
		options[2] = buf.getFloat(); // right
		options[3] = buf.getFloat(); // bottom
		
		mmPerPixel = buf.getFloat();

		offsetY = buf.getInt(); // TODO 확인할 것. - 원본.

		dsizeWidth = buf.getInt();
		dsizeHeight = buf.getInt();
		dsizeCenter.x = buf.getFloat();
		dsizeCenter.y = buf.getFloat();
		
		offsetX = buf.getInt();
		offsetY = buf.getInt();
	}

	// FIXME
	public static MultiViewData load(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		
		MultiViewData data = new MultiViewData();

		data.flags = TLVFormat.readTLV(fis).getIntValue();
		data.carWidth = TLVFormat.readTLV(fis).getIntValue();
		data.carLength = TLVFormat.readTLV(fis).getIntValue();
		data.carHeight = TLVFormat.readTLV(fis).getIntValue();
		data.carTread = TLVFormat.readTLV(fis).getIntValue();
		data.carWheelBase = TLVFormat.readTLV(fis).getIntValue();
		
		data.maskFfov = TLVFormat.readTLV(fis).getFloatValue();
		data.maskRfov = TLVFormat.readTLV(fis).getFloatValue();
		data.maskBlend = TLVFormat.readTLV(fis).getFloatValue();
		data.maskWidth = TLVFormat.readTLV(fis).getIntValue();
		data.maskType = TLVFormat.readTLV(fis).getIntValue();
		
		data.dsizeWidth = TLVFormat.readTLV(fis).getIntValue();
		data.dsizeHeight = TLVFormat.readTLV(fis).getIntValue();
		data.dsizeCenter.x = TLVFormat.readTLV(fis).getFloatValue();
		data.dsizeCenter.y = TLVFormat.readTLV(fis).getFloatValue();
		
		data.mmPerPixel = TLVFormat.readTLV(fis).getFloatValue();
		
		data.offsetX = TLVFormat.readTLV(fis).getIntValue();
		data.offsetY = TLVFormat.readTLV(fis).getIntValue();

	    fis.close();
		
		return data;
	}
	
	// FIXME
	public void store(String path) throws IOException {
		FileOutputStream fos = new FileOutputStream(path);
		DataOutputStream dos = new DataOutputStream(fos);

		TLVFormat.write(dos, 1, flags);
		TLVFormat.write(dos, 2, carWidth);
		TLVFormat.write(dos, 3, carLength);
		TLVFormat.write(dos, 4, carHeight);
		TLVFormat.write(dos, 5, carTread);
		TLVFormat.write(dos, 6, carWheelBase);
		TLVFormat.write(dos, 7, maskFfov);
		TLVFormat.write(dos, 8, maskRfov);
		TLVFormat.write(dos, 9, maskBlend);
		TLVFormat.write(dos, 10, maskWidth);
		TLVFormat.write(dos, 11, maskType);
		TLVFormat.write(dos, 12, dsizeWidth);
		TLVFormat.write(dos, 13, dsizeHeight);
		TLVFormat.write(dos, 14, dsizeCenter.x);
		TLVFormat.write(dos, 15, dsizeCenter.y);
		TLVFormat.write(dos, 16, mmPerPixel);
		TLVFormat.write(dos, 17, offsetX);
		TLVFormat.write(dos, 18, offsetY);
		
		dos.close();
	}
}
