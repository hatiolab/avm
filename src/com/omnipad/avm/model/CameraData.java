package com.omnipad.avm.model;

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
	
	public CameraData(ByteBuffer buf) {

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
		
		worldCenter.x = buf.getFloat();
		worldCenter.y = buf.getFloat();
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
	
	public byte[] getBytes() {
		ByteBuffer buf = ByteBuffer.allocate(1024);
		buf.order(TLVFormat.order);
		
		buf.putFloat(f);
		buf.putFloat(mu);
		buf.putFloat(mv);
		buf.putFloat(cx);
		buf.putFloat(cy);

		for(int i = 0;i < k.length;i++)
			buf.putFloat(k[i]);

		for(int i = 0;i < d.length;i++)
			buf.putFloat(d[i]);

		buf.putInt(ssizeWidth);
		buf.putInt(ssizeHeight);
		buf.putInt(offsetX);
		buf.putInt(offsetY);
		
		buf.putFloat(camTransX);
		buf.putFloat(camTransY);
		buf.putFloat(camHeight);
		buf.putFloat(downAngle);
		buf.putFloat(panAngle);
		buf.putFloat(rotateAngle);

		buf.putFloat(worldCenter.x);
		buf.putFloat(worldCenter.y);
		buf.putInt(markerType);
		buf.putInt(markerLength);
		buf.putInt(numPoints);
		
		// TODO 아래 로직이 원본과 같지 않음 확인 필요. numPoints should be 10.
		for(int i = 0;i < numPoints;i++) {
			buf.putFloat(ipts[i].x);
			buf.putFloat(ipts[i].y);
		}
		for(int i = 0;i < 10 - numPoints;i++) {
			buf.putFloat(0.f);
			buf.putFloat(0.f);
		}
			
		for(int i = 0;i < numPoints;i++) {
			buf.putFloat(wpts[i].x);
			buf.putFloat(wpts[i].y);
		}
		for(int i = 0;i < 10 - numPoints;i++) {
			buf.putFloat(0.f);
			buf.putFloat(0.f);
		}
		
		byte[] ret = new byte[buf.position()];
		
		System.arraycopy(buf.array(), 0, ret, 0, ret.length);
		
		return ret;
	}
}
