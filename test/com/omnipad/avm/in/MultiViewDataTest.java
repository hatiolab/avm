package com.omnipad.avm.in;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MultiViewDataTest {

	String path = System.getProperty("java.io.tmpdir") + "multi-view-data.bin";

	@Before
	public void setUp() throws Exception {
		MultiViewData data = new MultiViewData();
		
		data.maskBlend = 0.4f;
		data.dsizeCenter.x = 0.9f;
		data.offsetX = 67;

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
			MultiViewData data = MultiViewData.load(path);
			
			assertEquals(data.maskBlend, 0.4f, 0f);
			assertEquals(data.dsizeCenter.x, 0.9f, 0f);
			assertEquals(data.offsetX, 67);
			
			data.offsetY = 78;
			data.dsizeWidth = 960;
			
			data.store(path);
			
			data = MultiViewData.load(path);
			
			assertEquals(data.offsetY, 78);
			assertEquals(data.dsizeCenter.x, 0.9f, 0f);
			
		} catch (IOException e) {
			e.printStackTrace();
			assertFalse(true);
		}
	}

}
