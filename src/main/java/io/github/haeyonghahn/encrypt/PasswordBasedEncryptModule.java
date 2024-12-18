package io.github.haeyonghahn.encrypt;

import java.io.File;
import java.io.FileInputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;

import io.github.haeyonghahn.PdfConvertModule;
import io.github.haeyonghahn.source.PdfSource;

public class PasswordBasedEncryptModule implements PdfConvertModule {

	private final String ownerPassword;
	private final String userPassword;
	private PdfSource pdfSource;

	public PasswordBasedEncryptModule(String ownerPassword, String userPassword) {
		this.ownerPassword = ownerPassword;
		this.userPassword = userPassword;
	}

	@Override
	public void setPdfSource(PdfSource pdfSource) {
		this.pdfSource = pdfSource;
	}

	@Override
	public void execute() {
		File file = new File(pdfSource.getOutputPath());
		if (file.exists()) {
			try (FileInputStream fis = new FileInputStream(file)) {
				PDDocument doc = PDDocument.load(fis);
				AccessPermission permission = new AccessPermission();
				StandardProtectionPolicy policy = new StandardProtectionPolicy(ownerPassword, userPassword, permission);
				policy.setEncryptionKeyLength(128);
				policy.setPermissions(permission);
				doc.protect(policy);
				doc.save(file);
			} catch (Exception e) {
				throw new RuntimeException("An error occurred while encrypting the PDF file.", e);
			}
		} else {
			throw new RuntimeException("File does not exist.");
		}
	}
}
