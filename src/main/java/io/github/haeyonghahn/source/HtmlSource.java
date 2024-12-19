package io.github.haeyonghahn.source;

import java.io.File;

public class HtmlSource extends PdfSource {

    public HtmlSource(File file) {
        String[] originFileName = file.getName().split("\\.");
        String newFileName = originFileName[0] + ".pdf";
        String fileExtension = getFileExtension(file.getName());

        if (!"html".equals(fileExtension) && !"htm".equals(fileExtension))  {
            throw new RuntimeException("It is not a html file.");
        }
        super.setOriginalFileName(file.getName());
        super.setNewFileName(newFileName);
        super.setOriginalExtension(fileExtension);
    }
}
