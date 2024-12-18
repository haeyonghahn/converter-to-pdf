package io.github.haeyonghahn;

import io.github.haeyonghahn.source.PdfSource;

public interface PdfConvertModule {

	void setPdfSource(PdfSource pdfSource);
	void execute();
}
