package io.github.haeyonghahn.mock;

import io.github.haeyonghahn.constant.PdfConvertConstant;
import io.github.haeyonghahn.source.PdfSource;

public class FakePdfSource extends PdfSource {

	@Override
	public String getOriginalFileName() {
		return "test.jpg";
	}

	@Override
	public String getOriginalExtension() {
		return "jpg";
	}

	@Override
	public String getOutputPath() {
		return PdfConvertConstant.getTemporaryDirectory() + "test.jpg";
	}
}
