package com.omnipad.avm;

import com.omnipad.avm.camera.Camera;
import com.omnipad.avm.camera.MarkerType;
import com.omnipad.avm.in.CalDatum;
import com.omnipad.avm.in.CamPose;
import com.omnipad.avm.in.CameraData;
import com.omnipad.avm.in.MultiViewData;
import com.omnipad.avm.in.PGSData;
import com.omnipad.avm.in.Point2D;
import com.omnipad.avm.in.SingleViewData;

public class OPAVM {

	public OPAVM() {
		// TODO Auto-generated constructor stub
	}
	
	int calibInit(CalDatum calParams[], MarkerType markerType, int markerSize) {
		return 0;
	}
	
	int detectMarker(int w, int h, byte[] srcImg, CameraData cameraData, Camera camera) {
		return 0;
	}
	
	int calibApply(CalDatum[] calParams, CameraData[] cameraData, CamPose camPose) {
		return 0;
	}
	
	int singleViewInit(SingleViewData data) {
		return 0;
	}
	
	int singleViewGetBin(byte[] bin, int len) {
		// len = width * height * 4
		return 0;
	}
	
	int singleViewGetLUT(Point2D dstLut, int len) {
		// len = width * height
		return 0;
	}
	
	int singleViewApply(int srcw, int srch, byte[] srcImg, byte[] dstImg) {
		return 0;
	}
	
	void singleViewClose() {
		
	}
	
	int PGSInit(PGSData pdata, SingleViewData sdata) {
		return 0;
	}
	
	int PGSApply(int srcW, int srcH, byte[] img, PGSData pdata) {
		return 0;
	}
	
	void PGSClose() {
		
	}
	
	int multiViewInit(MultiViewData data) {
		return 0;
	}
	
	// len = width * height * 4
	int multiViewGetBin(byte[] bin, int len, Camera camera) {
		return 0;
	}
	
	int multiViewGetMaskBin(byte[] bin, int len) {
		return 0;
	}

	int multiViewGetMask(byte[] mask, int len) {
		// len = height * getWidthByte(width, 24)
		return 0;
	}
	
	int multiViewGetLUT(byte[] mask, int len) {
		// len = width * height
		return 0;
	}
	
	int multiViewApply(int w, int h, byte[] imgFront, byte[] imgRear, byte[] imgLeft, byte[] imgRight, byte[] imgTop) {
		return 0;
	}
	
	void multiViewClose() {
		
	}
}
