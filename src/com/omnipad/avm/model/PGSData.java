package com.omnipad.avm.model;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class PGSData {
	public float tread;
	public float wheelBase;
	public float parkingTrjDist;
	public int steerAngle;
	public int carWidth;
	public int carLength;
	public int carHeight;
	public int rearAxle;
	public int offset;
	public int offsetDistance;
	public Point2D rotCenter;

	public int coordType;
	public int angle;
	public int points;
	public Point2D pgsWorld;
	public Point2D pgsImage;
	public Point2D pgsView;

	public int line;

	public PGSLine[] pgsLines;

	public PGSData(MultiViewData topPlanerView) {
		carWidth = topPlanerView.carWidth;
		carLength = topPlanerView.carLength;
		carHeight = topPlanerView.carHeight;
		tread = topPlanerView.carTread;
		wheelBase = topPlanerView.carWheelBase;

		offset = 0;
		rearAxle = 0;
	}

	public PGSData(ByteBuffer buf) throws IOException {
		coordType = buf.getInt();
		line = buf.getInt();
		
		ByteBuffer buffer = ByteBuffer.wrap(buf.array(), buf.position(), 60);
		
		IntBuffer intBuffer = buffer.asIntBuffer();
		
		for(int i = 0;i < line;i++) {
			pgsLines[i] = new PGSLine();
			intBuffer.position(i);
			pgsLines[i].distance = intBuffer.get();
			intBuffer.position(i + 5);
			pgsLines[i].width = intBuffer.get();
			intBuffer.position(i + 10);
			pgsLines[i].color = intBuffer.get();
		}
		
	}

}
