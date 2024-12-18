package io.github.haeyonghahn;

public class Pdf {

	private final byte[] bytes;
	private final long length;

	public Pdf(byte[] bytes, long length) {
		this.bytes = bytes;
		this.length = length;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public long getLength() {
		return length;
	}
}
