package io.github.haeyonghahn;

import java.io.File;

import io.github.haeyonghahn.constant.PdfConvertConstant;
import io.github.haeyonghahn.converter.ImageConvertModule;
import io.github.haeyonghahn.source.ImageSource;
import io.github.haeyonghahn.source.PdfSource;

public class PdfApplication {

	public static void main(String[] args) {
		File exampleFile = new File(String.format("%s/%s",
			PdfConvertConstant.getTemporaryDirectory(), "dog.jpg"));

		PdfSource pdfSource = new ImageSource(exampleFile);
		PdfConverter pdfConverter = new PdfConverter(pdfSource)
			.addModule(new ImageConvertModule());

		Pdf pdf = pdfConverter.convert(pdfSource);
		pdf.getBytes();
	}
}
