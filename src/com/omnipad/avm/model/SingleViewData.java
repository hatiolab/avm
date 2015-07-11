package com.omnipad.avm.model;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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

	public SingleViewData(ByteBuffer buf) throws IOException {
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

	// FIXME
	public static SingleViewData load(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		
		SingleViewData data = new SingleViewData();

		data.flags = TLVFormat.readTLV(fis).getIntValue();
		data.w = TLVFormat.readTLV(fis).getFloatValue();
		data.vx = TLVFormat.readTLV(fis).getFloatValue();
		data.vy = TLVFormat.readTLV(fis).getFloatValue();
		data.tx = TLVFormat.readTLV(fis).getFloatValue();
		data.ty = TLVFormat.readTLV(fis).getFloatValue();
		data.rot = TLVFormat.readTLV(fis).getFloatValue();
		data.sx = TLVFormat.readTLV(fis).getFloatValue();
		data.sy = TLVFormat.readTLV(fis).getFloatValue();
		data.shx = TLVFormat.readTLV(fis).getFloatValue();
		data.shy = TLVFormat.readTLV(fis).getFloatValue();
		data.secWidth = TLVFormat.readTLV(fis).getFloatValue();

		data.dsizeWidth = TLVFormat.readTLV(fis).getIntValue();
		data.dsizeHeight = TLVFormat.readTLV(fis).getIntValue();
		data.offsetX = TLVFormat.readTLV(fis).getIntValue();
		data.offsetY = TLVFormat.readTLV(fis).getIntValue();
		data.carWidth = TLVFormat.readTLV(fis).getIntValue();
		data.carLength = TLVFormat.readTLV(fis).getIntValue();

	    data.dsizeCenter.x = data.dsizeWidth * 0.5f;
	    data.dsizeCenter.y = data.dsizeHeight * 0.5f;

	    fis.close();
		
		return data;
	}
	
	// FIXME
	public void store(String path) throws IOException {
		FileOutputStream fos = new FileOutputStream(path);
		DataOutputStream dos = new DataOutputStream(fos);

		TLVFormat.write(dos, 1, flags);
		TLVFormat.write(dos, 2, w);
		TLVFormat.write(dos, 3, vx);
		TLVFormat.write(dos, 4, vy);
		TLVFormat.write(dos, 5, tx);
		TLVFormat.write(dos, 6, ty);
		TLVFormat.write(dos, 7, rot);
		TLVFormat.write(dos, 8, sx);
		TLVFormat.write(dos, 9, sy);
		TLVFormat.write(dos, 10, shx);
		TLVFormat.write(dos, 11, shy);
		TLVFormat.write(dos, 12, secWidth);

		TLVFormat.write(dos, 13, dsizeWidth);
		TLVFormat.write(dos, 14, dsizeHeight);
		TLVFormat.write(dos, 15, offsetX);
		TLVFormat.write(dos, 16, offsetY);
		TLVFormat.write(dos, 17, carWidth);
		TLVFormat.write(dos, 18, carLength);
		
		dos.close();
	}
}
