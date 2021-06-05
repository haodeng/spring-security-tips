package demo.hao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class OpenIdConnectAuthApplication {

    private final String resourceServerUrl = "http://localhost:8081/resources";

    @Bean
    WebClient client(ClientRegistrationRepository regRepo, OAuth2AuthorizedClientRepository cliRepo) {
        ServletOAuth2AuthorizedClientExchangeFilterFunction function =
                new ServletOAuth2AuthorizedClientExchangeFilterFunction(regRepo, cliRepo);

        function.setDefaultOAuth2AuthorizedClient(true);

        return WebClient.builder()
                .baseUrl(resourceServerUrl)
                .apply(function.oauth2Configuration())
                .build();
    }

    public static void main(String[] args) {
        SpringApplication.run(OpenIdConnectAuthApplication.class, args);
    }
}
