package com.management.user.input.endpoint;

import com.management.user.model.Report;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

   // @PreAuthorize("hasRole('ADMINISTRATOR')")
    @GetMapping("/report")
    public Report downloadReport() {

        return null;
    }
}
