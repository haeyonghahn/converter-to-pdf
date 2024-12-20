package io.github.haeyonghahn.mock;

import io.github.haeyonghahn.Pdf;
import io.github.haeyonghahn.constant.PdfConvertConstant;
import io.github.haeyonghahn.source.PdfSource;

public class FakePdfConverter {

	public FakePdfConverter(PdfSource pdfSource) {
		pdfSource.setOutputPath(PdfConvertConstant.getTemporaryDirectory() + "test.jpg");
	}

	public Pdf convert(PdfSource pdfSource) {
		return new Pdf(pdfSource.getOutputBytes(), pdfSource.getOutputLength());
	}
}
