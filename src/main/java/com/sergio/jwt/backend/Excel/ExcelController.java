package com.sergio.jwt.backend.Excel;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController

public class ExcelController {

    @GetMapping("/generateExcel")
    public ResponseEntity<String> generateExcel() {
        try {
            // Crear un libro de Excel
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("Datos");

            // Agregar datos de ejemplo
            Row headerRow = sheet.createRow(0);
            Cell cell = headerRow.createCell(0);
            cell.setCellValue("Nombre");

            Row dataRow = sheet.createRow(1);
            Cell dataCell = dataRow.createCell(0);
            dataCell.setCellValue("Ejemplo");

            // Convertir el libro de Excel a un array de bytes
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            byte[] excelBytes = outputStream.toByteArray();
            workbook.close();

            // Codificar en base64 y devolver la respuesta
            String base64Encoded = java.util.Base64.getEncoder().encodeToString(excelBytes);
            return ResponseEntity.ok(base64Encoded);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error al generar el Excel");
        }
    }
}
