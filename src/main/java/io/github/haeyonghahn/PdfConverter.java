package io.github.haeyonghahn;

import java.util.ArrayList;
import java.util.List;

import io.github.haeyonghahn.source.PdfSource;

public class PdfConverter {

	private PdfSource pdfSource;
	private List<PdfConvertModule> modules = new ArrayList<>();

	public PdfConverter addModule(PdfConvertModule module) {
		module.setPdfSource(pdfSource);
		modules.add(module);
		module.execute();
		return this;
	}

	public PdfConverter(PdfSource pdfSource) {
		this.pdfSource = pdfSource;
	}

	public Pdf convert(PdfSource pdfSource) {
		return new Pdf(pdfSource.getOutputFile());
	}
}
