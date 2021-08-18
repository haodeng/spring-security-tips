package demo.hao;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class DemoController {

    @GetMapping
    public String hi(Authentication auth) {
        return "Hi, " + auth;
    }

    @DeleteMapping("/del")
    public String delete() {
        return "Hi, delete works";
    }
}
