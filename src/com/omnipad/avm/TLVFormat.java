package com.omnipad.avm;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TLVFormat {
	
	public static void write(DataOutputStream os, int tag, int value) throws IOException {
		os.writeInt(tag);
		os.writeInt(4);
		os.writeInt(value);
	}
	
	public static void write(DataOutputStream os, int tag, float value) throws IOException {
		os.writeInt(tag);
		os.writeInt(4);
		os.writeFloat(value);
	}
	
	public static int readInt(DataInputStream is) throws IOException {
		is.readInt();
		is.readInt();
		return is.readInt();
	}
	
	public static float readFloat(DataInputStream is) throws IOException {
		is.readInt();
		is.readInt();
		return is.readFloat();
	}
}
