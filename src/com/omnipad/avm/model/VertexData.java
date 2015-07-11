package com.omnipad.avm.model;

import java.io.FileInputStream;
import java.io.IOException;

import com.omnipad.avm.calib.Const;
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
	
	public VertexData(int viewWidth, int viewHeight, int resolution, int nCh) {
		setCameraParams(viewWidth, viewHeight, resolution, nCh);
	}
	
	public void setCameraParams(int viewWidth, int viewHeight, int resolution, int nCh) {
		int xLength = (viewWidth  / resolution) +  ((viewWidth % resolution > 0) ? 1:0);
		int yLength = (viewHeight / resolution) +  ((viewHeight % resolution > 0) ? 1:0);	
		int nCount = xLength * yLength;

		this.vertexCount	=  nCount * (POSITION_DATA_SIZE_IN_ELEMENTS + MSK_DATA_SIZE_IN_ELEMENTS + LUT_DATA_SIZE_IN_ELEMENTS * nCh);
		this.nStride		=  (POSITION_DATA_SIZE_IN_ELEMENTS + MSK_DATA_SIZE_IN_ELEMENTS + LUT_DATA_SIZE_IN_ELEMENTS * nCh) * BYTES_PER_FLOAT;

		this.indexCount	= (xLength*2*(yLength-1) + 2*( yLength - 2 ));		

		this.resolution	= resolution;
		this.nCh			= nCh;

		this.viewWidth	= viewWidth;
		this.viewHeight	= viewHeight;
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
				int viewWidth = Util.readCalibInfoInt(is);
				int viewHeight = Util.readCalibInfoInt(is);
				int nCh = Util.readCalibInfoInt(is);
				int resolution = Util.readCalibInfoInt(is);

				vbo.setCameraParams(viewWidth, viewHeight, resolution, nCh);

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
	
	public void build(Point2D[] lookupTable, int videoWidth, int videoHeight, int screenWidth, int screenHeight, int offsetX, int offsetY) {
		float[] vertices	= this.vertices;
		short[] indices 	= this.indices;
		int viewWidth		= this.viewWidth;
		int viewHeight		= this.viewHeight;
		int nCh				= this.nCh;
		int camId			= this.camId;
		int resolution		= this.resolution;

		float viewOffsetX	= (float)offsetX * 2.f;// / (float)viewWidth;
		float viewOffsetY	= (float)offsetY * 2.f;// / (float)viewHeight;

		float screenRatioX	= (float)viewWidth/(float)screenWidth;
		float screenRatioY 	= (float)viewHeight/(float)screenHeight;

		float screenOffsetX = (float)(viewWidth - screenWidth   + viewOffsetX)/ (float)screenWidth;
		float screenOffsetY = (float)(viewHeight - screenHeight + viewOffsetY)/ (float)screenHeight;

		float normTexX		= 1.f/(float)videoWidth;
		float normTexY		= 1.f/(float)videoHeight;

		int xLength			= (viewWidth  / resolution) +  ((viewWidth%resolution > 0) ? 1:0);
		int yLength			= (viewHeight / resolution) +  ((viewHeight%resolution > 0) ? 1:0);	

		// Texture postion
		float xOffset, yOffset;
		
		switch(camId) {
		case Const.CAM_FRONT:
			xOffset = 0.0f;
			yOffset = 0.0f;
			break;
		case Const.CAM_REAR:
			xOffset = 0.5f;
			yOffset = 0.0f;
			break;
		case Const.CAM_LEFT:
			xOffset = 0.0f;
			yOffset = 0.5f;
			break;
		case Const.CAM_RIGHT:
			xOffset = 0.5f;
			yOffset = 0.5f;
			break;
		default:
			xOffset = 0.0f;
			yOffset = 0.0f;
		}

		// Building the Position data
		int x, y, i;
		int nX, nY, nTri, nIndex;
		int viewSize = viewWidth * viewHeight;
		Point2D[] line = new Point2D[xLength];
		
		nIndex = 0;

		for( y = 0; y < yLength; y++ ) {

			float yRatio = (float)y / (float)(yLength -1);

			nY = y * resolution;
			if(nY > viewHeight - 1) nY = viewHeight - 1;
			float yPos = ((((float)nY) * 2.f/(float)viewHeight - 1.f)) * screenRatioY;

			System.arraycopy(lookupTable, nY*viewWidth, line, 0, xLength); 

			for( x = 0; x < xLength; x++ ){

				float xRatio = (float)x / (float)(xLength-1);

				nX = x * resolution;
				if(nX > viewWidth - 1) nX = viewWidth - 1;
				float xPos = ((((float)nX) * 2.f/(float)viewWidth  - 1.f)) * screenRatioX ;

				// Position
				vertices[nIndex++] =   xPos  + screenOffsetX;
				vertices[nIndex++] =  -(yPos + screenOffsetY);
				vertices[nIndex++] = 0.f;

				// Normalize the normal
				//pVertices[nIndex].nx = 1.f;
				//pVertices[nIndex].ny = 1.f;
				//pVertices[nIndex].nz = 1.f;

				// mask
				vertices[nIndex++] = xRatio;
				vertices[nIndex++] = yRatio;

				// lut
				vertices[nIndex++] = line[nX].x  * normTexX + xOffset;
				vertices[nIndex++] = line[nX].y  * normTexY + yOffset;

				// Add some lut.
				if( nCh == 4 ) {
					vertices[nIndex++] = line[nX+viewSize].x  * normTexX + 0.5f;
					vertices[nIndex++] = line[nX+viewSize].y  * normTexY + 0.0f;

					vertices[nIndex++] = line[nX+viewSize * 2].x  * normTexX + 0.0f;
					vertices[nIndex++] = line[nX+viewSize * 2].y  * normTexY + 0.5f;

					vertices[nIndex++] = line[nX+viewSize * 3].x * normTexX + 0.5f;
					vertices[nIndex++] = line[nX+viewSize * 3].y * normTexY + 0.5f;
				}
			}
		}
		
		// The next step is to link all of these vertices together, using the index buffer.
		nIndex = 0;

		for (y = 0; y < yLength - 1; y++) {
			if (y > 0) {
				// Degenerate begin: repeat first vertex
				indices[nIndex++] = (short) (y * xLength);
			}
			for (x = 0; x < xLength; x++) {
				// One part of the strip
				indices[nIndex++] = (short) ((y * xLength) + x);
				indices[nIndex++] = (short) (((y + 1) * xLength) + x);
			}
			if (y < yLength - 2) {
				// Degenerate end: repeat last vertex
				indices[nIndex++] = (short) (((y + 1) * xLength) + (xLength - 1));
			}
		}
		
	}
}
