package com.github.sample;

import com.github.pdf.constant.PdfPageOrientation;
import com.github.pdf.constant.PdfPageSize;
import com.lowagie.text.Document;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfWriter;

import java.io.*;

/**
 * @author Theo
 * @since 2024/12/14
 */
public class ByteToPdf {
    public Pdf convert(byte[] bytes) {
        Rectangle pageSize = getPageSize();
        OutputStream out = new FileOutputStream("pdf.pdf");
        BufferedOutputStream bos = new BufferedOutputStream(out);
        Document document = new Document(pageSize, 0.0F, 0.0F, 0.0F, 0.0F);
        PdfWriter writer = PdfWriter.getInstance(document, bos);
        document.open();
        Image image = Image.getInstance(bytes);
        image.scalePercent((document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin()) / image.getWidth() * 100.0F);
        document.newPage();
        writer.open();
        document.add(image);
        document.close();
        writer.close();
        return new Pdf(Byte.parseByte());
    }


    private Rectangle getPageSize() {
        Rectangle pageSize = null;
        return pageSize;
    }
}
