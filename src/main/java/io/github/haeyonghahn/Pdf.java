package io.github.haeyonghahn;

public class Pdf {

	private final byte[] bytes;
	private final long length;
	private final String name;

	public Pdf(byte[] bytes, long length, String name) {
		this.bytes = bytes;
		this.length = length;
		this.name = name;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public long getLength() {
		return length;
	}

	public String getName() {
		return name;
	}
}
