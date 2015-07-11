package com.omnipad.avm.model;

public class CalDatum {
	Point2D[] mkP;	// size 10
	Point3D[] mkV;	// size 10
	float[][] mkDist;	// size 10 x 10
	
	boolean mirror;
	
	float camTransX;	// mm
	float camTransY;	// mm
	float camHeight;	// mm
	float downAngle;	// deg
	float rotateAngle;	// deg
	float panAngle;		// deg
	
	DstData	ddata;
	CapDiff	cdiff;
	int	points;
	
	public CalDatum() {
		// TODO Auto-generated constructor stub
	}

}
