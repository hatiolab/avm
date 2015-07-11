package com.omnipad.avm;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class TLVFormat {

	public static final ByteOrder order = ByteOrder.LITTLE_ENDIAN;
	
	public int tag;
	public int len;
	public byte[] val;

	public static void write(OutputStream os, int value) throws IOException {
		ByteBuffer buf = ByteBuffer.allocate(4);
		buf.order(order);
		buf.putInt(value);
		
		os.write(buf.array());
	}
	
	public static void write(OutputStream os, short value) throws IOException {
		ByteBuffer buf = ByteBuffer.allocate(2);
		buf.order(order);
		buf.putShort(value);
		
		os.write(buf.array());
	}
	
	public static void write(OutputStream os, float value) throws IOException {
		ByteBuffer buf = ByteBuffer.allocate(4);
		buf.order(order);
		buf.putFloat(value);
		
		os.write(buf.array());
	}
	
	public static void write(OutputStream os, byte[] value) throws IOException {
		os.write(value);
	}
	
	public static int readBytes(InputStream is, byte[] value) throws IOException {
		return is.read(value);
	}
	
	public static int readInt(InputStream is) throws IOException {
		byte[] buf = new byte[4];
		is.read(buf);
		
		ByteBuffer bb = ByteBuffer.wrap(buf);
		bb.order(order);
		
		return bb.getInt();
	}
	
	public static short readShort(InputStream is) throws IOException {
		byte[] buf = new byte[2];
		is.read(buf);
		
		ByteBuffer bb = ByteBuffer.wrap(buf);
		bb.order(order);
		
		return bb.getShort();
	}
	
	public static float readFloat(InputStream is) throws IOException {
		byte[] buf = new byte[4];
		is.read(buf);
		
		ByteBuffer bb = ByteBuffer.wrap(buf);
		bb.order(order);
		
		return bb.getFloat();
	}
	
	public static void write(OutputStream os, int tag, int value) throws IOException {
		ByteBuffer buf = ByteBuffer.allocate(12);
		buf.order(order);
		
		buf.putInt(tag);
		buf.putInt(4);
		buf.putInt(value);
		
		os.write(buf.array());
	}
	
	public static void write(OutputStream os, int tag, short value) throws IOException {
		ByteBuffer buf = ByteBuffer.allocate(10);
		buf.order(order);
		
		buf.putInt(tag);
		buf.putInt(2);
		buf.putShort(value);
		
		os.write(buf.array());
	}
	
	public static void write(OutputStream os, int tag, float value) throws IOException {
		ByteBuffer buf = ByteBuffer.allocate(12);
		buf.order(order);
		
		buf.putInt(tag);
		buf.putInt(4);
		buf.putFloat(value);
		
		os.write(buf.array());
	}
	
	public static void write(OutputStream os, int tag, byte[] value) throws IOException {
		ByteBuffer buf = ByteBuffer.allocate(8 + value.length);
		buf.order(order);

		buf.putInt(tag);
		buf.putInt(value.length);
		buf.put(value);
		
		os.write(buf.array());
	}
	
	public static TLVFormat readTLV(InputStream is) throws IOException {
		byte[] buf = new byte[8];
		is.read(buf);
		
		ByteBuffer bb = ByteBuffer.wrap(buf);
		bb.order(order);
		
		TLVFormat tlv = new TLVFormat();
		
		tlv.tag = bb.getInt();
		tlv.len = bb.getInt();
		
		tlv.val = new byte[tlv.len];
		
		is.read(tlv.val);

		return tlv;
	}
	
	public int getIntValue() {
		ByteBuffer buf = ByteBuffer.wrap(val);
		buf.order(order);
		
		return buf.getInt();
	}
	
	public short getShortValue() {
		ByteBuffer buf = ByteBuffer.wrap(val);
		buf.order(order);
		
		return buf.getShort();
	}
	
	public float getFloatValue() {
		ByteBuffer buf = ByteBuffer.wrap(val);
		buf.order(order);
		
		return buf.getFloat();
	}
	
	public byte[] getBytesValue() {
		return val;
	}
	
//	public static void write(DataOutputStream os, int tag, byte[] buffer) throws IOException {
//		os.writeInt(tag);
//		os.writeInt(buffer.length);
//		os.write(buffer);
//	}
	
//	public static void write(DataOutputStream os, int tag, int value) throws IOException {
//		os.writeInt(tag);
//		os.writeInt(4);
//		os.writeInt(value);
//	}
	
//	public static void write(DataOutputStream os, int tag, float value) throws IOException {
//		os.writeInt(tag);
//		os.writeInt(4);
//		os.writeFloat(value);
//	}
	
//	public static int readInt(DataInputStream is) throws IOException {
//		is.readInt();
//		is.readInt();
//		return is.readInt();
//	}
//	
//	public static float readFloat(DataInputStream is) throws IOException {
//		is.readInt();
//		is.readInt();
//		return is.readFloat();
//	}
}
