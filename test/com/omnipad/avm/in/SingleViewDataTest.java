package com.omnipad.avm.in;

import java.io.IOException;

import junit.framework.TestCase;

public class SingleViewDataTest extends TestCase {

	String path = System.getProperty("java.io.tmpdir") + "single-view-data.bin";
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();

		SingleViewData data = new SingleViewData();
		
		data.sy = 0.4f;
		data.shx = 0.9f;
		data.offsetX = 67;

		try {
			data.store(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testLoad() {
		try {
			SingleViewData data = SingleViewData.load(path);
			
			assertEquals(data.sy, 0.4f);
			assertEquals(data.shx, 0.9f);
			assertEquals(data.offsetX, 67);
			
			data.offsetY = 78;
			data.dsizeWidth = 960;
			
			data.store(path);
			
			data = SingleViewData.load(path);
			
			assertEquals(data.offsetY, 78);
			assertEquals(data.dsizeCenter.x, 480.0f);
			
		} catch (IOException e) {
			e.printStackTrace();
			assertFalse(true);
		}
	}
}
