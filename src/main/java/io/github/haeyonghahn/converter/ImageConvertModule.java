package io.github.haeyonghahn.converter;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;
import io.github.haeyonghahn.PdfConvertModule;
import io.github.haeyonghahn.constant.PdfPageOrientation;
import io.github.haeyonghahn.constant.PdfPageSize;
import io.github.haeyonghahn.source.ImageSource;
import io.github.haeyonghahn.source.PdfSource;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class ImageConvertModule implements PdfConvertModule {

	private PdfSource pdfSource;

	@Override
	public void setPdfSource(PdfSource pdfSource) {
		this.pdfSource = pdfSource;
	}

	@Override
	public void execute() {
		ImageSource imageSource = (ImageSource) pdfSource;
		try (OutputStream out = new FileOutputStream(imageSource.getOutputPath())) {
			Rectangle pageSize = getPageSize();
			Document document = new Document(pageSize, 0.0F, 0.0F, 0.0F, 0.0F);
			PdfWriter writer = PdfWriter.getInstance(document, out);
			document.open();
			Image image = Image.getInstance(imageSource.getOutputBytes());
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
