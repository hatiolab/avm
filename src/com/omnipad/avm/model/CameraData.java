package com.omnipad.avm.model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

import com.omnipad.avm.TLVFormat;

public class CameraData {
	public int camID;

	public float f;	// focal length
	public float mu;	// pixels/mm
	public float mv;	// pixels/mm
	public float cx;	// x of optical center
	public float cy;	// y of optical center
	public float[] k = new float[5];	// distortion
	public float[] d = new float[9];	// undistortion

	public int ssizeWidth;	// width of image
	public int ssizeHeight;	// height of image
	public int offsetX;
	public int offsetY;

	public float camTransX;	// mm
	public float camTransY;	// mm
	public float camHeight;	// mm
	public float downAngle;	// deg
	public float rotateAngle;	// deg
	public float panAngle;		// deg

	public Point2D worldCenter = new Point2D();	// centor of car in world coordinate
	public int markerType;
	public int markerLength;

	public int numPoints;
	public Point2D[] ipts = new Point2D[20];
	public Point2D[] wpts = new Point2D[20];
	
	public CameraData() {
	}
	
	public CameraData(ByteBuffer buf) throws IOException {

		f = buf.getFloat();
		mu = buf.getFloat();
		mv = buf.getFloat();
		cx = buf.getFloat();
		cy = buf.getFloat();
		
		for(int i = 0;i < k.length;i++)
			k[i] = buf.getFloat();

		for(int i = 0;i < d.length;i++)
			d[i] = buf.getFloat();
		
		ssizeWidth = buf.getInt();
		ssizeHeight = buf.getInt();
		offsetX = buf.getInt();
		offsetY = buf.getInt();
		
		camTransX = buf.getFloat();
		camTransY = buf.getFloat();
		camHeight = buf.getFloat();
		downAngle = buf.getFloat();
		panAngle = buf.getFloat();
		rotateAngle = buf.getFloat();
		
		worldCenter.x = buf.getInt();
		worldCenter.y = buf.getInt();
		markerType = buf.getInt();		
		markerLength = buf.getInt();
		numPoints = buf.getInt();
		
		// TODO 아래 로직이 원본과 같지 않음 확인 필요. numPoints should be 10.
		for(int i = 0;i < numPoints;i++) {
			ipts[i] = new Point2D();
			ipts[i].x = buf.getFloat();
			ipts[i].y = buf.getFloat();
		}
		for(int i = 0;i < 10 - numPoints;i++) {
			buf.getFloat();
			buf.getFloat();
		}
			
		for(int i = 0;i < numPoints;i++) {
			wpts[i] = new Point2D();
			wpts[i].x = buf.getFloat();
			wpts[i].y = buf.getFloat();
		}
		for(int i = 0;i < 10 - numPoints;i++) {
			buf.getFloat();
			buf.getFloat();
		}
	}
	
	// FIXME
	public static CameraData load(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		
		CameraData data = new CameraData();

		data.f = TLVFormat.readTLV(fis).getFloatValue();
		data.mu = TLVFormat.readTLV(fis).getFloatValue();
		data.mv = TLVFormat.readTLV(fis).getFloatValue();
		data.cx = TLVFormat.readTLV(fis).getFloatValue();
		data.cy = TLVFormat.readTLV(fis).getFloatValue();
		data.k[0] = TLVFormat.readTLV(fis).getFloatValue();
		data.k[1] = TLVFormat.readTLV(fis).getFloatValue();
		data.k[2] = TLVFormat.readTLV(fis).getFloatValue();
		data.k[3] = TLVFormat.readTLV(fis).getFloatValue();
		data.k[4] = TLVFormat.readTLV(fis).getFloatValue();
		data.d[0] = TLVFormat.readTLV(fis).getFloatValue();
		data.d[1] = TLVFormat.readTLV(fis).getFloatValue();
		data.d[2] = TLVFormat.readTLV(fis).getFloatValue();
		data.d[3] = TLVFormat.readTLV(fis).getFloatValue();
		data.d[4] = TLVFormat.readTLV(fis).getFloatValue();
		data.d[5] = TLVFormat.readTLV(fis).getFloatValue();
		data.d[6] = TLVFormat.readTLV(fis).getFloatValue();
		data.d[7] = TLVFormat.readTLV(fis).getFloatValue();
		data.d[8] = TLVFormat.readTLV(fis).getFloatValue();
		
		data.ssizeWidth = TLVFormat.readTLV(fis).getIntValue();
		data.ssizeHeight = TLVFormat.readTLV(fis).getIntValue();
		data.offsetX = TLVFormat.readTLV(fis).getIntValue();
		data.offsetY = TLVFormat.readTLV(fis).getIntValue();

		data.worldCenter.x = TLVFormat.readTLV(fis).getIntValue();
		data.worldCenter.y = TLVFormat.readTLV(fis).getIntValue();
		data.markerLength = TLVFormat.readTLV(fis).getIntValue();
		data.markerType = TLVFormat.readTLV(fis).getIntValue();

		fis.close();
		
		return data;
	}
	
	// FIXME
	public void store(String path) throws IOException {
		FileOutputStream fos = new FileOutputStream(path);

		TLVFormat.write(fos, 1, f);
		TLVFormat.write(fos, 2, mu);
		TLVFormat.write(fos, 3, mv);
		TLVFormat.write(fos, 4, cx);
		TLVFormat.write(fos, 5, cy);
		TLVFormat.write(fos, 6, k[0]);
		TLVFormat.write(fos, 7, k[1]);
		TLVFormat.write(fos, 8, k[2]);
		TLVFormat.write(fos, 9, k[3]);
		TLVFormat.write(fos, 10, k[4]);
		TLVFormat.write(fos, 11, d[0]);
		TLVFormat.write(fos, 12, d[1]);
		TLVFormat.write(fos, 13, d[2]);
		TLVFormat.write(fos, 14, d[3]);
		TLVFormat.write(fos, 15, d[4]);
		TLVFormat.write(fos, 16, d[5]);
		TLVFormat.write(fos, 17, d[6]);
		TLVFormat.write(fos, 18, d[7]);
		TLVFormat.write(fos, 19, d[8]);
		TLVFormat.write(fos, 20, ssizeWidth);
		TLVFormat.write(fos, 21, ssizeHeight);
		TLVFormat.write(fos, 22, offsetX);
		TLVFormat.write(fos, 23, offsetY);
		
		TLVFormat.write(fos, 24, worldCenter.x);
		TLVFormat.write(fos, 25, worldCenter.y);
		TLVFormat.write(fos, 26, markerLength);
		TLVFormat.write(fos, 27, markerType);
		
		fos.close();
	}
}
