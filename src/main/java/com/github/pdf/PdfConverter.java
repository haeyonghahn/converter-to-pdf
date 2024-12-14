package com.github.pdf;

import com.github.pdf.model.PdfConvertParameter;
import com.github.pdf.strategy.PdfConvertStrategy;
import com.github.pdf.strategy.PdfConvertStrategyFactory;

public class PdfConverter {

	public String toPdf(PdfConvertParameter pdfConvertParameter) {
		try {
			PdfConvertStrategy pdfConvertStrategy = PdfConvertStrategyFactory.getStrategy(pdfConvertParameter);
			return pdfConvertStrategy.convert();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
