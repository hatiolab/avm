package com.omnipad.avm;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.omnipad.avm.out.Box;
import com.omnipad.avm.out.LUT;
import com.omnipad.avm.out.Mask;
import com.omnipad.avm.out.VNT;

public class VertexMaker {
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

	int viewIndex;
	int camId;
	
	LUT.Data lutData;
	Mask mask;
	Box box;
	int step;
	
	DataOutputStream vertexDos, textureDos;

	public VertexMaker(int viewIndex, int camId, LUT.Data lutData, Mask mask, Box box, int step) {
		this.viewIndex = viewIndex;
		this.camId = camId;
		this.lutData = lutData;
		this.mask = mask;
		this.box = box;
		this.step = step;
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
		  int width = 1440;
		  int height = 960;

		  int mesh_width = 0;
		  int mesh_height = 0;
		  int count = 0;

		  int widthLut = lutData.width;
		  int heightLut = lutData.height;

		  float viewWidth = 960;
		  float viewHeight = 600;

		  if( viewIndex == VIEW_ID_TOP_ONLY ){
		    viewWidth = lutData.width;
		    viewHeight = lutData.height;
		    viewIndex = VIEW_ID_TOP;
		  }else if( viewIndex == VIEW_ID_FRONT ){
		    //viewWidth = lutData.width; //
		    //viewHeight = lutData.height;
		  }

		  int x, y;

		  for (y = 0; y < heightLut;y+=step){
		    mesh_height++;

		    if((y < heightLut-1) && y+step > heightLut)
		      y = heightLut-1-step;
		  }

		  for (x = 0; x < widthLut;x+=step){
		    mesh_width++;

		    if((x < widthLut-1) && x+step > widthLut)
		      x = widthLut-1-step;
		  }

		  count = mesh_width * mesh_height;

		  VNT.Data[] vnt = new VNT.Data[count];

		  int xIndex = 0;
		  int mIndex = 0;

		  //인덱스 버퍼의 수를 계산한다.
		  int size = (mesh_width*2*(mesh_height-1) + 2*( mesh_height - 2 ) );
		  short[] indices = new short[size];

		  for(y = 0; y < (mesh_height-1) ; y++ ){
		    for( x = 0; x < mesh_width ; x++ ){
		      indices[xIndex++] = (short)((mesh_width * y) + x);
		      indices[xIndex++] = (short)((mesh_width) * (y+1) + x);
		    }
		    if( y < (mesh_height-2) ){
		      indices[xIndex++] = indices[xIndex-1];
		      indices[xIndex++] = (short)((mesh_width) * (y+1));
		    }
		  }

		  // set view offset
		  float view_offsetx = 0.f;
		  float view_offsety = 0.f;

		  switch (viewIndex) {
		      case VIEW_ID_FRONT:
		      case VIEW_ID_REAR:
		      case VIEW_ID_LEFT_SIDE:
		      case VIEW_ID_RIGHT_SIDE:
		        view_offsetx = 334.0f;
		        view_offsety = 0.0f;
		        break;
		      case VIEW_ID_FRONT_FULL:
		      case VIEW_ID_REAR_FULL:
		        view_offsetx = 0.0f;
		        view_offsety = 0.0f;
		        break;
		      case VIEW_ID_DOUBLE_LEFT:
		        view_offsetx = 334.0f;
		        view_offsety = 0.0f;
		        break;
		      case VIEW_ID_DOUBLE_RIGHT:
		        view_offsetx = 656.0f;
		        view_offsety = 0.0f;
		        break;
		      case VIEW_ID_TOP:
		      case VIEW_ID_TOP_LAND:
		        view_offsetx = 0.0f;
		        view_offsety = 0.0f;
		        break;
		    }

		  float screen_offsetx = (widthLut  - viewWidth) * 0.5f + view_offsetx;
		  float screen_offsety = (heightLut - viewHeight) * 0.5f + view_offsety;

		  {

		    float hw = (float)(widthLut * 0.5);
		    float hh = (float)(heightLut * 0.5);

		    float screen_ratiox = widthLut/viewWidth;
		    float screen_ratioy = height/viewHeight;

		    float norm_posx = 1.f/hw * screen_ratiox;
		    float norm_posy = 1.f/hh * screen_ratioy;
		    float norm_texx = 1.f/(width-1);
		    float norm_texy = 1.f/(height-1);

		    int nxt_x, nxt_y;

		    float offset_x = 0.0f;
		    float offset_y = 0.0f;

		    switch(camId) {
		    case CAM_FRONT:
		      offset_x = 0.0f;
		      offset_y = 0.0f;
		      break;
		    case CAM_REAR:
		      offset_x = 0.5f;
		      offset_y = 0.0f;
		      break;
		    case CAM_LEFT:
		      offset_x = 0.0f;
		      offset_y = 0.5f;
		      break;
		    case CAM_RIGHT:
		      offset_x = 0.5f;
		      offset_y = 0.5f;
		      break;
		    }
		    //버텍스 버퍼
		    for (y = 0; y < heightLut;y+=step)
		    {
//		      Point2D lutLine = lutData.points[y*widthLut];
		      int lutLineIndex = y * widthLut;
		      xIndex = 0;
		      for (x = 0; x < widthLut;x+=step)
		      {
		        float mask = 0.1f;

		        vnt[mIndex].x =   ( (x)   - hw + screen_offsetx)* norm_posx;
		        vnt[mIndex].y =  -(((y)   - hh + screen_offsety)* norm_posy);

//		        if( box != null && (viewIndex == VIEW_ID_TOP || viewIndex == VIEW_ID_TOP_LAND || viewIndex == VIEW_ID_TOP_ONLY ) ){
//		          int emptyFind = 0;
//		          mask = maskValue( mask , camId , y*widthLut + x , emptyFind );
//		          findMaskValue( mask , camId , y*widthLut + x, vnt[mIndex], box );
//		        }
//
//		        vnt[mIndex].z = (mask != HIDEMASK) ? SHOWMASK : HIDEMASK ;
//
//		        vnt[mIndex].nx = 1.f;
//		        vnt[mIndex].ny = 1.f;
//		        if( (viewIndex == VIEW_ID_TOP || viewIndex == VIEW_ID_TOP_LAND || viewIndex == VIEW_ID_TOP_ONLY ) ){
//		          vnt[mIndex].nz = ( mask != HIDEMASK && mask != SHOWMASK ) ? mask : 1.0f;
//		        }else{
//		          vnt[mIndex].nz = 1.0f;
//		        }

		        if( (viewIndex == VIEW_ID_TOP || viewIndex == VIEW_ID_TOP_LAND || viewIndex == VIEW_ID_TOP_ONLY ) ){
		          if( vnt[mIndex].nz != 1.0f ){
		            //__android_log_print(ANDROID_LOG_INFO, LOG_TAG, "nz=%f", vnt[mIndex].nz );
		          }
		        }

		        vnt[mIndex].u = (lutData.points[lutLineIndex + x].x * norm_texx+ offset_x);
		        vnt[mIndex].v = (lutData.points[lutLineIndex + x].y * norm_texy+ offset_y);

		        mIndex++;

		        if((x < widthLut-1) && x+step > widthLut)
		          x = widthLut-1-step;

		      }

		      if((y < heightLut-1) && y+step > heightLut)
		        y = heightLut-1-step;

		    }
		  }

//		  writeIDX(mesh_width, mesh_height, indices , pidxfileName );
//		  if( box != null && (viewIndex == VIEW_ID_TOP || viewIndex == VIEW_ID_TOP_LAND || viewIndex == VIEW_ID_TOP_ONLY ) ){
//		    writeVNT(mesh_width, mesh_height, box.minX , box.minY , box.maxX , box.maxY , vnt , pvntfileName );
//		  }else{
//		    writeVNT(mesh_width, mesh_height, 0 , 0 , 0 , 0 , vnt , pvntfileName );
//		  }
	}
	
	public void done() throws IOException {
		vertexDos.close();
		textureDos.close();
	}
}
