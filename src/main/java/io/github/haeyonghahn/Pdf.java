package io.github.haeyonghahn;

public class Pdf {

	private final byte[] bytes;

	public Pdf(byte[] bytes) {
		this.bytes = bytes;
	}

	public byte[] getBytes() {
		return bytes;
	}
}