package com.omnipad.avm.out;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Index {
	public int meshWidth;
	public int meshHeight;
	
	public short[] data;

	public void store(String path) throws IOException {
		FileOutputStream fos = new FileOutputStream(path);
		DataOutputStream dos = new DataOutputStream(fos);

		dos.writeInt(meshWidth);
		dos.writeInt(meshHeight);
		dos.writeInt(data.length);
		for(int i = 0;i < data.length;i++)
			dos.writeShort(data[i]);

		dos.close();
	}
}

