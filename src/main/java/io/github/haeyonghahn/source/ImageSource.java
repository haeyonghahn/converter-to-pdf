package io.github.haeyonghahn.source;

import java.io.File;
import java.util.UUID;

public class ImageSource extends PdfSource {

	private String newFileName;

	public ImageSource(File file) {
		String fileExtension = getFileExtension(file.getName());
		if (!"jpg".equals(fileExtension) && !"jpeg".equals(fileExtension))  {
			throw new RuntimeException("It is not a jpg or jpeg file.");
		}
		super.setOriginalFileName(file.getName());
		super.setOriginalExtension(fileExtension);
		this.newFileName = UUID.randomUUID().toString().replaceAll("-", "");
	}

	public String getNewFileName() {
		return newFileName;
	}

	public void setNewFileName(String newFileName) {
		this.newFileName = newFileName;
	}

	private String getFileExtension(String fileName) {
		String extension = null;
		int i = fileName.lastIndexOf('.');
		if (i > 0)
			extension = fileName.substring(i + 1);
		return extension;
	}
}
