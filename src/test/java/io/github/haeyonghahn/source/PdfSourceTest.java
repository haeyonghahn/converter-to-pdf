package io.github.haeyonghahn.source;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("PdfSource 테스트")
public class PdfSourceTest {

	@DisplayName("파일 이름에서 확장자를 가져온다.")
	@Test
	void getFileExtension() throws IOException {
		// given
		File tempFile = File.createTempFile("test", ".jpg");
		tempFile.deleteOnExit();

		// when
		PdfSource pdfSource = new ImageSource(tempFile);
		String fileExtension = pdfSource.getFileExtension(tempFile.getName());

		// then
		assertEquals("jpg", fileExtension);
	}
}
