package io.github.haeyonghahn.converter;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import io.github.haeyonghahn.constant.PdfConvertConstant;
import io.github.haeyonghahn.mock.FakePdfSource;

@DisplayName("ImageConvertModule 테스트")
public class ImageConvertModuleTest {

	@DisplayName("pdf 로 변환할 이미지 파일이 없는 경우 예외를 발생시킨다.")
	@Test
	void execute() throws IOException {
		// given
		ImageConvertModule imageConvertModule = new ImageConvertModule();
		imageConvertModule.setPdfSource(new FakePdfSource());

		// when & then
		assertThrows(RuntimeException.class, imageConvertModule::execute);

		Files.deleteIfExists(Path.of(PdfConvertConstant.getTemporaryDirectory() + "test.jpg"));
	}
}
