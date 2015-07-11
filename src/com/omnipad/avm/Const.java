package com.omnipad.avm;

public class Const {
	// Camera ID
	public final static int CAM_FRONT				=	0;
	public final static int CAM_REAR				=	1;
	public final static int CAM_LEFT				=	2;
	public final static int CAM_RIGHT				=	3;
	public final static int CAM_VIRTUAL				=	4;
	public final static int CAM_TOTAL				=	5;

	// 2D AVM
	public final static int  AVM2D_FULL			= 	0;
	public final static int  AVM2D_FRONT		=	1;
	public final static int  AVM2D_REAR			=	2;
	public final static int  AVM2D_LS			=	3;
	public final static int  AVM2D_SLD			=	4;
	public final static int  AVM2D_TOTAL		=	5;

	// View Mode
	public final static int   VIEW_FRONT		=	0;
	public final static int   VIEW_FRONT_FULL	=	1;
	public final static int   VIEW_REAR			=	2;
	public final static int   VIEW_REAR_FULL	=	3;
	public final static int   VIEW_LEFT			=	4;
	public final static int   VIEW_LEFT_FRONT	=	5;
	public final static int   VIEW_LEFT_REAR	=	6;
	public final static int   VIEW_RIGHT		=	7;
	public final static int   VIEW_RIGHT_FRONT	=	8;
	public final static int   VIEW_RIGHT_REAR	=	9;
	public final static int   VIEW_TOTAL		=	10;
	
	// FLAG
	public final static int   HV_ENABLE			=	1;
	public final static int   HV_POLY			=	32;
	public final static int   HV_FOV			=		64;
	public final static int   HV_FLIP_VERTICAL	=	256;
	public final static int   HV_FLIP_HORIZONTAL	=	512;
	public final static int   HV_3SECTION		=	4096;
	public final static int   HV_OVERLAY		=		8192;
	public final static int   HV_MASK_OVERLAY	=	16384;
	public final static int   HV_FULLTOPVIEW	=		32768;
	
	// pgs
	public final static int HV_STEER_ANGLE_MIN	=	-40;
	public final static int HV_STEER_ANGLE_MAX	=	40;
	public final static int HV_PGS_POINTS_MAX	=	100;
	
	
	static String DEFAULT_INI_PATH = "/sdcard/omnipad/default.ini";
	static String USER_INI_PATH = "/sdcard/omnipad/user.ini";

	static String PGS_DATA_PATH = "/sdcard/omnipad/lut/rear.pgs";
	static String PGS_FULL_DATA_PATH = "/sdcard/omnipad/lut/rear_full.pgs";

	static String CAMERA_SECTION[] = { "CAMERA_FRONT", "CAMERA_REAR",
			"CAMERA_LEFT", "CAMERA_RIGHT", };

	static String VIEW_SECTION[] = { "VIEW_FRONT", "VIEW_FRONT_FULL",
			"VIEW_REAR", "VIEW_REAR_FULL", "VIEW_LEFT", "VIEW_RIGHT",
			"VIEW_LEFT_FRONT", "VIEW_RIGHT_FRONT", "AVM2D_FULL", "AVM2D_LS",
			"AVM2D_FULL" };

	static String CAMERA_DATA_PATH[] = {
			"/sdcard/omnipad/conf/camparam_front.ini",
			"/sdcard/omnipad/conf/camparam_rear.ini",
			"/sdcard/omnipad/conf/camparam_left.ini",
			"/sdcard/omnipad/conf/camparam_right.ini", };

	static String VIEW_DATA_PATH[] = { "/sdcard/omnipad/view/front_view.ini",
			"/sdcard/omnipad/view/front_full_view.ini",
			"/sdcard/omnipad/view/rear_view.ini",
			"/sdcard/omnipad/view/rear_full_view.ini",
			"/sdcard/omnipad/view/left_side_view.ini",
			"/sdcard/omnipad/view/right_side_view.ini",
			"/sdcard/omnipad/view/double_left_view.ini",
			"/sdcard/omnipad/view/double_right_view.ini",
			"/sdcard/omnipad/view/top_planer_view.ini",
			"/sdcard/omnipad/view/top_planer_landscape_view.ini" };

	static String DEFAULT_CAMERA_DATA_PATH[] = { "camparam_front.ini",
			"camparam_rear.ini", "camparam_left.ini", "camparam_right.ini", };

	static String DEFAULT_VIEW_DATA_PATH[] = { "front_view.ini",
			"front_full_view.ini", "rear_view.ini", "rear_full_view.ini",
			"left_side_view.ini", "right_side_view.ini",
			"double_left_view.ini", "double_right_view.ini",
			"top_planer_view.ini", "top_planer_landscape_view.ini" };

