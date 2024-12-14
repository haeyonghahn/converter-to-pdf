package com.github.pdf;

import java.util.UUID;

import com.github.pdf.model.PdfFileConvertParameter;

public class ConvertToPdf {

	public static void main(String[] args) {
		PdfConverter pdfConverter = new PdfConverter();
		PdfFileConvertParameter files = new PdfFileConvertParameter();
		files.setOriginalFileName("dog.jpg");
		files.setOriginalExtension("jpg");
		files.setNewFileName(UUID.randomUUID().toString().replaceAll("-", ""));

		String pdf = pdfConverter.toPdf(files);
		System.out.println(pdf);
	}
}
