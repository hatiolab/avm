package com.omnipad.avm.out;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class VNT {

    public class Data {
        public float x, y, z;		// Vertex
        public float nx, ny, nz;	// Normal
        public float u, v;			// Texcoord0
    }
    
	public VNT() {
		// TODO Auto-generated constructor stub
	}

	public void store(String path) throws IOException {
		FileOutputStream fos = new FileOutputStream(path);
		DataOutputStream dos = new DataOutputStream(fos);

//		write( fd, &(mesh_width), 4);
//		write( fd, &(mesh_height), 4);
//		write( fd, &(minCX), 4);
//		write( fd, &(minCY), 4);
//		write( fd, &(maxCX), 4);
//		write( fd, &(maxCY), 4);
//
//		write( fd, &(len), 4);
//		write( fd, pvnt, len*sizeof(VNT) );
//		dos.writeFloat(x);
//		dos.writeFloat(y);
//		dos.writeFloat(z);
//		dos.writeFloat(nx);
//		dos.writeFloat(ny);
//		dos.writeFloat(nz);
//		dos.writeFloat(u);
//		dos.writeFloat(v);
		
		dos.close();
	}
}

//int writeVNT(int mesh_width, int mesh_height, float minCX , float minCY , float maxCX , float maxCY , VNT * pvnt , const char * fileName ) {
//
// TODO 이런 제길..
//		int len = mesh_width * mesh_height;
//		int    fd;
//
//		if ( 0 <= ( fd = open( outFile, O_WRONLY | O_CREAT |O_TRUNC, 644)) ){
//			write( fd, &(mesh_width), 4);
//			write( fd, &(mesh_height), 4);
//			write( fd, &(minCX), 4);
//			write( fd, &(minCY), 4);
//			write( fd, &(maxCX), 4);
//			write( fd, &(maxCY), 4);
//
//			write( fd, &(len), 4);
//			write( fd, pvnt, len*sizeof(VNT) );
//
//			close( fd);
//
//			__android_log_print(ANDROID_LOG_INFO, LOG_TAG, "write file : %s",
//							outFile);
//
//			return HV_OK;
//		}
//
//		__android_log_print(ANDROID_LOG_ERROR, LOG_TAG, "ERROR write file : %s",
//						outFile);
//
//	}
//	return HV_FAILED;
//}