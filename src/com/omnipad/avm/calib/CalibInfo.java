package com.omnipad.avm.calib;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import com.omnipad.avm.in.CameraData;
import com.omnipad.avm.in.MultiViewData;
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
		FileInputStream fis = new FileInputStream(path);
		DataInputStream dis = new DataInputStream(fis);
		
		CalibInfo info = new CalibInfo();

		while(dis.available() > 0) {
			int tag = dis.readInt();
			int len = dis.readInt();

			switch(tag) {
			case TAG_CAMERA_FRONT:
				info.cameraData[Const.CAM_FRONT] = new CameraData(dis);
				info.cameraData[Const.CAM_FRONT].camID = Const.CAM_FRONT;
				break;
			case TAG_CAMERA_REAR:
				info.cameraData[Const.CAM_REAR] = new CameraData(dis);
				info.cameraData[Const.CAM_REAR].camID = Const.CAM_REAR;
				break;
			case TAG_CAMERA_LEFT:
				info.cameraData[Const.CAM_LEFT] = new CameraData(dis);
				info.cameraData[Const.CAM_LEFT].camID = Const.CAM_LEFT;
				break;
			case TAG_CAMERA_RIGHT:
				info.cameraData[Const.CAM_RIGHT] = new CameraData(dis);
				info.cameraData[Const.CAM_RIGHT].camID = Const.CAM_RIGHT;
				break;
			// topview data
			case TAG_AVM2D_FULL:
				info.multiViewData[Const.AVM2D_FULL] = new MultiViewData(dis);
				info.multiViewData[Const.AVM2D_FULL].cameraData = info.cameraData;
				break;
			case TAG_AVM2D_FRONT:
				info.multiViewData[Const.AVM2D_FRONT] = new MultiViewData(dis);
				info.multiViewData[Const.AVM2D_FRONT].cameraData = info.cameraData;
				break;
			case TAG_AVM2D_REAR:
				info.multiViewData[Const.AVM2D_REAR] = new MultiViewData(dis);
				info.multiViewData[Const.AVM2D_REAR].cameraData = info.cameraData;
				break;
			case TAG_AVM2D_LS:
				info.multiViewData[Const.AVM2D_LS] = new MultiViewData(dis);
				info.multiViewData[Const.AVM2D_LS].cameraData = info.cameraData;
				break;
			case TAG_AVM2D_SLD:
				info.multiViewData[Const.AVM2D_SLD] = new MultiViewData(dis);
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
				info.singleViewData[Const.VIEW_FRONT] = new SingleViewData(dis);
				info.singleViewData[Const.VIEW_FRONT].cameraData = info.cameraData[Const.CAM_FRONT];
				break;
			case TAG_VIEW_FRONT_FULL:
				info.singleViewData[Const.VIEW_FRONT] = new SingleViewData(dis);
				info.singleViewData[Const.VIEW_FRONT_FULL].cameraData = info.cameraData[Const.CAM_FRONT];
				break;
			case TAG_VIEW_REAR:
				info.singleViewData[Const.VIEW_REAR] = new SingleViewData(dis);
				info.singleViewData[Const.VIEW_REAR].cameraData = info.cameraData[Const.CAM_REAR];
				break;
			case TAG_VIEW_REAR_FULL:
				info.singleViewData[Const.VIEW_REAR_FULL] = new SingleViewData(dis);
				info.singleViewData[Const.VIEW_REAR_FULL].cameraData = info.cameraData[Const.CAM_REAR];
				break;
			case TAG_VIEW_LEFT:
				info.singleViewData[Const.VIEW_LEFT] = new SingleViewData(dis);
				info.singleViewData[Const.VIEW_LEFT].cameraData = info.cameraData[Const.CAM_LEFT];
				break;
			case TAG_VIEW_LEFT_FRONT:
				info.singleViewData[Const.VIEW_LEFT_FRONT] = new SingleViewData(dis);
				info.singleViewData[Const.VIEW_LEFT_FRONT].cameraData = info.cameraData[Const.CAM_LEFT];
				break;
			case TAG_VIEW_LEFT_REAR:
				info.singleViewData[Const.VIEW_LEFT_REAR] = new SingleViewData(dis);
				info.singleViewData[Const.VIEW_LEFT_REAR].cameraData = info.cameraData[Const.CAM_LEFT];
				break;
			case TAG_VIEW_RIGHT:
				info.singleViewData[Const.VIEW_RIGHT] = new SingleViewData(dis);
				info.singleViewData[Const.VIEW_RIGHT].cameraData = info.cameraData[Const.CAM_RIGHT];
				break;
			case TAG_VIEW_RIGHT_FRONT:
				info.singleViewData[Const.VIEW_RIGHT_FRONT] = new SingleViewData(dis);
				info.singleViewData[Const.VIEW_RIGHT_FRONT].cameraData = info.cameraData[Const.CAM_RIGHT];
				break;
			case TAG_VIEW_RIGHT_REAR:
				info.singleViewData[Const.VIEW_RIGHT_REAR] = new SingleViewData(dis);
				info.singleViewData[Const.VIEW_RIGHT_REAR].cameraData = info.cameraData[Const.CAM_RIGHT];
				break;
			// pgs data
			case TAG_STATIC_PG_DATA:
				err = setPgsParams(&binData[dwBytes], tlv.len, &g_pgsData[0]);
				break;

			case TAG_DYNAMIC_PG_DATA:
				err = setPgsParams(&binData[dwBytes], tlv.len, &g_pgsData[1]);
				break;			
			}
		}
		
		
		fis.close();
		
		return info;
	}
}
/*
DWORD index = 0;
DWORD dwBytesTotal	= 0;
DWORD dwBytes = 0;
TlvQ tlv;
INT err = HV_OK;


// file pointer move to end
fseek(fp, 0L, SEEK_END);
dwBytesTotal = ftell(fp);
BYTE * binData = (BYTE *)malloc(dwBytesTotal * sizeof(BYTE));
if( binData == NULL) {
	printf("loadCalibInfoFile() - failed. memory allocation error !!");
	return FALSE;
}

rewind(fp);
fread(binData, dwBytesTotal, 1, fp);
fclose(fp);

// create fold
dwBytes = 0;

while ( dwBytes < dwBytesTotal ) {
	
	// read tag
	memcpy(&tlv, (void *)&binData[dwBytes], sizeof(TlvQ));
	dwBytes += sizeof(TlvQ);

	switch(tlv.tag) {
		// camera data
		case TAG_CAMERA_FRONT:
			err = setCameraParams(&binData[dwBytes], tlv.len, &cameraData[CAM_FRONT]);
			cameraData[CAM_FRONT].camID = CAM_FRONT;
			break;
		case TAG_CAMERA_REAR:
			err = setCameraParams(&binData[dwBytes], tlv.len, &cameraData[CAM_REAR]);
			cameraData[CAM_REAR].camID = CAM_REAR;
			break;
		case TAG_CAMERA_LEFT:
			err = setCameraParams(&binData[dwBytes], tlv.len, &cameraData[CAM_LEFT]);
			cameraData[CAM_LEFT].camID = CAM_LEFT;
			break;
		case TAG_CAMERA_RIGHT:
			err = setCameraParams(&binData[dwBytes], tlv.len, &cameraData[CAM_RIGHT]);
			cameraData[CAM_RIGHT].camID = CAM_RIGHT;
			break;
		// topview data
		case TAG_AVM2D_FULL:
			err = setTopviewParams(&binData[dwBytes], tlv.len, &g_multiviewData[AVM2D_FULL]);
			g_multiviewData[AVM2D_FULL].CameraData = &cameraData[0];
			break;
		case TAG_AVM2D_FRONT:
			err = setTopviewParams(&binData[dwBytes], tlv.len, &g_multiviewData[AVM2D_FRONT]);
			g_multiviewData[AVM2D_FRONT].CameraData = &cameraData[0];
			break;
		case TAG_AVM2D_REAR:
			err = setTopviewParams(&binData[dwBytes], tlv.len, &g_multiviewData[AVM2D_REAR]);
			g_multiviewData[AVM2D_REAR].CameraData = &cameraData[0];
			break;
		case TAG_AVM2D_LS:
			err = setTopviewParams(&binData[dwBytes], tlv.len, &g_multiviewData[AVM2D_LS]);
			g_multiviewData[AVM2D_LS].CameraData = &cameraData[0];
			break;
		case TAG_AVM2D_SLD:
			err = setTopviewParams(&binData[dwBytes], tlv.len, &g_multiviewData[AVM2D_SLD]);
			g_multiviewData[AVM2D_SLD].CameraData = &cameraData[0];
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
			err = setCamviewParams(&binData[dwBytes], tlv.len, &g_singleviewData[VIEW_FRONT]);
			g_singleviewData[VIEW_FRONT].CameraData = &cameraData[CAM_FRONT];
			break;
		case TAG_VIEW_FRONT_FULL:
			err = setCamviewParams(&binData[dwBytes], tlv.len, &g_singleviewData[VIEW_FRONT_FULL]);
			g_singleviewData[VIEW_FRONT_FULL].CameraData = &cameraData[CAM_FRONT];
			break;
		case TAG_VIEW_REAR:
			err = setCamviewParams(&binData[dwBytes], tlv.len, &g_singleviewData[VIEW_REAR]);
			g_singleviewData[VIEW_REAR].CameraData = &cameraData[CAM_REAR];
			break;
		case TAG_VIEW_REAR_FULL:
			err = setCamviewParams(&binData[dwBytes], tlv.len, &g_singleviewData[VIEW_REAR_FULL]);
			g_singleviewData[VIEW_REAR_FULL].CameraData = &cameraData[CAM_REAR];
			break;
		case TAG_VIEW_LEFT:
			err = setCamviewParams(&binData[dwBytes], tlv.len, &g_singleviewData[VIEW_LEFT]);
			g_singleviewData[VIEW_LEFT].CameraData = &cameraData[CAM_LEFT];
			break;
		case TAG_VIEW_LEFT_FRONT:
			err = setCamviewParams(&binData[dwBytes], tlv.len, &g_singleviewData[VIEW_LEFT_FRONT]);
			g_singleviewData[VIEW_LEFT_FRONT].CameraData = &cameraData[CAM_LEFT];
			break;
		case TAG_VIEW_LEFT_REAR:
			err = setCamviewParams(&binData[dwBytes], tlv.len, &g_singleviewData[VIEW_LEFT_REAR]);
			g_singleviewData[VIEW_LEFT_REAR].CameraData = &cameraData[CAM_LEFT];
			break;
		case TAG_VIEW_RIGHT:
			err = setCamviewParams(&binData[dwBytes], tlv.len, &g_singleviewData[VIEW_RIGHT]);
			g_singleviewData[VIEW_RIGHT].CameraData = &cameraData[CAM_RIGHT];
			break;
		case TAG_VIEW_RIGHT_FRONT:
			err = setCamviewParams(&binData[dwBytes], tlv.len, &g_singleviewData[VIEW_RIGHT_FRONT]);
			g_singleviewData[VIEW_RIGHT_FRONT].CameraData = &cameraData[CAM_RIGHT];
			break;
		case TAG_VIEW_RIGHT_REAR:
			err = setCamviewParams(&binData[dwBytes], tlv.len, &g_singleviewData[VIEW_RIGHT_REAR]);
			g_singleviewData[VIEW_RIGHT_REAR].CameraData = &cameraData[CAM_RIGHT];
			break;
		// pgs data
		case TAG_STATIC_PG_DATA:
			err = setPgsParams(&binData[dwBytes], tlv.len, &g_pgsData[0]);
			break;

		case TAG_DYNAMIC_PG_DATA:
			err = setPgsParams(&binData[dwBytes], tlv.len, &g_pgsData[1]);
			break;
	}
	dwBytes += tlv.len;
	if( err != HV_OK) {
		printf("loadCalibInfoFile() - failed. TAG(%d) size ERROR !!", tlv.tag);
		break;
	}
}


// car_length
for(index = 0; index < VIEW_TOTAL; index++) {
	HvSingleviewData * psdata = &g_singleviewData[index];
	if( psdata->flags & HV_ENABLE) {
		psdata->car_width = g_multiviewData[0].car_width;
		psdata->car_length = g_multiviewData[0].car_length;
	}
}

// pgs_data
for(index=0; index < 2; index++){
	HvPGSData *ppgs = &g_pgsData[index];
	ppgs->nCarWidth  = g_multiviewData[0].car_width;
	ppgs->nCarLength = g_multiviewData[0].car_length;
	ppgs->nCarHeight = g_multiviewData[0].car_height;
	ppgs->tread      = g_multiviewData[0].car_tread;
	ppgs->wheelbase  = g_multiviewData[0].car_wheelbase;

	ppgs->numAngle	= HV_STEER_ANGLE_MAX * 2 + 1; // -MAX ~ +MAX
	ppgs->numPoints = HV_PGS_POINTS_MAX;
}

free(binData);
binData = NULL;

return err;
}
*/