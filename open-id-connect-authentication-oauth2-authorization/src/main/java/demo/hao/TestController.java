package demo.hao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@RestController
public class TestController {

    @Autowired
    private WebClient client;

    @GetMapping("/")
    String hello() {
        return "hello";
    }

    @GetMapping("/something")
    String getSomething() {
        return client.get()
                .uri("/something")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    @GetMapping("/claims")
    Map getClaims() {
        return client.get()
                .uri("/claims")
                .retrieve()
                .bodyToMono(Map.class)
                .block();
    }

    @GetMapping("/email")
    String getEmail() {
        return client.get()
                .uri("/email")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
