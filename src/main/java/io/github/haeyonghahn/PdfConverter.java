package io.github.haeyonghahn;

import io.github.haeyonghahn.constant.PdfConvertConstant;
import io.github.haeyonghahn.source.PdfSource;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

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
		String tempPath = PdfConvertConstant.getTemporaryDirectory();
		Path sourcePath = Paths.get(String.format("%s/%s", tempPath, pdfSource.getOriginalFileName()));
		File file = sourcePath.toFile();

		try (InputStream is = new FileInputStream(file)) {
			byte[] fileData = is.readAllBytes();
			String outputPath = tempPath + pdfSource.getNewFileName();
			pdfSource.setOutputBytes(fileData);
			pdfSource.setOutputPath(outputPath);
			pdfSource.setOutputLength(fileData.length);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		this.pdfSource = pdfSource;
	}

	public Pdf convert(PdfSource pdfSource) {
		return new Pdf(pdfSource.getOutputBytes(), pdfSource.getOutputLength());
	}
}
