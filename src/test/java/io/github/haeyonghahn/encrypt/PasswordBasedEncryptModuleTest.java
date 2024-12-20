package io.github.haeyonghahn.encrypt;

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

@DisplayName("PasswordBasedEncryptModule 테스트")
public class PasswordBasedEncryptModuleTest {

	@DisplayName("pdf 로 변환할 파일이 없는 경우 예외를 발생시킨다.")
	@Test
	void execute() throws IOException {
		// given
		PasswordBasedEncryptModule passwordBasedEncryptModule = new PasswordBasedEncryptModule("qwer1234", "qwer1234");
		passwordBasedEncryptModule.setPdfSource(new FakePdfSource());

		// when
		RuntimeException exception = assertThrows(RuntimeException.class, passwordBasedEncryptModule::execute);

		assertTrue(exception.getMessage().contains("File does not exist."));
	}

	@DisplayName("pdf 로 변환할 파일이 존재하지만 pdf 로 변환이 안될 경우 예외를 발생시킨다.")
	@Test
	void notConvert() throws IOException {
		// given
		PdfSource pdfSource = new FakePdfSource();
		OutputStream out = new FileOutputStream(pdfSource.getOutputPath());
		out.close();

		PasswordBasedEncryptModule passwordBasedEncryptModule = new PasswordBasedEncryptModule("qwer1234", "qwer1234");
		passwordBasedEncryptModule.setPdfSource(pdfSource);

		// when
		RuntimeException exception = assertThrows(RuntimeException.class, passwordBasedEncryptModule::execute);
		Files.deleteIfExists(Path.of(PdfConvertConstant.getTemporaryDirectory() + "test.jpg"));

		// then
		assertTrue(exception.getMessage().contains("An error occurred while encrypting the PDF file."));
	}
}
