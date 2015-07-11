package com.omnipad.avm.calib;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

import org.omg.CORBA_2_3.portable.OutputStream;

public class Util {
	
	public static final int readCalibInfoInt(InputStream is) throws IOException {
		byte[] buf = new byte[4];
		is.read(buf);
		return readCalibInfoInt(buf, 0);
	}

	public static final int readCalibInfoShort(InputStream is) throws IOException {
		byte[] buf = new byte[2];
		is.read(buf);
		return readCalibInfoShort(buf, 0);
	}

	public static final float readCalibInfoFloat(InputStream is) throws IOException {
		byte[] buf = new byte[4];
		is.read(buf);
		return readCalibInfoFloat(buf, 0);
	}

	public static final void writeCalibInfoInt(OutputStream os, int v) throws IOException {
		byte[] buf = new byte[4];
		
		buf[0] = (byte)(0x00FF & v);
		buf[1] = (byte)(0x00FF & (v >> 8));
		buf[2] = (byte)(0x00FF & (v >> 16));
		buf[3] = (byte)(0x00FF & (v >> 24));

		os.write(buf);
	}
	
	public static final void writeCalibInfoShort(OutputStream os, short v) throws IOException {
		byte[] buf = new byte[2];
		
		buf[0] = (byte)(0x00FF & v);
		buf[1] = (byte)(0x00FF & (v >> 8));

		os.write(buf);
	}

	public static final void writeCalibInfoFloat(OutputStream os, float v) throws IOException {
		byte[] buf = new byte[4];

		int intBits = Float.floatToIntBits(v); 
		
		buf[3] = (byte)(0x00FF & intBits);
		buf[2] = (byte)(0x00FF & (intBits >> 8));
		buf[1] = (byte)(0x00FF & (intBits >> 16));
		buf[0] = (byte)(0x00FF & (intBits >> 24));

		os.write(buf);
	}
	
	public static final int readCalibInfoInt(byte[] buf, int offset) {
		return (0x00FF & buf[offset + 0])
				| ((0x00FF & buf[offset + 1]) << 8)
				| ((0x00FF & buf[offset + 2]) << 16)
				| ((0x00FF & buf[offset + 3]) << 24);
	}

	public static final long readCalibInfoInt(ByteBuffer buf) {
		byte[] tmpbuf = new byte[4];
		buf.get(tmpbuf);
		
		return (0x00FF & tmpbuf[0])
				| ((0x00FF & tmpbuf[1]) << 8)
				| ((0x00FF & tmpbuf[2]) << 16)
				| ((0x00FF & tmpbuf[3]) << 24);
	}

	public static final int readCalibInfoShort(byte[] buf, int offset) {
		return (0x00FF & buf[offset + 0])
				| ((0x00FF & buf[offset + 1]) << 8);
	}

	public static final long readCalibInfoShort(ByteBuffer buf) {
		byte[] tmpbuf = new byte[2];
		buf.get(tmpbuf);
		
		return (0x00FF & tmpbuf[0])
				| ((0x00FF & tmpbuf[1]) << 8);
	}

	public static final void writeCalibInfoInt(int value, byte[] buf, int offset) {
		buf[offset + 0] = (byte)(0x00FF & value);
		buf[offset + 1] = (byte)(0x00FF & (value >> 8));
		buf[offset + 2] = (byte)(0x00FF & (value >> 16));
		buf[offset + 3] = (byte)(0x00FF & (value >> 24));
	}
	
	public static final void writeCalibInfoInt(int value, ByteBuffer buf) {
		buf.put((byte)(0x00FF & (value >> 24)));
		buf.put((byte)(0x00FF & (value >> 16)));
		buf.put((byte)(0x00FF & (value >> 8)));
		buf.put((byte)(0x00FF & value));
	}
	
	public static final float readCalibInfoFloat(byte[] buf, int offset) {
		int intBits = (0x00FF & buf[offset + 0])
				| ((0x00FF & buf[offset + 1]) << 8)
				| ((0x00FF & buf[offset + 2]) << 16)
				| ((0x00FF & buf[offset + 3]) << 24);
		
		return Float.intBitsToFloat(intBits);
	}

	public static final float readCalibInfoFloat(ByteBuffer buf) {
		byte[] tmpbuf = new byte[4];
		buf.get(tmpbuf);
		
		int intBits =  (0x00FF & tmpbuf[0])
				| ((0x00FF & tmpbuf[1]) << 8)
				| ((0x00FF & tmpbuf[2]) << 16)
				| ((0x00FF & tmpbuf[3]) << 24);

		return Float.intBitsToFloat(intBits);
	}

	public static final void writeCalibInfoFloat(float value, byte[] buf, int offset) {
		int intBits = Float.floatToIntBits(value); 
				
		buf[offset + 0] = (byte)(0x00FF & intBits);
		buf[offset + 1] = (byte)(0x00FF & (intBits >> 8));
		buf[offset + 2] = (byte)(0x00FF & (intBits >> 16));
		buf[offset + 3] = (byte)(0x00FF & (intBits >> 24));
	}
	
	public static final void writeCalibInfoFloat(long value, ByteBuffer buf) {
		buf.put((byte)(0x00FF & (value >> 24)));
		buf.put((byte)(0x00FF & (value >> 16)));
		buf.put((byte)(0x00FF & (value >> 8)));
		buf.put((byte)(0x00FF & value));
	}
}
