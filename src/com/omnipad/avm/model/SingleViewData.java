package com.omnipad.avm.model;

import java.nio.ByteBuffer;

import com.omnipad.avm.Const;
import com.omnipad.avm.TLVFormat;

public class SingleViewData {
	public int flags;
	public float w;
	public float vx;
	public float vy;
	public float tx;
	public float ty;
	public float rot;
	public float sx;
	public float sy;
	public float shx;
	public float shy;
	public float secWidth;
	
	public int dsizeWidth;
	public int dsizeHeight;
	public Point2D dsizeCenter = new Point2D();
	
	public int offsetX;
	public int offsetY;
	
	public int carWidth;	// 자동차 전폭
	public int carLength;	// 자동차 전장
	
	public float mmPerPixel;	// 출력 영상 단위 픽셀 당 공간해상도
	
	public CameraData cameraData;
	
	public SingleViewData() {
	}

	public SingleViewData(ByteBuffer buf) {
		flags = buf.getInt();
		w = buf.getFloat();
		vx = buf.getFloat();
		vy = buf.getFloat();
		tx = buf.getFloat();
		ty = buf.getFloat();
		rot = buf.getFloat();
		sx = buf.getFloat();
		sy = buf.getFloat();
		shx = buf.getFloat();
		shy = buf.getFloat();
		secWidth = buf.getFloat();

		offsetY = buf.getInt(); // TODO 확인 요. 원본 확인.
		dsizeWidth = buf.getInt();
		dsizeHeight = buf.getInt();
	    dsizeCenter.x = buf.getFloat();
	    dsizeCenter.y = buf.getFloat();
		offsetX = buf.getInt();
		offsetY = buf.getInt();
		
		flags |= Const.HV_ENABLE;
	}

	public byte[] getBytes() {
		ByteBuffer buf = ByteBuffer.allocate(1024);
		buf.order(TLVFormat.order);
		
		buf.putInt(flags);

		buf.putFloat(w);
		buf.putFloat(vx);
		buf.putFloat(vy);
		buf.putFloat(tx);
		buf.putFloat(ty);
		buf.putFloat(rot);
		buf.putFloat(sx);
		buf.putFloat(sy);
		buf.putFloat(shx);
		buf.putFloat(shy);
		buf.putFloat(secWidth);

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
