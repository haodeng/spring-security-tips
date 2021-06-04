package demo.hao;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    String all() {
        return "Hi, all";
    }

    @GetMapping("/admin")
    String admin() {
        return "Hi, admin";
    }
}
