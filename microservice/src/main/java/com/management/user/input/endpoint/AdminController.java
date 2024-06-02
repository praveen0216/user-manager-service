package com.management.user.input.endpoint;

import com.management.user.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

   @PreAuthorize("hasRole('ADMINISTRATOR')")
   @GetMapping("/report")
   public ResponseEntity<ByteArrayResource> downloadReport() {
       byte[] data = adminService.generateReport();

       String date = new SimpleDateFormat("ddMMyyyy").format(new Date());
       String filename = "report_" + date + ".csv";

       ByteArrayResource resource = new ByteArrayResource(data);

       HttpHeaders headers = new HttpHeaders();
       headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename);

       return ResponseEntity.ok()
               .headers(headers)
               .contentType(MediaType.APPLICATION_OCTET_STREAM)
               .body(resource);
   }
}
