package com.github.pdf;

import java.io.File;

import com.github.pdf.constant.PdfConvertConstant;
import com.github.pdf.converter.ImageConvertModule;
import com.github.pdf.encrypt.PasswordBasedEncryptModule;
import com.github.pdf.source.ImageSource;
import com.github.pdf.source.PdfSource;

public class PdfApplication {

	public static void main(String[] args) {
		File exampleFile = new File(String.format("%s/%s",
			PdfConvertConstant.getTemporaryDirectory(), "dog.jpg"));

		PdfSource pdfSource = new ImageSource(exampleFile);
		PdfConverter pdfConverter = new PdfConverter(pdfSource)
			.addModule(new ImageConvertModule())
			.addModule(new PasswordBasedEncryptModule("qwer1234", "qwer1234"));

		// pdf 파일 반환
		Pdf pdf = pdfConverter.convert(pdfSource);
	}
}
