package com.omnipad.avm;

import com.omnipad.avm.camera.Camera;
import com.omnipad.avm.model.CalDatum;
import com.omnipad.avm.model.CamPose;
import com.omnipad.avm.model.CameraData;
import com.omnipad.avm.model.MultiViewData;
import com.omnipad.avm.model.PGSData;
import com.omnipad.avm.model.Point2D;
import com.omnipad.avm.model.SingleViewData;

public class OPAVM {
	
	public OPAVM() {
		// TODO Auto-generated constructor stub
	}

	public static int calibInit(CalDatum[] calParams, CameraData[] cameraData) {
		return 0;
	}
	
	public static int detectMarker(int w, int h, byte[] srcImg, CameraData cameraData, Camera camera, boolean bGray) {
		return 0;
	}
	
	public static int calibApply(CalDatum[] calParams, CameraData[] cameraData, CamPose camPose) {
		return 0;
	}
	
	public static int singleViewInit(SingleViewData data) {
		return 0;
	}
	
	public static int singleViewGetBin(byte[] bin, int len) {
		// len = width * height * 4
		return 0;
	}
	
	public static int singleViewGetLUT(Point2D dstLut, int len) {
		// len = width * height
		return 0;
	}
	
	public static int singleViewApply(int srcw, int srch, byte[] srcImg, byte[] dstImg) {
		return 0;
	}
	
	public static void singleViewClose() {
		
	}
	
	public static int PGSInit(PGSData pdata, SingleViewData sdata) {
		return 0;
	}
	
	public static int PGSApply(int srcW, int srcH, byte[] img, PGSData pdata) {
		return 0;
	}
	
	public static void PGSClose() {
		
	}
	
	public static int multiViewInit(MultiViewData data) {
		return 0;
	}
	
	// len = width * height * 4
	public static int multiViewGetBin(byte[] bin, int len, Camera camera) {
		return 0;
	}
	
	public static int multiViewGetMaskBin(byte[] bin, int len) {
		return 0;
	}

	public static int multiViewGetMask(byte[] mask, int len) {
		// len = height * getWidthByte(width, 24)
		return 0;
	}
	
	public static int multiViewGetLUT(byte[] mask, int len) {
		// len = width * height
		return 0;
	}
	
	public static int multiViewApply(int w, int h, byte[] imgFront, byte[] imgRear, byte[] imgLeft, byte[] imgRight, byte[] imgTop) {
		return 0;
	}
	
	public static void multiViewClose() {
		
	}
}
