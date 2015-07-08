package com.omnipad.avm;

import java.util.Dictionary;

import com.omnipad.avm.in.CamPose;
import com.omnipad.avm.in.CameraData;
import com.omnipad.avm.in.MultiViewData;
import com.omnipad.avm.in.PGSData;
import com.omnipad.avm.in.SingleViewData;
import com.omnipad.avm.out.LUT;
import com.omnipad.avm.out.Mask;

public class Data {
	
	CameraData[] s_cameraData; // size 4

	SingleViewData sv_front_view;
	SingleViewData sv_front_full_view;
	SingleViewData sv_rear_view;
	SingleViewData sv_rear_full_view;
	SingleViewData sv_left_side_view;
	SingleViewData sv_right_side_view;
	SingleViewData sv_double_left_view;
	SingleViewData sv_double_right_view;
	MultiViewData mv_top_planer_view;
	MultiViewData mv_top_planer_landscape_view;
	MultiViewData mv_toponly_planer_view;
	PGSData s_pgs_data;

	CamPose s_cameraPose;

	//
	LUT lut_sv_front_view;
	LUT lut_sv_front_full_view;
	LUT lut_sv_rear_view;
	LUT lut_sv_rear_full_view;
	LUT lut_sv_left_side_view;
	LUT lut_sv_right_side_view;
	LUT lut_sv_double_left_view;
	LUT lut_sv_double_right_view;
	LUT[] lut_mv_top_planer_view; // size 4
	LUT[] lut_mv_top_planer_landscape_view; // size 4

	//
	Mask mask_mv_top_planer_view;
	Mask mask_mv_top_planer_landscape_view;

	Dictionary iniDefault = null;
	String sdcardRoot;

	boolean omni_pad_initialize_flag = false;

	public Data() {
		// TODO Auto-generated constructor stub
	}

}
