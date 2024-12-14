package com.github.sample.source;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author Theo
 * @since 2024/12/14
 */
public class JpgPdfSource implements PdfSource {

    private final File file;

    public JpgPdfSource(File file) {
        // 무조건 JPG 이어야 한다.
        // 유효성 검사
        this.file = file;
    }

    @Override
    public byte[] getBytes() throws IOException {
        return Files.readAllBytes(file.toPath());
    }
}
