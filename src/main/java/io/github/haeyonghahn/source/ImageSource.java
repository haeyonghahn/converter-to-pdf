package io.github.haeyonghahn.source;

import java.io.File;

public class ImageSource extends PdfSource {

	public ImageSource(File file) {
		String[] originFileName = file.getName().split("\\.");
		String newFileName = originFileName[0] + ".pdf";
		String fileExtension = getFileExtension(file.getName());

		if (!"jpg".equals(fileExtension) && !"jpeg".equals(fileExtension))  {
			throw new RuntimeException("It is not a jpg or jpeg file.");
		}
		super.setOriginalFileName(file.getName());
		super.setNewFileName(newFileName);
		super.setOriginalExtension(fileExtension);
	}
}
