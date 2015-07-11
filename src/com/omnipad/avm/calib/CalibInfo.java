package com.omnipad.avm.calib;

import java.io.FileInputStream;
import java.io.IOException;

import com.omnipad.avm.in.CameraData;
import com.omnipad.avm.in.MultiViewData;
import com.omnipad.avm.in.PGSData;
import com.omnipad.avm.in.SingleViewData;

public class CalibInfo {
	// Tags
	public final static int TAG_CAMERA_FRONT =		10;
	public final static int TAG_CAMERA_REAR =			11;
	public final static int TAG_CAMERA_LEFT =			12;
	public final static int TAG_CAMERA_RIGHT =		13;

	public final static int TAG_AVM2D_FULL =			20;
	public final static int TAG_AVM2D_FRONT =			21;
	public final static int TAG_AVM2D_REAR =			22;
	public final static int TAG_AVM2D_LS =			23;
	public final static int TAG_AVM2D_SLD =			24;

	public final static int TAG_AVM2D_FULL_MASK =		30;
	public final static int TAG_AVM2D_FRONT_MASK =	31;
	public final static int TAG_AVM2D_REAR_MASK =		32;
	public final static int TAG_AVM2D_LS_MASK =		33;
	public final static int TAG_AVM2D_SLD_MASK =		34;

	public final static int TAG_VIEW_FRONT =			40;
	public final static int TAG_VIEW_FRONT_FULL =		41;
	public final static int TAG_VIEW_REAR =			42;
	public final static int TAG_VIEW_REAR_FULL =		43;
	public final static int TAG_VIEW_LEFT =			44;
	public final static int TAG_VIEW_LEFT_FRONT =		45;
	public final static int TAG_VIEW_LEFT_REAR =		46;
	public final static int TAG_VIEW_RIGHT =			47;
	public final static int TAG_VIEW_RIGHT_FRONT =	48;
	public final static int TAG_VIEW_RIGHT_REAR =		49;

	// Tag Offset
	public final static int TAG_CAMERA =				10;
	public final static int TAG_AVM2D =				20;
	public final static int TAG_MASK =				30;
	public final static int TAG_CAMVIEW =				40;
	public final static int TAG_STATIC_PG_DATA =		60;
	public final static int TAG_DYNAMIC_PG_DATA =		61;
	
	CameraData[] cameraData = new CameraData[Const.CAM_TOTAL];
	MultiViewData[] multiViewData = new MultiViewData[Const.AVM2D_TOTAL];
	SingleViewData[] singleViewData = new SingleViewData[Const.VIEW_TOTAL];
	PGSData[] pgsData = new PGSData[2];
	
	public CalibInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public int setTopViewParams() {
		return 0;
	}
	
	public int setCamViewParams() {
		return 0;
	}
	
