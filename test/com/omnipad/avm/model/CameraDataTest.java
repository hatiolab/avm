package com.omnipad.avm.model;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.omnipad.avm.model.CameraData;

public class CameraDataTest {

	String path = System.getProperty("java.io.tmpdir") + "camera-data.bin";

	@Before
	public void setUp() throws Exception {
		CameraData data = new CameraData();
		
		data.d[8] = 100.0f;
		data.offsetY = 200;

		try {
			data.store(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		try {
			CameraData data = CameraData.load(path);
			
			assertEquals(data.d[8], 100.0f, 0f);
			assertEquals(data.offsetY, 200);
			
			data.offsetY = 78;
			data.d[7] = 203.9f;
			
			data.store(path);
			
			data = CameraData.load(path);
			
			assertEquals(data.offsetY, 78);
			assertEquals(data.d[7], 203.9f, 0f);
			
		} catch (IOException e) {
			e.printStackTrace();
			assertFalse(true);
		}
	}

}
