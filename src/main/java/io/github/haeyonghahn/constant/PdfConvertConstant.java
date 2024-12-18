package io.github.haeyonghahn.constant;

import java.io.File;
import java.nio.file.Paths;

public class PdfConvertConstant {

	public static String getTemporaryDirectory() {
		if (System.getProperty("temp.dir") == null) {
			String projectRoot = System.getProperty("user.dir");
			String staticPath = projectRoot + "/src/main/resources/static/";
			return Paths.get(new File(staticPath).getPath()).toString();
		} else {
			return System.getProperty("temp.dir");
		}
	}
}
