package io.github.haeyonghahn.source;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ImageSource 테스트")
public class ImageSourceTest {

	@DisplayName("파일이 jpg 확장자, jpeg 확장자가 아닌 경우 이미지소스를 생성할 수 없다.")
	@Test
	void imageSource() throws IOException {
		// given
		File tempFile = File.createTempFile("test", ".pdf");
		tempFile.deleteOnExit();

		// when
		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			new ImageSource(tempFile);
		});

		// then
		assertEquals("It is not a jpg or jpeg file.", exception.getMessage());
	}
}
