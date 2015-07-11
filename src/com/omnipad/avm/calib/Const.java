package com.omnipad.avm.calib;

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
	
}
