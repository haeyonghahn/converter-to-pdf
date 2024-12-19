package io.github.haeyonghahn.source;

import io.github.haeyonghahn.constant.PdfPageOrientation;
import io.github.haeyonghahn.constant.PdfPageSize;

public abstract class PdfSource {

	private String originalFileName;
	private String newFileName;
	private String originalExtension;
	private PdfPageOrientation pdfPageOrientation = PdfPageOrientation.DEFAULT;
	private PdfPageSize pdfPageSize = PdfPageSize.A4;

	private byte[] outputBytes;
	private String outputPath;
	private long outputLength;

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

	public String getNewFileName() {
		return newFileName;
	}

	public void setNewFileName(String newFileName) {
		this.newFileName = newFileName;
	}

	public String getOriginalExtension() {
		return originalExtension;
	}

	public void setOriginalExtension(String originalExtension) {
		this.originalExtension = originalExtension;
	}

	public PdfPageOrientation getPdfPageOrientation() {
		return pdfPageOrientation;
	}

	public void setPdfPageOrientation(PdfPageOrientation pdfPageOrientation) {
		this.pdfPageOrientation = pdfPageOrientation;
	}

	public PdfPageSize getPdfPageSize() {
		return pdfPageSize;
	}

	public void setPdfPageSize(PdfPageSize pdfPageSize) {
		this.pdfPageSize = pdfPageSize;
	}

	public byte[] getOutputBytes() {
		return outputBytes;
	}

	public void setOutputBytes(byte[] outputBytes) {
		this.outputBytes = outputBytes;
	}

	public String getOutputPath() {
		return outputPath;
	}

	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}

	public long getOutputLength() {
		return outputLength;
	}

	public void setOutputLength(long outputLength) {
		this.outputLength = outputLength;
	}

	protected String getFileExtension(String fileName) {
		String extension = null;
		int i = fileName.lastIndexOf('.');
		if (i > 0)
			extension = fileName.substring(i + 1);
		return extension;
	}
}
