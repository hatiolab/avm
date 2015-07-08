package com.omnipad.avm;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.ini4j.Ini;

import com.omnipad.avm.in.CalDatum;
import com.omnipad.avm.in.CamPose;
import com.omnipad.avm.in.CameraData;
import com.omnipad.avm.out.LUT;

public class AVM {

	static String sdcardRoot = "/mnt";

	CamPose camPose;

	LUT.Data lutFrontSingleView;
	LUT.Data lutFrontFullSingleView;
	LUT.Data lutRearSingleView;
	LUT.Data lutRearFullSingleView;
	LUT.Data lutLeftSideSingleView;
	LUT.Data lutRightSideSingleView;
	LUT.Data lutDoubleLeftSingleView;
	LUT.Data lutDoubleRightSingleView;
	LUT.Data[] lutTopPlanerMultiView;
	LUT.Data[] lutTopPlanerLandscapeMultiView;

	public AVM() {
		// TODO Auto-generated constructor stub
	}

	public static void setSDCardRootPath(String path) {
		sdcardRoot = path;
	}

	void init() throws Exception, FileNotFoundException, IOException {
		Ini ini = new Ini();
		try {
			ini.load(new FileReader(sdcardRoot + Const.USER_INI_PATH));
		} catch (Exception e) {
			ini.load(new FileReader(sdcardRoot + Const.DEFAULT_INI_PATH));
		}

		camPose = new CamPose();
//		lutFrontSingleView = new LUT.Data(0, 0);

	}

	CalDatum calParams[] = new CalDatum[4];

	// CameraData cameraData[] = new CameraData[4];

	int getCameraPos(CamPose camPose, CameraData[] cameraData) {
		int err;

		/* initialize calParams and allocate memory */
		err = OPAVM.calibInit(calParams, cameraData);
		if (err != ErrorCode.OK) {
			System.out.println("hvCalibInit() - FAILED to initialze. ERRNO "
					+ err);
			return ErrorCode.FAILED;
		}

		/* calculate 4 cameras calibration */
		err = OPAVM.calibApply(calParams, cameraData, camPose);
		if (err != ErrorCode.OK) {
			System.out.println("hvCalibApply() - FAILED to calibrate. ERRNO "
					+ err);
			return ErrorCode.FAILED;
		}

		return ErrorCode.OK;
	}
	
	int initView() {
		return 0;
	}
}

/*
int oplib_initView(ESContext *esContext)
{
	UserData *userData = (UserData*)esContext->userData;
	int err;
	int i;

	// initialize SVM
#ifdef A20_LINUX
	err = oplib_loadViewData("/mnt/sdcard/mmcblk0p1/lut/odCalibInfo.bin");
#else
	err = oplib_loadViewData("data/odCalibInfo.bin");
#endif
	if(err!=HV_OK)
		return err;

	// reset IBO
	oplib_releaseView(esContext);

	// allocation memory
	userData->numTopview = AVM2D_TOTAL;
	userData->topviewData = (HvVertexData*)malloc(userData->numTopview * sizeof(HvVertexData));
	for(i =0; i < userData->numTopview; i++) {
		HvMultiviewData * pmdata = &g_multiviewData[i];
		HvVertexData *pVertex = &userData->topviewData[i];
		memset(pVertex, 0, sizeof(HvVertexData));

		if( pmdata->flags & HV_ENABLE) {
			InitIBO(pVertex, pmdata->dsize_width, pmdata->dsize_height, 4, 4);
		}
	}

	userData->numCamview = VIEW_TOTAL;
	userData->camviewData = (HvVertexData*)malloc(userData->numCamview * sizeof(HvVertexData));
	for(i =0; i < userData->numCamview; i++) {
		HvSingleviewData * psdata = &g_singleviewData[i];
		HvVertexData *pVertex = &userData->camviewData[i];
		memset(pVertex, 0, sizeof(HvVertexData));

		if( psdata->flags & HV_ENABLE) {
			InitIBO(pVertex, psdata->dsize_width, psdata->dsize_height, 4, 1);
		}
	}

	// camera calibration
	g_camPose.CamHeight	= 0.f;
	g_camPose.CamTransX	= 0.f;
	g_camPose.CamTransY	= 0.f;
	g_camPose.DownAngle	= 0.f;
	g_camPose.PanAngle	= 0.f;
	g_camPose.RotateAngle = 0.f;

	err = oplib_buildVertex(esContext);
	if(err!=HV_OK)
		return err;

	return HV_OK;
}

 */