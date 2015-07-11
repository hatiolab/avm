package com.omnipad.avm.model;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class VertexDataTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		try {
			VertexData vbo = VertexData.load("resource/avmfv.vbo");
			vbo = VertexData.load("resource/avmfv.vbo");
			vbo = VertexData.load("resource/avmls.vbo");
			vbo = VertexData.load("resource/Frontfv.vbo");
			vbo = VertexData.load("resource/Frontvw.vbo");
			vbo = VertexData.load("resource/LeftFrontvw.vbo");
			vbo = VertexData.load("resource/Leftvw.vbo");
			vbo = VertexData.load("resource/Rearfv.vbo");
			vbo = VertexData.load("resource/Rearvw.vbo");
			vbo = VertexData.load("resource/RightFrontvw.vbo");
			vbo = VertexData.load("resource/Rightvw.vbo");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
