package com.omnipad.avm;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class YUV420Test {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		byte[] image = null;
		try {
			image = YUV420.load("resource/camyuv.bin", 1440, 960);
		} catch (IOException e) {
			assertTrue(false);
		}
		
		assertEquals(image.length, YUV420.size(1440,  960));
	}

}
