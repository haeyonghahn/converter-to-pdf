package com.github.pdf.source;

import com.github.pdf.constant.PdfPageOrientation;
import com.github.pdf.constant.PdfPageSize;

public abstract class PdfSource {

	private String originalFileName;
	private String originalExtension;
	private PdfPageOrientation pdfPageOrientation = PdfPageOrientation.DEFAULT;
	private PdfPageSize pdfPageSize = PdfPageSize.A4;
	private byte[] outputFile;
	private String outputPath;

	public String getOriginalFileName() {
		return originalFileName;
	}

	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
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

	public byte[] getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(byte[] outputFile) {
		this.outputFile = outputFile;
	}

	public String getOutputPath() {
		return outputPath;
	}

	public void setOutputPath(String outputPath) {
		this.outputPath = outputPath;
	}
}
