package io.github.haeyonghahn.encrypt;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.github.haeyonghahn.mock.FakePdfSource;

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
}
