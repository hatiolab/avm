package com.omnipad.avm.camera;

import java.util.HashMap;

import com.omnipad.avm.model.CalDatum;
import com.omnipad.avm.model.CameraData;

public class Calibrator {
	boolean autoDetect = false;

	MarkerType markerType;
	MaskType maskType;
	
	HashMap<Camera, CameraData> cameraDatas;

	int markerLength = 1000;

	CalDatum[] calParams = new CalDatum[4]; // 카메라 보정을 위한 파라미터
	
	public Calibrator() {
		// TODO Auto-generated constructor stub
	}

	public void init() {
		// HVAPI(INT)  hvCalibInit(HvCalDatum CalParams[], INT marker_type, INT marker_size);
		// ..
	}
	
	public void detectMarker(int w, int h, byte[] image, MarkerType markerType, int markerLength, Camera camera) {
		CameraData cameraData = cameraDatas.get(camera);
		
		//hvDetectMarker(w, h, image, cameraData, camera);
	}
	
	public void start() {
		if(markerType != MarkerType.SQUARE)
			autoDetect = false;

		if(autoDetect) {
			for (Camera camera : Camera.values()) {
				String path = CapturedImage.get(camera);

				int w = 720;
				int h = 480;

//				BitmapFactory.Options options = new BitmapFactory.Options();
//				options.inPreferredConfig = Bitmap.Config.ARGB_8888;
//				Bitmap bitmap = BitmapFactory.decodeFile(path, options);
//				bitmap.getPixels(pixels, offset, stride, x, y, width, height);

//				byte[] image = loadbmp(path, w, h);

//		        detectMarker(w, h, image, markerType, markerLength, camera);
			}
	    }

		// calculate 4 cameras calibration
//	    hvCalibApply(calParams, s_cameraData, &s_cameraPose);

//	    writeAll();		
	}
	
//	public void writeSingleViewData() {
//		...
//	}
//	
//	public void writeMultiViewData() {
//		
//	}
//	
//	public void writeCameraParams() {
//		
//	}
//	
//	public void writeAll() {
//	  SAPPEND_START( s_user_ini_path );
//	
//	  writeSingleviewData(file, s_viewSection[VIEW_ID_FRONT], &sv_front_view);
//	  writeSingleviewData(file, s_viewSection[VIEW_ID_FRONT_FULL],
//	      &sv_front_full_view);
//	  writeSingleviewData(file, s_viewSection[VIEW_ID_REAR], &sv_rear_view);
//	  writeSingleviewData(file, s_viewSection[VIEW_ID_REAR_FULL],
//	      &sv_rear_full_view);
//	  writeSingleviewData(file, s_viewSection[VIEW_ID_LEFT_SIDE],
//	      &sv_left_side_view);
//	  writeSingleviewData(file, s_viewSection[VIEW_ID_RIGHT_SIDE],
//	      &sv_right_side_view);
//	  writeSingleviewData(file, s_viewSection[VIEW_ID_DOUBLE_LEFT],
//	      &sv_double_left_view);
//	  writeSingleviewData(file, s_viewSection[VIEW_ID_DOUBLE_RIGHT],
//	      &sv_double_right_view);
//	
//	  writeMultiviewData(file, s_viewSection[VIEW_ID_TOP], &mv_top_planer_view);
//	  writeMultiviewData(file, s_viewSection[VIEW_ID_TOP_LAND],
//	      &mv_top_planer_landscape_view);
//	  //writeMultiviewData(file, s_viewSection[VIEW_ID_TOP_ONLY],
//	  //      &mv_toponly_planer_view);
//	
//	  writeCameraParam(file, s_cameraSection[CAM_FRONT],
//	      &s_cameraData[CAM_FRONT]);
//	  writeCameraParam(file, s_cameraSection[CAM_REAR], &s_cameraData[CAM_REAR]);
//	  writeCameraParam(file, s_cameraSection[CAM_LEFT], &s_cameraData[CAM_LEFT]);
//	  writeCameraParam(file, s_cameraSection[CAM_RIGHT],
//	      &s_cameraData[CAM_RIGHT]);
//	}
}
