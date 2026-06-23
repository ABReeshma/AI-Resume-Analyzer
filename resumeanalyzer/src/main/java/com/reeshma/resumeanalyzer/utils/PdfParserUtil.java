package com.reeshma.resumeanalyzer.utils;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class PdfParserUtil {

    public static String extractText(MultipartFile file) throws IOException {

        PDDocument document = Loader.loadPDF(file.getBytes());

        PDFTextStripper pdfTextStripper = new PDFTextStripper();

        String text = pdfTextStripper.getText(document);

        document.close();

        return text;
    }
}