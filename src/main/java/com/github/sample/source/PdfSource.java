package com.github.sample.source;

import java.io.IOException;

/**
 * @author Theo
 * @since 2024/12/14
 */
public interface PdfSource {
    byte[] getBytes() throws IOException;
}
