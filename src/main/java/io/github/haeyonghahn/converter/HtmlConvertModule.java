package io.github.haeyonghahn.converter;

import com.itextpdf.text.DocumentException;
import io.github.haeyonghahn.PdfConvertModule;
import io.github.haeyonghahn.constant.PdfConvertConstant;
import io.github.haeyonghahn.source.PdfSource;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.w3c.tidy.Tidy;

import java.io.*;

public class HtmlConvertModule implements PdfConvertModule {

    private PdfSource pdfSource;

    @Override
    public void setPdfSource(PdfSource pdfSource) {
        this.pdfSource = pdfSource;
    }

    @Override
    public void execute() {
        try {
            InputStream is = new ByteArrayInputStream(pdfSource.getOutputBytes());
            InputStreamReader isReader = new InputStreamReader(is);
            BufferedReader reader = new BufferedReader(isReader);
            StringBuilder html = new StringBuilder();

            String str = "";
            while ((str = reader.readLine()) != null) {
                html.append(str).append("\n");
            }
            OutputStream os = new FileOutputStream(pdfSource.getOutputPath());

            ITextRenderer renderer = initRenderer();
            renderer.setDocumentFromString(tidyHtml(html.toString()));
            renderer.layout();
            renderer.createPDF(os, false);
            renderer.finishPDF();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private ITextRenderer initRenderer() throws DocumentException, IOException {
        ITextRenderer renderer = new ITextRenderer();
        renderer.getFontResolver().addFont(PdfConvertConstant.getFontDirectory(), "Identity-H", true);
        return renderer;
    }

    private String tidyHtml(String html) {
        Tidy tidy = new Tidy();

        tidy.setDocType("omit");
        tidy.setXmlOut(true);
        tidy.setForceOutput(true);
        tidy.setQuiet(true);
        tidy.setTidyMark(false);
        tidy.setInputEncoding("UTF-8");
        StringWriter swError = new StringWriter();
        tidy.setErrout(new PrintWriter(swError));
        StringWriter sw = new StringWriter();
        tidy.parse(new StringReader(html), sw);
        String tidyHtml = sw.getBuffer().toString();
        tidyHtml = tidyHtml.replaceAll("(\r\n|\n|\r)+\\s*<", "<");

        return tidyHtml;
    }
}
