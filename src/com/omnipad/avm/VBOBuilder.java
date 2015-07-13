package com.omnipad.avm;

import com.omnipad.avm.model.CalibInfo;
import com.omnipad.avm.model.MultiViewData;
import com.omnipad.avm.model.PGSData;
import com.omnipad.avm.model.SingleViewData;
import com.omnipad.avm.model.VertexData;

public class VBOBuilder {
	
	CalibInfo calibInfo;
	
	SingleViewData svFront = new SingleViewData();
	SingleViewData svFrontFull = new SingleViewData();
	SingleViewData svRear = new SingleViewData();
	SingleViewData svRearFull = new SingleViewData();
	SingleViewData svLeftSide = new SingleViewData();
	SingleViewData svRightSide = new SingleViewData();
	SingleViewData svDoubleLeft = new SingleViewData();
	SingleViewData svDoubleRight = new SingleViewData();

	MultiViewData mvTop = new MultiViewData();
	MultiViewData mvTopLandscape = new MultiViewData();
	MultiViewData mvTopOnly = new MultiViewData();
		
	VertexData vdFront;
	VertexData vdFrontFull;
	VertexData vdRear;
	VertexData vdRearFull;
	VertexData vdLeftSide;
	VertexData vdRightSide;
	VertexData vdDoubleLeft;
	VertexData vdDoubleRight;
	VertexData vdTop;
	VertexData vdTopLandscape;

	byte[] maskFront;
	byte[] maskFrontFull;
	byte[] maskRear;
	byte[] maskRearFull;
	byte[] maskLeftSide;
	byte[] maskRightSide;
	byte[] maskDoubleLeft;
	byte[] maskDoubleRight;
	byte[] maskTop;
	byte[] maskTopLandscape;
	
	PGSData pgsData;

	public VBOBuilder(CalibInfo calibInfo) {
		this.calibInfo = calibInfo;
	}
	
	public void buildSingleView(int viewMode) {
		
	}
	
	public void buildMultiView(int viewMode) {
		
	}
	
	public void buildAll() {
		
	}
}
