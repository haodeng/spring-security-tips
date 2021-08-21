package demo.hao;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class DemoController {

    @GetMapping
    public String hi(Authentication auth) {
        return "Hi, " + auth;
    }

    @PreAuthorize("hasRole('" + SecurityConfig.ADMIN + "')")
    @DeleteMapping("/del")
    public String delete(Authentication auth) {
        return "Hi, delete works";
    }
}
