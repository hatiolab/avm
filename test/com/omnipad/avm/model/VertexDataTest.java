package com.omnipad.avm.model;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VertexDataTest {

	String tempPath = System.getProperty("java.io.tmpdir") + "vbo.bin";

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	private void load(String path) throws IOException {
		VertexData vbo = VertexData.load(path);
		for(int i = 0;i < 100;i++)
			System.out.println("Index " + i + " : " + vbo.indices[i]);
		for(int i = 0;i < 100;i++)
			System.out.println("Vertex " + i + " : " + vbo.vertices[i]);
		for(int i = 0;i < 100;i++)
			System.out.println("Mask " + i + " : " + vbo.viewMask[i]);

		vbo.save(tempPath);
		
		VertexData vbo_comp = VertexData.load(tempPath);
		
		for(int i = 0;i < 100;i++)
			assertEquals(vbo.indices[i], vbo_comp.indices[i]);
		for(int i = 0;i < 100;i++)
			assertEquals(vbo.vertices[i], vbo_comp.vertices[i], 0.0f);
		for(int i = 0;i < 100;i++)
			assertEquals(vbo.viewMask[i], vbo_comp.viewMask[i]);
	}
	
	@Test
	public void test() {
		try {
			load("resource/avmfv.vbo");
			load("resource/avmfv.vbo");
			load("resource/avmls.vbo");
			load("resource/Frontfv.vbo");
			load("resource/Frontvw.vbo");
			load("resource/LeftFrontvw.vbo");
			load("resource/Leftvw.vbo");
			load("resource/Rearfv.vbo");
			load("resource/Rearvw.vbo");
			load("resource/RightFrontvw.vbo");
			load("resource/Rightvw.vbo");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
