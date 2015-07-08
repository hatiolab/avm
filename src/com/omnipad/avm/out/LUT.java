package com.omnipad.avm.out;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.omnipad.avm.in.Point2D;

public class LUT {
	
	int meshWidth;
	int meshHeight;
	float minCx;
	float minCy;
	float maxCx;
	float maxCy;

	Data[] data;

	public class Data {
		public int width;
		public int height;
		public int len; // Point2D 갯수
		public Point2D[] points;

		public Data(int width, int height) {
			this.width = width;
			this.height = height;
			this.len = width * height;

			if (this.len > 0)
				points = new Point2D[this.len];
		}
	}

	public LUT() {
		// TODO Auto-generated constructor stub
	}

	public void store(String path) throws IOException {
		FileOutputStream fos = new FileOutputStream(path);
		DataOutputStream dos = new DataOutputStream(fos);

		dos.writeInt(meshWidth);
		dos.writeInt(meshHeight);
		dos.writeFloat(minCx);
		dos.writeFloat(minCy);
		dos.writeFloat(maxCx);
		dos.writeFloat(maxCy);

		dos.writeInt(data.length);

		for (int i = 0; i < data.length; i++) {
			dos.writeInt(data[i].width);
			dos.writeInt(data[i].height);
			dos.writeInt(data[i].len);
			for (int j = 0; j < data[i].points.length; j++) {
				dos.writeFloat(data[i].points[j].x);
				dos.writeFloat(data[i].points[j].y);
			}
		}

		dos.close();
	}
}

// int writeLUT(int view_index , int cam_id , const char * fileName, LUT * plut)
// {
//
// TODO 아래 구현안됨.
// if( view_index == VIEW_ID_TOP_LAND ){
// buildTopVerticesAndTex(view_index,cam_id,s_multi_landscape_vexDataPath[cam_id],s_multi_landscape_texDataPath[cam_id],
// plut);
// }else if( view_index == VIEW_ID_TOP || view_index == VIEW_ID_TOP_ONLY ){
// buildTopVerticesAndTex(view_index,cam_id,s_multi_vexDataPath[cam_id],s_multi_texDataPath[cam_id],
// plut);
// }else{
// buildTopVerticesAndTex(view_index,cam_id,s_vexDataPath[view_index],s_texDataPath[view_index],
// plut);
// }
//
// }

