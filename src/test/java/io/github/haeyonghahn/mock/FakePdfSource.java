package io.github.haeyonghahn.mock;

import io.github.haeyonghahn.source.PdfSource;

public class FakePdfSource extends PdfSource {

	@Override
	public String getOriginalFileName() {
		return "test.jpg";
	}
}
