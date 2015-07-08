package com.omnipad.avm;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.omnipad.avm.out.LUT;

public class VertexBuilder {
	final static int CAM_FRONT = 0;
	final static int CAM_REAR = 1;
	final static int CAM_LEFT = 2;
	final static int CAM_RIGHT = 3;
	final static int CAM_VIRTUAL = 4;
	final static int CAM_TOTAL = 5;

	final static int VIEW_ID_FRONT = 0;
	final static int VIEW_ID_FRONT_FULL = 1;
	final static int VIEW_ID_REAR = 2;
	final static int VIEW_ID_REAR_FULL = 3;
	final static int VIEW_ID_LEFT_SIDE = 4;
	final static int VIEW_ID_RIGHT_SIDE = 5;
	final static int VIEW_ID_DOUBLE_LEFT = 6;
	final static int VIEW_ID_DOUBLE_RIGHT = 7;
	final static int VIEW_ID_TOP = 8;
	final static int VIEW_ID_TOP_LAND = 9;
	final static int VIEW_ID_TOP_ONLY = 10;

	DataOutputStream vertexDos, textureDos;

	int viewIndex;
	int camId;
	LUT.Data lutData;

	public VertexBuilder(int viewIndex, int camId, LUT.Data lutData) {
		this.viewIndex = viewIndex;
		this.camId = camId;
		this.lutData = lutData;
	}

	public void ready(String vertexPath, String texturePath) throws IOException {
		File vertextFile, textureFile;

		vertextFile = new File(vertexPath);
		if (!vertextFile.exists()) {
			vertextFile.createNewFile();
		}

		vertexDos = new DataOutputStream(new FileOutputStream(vertextFile));

		textureFile = new File(texturePath);
		if (!textureFile.exists()) {
			textureFile.createNewFile();
		}

		textureDos = new DataOutputStream(new FileOutputStream(textureFile));
	}

