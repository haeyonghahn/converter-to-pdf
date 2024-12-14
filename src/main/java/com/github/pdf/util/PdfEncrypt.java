package com.github.pdf.util;

import java.io.File;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.ProtectionPolicy;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;

public class PdfEncrypt {

	public static void setPdfEncrypt(String ownerPassword, String userPassword, String filePath) throws Exception {
		File file = new File(filePath);
		try (PDDocument doc = Loader.loadPDF(file)) {
			AccessPermission permission = new AccessPermission();
			StandardProtectionPolicy policy = new StandardProtectionPolicy(ownerPassword, userPassword, permission);
			policy.setEncryptionKeyLength(128);
			policy.setPermissions(permission);
			doc.protect((ProtectionPolicy) policy);
			doc.save(file);
		} catch (Exception e) {
			throw new RuntimeException("An error occurred while encrypting the PDF file.", e);
		}
	}
}
