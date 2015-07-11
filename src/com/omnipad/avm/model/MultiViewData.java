package com.omnipad.avm.model;

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
	
	public MultiViewData(ByteBuffer buf) {
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

	public byte[] getBytes() {
		ByteBuffer buf = ByteBuffer.allocate(1024);
		buf.order(TLVFormat.order);
		
		
		buf.putInt(flags);
		buf.putInt(carWidth);
		buf.putInt(carLength);
		buf.putInt(carHeight);
		buf.putInt(carWheelBase);
		buf.putInt(carTread);

		buf.putFloat(maskFfov);
		buf.putFloat(maskRfov);
		buf.putFloat(maskBlend);
		
		buf.putInt(maskColor);
		buf.putInt(maskWidth);
		buf.putInt(maskType);

		buf.putFloat(options[0]);
		buf.putFloat(options[1]);
		buf.putFloat(options[2]);
		buf.putFloat(options[3]);
		
		buf.putFloat(mmPerPixel);
		
		buf.putInt(offsetY);
		buf.putInt(dsizeWidth);
		buf.putInt(dsizeHeight);

		buf.putFloat(dsizeCenter.x);
		buf.putFloat(dsizeCenter.y);

		buf.putInt(offsetX);
		buf.putInt(offsetY);

		byte[] ret = new byte[buf.position()];
		
		System.arraycopy(buf.array(), 0, ret, 0, ret.length);
		
		return ret;	
	}
}
