package io.github.haeyonghahn.converter;

import java.io.FileOutputStream;
import java.io.OutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;

import io.github.haeyonghahn.PdfConvertModule;
import io.github.haeyonghahn.constant.PdfPageOrientation;
import io.github.haeyonghahn.constant.PdfPageSize;
import io.github.haeyonghahn.source.PdfSource;

public class ImageConvertModule implements PdfConvertModule {

	private PdfSource pdfSource;

	@Override
	public void setPdfSource(PdfSource pdfSource) {
		this.pdfSource = pdfSource;
	}

	@Override
	public void execute() {
		if ("jpg".equals(pdfSource.getOriginalExtension()) || "jpeg".equals(pdfSource.getOriginalExtension())) {
			try (OutputStream out = new FileOutputStream(pdfSource.getOutputPath())) {
				Rectangle pageSize = getPageSize();
				Document document = new Document(pageSize, 0.0F, 0.0F, 0.0F, 0.0F);
				PdfWriter writer = PdfWriter.getInstance(document, out);
				document.open();
				Image image = Image.getInstance(pdfSource.getOutputBytes());
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
	}

	private Rectangle getPageSize() {
		Rectangle pageSize;
		PdfPageSize size = pdfSource.getPdfPageSize();
		PdfPageOrientation orientation = pdfSource.getPdfPageOrientation();
		pageSize = switch (size) {
			case A3 -> PageSize.A3;
			case A5 -> PageSize.A5;
			default -> PageSize.A4;
		};
		if (PdfPageOrientation.LANDSCAPE.equals(orientation)) {
			pageSize = pageSize.rotate();
		}
		return pageSize;
	}
}
