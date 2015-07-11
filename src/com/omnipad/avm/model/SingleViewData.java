package com.omnipad.avm.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.omnipad.avm.TLVFormat;
import com.omnipad.avm.calib.Const;
import com.omnipad.avm.calib.Util;

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

	public SingleViewData(InputStream is) throws IOException {
		flags = Util.readCalibInfoInt(is);
		w = Util.readCalibInfoFloat(is);
		vx = Util.readCalibInfoFloat(is);
		vy = Util.readCalibInfoFloat(is);
		tx = Util.readCalibInfoFloat(is);
		ty = Util.readCalibInfoFloat(is);
		rot = Util.readCalibInfoFloat(is);
		sx = Util.readCalibInfoFloat(is);
		sy = Util.readCalibInfoFloat(is);
		shx = Util.readCalibInfoFloat(is);
		shy = Util.readCalibInfoFloat(is);
		secWidth = Util.readCalibInfoFloat(is);

		offsetY = Util.readCalibInfoInt(is); // TODO 확인 요. 원본 확인.
		dsizeWidth = Util.readCalibInfoInt(is);
		dsizeHeight = Util.readCalibInfoInt(is);
	    dsizeCenter.x = Util.readCalibInfoFloat(is);
	    dsizeCenter.y = Util.readCalibInfoFloat(is);
		offsetX = Util.readCalibInfoInt(is);
		offsetY = Util.readCalibInfoInt(is);
		
		flags |= Const.HV_ENABLE;
	}

	public static SingleViewData load(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		DataInputStream dis = new DataInputStream(fis);
		
		SingleViewData data = new SingleViewData();

		data.flags = TLVFormat.readInt(dis);
		data.w = TLVFormat.readFloat(dis);
		data.vx = TLVFormat.readFloat(dis);
		data.vy = TLVFormat.readFloat(dis);
		data.tx = TLVFormat.readFloat(dis);
		data.ty = TLVFormat.readFloat(dis);
		data.rot = TLVFormat.readFloat(dis);
		data.sx = TLVFormat.readFloat(dis);
		data.sy = TLVFormat.readFloat(dis);
		data.shx = TLVFormat.readFloat(dis);
		data.shy = TLVFormat.readFloat(dis);
		data.secWidth = TLVFormat.readFloat(dis);

		data.dsizeWidth = TLVFormat.readInt(dis);
		data.dsizeHeight = TLVFormat.readInt(dis);
		data.offsetX = TLVFormat.readInt(dis);
		data.offsetY = TLVFormat.readInt(dis);
		data.carWidth = TLVFormat.readInt(dis);
		data.carLength = TLVFormat.readInt(dis);

	    data.dsizeCenter.x = data.dsizeWidth * 0.5f;
	    data.dsizeCenter.y = data.dsizeHeight * 0.5f;

	    dis.close();
		
		return data;
	}
	
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
