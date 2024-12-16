package com.github.pdf;

import com.github.pdf.source.PdfSource;

public interface PdfConvertModule {

	void setPdfSource(PdfSource pdfSource);
	void execute();
}
