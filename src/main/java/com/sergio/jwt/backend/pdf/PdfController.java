package com.sergio.jwt.backend.pdf;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;

@RestController
public class PdfController {

    @GetMapping("/generateJPdf")
    public ResponseEntity<String> generatePdf() {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, baos);

            document.open();
            document.add(new Paragraph("¡Hola, este es un PDF generado con iText!"));
            document.close();

            byte[] pdfBytes = baos.toByteArray();
            String base64Encoded = java.util.Base64.getEncoder().encodeToString(pdfBytes);

            return ResponseEntity.ok(base64Encoded);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error al generar el PDF");
        }
    }
}
