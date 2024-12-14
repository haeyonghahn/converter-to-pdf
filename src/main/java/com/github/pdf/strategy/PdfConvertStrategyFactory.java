package com.github.pdf.strategy;

import com.github.pdf.converter.ImageToPdfConverter;
import com.github.pdf.model.PdfConvertParameter;

public class PdfConvertStrategyFactory {

	public static PdfConvertStrategy getStrategy(PdfConvertParameter pdfConvertParameter) {
		ImageToPdfConverter imageToPdfConverter = null;
		String fileExtension = pdfConvertParameter.getOriginalExtension();
		if ("jpg".equals(fileExtension) || "jpeg".equals(fileExtension)) {
			imageToPdfConverter = new ImageToPdfConverter(pdfConvertParameter);
		} else if ("html".equals(fileExtension)) {
			imageToPdfConverter = new ImageToPdfConverter(pdfConvertParameter);
		} else {
			throw new RuntimeException(fileExtension + " can not be converted!");
		}
		return imageToPdfConverter;
	}
}
