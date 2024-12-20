package io.github.haeyonghahn;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.github.haeyonghahn.constant.PdfConvertConstant;
import io.github.haeyonghahn.mock.FakePdfSource;
import io.github.haeyonghahn.source.PdfSource;

@DisplayName("PdfConverter 테스트")
public class PdfConverterTest {

	@DisplayName("파일이 없는 경우 pdfConverter를 생성할 수 없다.")
	@Test
	void pdfConverter() {
		// given
		PdfSource pdfSource = new FakePdfSource();

		// when
		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			new PdfConverter(pdfSource);
		});

		// then
		assertTrue(exception.getMessage().contains("File Not found. "));
	}

	@DisplayName("Pdf 클래스로 변환되었는지 확인한다.")
	@Test
	void convert() throws IOException {
		// given
		PdfSource pdfSource = new FakePdfSource();
		OutputStream out = new FileOutputStream(pdfSource.getOutputPath());
		out.close();

		// when
		PdfConverter pdfConverter = new PdfConverter(pdfSource);
		Pdf pdf = pdfConverter.convert(pdfSource);
		Files.deleteIfExists(Path.of(PdfConvertConstant.getTemporaryDirectory() + "test.jpg"));

		// then
		assertEquals(pdf.getBytes(), pdfSource.getOutputBytes());
		assertEquals(pdf.getLength(), pdfSource.getOutputLength());
	}
}
