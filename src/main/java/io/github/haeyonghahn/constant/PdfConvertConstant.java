package io.github.haeyonghahn.constant;

public class PdfConvertConstant {

	public static String getTemporaryDirectory() {
		if (System.getProperty("temp.dir") == null) {
			return System.getProperty("user.dir") + "/src/main/resources/static/";
		} else {
			return System.getProperty("temp.dir");
		}
	}
}
