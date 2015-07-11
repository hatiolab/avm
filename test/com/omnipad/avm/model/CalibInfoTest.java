package com.omnipad.avm.model;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.omnipad.avm.model.CalibInfo;

public class CalibInfoTest {

	String tempPath = System.getProperty("java.io.tmpdir") + "calib-info.bin";

	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		try {
			CalibInfo info = CalibInfo.load("resource/odCalibInfo.bin");
			
			info.save(tempPath);
			
			CalibInfo info_comp = CalibInfo.load(tempPath);
			
			assertEquals(info.cameraData[0].cx, info_comp.cameraData[0].cx, 0.0f);
			assertEquals(info.singleViewData[5].mmPerPixel, info_comp.singleViewData[5].mmPerPixel, 0.0f);
			assertEquals(info.multiViewData[0].cp.x, info_comp.multiViewData[0].cp.x, 0.0f);
			assertEquals(info.pgsData.length, info_comp.pgsData.length, 0.0f);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