	static String LUT_DATA_PATH[] = { "/sdcard/omnipad/lut/front_view.lut",
			"/sdcard/omnipad/lut/front_full_view.lut",
			"/sdcard/omnipad/lut/rear_view.lut",
			"/sdcard/omnipad/lut/rear_full_view.lut",
			"/sdcard/omnipad/lut/left_side_view.lut",
			"/sdcard/omnipad/lut/right_side_view.lut",
			"/sdcard/omnipad/lut/double_left_view.lut",
			"/sdcard/omnipad/lut/double_right_view.lut",
			"/sdcard/omnipad/lut/top_planer_view.lut",
			"/sdcard/omnipad/lut/top_planer_landscape_view.lut" };

	static String MULTI_LUT_DATA_PATH[] = {
			"/sdcard/omnipad/lut/top_planer_view_front.lut",
			"/sdcard/omnipad/lut/top_planer_view_rear.lut",
			"/sdcard/omnipad/lut/top_planer_view_left.lut",
			"/sdcard/omnipad/lut/top_planer_view_right.lut" };

	static String MULTI_LANDSCAPE_LUT_DATA_PATH[] = {
			"/sdcard/omnipad/lut/top_planer_landscape_view_front.lut",
			"/sdcard/omnipad/lut/top_planer_landscape_view_rear.lut",
			"/sdcard/omnipad/lut/top_planer_landscape_view_left.lut",
			"/sdcard/omnipad/lut/top_planer_landscape_view_right.lut" };
	static String MASK_DATA_PATH[] = { "/sdcard/omnipad/mask/front_view.msk",
			"/sdcard/omnipad/mask/front_full_view.msk",
			"/sdcard/omnipad/mask/rear_view.msk",
			"/sdcard/omnipad/mask/rear_full_view.msk",
			"/sdcard/omnipad/mask/left_side_view.msk",
			"/sdcard/omnipad/mask/right_side_view.msk",
			"/sdcard/omnipad/mask/double_left_view.msk",
			"/sdcard/omnipad/mask/double_right_view.msk",
			"/sdcard/omnipad/mask/top_planer_view.msk",
			"/sdcard/omnipad/mask/top_planer_landscape_view.msk" };

	static String DETECT_IMAGE_PATH[] = { "/sdcard/omnipad/img/front.bmp",
			"/sdcard/omnipad/img/rear.bmp", "/sdcard/omnipad/img/left.bmp",
			"/sdcard/omnipad/img/right.bmp" };

	static String VERTEX_DATA_PATH[] = { "/sdcard/omnipad/vex/front_view.vex",
			"/sdcard/omnipad/vex/front_full_view.vex",
			"/sdcard/omnipad/vex/rear_view.vex",
			"/sdcard/omnipad/vex/rear_full_view.vex",
			"/sdcard/omnipad/vex/left_side_view.vex",
			"/sdcard/omnipad/vex/right_side_view.vex",
			"/sdcard/omnipad/vex/double_left_view.vex",
			"/sdcard/omnipad/vex/double_right_view.vex" };

	static String MULTI_VERTEX_DATA_PATH[] = {
			"/sdcard/omnipad/vex/top_planer_view_front.vex",
			"/sdcard/omnipad/vex/top_planer_view_rear.vex",
			"/sdcard/omnipad/vex/top_planer_view_left.vex",
			"/sdcard/omnipad/vex/top_planer_view_right.vex" };

	static String MULTI_LANDSCAPE_VERTEX_DATA_PATH[] = {
			"/sdcard/omnipad/vex/top_planer_landscape_view_front.vex",
			"/sdcard/omnipad/vex/top_planer_landscape_view_rear.vex",
			"/sdcard/omnipad/vex/top_planer_landscape_view_left.vex",
			"/sdcard/omnipad/vex/top_planer_landscape_view_right.vex" };

	static String TEXTURE_DATA_PATH[] = { "/sdcard/omnipad/tex/front_view.tex",
			"/sdcard/omnipad/tex/front_full_view.tex",
			"/sdcard/omnipad/tex/rear_view.tex",
			"/sdcard/omnipad/tex/rear_full_view.tex",
			"/sdcard/omnipad/tex/left_side_view.tex",
			"/sdcard/omnipad/tex/right_side_view.tex",
			"/sdcard/omnipad/tex/double_left_view.tex",
			"/sdcard/omnipad/tex/double_right_view.tex",
			"/sdcard/omnipad/tex/top_planer_view.tex",
			"/sdcard/omnipad/tex/top_planer_landscape_view.tex" };

	static String MULTI_TEXTURE_DATA_PATH[] = {
			"/sdcard/omnipad/tex/top_planer_view_front.tex",
			"/sdcard/omnipad/tex/top_planer_view_rear.tex",
			"/sdcard/omnipad/tex/top_planer_view_left.tex",
			"/sdcard/omnipad/tex/top_planer_view_right.tex" };

