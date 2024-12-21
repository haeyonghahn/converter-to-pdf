package io.github.haeyonghahn.controller;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.github.haeyonghahn.Pdf;
import io.github.haeyonghahn.PdfConverter;
import io.github.haeyonghahn.constant.PdfConvertConstant;
import io.github.haeyonghahn.converter.ImageConvertModule;
import io.github.haeyonghahn.encrypt.PasswordBasedEncryptModule;
import io.github.haeyonghahn.source.ImageSource;
import io.github.haeyonghahn.source.PdfSource;

@RestController
@RequestMapping("/api")
public class FileController {

	@PostMapping("/file")
	public ResponseEntity<Object> file(@RequestPart("multipartFile") MultipartFile multipartFile) {
		try {
			File file = new File(PdfConvertConstant.getTemporaryDirectory() + "/" + multipartFile.getOriginalFilename());
			multipartFile.transferTo(file);

			PdfSource pdfSource = new ImageSource(file);
			PdfConverter pdfConverter = new PdfConverter(pdfSource)
				.addModule(new ImageConvertModule())
				.addModule(new PasswordBasedEncryptModule("qwer1234", "qwer1234"));

			Pdf pdf = pdfConverter.convert(pdfSource);
			InputStream is = new ByteArrayInputStream(pdf.getBytes());

			HttpHeaders headers = new HttpHeaders();
			headers.add("Content-Disposition", String.format("inline; filename=%s", pdf.getName()));

			return ResponseEntity.ok()
				.headers(headers)
				.contentLength(pdf.getLength())
				.contentType(MediaType.parseMediaType("application/octet-stream; charset=utf8"))
				.body(new InputStreamResource(is));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
