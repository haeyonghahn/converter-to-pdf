package com.github.pdf.converter;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.poi.util.IOUtils;

import com.github.pdf.constant.PdfConvertConstant;
import com.github.pdf.constant.PdfPageOrientation;
import com.github.pdf.constant.PdfPageSize;
import com.github.pdf.model.PdfConvertParameter;
import com.github.pdf.model.PdfFileConvertParameter;
import com.github.pdf.strategy.PdfConvertStrategy;
import com.github.pdf.util.PdfEncrypt;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;

public class ImageToPdfConverter implements PdfConvertStrategy {

	private final PdfConvertParameter pdfConvertParameter;

	public ImageToPdfConverter(PdfConvertParameter pdfConvertParameter) {
		this.pdfConvertParameter = pdfConvertParameter;
	}

	@Override
	public String convert() throws Exception {
		String outputPath;
		String[] originFileName = pdfConvertParameter.getOriginalFileName().split("\\.");
		String createFileName = originFileName[0];
		String tempPath = PdfConvertConstant.getTemporaryDirectory();
		PdfFileConvertParameter pdfFileConvertParameter = (PdfFileConvertParameter) pdfConvertParameter;
		Path sourcePath = Paths.get(String.format("%s/%s", tempPath, pdfFileConvertParameter.getOriginalFileName()));
		File file = sourcePath.toFile();

		try (InputStream is = new FileInputStream(file)) {
			byte[] fileData = IOUtils.toByteArray(is);
			outputPath = tempPath + "/" + createFileName + ".pdf";
			createPdf(fileData, tempPath, createFileName);
			if (pdfConvertParameter.getOwnerPassword() != null && pdfConvertParameter.getUserPassword() != null) {
				PdfEncrypt.setPdfEncrypt(pdfConvertParameter.getOwnerPassword(), pdfConvertParameter.getUserPassword(), outputPath);
			}
		} catch(FileNotFoundException e) {
			throw new FileNotFoundException(e.getMessage());
		}
		// TODO : 실제 파일이 위치한 경로가 반환되어 보안적인 위험이 존재.
		return outputPath;
	}

	private void createPdf(byte[] fileData, String filePath, String createFileName) {
		try (InputStream is = new ByteArrayInputStream(fileData);
			 OutputStream out = new FileOutputStream(filePath + "/" + createFileName + ".pdf")) {
			Rectangle pageSize = getPageSize();
			Document document = new Document(pageSize, 0.0F, 0.0F, 0.0F, 0.0F);
			PdfWriter writer = PdfWriter.getInstance(document, out);
			document.open();
			Image image = Image.getInstance(fileData);
			image.scalePercent((document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin()) / image.getWidth() * 100.0F);
			document.newPage();
			writer.open();
			document.add((Element) image);
			document.close();
			writer.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private Rectangle getPageSize() {
		Rectangle pageSize;
		PdfPageSize size = pdfConvertParameter.getPdfPageSize();
		PdfPageOrientation orientation = pdfConvertParameter.getPdfPageOrientation();
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