	static String MULTI_LANDSCAPE_TEXTURE_DATA_PATH[] = {
			"/sdcard/omnipad/tex/top_planer_landscape_view_front.tex",
			"/sdcard/omnipad/tex/top_planer_landscape_view_rear.tex",
			"/sdcard/omnipad/tex/top_planer_landscape_view_left.tex",
			"/sdcard/omnipad/tex/top_planer_landscape_view_right.tex" };

	static String VNT_DATA_PATH[] = { "/sdcard/omnipad/vnt/front_view.vnt",
			"/sdcard/omnipad/vnt/front_full_view.vnt",
			"/sdcard/omnipad/vnt/rear_view.vnt",
			"/sdcard/omnipad/vnt/rear_full_view.vnt",
			"/sdcard/omnipad/vnt/left_side_view.vnt",
			"/sdcard/omnipad/vnt/right_side_view.vnt",
			"/sdcard/omnipad/vnt/double_left_view.vnt",
			"/sdcard/omnipad/vnt/double_right_view.vnt",
			"/sdcard/omnipad/vnt/top_planer_view.vnt",
			"/sdcard/omnipad/vnt/top_planer_landscape_view.vnt" };

	static String MULTI_VNT_DATA_PATH[] = {
			"/sdcard/omnipad/vnt/top_planer_view_front.vnt",
			"/sdcard/omnipad/vnt/top_planer_view_rear.vnt",
			"/sdcard/omnipad/vnt/top_planer_view_left.vnt",
			"/sdcard/omnipad/vnt/top_planer_view_right.vnt" };

	static String MULTI_TOPONLY_VNT_DATA_PATH[] = {
			"/sdcard/omnipad/vnt/toponly_planer_view_front.vnt",
			"/sdcard/omnipad/vnt/toponly_planer_view_rear.vnt",
			"/sdcard/omnipad/vnt/toponly_planer_view_left.vnt",
			"/sdcard/omnipad/vnt/toponly_planer_view_right.vnt" };

	static String MULTI_LANDSCAPE_VNT_DATA_PATH[] = {
			"/sdcard/omnipad/vnt/top_planer_landscape_view_front.vnt",
			"/sdcard/omnipad/vnt/top_planer_landscape_view_rear.vnt",
			"/sdcard/omnipad/vnt/top_planer_landscape_view_left.vnt",
			"/sdcard/omnipad/vnt/top_planer_landscape_view_right.vnt" };

	static String INDEX_DATA_PATH[] = { "/sdcard/omnipad/vnt/front_view.idx",
			"/sdcard/omnipad/vnt/front_full_view.idx",
			"/sdcard/omnipad/vnt/rear_view.idx",
			"/sdcard/omnipad/vnt/rear_full_view.idx",
			"/sdcard/omnipad/vnt/left_side_view.idx",
			"/sdcard/omnipad/vnt/right_side_view.idx",
			"/sdcard/omnipad/vnt/double_left_view.idx",
			"/sdcard/omnipad/vnt/double_right_view.idx",
			"/sdcard/omnipad/vnt/top_planer_view.idx",
			"/sdcard/omnipad/vnt/top_planer_landscape_view.idx" };

	static String MULTI_INDEX_DATA_PATH[] = {
			"/sdcard/omnipad/vnt/top_planer_view_front.idx",
			"/sdcard/omnipad/vnt/top_planer_view_rear.idx",
			"/sdcard/omnipad/vnt/top_planer_view_left.idx",
			"/sdcard/omnipad/vnt/top_planer_view_right.idx" };

	static String MULTI_TOPONLY_INDEX_DATA_PATH[] = {
			"/sdcard/omnipad/vnt/toponly_planer_view_front.idx",
			"/sdcard/omnipad/vnt/toponly_planer_view_rear.idx",
			"/sdcard/omnipad/vnt/toponly_planer_view_left.idx",
			"/sdcard/omnipad/vnt/toponly_planer_view_right.idx" };

	static String MULTI_LANDSCAPE_INDEX_DATA_PATH[] = {
			"/sdcard/omnipad/vnt/top_planer_landscape_view_front.idx",
			"/sdcard/omnipad/vnt/top_planer_landscape_view_rear.idx",
			"/sdcard/omnipad/vnt/top_planer_landscape_view_left.idx",
			"/sdcard/omnipad/vnt/top_planer_landscape_view_right.idx" };


	static public float[] texs = { 0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, };

	static public float[] left_texs = { 0.0f, 0.5f, 0.0f, 1.0f, 0.5f, 1.0f, 0.5f, 0.5f, };

	static public float[] right_texs = { 0.5f, 0.5f, 0.5f, 1.0f, 1.0f, 1.0f, 1.0f, 0.5f, };

	static public float[] front_texs = { 0.0f, 0.0f, 0.0f, 0.5f, 0.5f, 0.5f, 0.5f, 0.0f, };

	static public float[] rear_texs = { 0.5f, 0.0f, 0.5f, 0.5f, 1.0f, 0.5f, 1.0f, 0.0f, };

}
