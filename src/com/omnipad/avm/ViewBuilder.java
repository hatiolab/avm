package com.omnipad.avm;

import com.omnipad.avm.camera.Camera;
import com.omnipad.avm.camera.MaskType;
import com.omnipad.avm.model.MultiViewData;
import com.omnipad.avm.model.PGSData;
import com.omnipad.avm.model.SingleViewData;
import com.omnipad.avm.out.LUT;
import com.omnipad.avm.out.Mask;

public class ViewBuilder {

	SingleViewData sv_front_view = new SingleViewData();
	SingleViewData sv_front_full_view = new SingleViewData();
	SingleViewData sv_rear_view = new SingleViewData();
	SingleViewData sv_rear_full_view = new SingleViewData();
	SingleViewData sv_left_side_view = new SingleViewData();
	SingleViewData sv_right_side_view = new SingleViewData();
	SingleViewData sv_double_left_view = new SingleViewData();
	SingleViewData sv_double_right_view = new SingleViewData();

	MultiViewData mv_top_planer_view = new MultiViewData();
	MultiViewData mv_top_planer_landscape_view = new MultiViewData();
	MultiViewData mv_toponly_planer_view = new MultiViewData();
	
	LUT lut_sv_front_view = new LUT();
	LUT lut_sv_front_full_view = new LUT();
	LUT lut_sv_rear_view = new LUT();
	LUT lut_sv_rear_full_view = new LUT();
	LUT lut_sv_left_side_view = new LUT();
	LUT lut_sv_right_side_view = new LUT();
	LUT lut_sv_double_left_view = new LUT();
	LUT lut_sv_double_right_view = new LUT();
	LUT[] lut_mv_top_planer_view = new LUT[4];
	LUT[] lut_mv_top_planer_landscape_view = new LUT[4];

	Mask mask_mv_top_planer_view;
	Mask mask_mv_top_planer_landscape_view;
	
	PGSData   s_pgs_data;
	
	private void buildTopView(boolean landscape, MaskType maskType, MultiViewData mvd, LUT[] luts, Mask mask) {
		
	}

	public void buildTopView(MaskType maskType) {
		buildTopView(false, maskType, mv_top_planer_view, lut_mv_top_planer_view, mask_mv_top_planer_view);
	}
	
	public void buildTopLandscapeView(MaskType maskType) {
		buildTopView(true, maskType, mv_top_planer_landscape_view, lut_mv_top_planer_landscape_view, mask_mv_top_planer_landscape_view);
	}
	
	public void buildSingleView(View view, Camera camera, SingleViewData svd, LUT lut) {
		
	}
	
	public void build(MaskType maskType) {
		buildTopView(maskType);
		buildTopLandscapeView(maskType);
		
		buildSingleView(View.FRONT, Camera.FRONT, sv_front_view, lut_sv_front_view);
		buildSingleView(View.FRONT_FULL, Camera.FRONT, sv_front_full_view, lut_sv_front_full_view);
		buildSingleView(View.LEFT_SIDE, Camera.LEFT, sv_left_side_view, lut_sv_left_side_view);
		buildSingleView(View.RIGHT_SIDE, Camera.RIGHT, sv_right_side_view, lut_sv_right_side_view);
		buildSingleView(View.DOUBLE_LEFT, Camera.LEFT, sv_double_left_view, lut_sv_double_left_view);
		buildSingleView(View.DOUBLE_RIGHT, Camera.RIGHT, sv_double_right_view, lut_sv_double_right_view);
		buildSingleView(View.REAR, Camera.REAR, sv_rear_view, lut_sv_rear_view);
		buildSingleView(View.REAR_FULL, Camera.REAR, sv_rear_full_view, lut_sv_rear_full_view);
	}
}
