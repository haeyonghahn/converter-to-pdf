package com.github.pdf.util;

import java.io.File;
import java.io.FileInputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;

public class PdfEncrypt {

	public static void setPdfEncrypt(String ownerPassword, String userPassword, String filePath) {
		File file = new File(filePath);
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
