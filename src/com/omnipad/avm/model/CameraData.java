package com.omnipad.avm.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.omnipad.avm.TLVFormat;
import com.omnipad.avm.calib.Util;

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
	
	public CameraData(InputStream is) throws IOException {
		f = Util.readCalibInfoFloat(is);
		mu = Util.readCalibInfoFloat(is);
		mv = Util.readCalibInfoFloat(is);
		cx = Util.readCalibInfoFloat(is);
		cy = Util.readCalibInfoFloat(is);
		
		for(int i = 0;i < k.length;i++)
			k[i] = Util.readCalibInfoFloat(is);

		for(int i = 0;i < d.length;i++)
			d[i] = Util.readCalibInfoFloat(is);
		
		ssizeWidth = Util.readCalibInfoInt(is);
		ssizeHeight = Util.readCalibInfoInt(is);
		offsetX = Util.readCalibInfoInt(is);
		offsetY = Util.readCalibInfoInt(is);
		
		camTransX = Util.readCalibInfoFloat(is);
		camTransY = Util.readCalibInfoFloat(is);
		camHeight = Util.readCalibInfoFloat(is);
		downAngle = Util.readCalibInfoFloat(is);
		panAngle = Util.readCalibInfoFloat(is);
		rotateAngle = Util.readCalibInfoFloat(is);
		
		worldCenter.x = Util.readCalibInfoInt(is);
		worldCenter.y = Util.readCalibInfoInt(is);
		markerType = Util.readCalibInfoInt(is);		
		markerLength = Util.readCalibInfoInt(is);
		numPoints = Util.readCalibInfoInt(is);
		
		// TODO 아래 로직이 원본과 같지 않음 확인 필요. numPoints should be 10.
		for(int i = 0;i < numPoints;i++) {
			ipts[i] = new Point2D();
			ipts[i].x = Util.readCalibInfoFloat(is);
			ipts[i].y = Util.readCalibInfoFloat(is);
		}
		for(int i = 0;i < 10 - numPoints;i++) {
			Util.readCalibInfoFloat(is);
			Util.readCalibInfoFloat(is);
		}
			
		for(int i = 0;i < numPoints;i++) {
			wpts[i] = new Point2D();
			wpts[i].x = Util.readCalibInfoFloat(is);
			wpts[i].y = Util.readCalibInfoFloat(is);
		}
		for(int i = 0;i < 10 - numPoints;i++) {
			Util.readCalibInfoFloat(is);
			Util.readCalibInfoFloat(is);
		}
	}
	
	public static CameraData load(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		DataInputStream dis = new DataInputStream(fis);
		
		CameraData data = new CameraData();

		data.f = TLVFormat.readFloat(dis);
		data.mu = TLVFormat.readFloat(dis);
		data.mv = TLVFormat.readFloat(dis);
		data.cx = TLVFormat.readFloat(dis);
		data.cy = TLVFormat.readFloat(dis);
		data.k[0] = TLVFormat.readFloat(dis);
		data.k[1] = TLVFormat.readFloat(dis);
		data.k[2] = TLVFormat.readFloat(dis);
		data.k[3] = TLVFormat.readFloat(dis);
		data.k[4] = TLVFormat.readFloat(dis);
		data.d[0] = TLVFormat.readFloat(dis);
		data.d[1] = TLVFormat.readFloat(dis);
		data.d[2] = TLVFormat.readFloat(dis);
		data.d[3] = TLVFormat.readFloat(dis);
		data.d[4] = TLVFormat.readFloat(dis);
		data.d[5] = TLVFormat.readFloat(dis);
		data.d[6] = TLVFormat.readFloat(dis);
		data.d[7] = TLVFormat.readFloat(dis);
		data.d[8] = TLVFormat.readFloat(dis);
		
		data.ssizeWidth = TLVFormat.readInt(dis);
		data.ssizeHeight = TLVFormat.readInt(dis);
		data.offsetX = TLVFormat.readInt(dis);
		data.offsetY = TLVFormat.readInt(dis);

		data.worldCenter.x = TLVFormat.readInt(dis);
		data.worldCenter.y = TLVFormat.readInt(dis);
		data.markerLength = TLVFormat.readInt(dis);
		data.markerType = TLVFormat.readInt(dis);

		dis.close();
		
		return data;
	}
	
	public void store(String path) throws IOException {
		FileOutputStream fos = new FileOutputStream(path);
		DataOutputStream dos = new DataOutputStream(fos);

		TLVFormat.write(dos, 1, f);
		TLVFormat.write(dos, 2, mu);
		TLVFormat.write(dos, 3, mv);
		TLVFormat.write(dos, 4, cx);
		TLVFormat.write(dos, 5, cy);
		TLVFormat.write(dos, 6, k[0]);
		TLVFormat.write(dos, 7, k[1]);
		TLVFormat.write(dos, 8, k[2]);
		TLVFormat.write(dos, 9, k[3]);
		TLVFormat.write(dos, 10, k[4]);
		TLVFormat.write(dos, 11, d[0]);
		TLVFormat.write(dos, 12, d[1]);
		TLVFormat.write(dos, 13, d[2]);
		TLVFormat.write(dos, 14, d[3]);
		TLVFormat.write(dos, 15, d[4]);
		TLVFormat.write(dos, 16, d[5]);
		TLVFormat.write(dos, 17, d[6]);
		TLVFormat.write(dos, 18, d[7]);
		TLVFormat.write(dos, 19, d[8]);
		TLVFormat.write(dos, 20, ssizeWidth);
		TLVFormat.write(dos, 21, ssizeHeight);
		TLVFormat.write(dos, 22, offsetX);
		TLVFormat.write(dos, 23, offsetY);
		
		TLVFormat.write(dos, 24, worldCenter.x);
		TLVFormat.write(dos, 25, worldCenter.y);
		TLVFormat.write(dos, 26, markerLength);
		TLVFormat.write(dos, 27, markerType);
		
		dos.close();
	}
}
