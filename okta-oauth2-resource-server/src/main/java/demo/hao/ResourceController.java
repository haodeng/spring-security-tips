package demo.hao;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/resources")
class ResourceController {

    @GetMapping("/something")
    String getSomething() {
        return "This is really something!";
    }

    @GetMapping("/claims")
    Map<String, Object> getClaims(@AuthenticationPrincipal Jwt jwt) {
        return jwt.getClaims();
    }

    @GetMapping("/email")
    String getEmail(@AuthenticationPrincipal Jwt jwt) {
        return jwt.getSubject();
    }
}