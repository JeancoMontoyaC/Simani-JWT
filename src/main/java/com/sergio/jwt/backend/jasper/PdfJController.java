package com.sergio.jwt.backend.jasper;
import com.sergio.jwt.backend.Pet.Pet;
import com.sergio.jwt.backend.PetService.PetService;
import lombok.AllArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
@AllArgsConstructor
public class PdfJController {
    private PetService petService;

    @GetMapping("/generatePdf")
    public String generatePdf() {
        try {
            List<Pet> list = petService.findAllWithout();

            Map<String, Object> params = new HashMap<String, Object>();

            params.put("petsData", new JRBeanCollectionDataSource(list));

            // Cargar el archivo JRXML y compilarlo en un objeto JasperPrint
            ClassPathResource resource = new ClassPathResource("petsReport.jrxml");

            // Compilar el archivo JRXML
            JasperReport jasperReport = JasperCompileManager.compileReport(resource.getInputStream());

            // Crear un JasperPrint
            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport,
                    params,
                    new JREmptyDataSource());

            // Exportar el informe a un array de bytes
            byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);

            // Convertir el array de bytes a Base64
            String base64Pdf = java.util.Base64.getEncoder().encodeToString(pdfBytes);
            return base64Pdf;
        } catch (JRException e) {
            e.printStackTrace();
            return "Error al generar el PDF";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}