	public void build() throws IOException {
		float[] org = new float[2];
		float[] diff = new float[2];

		switch (camId) {
		case CAM_FRONT:
			org[0] = Const.front_texs[2];
			org[1] = Const.front_texs[3];
			diff[0] = Const.front_texs[4] - Const.front_texs[0];
			diff[1] = Const.front_texs[5] - Const.front_texs[1];
			break;
		case CAM_REAR:
			org[0] = Const.rear_texs[2];
			org[1] = Const.rear_texs[3];
			diff[0] = Const.rear_texs[4] - Const.rear_texs[0];
			diff[1] = Const.rear_texs[5] - Const.rear_texs[1];
			break;
		case CAM_LEFT:
			org[0] = Const.left_texs[2];
			org[1] = Const.left_texs[3];
			diff[0] = Const.left_texs[4] - Const.left_texs[0];
			diff[1] = Const.left_texs[5] - Const.left_texs[1];
			break;
		case CAM_RIGHT:
			org[0] = Const.right_texs[2];
			org[1] = Const.right_texs[3];
			diff[0] = Const.right_texs[4] - Const.right_texs[0];
			diff[1] = Const.right_texs[5] - Const.right_texs[1];
			break;
		}

		float zoomX = 1;
		float[] ratio = new float[2];
		int DISPLAY_WIDTH = 1260;
		int DISPLAY_HEIGHT = 1200;
		int leftMargin = (int) (DISPLAY_WIDTH) / 1920;

		if (viewIndex == VIEW_ID_FRONT_FULL || viewIndex == VIEW_ID_REAR_FULL) {
			DISPLAY_WIDTH = 1920;
			leftMargin = 0;
		}
		zoomX = (float) DISPLAY_WIDTH / (float) 1920;

		float[] vpoint = new float[8];
		float[] tpoint = new float[8];

		ratio[0] = (float) DISPLAY_WIDTH / (float) lutData.width;
		ratio[1] = (float) DISPLAY_HEIGHT / (float) lutData.height;

		int step_unit = 4;
		int x, y, k;
		float i_x_max, i_x_min, i_y_max, i_y_min;
		int xmax, ymax;

		xmax = lutData.width;
		ymax = lutData.height;

		int ystep = (int) ((float) (ymax - 0) / (float) step_unit);

		for (y = 0; y < ymax - 1; y += ystep) {
			if (ymax <= y + ystep) {
				ystep = ymax - y - 1;
				if (ystep == 0)
					break;
			}

			int xstep = (int) ((float) (xmax - 0) / (float) step_unit);
			for (x = 0; x < xmax - 1; x += xstep) {
				if (xmax <= x + xstep) {
					xstep = xmax - x - 1;
					if (xstep == 0)
						break;
				}

				vpoint[0] = (x);
				vpoint[1] = (y);
				vpoint[2] = (x);
				vpoint[3] = ((y + ystep));
				vpoint[4] = ((x + xstep));
				vpoint[5] = ((y + ystep));
				vpoint[6] = ((x + xstep));
				vpoint[7] = (y);

				tpoint[0] = lutData.points[y * xmax + x].x;
				tpoint[1] = lutData.points[y * xmax + x].y;
				tpoint[2] = lutData.points[y * xmax + ystep * xmax + x].x;
				tpoint[3] = lutData.points[y * xmax + ystep * xmax + x].y;
				tpoint[4] = lutData.points[y * xmax + ystep * xmax + x + xstep].x;
				tpoint[5] = lutData.points[y * xmax + ystep * xmax + x + xstep].y;
				tpoint[6] = lutData.points[y * xmax + x + xstep].x;
				tpoint[7] = lutData.points[y * xmax + x + xstep].y;

				i_x_max = tpoint[0]; // 네 점의 output image에서의 min max값이
				i_x_min = tpoint[0];
				i_y_max = tpoint[1];
				i_y_min = tpoint[1];

				for (k = 1; k < 4; k++) {
					if (tpoint[k * 2] < i_x_min)
						i_x_min = tpoint[k];
					if (tpoint[k * 2 + 1] < i_y_min)
						i_y_min = tpoint[k];
					if (tpoint[k * 2] > i_x_max)
						i_x_max = tpoint[k];
					if (tpoint[k * 2 + 1] > i_y_max)
						i_y_max = tpoint[k];
				}

				if (i_x_min >= 0 && i_x_max < lutData.width && i_y_min >= 0
						&& i_y_max < lutData.height) {

					tpoint[0] = org[0] + diff[0] * (tpoint[0])
							/ lutData.width;
					tpoint[1] = org[1] + diff[1] * (tpoint[1])
							/ lutData.height;
					tpoint[2] = org[0] + diff[0] * (tpoint[2])
							/ lutData.width;
					tpoint[3] = org[1] + diff[1] * (tpoint[3])
							/ lutData.height;

					tpoint[4] = org[0] + diff[0] * (tpoint[4])
							/ lutData.width;
					tpoint[5] = org[1] + diff[1] * (tpoint[5])
							/ lutData.height;
					tpoint[6] = org[0] + diff[0] * (tpoint[6])
							/ lutData.width;
					tpoint[7] = org[1] + diff[1] * (tpoint[7])
							/ lutData.height;

					for (int i = 0; i < 4; i++) {
						vpoint[i * 2] = leftMargin
								+ ((((vpoint[i * 2] * ratio[0]) / DISPLAY_WIDTH) - 0.5f) * 2 * zoomX);
						// vVertices_pos[i*2] =
						// -(((inPos[i].x*2)/(float)OUTPUT_IMG_WIDTH) - 0.5f)*2;
						vpoint[i * 2 + 1] = (0.5f - (vpoint[i * 2 + 1] * ratio[1])
								/ DISPLAY_HEIGHT) * 2;
					}

					for (int i = 0; i < 8; i++) {
						vertexDos.writeFloat(vpoint[i]);
						vertexDos.writeFloat(tpoint[i]);
					}
				}
			}
		}
	}

	public void done() throws IOException {
		vertexDos.close();
		textureDos.close();
	}

}
