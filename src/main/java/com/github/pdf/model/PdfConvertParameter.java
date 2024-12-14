package com.github.pdf.model;

import com.github.pdf.constant.PdfPageOrientation;
import com.github.pdf.constant.PdfPageSize;

public abstract class PdfConvertParameter {

	private String originalFileName;
	private String originalExtension;
	private String ownerPassword;
	private String userPassword;
	private PdfPageOrientation pdfPageOrientation = PdfPageOrientation.DEFAULT;
	private PdfPageSize pdfPageSize = PdfPageSize.A4;

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

	public String getOwnerPassword() {
		return ownerPassword;
	}

	public void setOwnerPassword(String ownerPassword) {
		this.ownerPassword = ownerPassword;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
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
}
