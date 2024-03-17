package com.ceng316.internshipmanagementsystemapi.controllers;
import com.ceng316.internshipmanagementsystemapi.services.DocumentService;

import org.springframework.core.io.Resource;

import org.springframework.http.ResponseEntity;

public class AnnouncementController {
    DocumentService documentService;
    ResponseEntity<Resource> downloadDocument(String documentName) {
        return null;
//            // Var olan Word belgesinin yolu
//            String dosyaYolu = "static/ornekdosya.docx"; // Örnek olarak "static" klasöründe bulunuyor diyelim.
//
//            // ClassPathResource kullanarak dosyayı okuyun
//            ClassPathResource resource = new ClassPathResource(dosyaYolu);
//
//            // Dosya bulunamadıysa hata döndürün
//            if (!resource.exists()) {
//                return ResponseEntity.notFound().build();
//            }
//
//            try {
//                // Dosyanın InputStream'ini alın
//                InputStream inputStream = resource.getInputStream();
//
//                // InputStreamResource oluşturun
//                InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
//
//                // İndirme için HttpHeaders oluşturun
//                HttpHeaders headers = new HttpHeaders();
//                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=ornekdosya.docx");
//
//                // İndirme işlemi için ResponseEntity oluşturun
//                return ResponseEntity.ok()
//                        .headers(headers)
//                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                        .body(inputStreamResource);
//            } catch (IOException e) {
//                // Hata durumunda uygun şekilde işleyin
//                e.printStackTrace();
//                return ResponseEntity.status(500).build();
//            }
    }
    void updateDocument(String documentName) {}
    void changeStatus(String newStatus) {}
}
