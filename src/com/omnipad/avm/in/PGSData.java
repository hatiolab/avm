package com.omnipad.avm.in;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class PGSData {
	float tread;
	float wheelBase;
	float parkingTrjDist;
	int steerAngle;
	int carWidth;
	int carLength;
	int carHeight;
	int rearAxle;
	int offset;
	int offsetDistance;
	Point2D rotCenter;

	int corrdType;
	int angle;
	int points;
	Point2D pgsWorld;
	Point2D pgsImage;
	Point2D pgsView;

	int line;

	public PGSData(MultiViewData topPlanerView) {
		carWidth = topPlanerView.carWidth;
		carLength = topPlanerView.carLength;
		carHeight = topPlanerView.carHeight;
		tread = topPlanerView.carTread;
		wheelBase = topPlanerView.carWheelBase;

		offset = 0;
		rearAxle = 0;
	}

	public void store(String path) throws IOException {
		FileOutputStream fos = new FileOutputStream(path);
		DataOutputStream dos = new DataOutputStream(fos);
		
		int pointBufferSize = 8 /* sizeof(Point2D) */ * this.angle * points;
		int size = 100 /* sizeof(PGSData) */ - 8 /* sizeof(Point2D) */ * 3 + pointBufferSize * 3;

		byte[] buffer = new byte[size];
		for(int i = 0;i < size;i++)
			buffer[i] = 0;
		
		int tailSize = 4 /* sizeof(INT) */ + 16 /* sizeof(PGSLine) */ * 5;
		int frontSize = 100 /* sizeof(PGSData) */ - (8 /* sizeof(Point2D) */ * 3) + tailSize; 

		int tailPosition = 100 /* end of PGSData */ - tailSize;

//		buffer.
//
//	    memcpy( buffer , (void *)pgsData , front_size );
//	    BYTE * ptempBuffer = buffer + front_size;
//
//	    __android_log_print(ANDROID_LOG_INFO, LOG_TAG, "pgsWorld memcpy size : [%d]",point_buffer_size);
//	    p( "world x =%f" , pgsData->pgsWorld[0].x  );
//	    p( "world y =%f" , pgsData->pgsWorld[0].y  );
//	    memcpy( ptempBuffer , pgsData->pgsWorld , point_buffer_size );
//
//	    ptempBuffer += point_buffer_size;
//	    __android_log_print(ANDROID_LOG_INFO, LOG_TAG, "pgsImage memcpy size : [%d]",point_buffer_size);
//	    p( "image x =%f" , pgsData->pgsImage[0].x  );
//	    p( "image y =%f" , pgsData->pgsImage[0].y  );
//
//	    memcpy( ptempBuffer , pgsData->pgsImage , point_buffer_size );
//
//	    ptempBuffer += point_buffer_size;
//	    __android_log_print(ANDROID_LOG_INFO, LOG_TAG, "pgsView memcpy size : [%d]",point_buffer_size);
//	    p( "view x =%f" , pgsData->pgsView[0].x  );
//	    p( "view y =%f" , pgsData->pgsView[0].y  );
//	    memcpy( ptempBuffer , pgsData->pgsView , point_buffer_size );
//	    ptempBuffer += point_buffer_size;
//
//	    memcpy( ptempBuffer , tail_pointer  , tail_size );
//
//	    int err = writeFile( fileName , buffer , size );
//
//	    free( buffer );
		
		dos.close();
	}
}
