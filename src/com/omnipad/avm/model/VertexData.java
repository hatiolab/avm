package com.omnipad.avm.model;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import com.omnipad.avm.calib.Util;


public class VertexData {

	public static final int TAG_VBO_PARAM	=		0x10;
	public static final int TAG_VBO_DATA	=		0x11;
	public static final int TAG_IBO_DATA	=		0x12;
	public static final int TAG_MSK_DATA	=		0x13;

	public static final int TYPE_VBO		=		0x01;
	public static final int TYPE_IBO		=		0x02;
	public static final int TYPE_MBO		=		0x04;

	public static final int POSITION_DATA_SIZE_IN_ELEMENTS	= 3;
	public static final int MSK_DATA_SIZE_IN_ELEMENTS		= 2;
	public static final int LUT_DATA_SIZE_IN_ELEMENTS		= 2;

	public static final int BYTES_PER_FLOAT					= 4;
	public static final int BYTES_PER_SHORT					= 2;

	public static final int TOPVERTEX					=	(POSITION_DATA_SIZE_IN_ELEMENTS + MSK_DATA_SIZE_IN_ELEMENTS + LUT_DATA_SIZE_IN_ELEMENTS * 4);
	public static final int NVERTEX						=	(POSITION_DATA_SIZE_IN_ELEMENTS + MSK_DATA_SIZE_IN_ELEMENTS + LUT_DATA_SIZE_IN_ELEMENTS);

	public int camId;
	public int resolution;

	public boolean enable;

	// Vertices
	public int vertexCount;
	public float[] vertices;

	// Index
	public int indexCount;
	public short[] indices;

	// Mask Texture
	public int viewWidth;
	public int viewHeight;
	public byte[] viewMask;

	// VBO
	public int[] vbo = new int[1];
	public int[] ibo = new int[1];

	public int nCh;
	public int nStride;
	
	public VertexData() {
	}
	
	private static int calcWidthByte(int width, int bitCount) {
		return (width * bitCount / 8 + 3) & ~3;
	}
	
	public VertexData(int width, int height, int resolution, int nCh) {
		int xLength = (width  / resolution) +  ((width % resolution > 0) ? 1:0);
		int yLength = (height / resolution) +  ((height % resolution > 0) ? 1:0);	
		int nCount = xLength * yLength;

		this.vertexCount	=  nCount * (POSITION_DATA_SIZE_IN_ELEMENTS + MSK_DATA_SIZE_IN_ELEMENTS + LUT_DATA_SIZE_IN_ELEMENTS * nCh);
		this.nStride		=  (POSITION_DATA_SIZE_IN_ELEMENTS + MSK_DATA_SIZE_IN_ELEMENTS + LUT_DATA_SIZE_IN_ELEMENTS * nCh) * BYTES_PER_FLOAT;

		this.indexCount	= (xLength*2*(yLength-1) + 2*( yLength - 2 ));		

		this.resolution	= resolution;
		this.nCh			= nCh;

		this.viewWidth	= width;
		this.viewHeight	= height;
		this.enable		= false;
	}

	public static VertexData load(String path) throws IOException {
		
		FileInputStream is = new FileInputStream(path);

		VertexData vbo = new VertexData();
				
		while(is.available() > 0) {
			int tag = Util.readCalibInfoInt(is);
			int len = Util.readCalibInfoInt(is);

			switch(tag) {
			// camera data
			case TAG_VBO_PARAM:
				vbo.camId = Util.readCalibInfoInt(is);
				vbo.viewWidth = Util.readCalibInfoInt(is);
				vbo.viewHeight = Util.readCalibInfoInt(is);
				vbo.nCh = Util.readCalibInfoInt(is);
				vbo.resolution = Util.readCalibInfoInt(is);
				
				int xLength = (vbo.viewWidth  / vbo.resolution) +  ((vbo.viewWidth % vbo.resolution > 0) ? 1:0);
				int yLength = (vbo.viewHeight / vbo.resolution) +  ((vbo.viewHeight % vbo.resolution > 0) ? 1:0);	
				int nCount = xLength * yLength;

				vbo.vertexCount	=  nCount * (POSITION_DATA_SIZE_IN_ELEMENTS + MSK_DATA_SIZE_IN_ELEMENTS + LUT_DATA_SIZE_IN_ELEMENTS * vbo.nCh);
				vbo.nStride		=  (POSITION_DATA_SIZE_IN_ELEMENTS + MSK_DATA_SIZE_IN_ELEMENTS + LUT_DATA_SIZE_IN_ELEMENTS * vbo.nCh) * BYTES_PER_FLOAT;

				vbo.indexCount	= (xLength*2*(yLength-1) + 2*( yLength - 2 ));		

				break;
			case TAG_VBO_DATA:
				if(len != vbo.vertexCount *  4) { // BYTES_PER_FLOAT
					System.out.println("SIZE MISMATCH");
					break;
				}

				vbo.vertices = new float[len / 4];
				for(int i = 0;i < vbo.vertices.length;i++)
					vbo.vertices[i] = Util.readCalibInfoFloat(is);
				break;
			case TAG_IBO_DATA:
				if(len != vbo.indexCount *  2) {  // BYTES_PER_SHORT
					System.out.println("SIZE MISMATCH");
					break;
				}
				
				vbo.indices = new short[len / 2];
				for(int i = 0;i < vbo.indices.length;i++)
					vbo.indices[i] = Util.readCalibInfoShort(is);
				break;
			case TAG_MSK_DATA:
				if(len != vbo.viewHeight * calcWidthByte(vbo.viewWidth, 24)) {  // BYTES_PER_SHORT
					System.out.println("SIZE MISMATCH");
					break;
				}
				vbo.viewMask = new byte[len];
				is.read(vbo.viewMask);

				break;
			default:
			}
		}
			
		is.close();
		
		return vbo;
	}
}
