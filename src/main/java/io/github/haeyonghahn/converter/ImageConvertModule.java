package io.github.haeyonghahn.converter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;

import io.github.haeyonghahn.PdfConvertModule;
import io.github.haeyonghahn.constant.PdfConvertConstant;
import io.github.haeyonghahn.constant.PdfPageOrientation;
import io.github.haeyonghahn.constant.PdfPageSize;
import io.github.haeyonghahn.source.ImageSource;
import io.github.haeyonghahn.source.PdfSource;

public class ImageConvertModule implements PdfConvertModule {

	private PdfSource pdfSource;

	@Override
	public void setPdfSource(PdfSource pdfSource) {
		this.pdfSource = pdfSource;
	}

	@Override
	public void execute() {
		String[] originFileName = pdfSource.getOriginalFileName().split("\\.");
		String createFileName = originFileName[0];
		String tempPath = PdfConvertConstant.getTemporaryDirectory();
		ImageSource imageSource = (ImageSource) pdfSource;
		Path sourcePath = Paths.get(String.format("%s/%s", tempPath, imageSource.getOriginalFileName()));
		File file = sourcePath.toFile();

		try (InputStream is = new FileInputStream(file)) {
			byte[] fileData = is.readAllBytes();
			String outputPath = tempPath + "/" + createFileName + ".pdf";
			createPdf(fileData, tempPath, createFileName);

			pdfSource.setOutputFile(fileData);
			pdfSource.setOutputPath(outputPath);
			pdfSource.setOutputLength(fileData.length);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void createPdf(byte[] fileData, String filePath, String createFileName) {
		try (OutputStream out = new FileOutputStream(filePath + "/" + createFileName + ".pdf")) {
			Rectangle pageSize = getPageSize();
			Document document = new Document(pageSize, 0.0F, 0.0F, 0.0F, 0.0F);
			PdfWriter writer = PdfWriter.getInstance(document, out);
			document.open();
			Image image = Image.getInstance(fileData);
			image.scalePercent((document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin()) / image.getWidth() * 100.0F);
			document.newPage();
			writer.open();
			document.add(image);
			document.close();
			writer.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private Rectangle getPageSize() {
		Rectangle pageSize;
		PdfPageSize size = pdfSource.getPdfPageSize();
		PdfPageOrientation orientation = pdfSource.getPdfPageOrientation();
		switch (size) {
			case A3:
				pageSize = PageSize.A3;
				break;
			case A5:
				pageSize  = PageSize.A5;
				break;
			default:
				pageSize  = PageSize.A4;
				break;
		}
		if (PdfPageOrientation.LANDSCAPE.equals(orientation)) {
			pageSize = pageSize.rotate();
		}
		return pageSize;
	}
}
