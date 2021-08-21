package demo.hao;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasRole('" + SecurityConfig.ADMIN + "')")
@RequestMapping("/admin")
public class AdminDemoController {

    @GetMapping
    public String hi(Authentication auth) {
        return "Hi, " + auth;
    }
}
