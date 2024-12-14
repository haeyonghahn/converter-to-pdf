package com.github.pdf.constant;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Objects;

public class PdfConvertConstant {

	public static String getTemporaryDirectory() {
		try {
			if (System.getenv("temp.dir") == null) {
				ClassLoader classLoader = PdfConvertConstant.class.getClassLoader();
				URL url = Objects.requireNonNull(classLoader.getResource("static"));
				return Paths.get(new File(url.toURI()).getPath()).toString();
			} else {
				return System.getenv("temp.dir");
			}
		} catch(URISyntaxException e){
			throw new RuntimeException(e);
		}
	}
}
