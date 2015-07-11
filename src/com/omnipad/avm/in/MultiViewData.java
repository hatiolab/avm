package com.omnipad.avm.in;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.omnipad.avm.TLVFormat;
import com.omnipad.avm.calib.Util;

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
	
	public MultiViewData(InputStream is) throws IOException {
		flags = Util.readCalibInfoInt(is);
		carWidth = Util.readCalibInfoInt(is);
		carLength = Util.readCalibInfoInt(is);
		carHeight = Util.readCalibInfoInt(is);
		carWheelBase = Util.readCalibInfoInt(is);
		carTread = Util.readCalibInfoInt(is);
		
		maskFfov = Util.readCalibInfoFloat(is);
		maskRfov = Util.readCalibInfoFloat(is);
		maskBlend = Util.readCalibInfoFloat(is);
		maskColor = Util.readCalibInfoInt(is);
		maskWidth = Util.readCalibInfoInt(is);
		maskType = Util.readCalibInfoInt(is);
		
		// mask car position
		options[0] = Util.readCalibInfoFloat(is); // top
		options[1] = Util.readCalibInfoFloat(is); // left
		options[2] = Util.readCalibInfoFloat(is); // right
		options[3] = Util.readCalibInfoFloat(is); // bottom
		
		mmPerPixel = Util.readCalibInfoFloat(is);

		offsetY = Util.readCalibInfoInt(is); // TODO 확인할 것. - 원본.

		dsizeWidth = Util.readCalibInfoInt(is);
		dsizeHeight = Util.readCalibInfoInt(is);
		dsizeCenter.x = Util.readCalibInfoFloat(is);
		dsizeCenter.y = Util.readCalibInfoFloat(is);
		
		offsetX = Util.readCalibInfoInt(is);
		offsetY = Util.readCalibInfoInt(is);
}

	public static MultiViewData load(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		DataInputStream dis = new DataInputStream(fis);
		
		MultiViewData data = new MultiViewData();

		data.flags = TLVFormat.readInt(dis);
		data.carWidth = TLVFormat.readInt(dis);
		data.carLength = TLVFormat.readInt(dis);
		data.carHeight = TLVFormat.readInt(dis);
		data.carTread = TLVFormat.readInt(dis);
		data.carWheelBase = TLVFormat.readInt(dis);
		
		data.maskFfov = TLVFormat.readFloat(dis);
		data.maskRfov = TLVFormat.readFloat(dis);
		data.maskBlend = TLVFormat.readFloat(dis);
		data.maskWidth = TLVFormat.readInt(dis);
		data.maskType = TLVFormat.readInt(dis);
		
		data.dsizeWidth = TLVFormat.readInt(dis);
		data.dsizeHeight = TLVFormat.readInt(dis);
		data.dsizeCenter.x = TLVFormat.readFloat(dis);
		data.dsizeCenter.y = TLVFormat.readFloat(dis);
		
		data.mmPerPixel = TLVFormat.readFloat(dis);
		
		data.offsetX = TLVFormat.readInt(dis);
		data.offsetY = TLVFormat.readInt(dis);

	    dis.close();
		
		return data;
	}
	
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
