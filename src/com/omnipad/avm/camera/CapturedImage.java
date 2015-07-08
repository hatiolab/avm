package com.omnipad.avm.camera;

import java.util.HashMap;

public class CapturedImage {
	static HashMap<Camera, String> map = new HashMap<Camera, String>();

	static {
		map.put(Camera.FRONT, "/sdcard/omnipad/img/front.bmp");
		map.put(Camera.REAR, "/sdcard/omnipad/img/rear.bmp");
		map.put(Camera.LEFT, "/sdcard/omnipad/img/left.bmp");
		map.put(Camera.RIGHT, "/sdcard/omnipad/img/right.bmp");
	}
	
	public static String get(Camera camera) {
		return map.get(camera);
	}
}
