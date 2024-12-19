package io.github.haeyonghahn.encrypt;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.github.haeyonghahn.PdfConverter;
import io.github.haeyonghahn.source.ImageSource;
import io.github.haeyonghahn.source.PdfSource;

@DisplayName("PasswordBasedEncryptModule 테스트")
public class PasswordBasedEncryptModuleTest {

	@DisplayName("pdf 파일을 비밀번호 설정한다.")
	@Test
	void execute() throws IOException {
		File tempFile = File.createTempFile("test", ".jpg");
		tempFile.deleteOnExit();

		PdfSource pdfSource = new ImageSource(tempFile);
		PdfConverter pdfConverter = new PdfConverter(pdfSource)
			.addModule(new PasswordBasedEncryptModule("qwer1234", "qwer1234"));
	}
}
