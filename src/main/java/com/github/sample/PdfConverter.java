package com.github.sample;

import com.github.sample.module.PdfCOnvertModule;
import com.github.sample.source.JpgPdfSource;
import com.github.sample.source.PdfSource;

import java.io.IOException;
import java.util.List;

/**
 * @author Theo
 * @since 2024/12/14
 */
public class PdfConverter {

    private final List<PdfCOnvertModule> modules = List.of();

    public PdfConverter addModule(PdfCOnvertModule module) {
        modules.add(module);
        return this;
    }

    public Pdf convert(PdfSource pdfSource) throws IOException {
        byte[] bytes = pdfSource.getBytes();
        var byteToPdf = new ByteToPdf();
        return byteToPdf.convert(bytes);
    }

    public static void main(String[] args) {
        var pdfSource= new JpgPdfSource("jpg.jpg");
        var pdfConverter = new PdfConverter()
                .addModule(new HtmlConvertModule())
                .addModule(new PdfEncryptModule("password"));
        try {
            Pdf pdf = pdfConverter.convert(pdfSource);
            pdf.writeTo("result.pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
