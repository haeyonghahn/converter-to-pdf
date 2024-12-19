package io.github.haeyonghahn;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.github.haeyonghahn.mock.FakePdfSource;
import io.github.haeyonghahn.source.PdfSource;

@DisplayName("PdfConverter 테스트")
public class PdfConverterTest {

	private PdfSource pdfSource;

	@BeforeEach
	void beforeEach() {
		pdfSource = new FakePdfSource();
	}

	@DisplayName("파일이 없는 경우 pdfConverter를 생성할 수 없다.")
	@Test
	void pdfConverter() throws IOException {
		// when
		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			new PdfConverter(pdfSource);
		});

		// then
		assertTrue(exception.getMessage().contains("File Not found. "));
	}
}