	public static final CalibInfo load(String path) throws IOException {
		FileInputStream is = new FileInputStream(path);
//		DataInputStream dis = new DataInputStream(fis);
		
		CalibInfo info = new CalibInfo();

		while(is.available() > 0) {
			int tag = Util.readCalibInfoInt(is);
			int len = Util.readCalibInfoInt(is);

			switch(tag) {
			case TAG_CAMERA_FRONT:
				info.cameraData[Const.CAM_FRONT] = new CameraData(is);
				info.cameraData[Const.CAM_FRONT].camID = Const.CAM_FRONT;
				break;
			case TAG_CAMERA_REAR:
				info.cameraData[Const.CAM_REAR] = new CameraData(is);
				info.cameraData[Const.CAM_REAR].camID = Const.CAM_REAR;
				break;
			case TAG_CAMERA_LEFT:
				info.cameraData[Const.CAM_LEFT] = new CameraData(is);
				info.cameraData[Const.CAM_LEFT].camID = Const.CAM_LEFT;
				break;
			case TAG_CAMERA_RIGHT:
				info.cameraData[Const.CAM_RIGHT] = new CameraData(is);
				info.cameraData[Const.CAM_RIGHT].camID = Const.CAM_RIGHT;
				break;
			// topview data
			case TAG_AVM2D_FULL:
				info.multiViewData[Const.AVM2D_FULL] = new MultiViewData(is);
				info.multiViewData[Const.AVM2D_FULL].cameraData = info.cameraData;
				break;
			case TAG_AVM2D_FRONT:
				info.multiViewData[Const.AVM2D_FRONT] = new MultiViewData(is);
				info.multiViewData[Const.AVM2D_FRONT].cameraData = info.cameraData;
				break;
			case TAG_AVM2D_REAR:
				info.multiViewData[Const.AVM2D_REAR] = new MultiViewData(is);
				info.multiViewData[Const.AVM2D_REAR].cameraData = info.cameraData;
				break;
			case TAG_AVM2D_LS:
				info.multiViewData[Const.AVM2D_LS] = new MultiViewData(is);
				info.multiViewData[Const.AVM2D_LS].cameraData = info.cameraData;
				break;
			case TAG_AVM2D_SLD:
				info.multiViewData[Const.AVM2D_SLD] = new MultiViewData(is);
				info.multiViewData[Const.AVM2D_SLD].cameraData = info.cameraData;
				break;
			// mask data
			case TAG_AVM2D_FULL_MASK:
			case TAG_AVM2D_FRONT_MASK:
			case TAG_AVM2D_REAR_MASK:
			case TAG_AVM2D_LS_MASK:
			case TAG_AVM2D_SLD_MASK:
				break;
			// camview data
			case TAG_VIEW_FRONT:
				info.singleViewData[Const.VIEW_FRONT] = new SingleViewData(is);
				info.singleViewData[Const.VIEW_FRONT].cameraData = info.cameraData[Const.CAM_FRONT];
				break;
			case TAG_VIEW_FRONT_FULL:
				info.singleViewData[Const.VIEW_FRONT_FULL] = new SingleViewData(is);
				info.singleViewData[Const.VIEW_FRONT_FULL].cameraData = info.cameraData[Const.CAM_FRONT];
				break;
			case TAG_VIEW_REAR:
				info.singleViewData[Const.VIEW_REAR] = new SingleViewData(is);
				info.singleViewData[Const.VIEW_REAR].cameraData = info.cameraData[Const.CAM_REAR];
				break;
			case TAG_VIEW_REAR_FULL:
				info.singleViewData[Const.VIEW_REAR_FULL] = new SingleViewData(is);
				info.singleViewData[Const.VIEW_REAR_FULL].cameraData = info.cameraData[Const.CAM_REAR];
				break;
			case TAG_VIEW_LEFT:
				info.singleViewData[Const.VIEW_LEFT] = new SingleViewData(is);
				info.singleViewData[Const.VIEW_LEFT].cameraData = info.cameraData[Const.CAM_LEFT];
				break;
			case TAG_VIEW_LEFT_FRONT:
				info.singleViewData[Const.VIEW_LEFT_FRONT] = new SingleViewData(is);
				info.singleViewData[Const.VIEW_LEFT_FRONT].cameraData = info.cameraData[Const.CAM_LEFT];
				break;
			case TAG_VIEW_LEFT_REAR:
				info.singleViewData[Const.VIEW_LEFT_REAR] = new SingleViewData(is);
				info.singleViewData[Const.VIEW_LEFT_REAR].cameraData = info.cameraData[Const.CAM_LEFT];
				break;
			case TAG_VIEW_RIGHT:
				info.singleViewData[Const.VIEW_RIGHT] = new SingleViewData(is);
				info.singleViewData[Const.VIEW_RIGHT].cameraData = info.cameraData[Const.CAM_RIGHT];
				break;
			case TAG_VIEW_RIGHT_FRONT:
				info.singleViewData[Const.VIEW_RIGHT_FRONT] = new SingleViewData(is);
				info.singleViewData[Const.VIEW_RIGHT_FRONT].cameraData = info.cameraData[Const.CAM_RIGHT];
				break;
			case TAG_VIEW_RIGHT_REAR:
				info.singleViewData[Const.VIEW_RIGHT_REAR] = new SingleViewData(is);
				info.singleViewData[Const.VIEW_RIGHT_REAR].cameraData = info.cameraData[Const.CAM_RIGHT];
				break;
			// pgs data
			case TAG_STATIC_PG_DATA:
				info.pgsData[0] = new PGSData(is);
				break;

			case TAG_DYNAMIC_PG_DATA:
				info.pgsData[1] = new PGSData(is);
				break;			
			}
		}
		
		// car_length
		for(int i = 0; i < Const.VIEW_TOTAL; i++) {
			SingleViewData sdata = info.singleViewData[i];
			if(sdata != null && (sdata.flags & Const.HV_ENABLE) > 0) {
				sdata.carWidth = info.multiViewData[0].carWidth;
				sdata.carLength = info.multiViewData[0].carLength;
			}
		}

		// pgs_data
		for(int i = 0; i < 2; i++){
			PGSData pgs = info.pgsData[i];
			if(pgs == null)
				continue;
			pgs.carWidth  = info.multiViewData[0].carWidth;
			pgs.carLength = info.multiViewData[0].carLength;
			pgs.carHeight = info.multiViewData[0].carHeight;
			pgs.tread      = info.multiViewData[0].carTread;
			pgs.wheelBase  = info.multiViewData[0].carWheelBase;

			pgs.angle	= Const.HV_STEER_ANGLE_MAX * 2 + 1; // -MAX ~ +MAX
			pgs.points = Const.HV_PGS_POINTS_MAX;
		}
		
		is.close();
		
		return info;
	}
}
