package choplan.db.application.properties.choplan.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    
    @GetMapping("/customer/test")
    public String customerTest() {
        return "Customer 접근 성공";
    }

    @GetMapping("/owner/test")
    public String ownerTest() {
        return "Owner 접근 성공";
    }

    @GetMapping("/admin/test")
    public String adminTest() {
        return "Admin 접근 성공";
    }
}
