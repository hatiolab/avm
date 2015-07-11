package com.omnipad.avm.model;

public class DstData {
	int width;
	int height;
	float dotPitchX;	// mm
	float dotPitchY;	// mm
	int count;
	Data[] data;
	float[] d;	// 9 float values
	
	class Data {
		float angle;
		float distance;
	}
	
	public DstData() {
		// TODO Auto-generated constructor stub
	}

}
