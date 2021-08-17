package demo.hao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;

@SpringBootTest
@AutoConfigureMockMvc
public class DemoControllerTest {
    WebTestClient webTestClient;

    @BeforeEach
    void setUp(@Autowired MockMvc mockMvc) {
        this.webTestClient = MockMvcWebTestClient.bindTo(mockMvc).build();
    }

    @Test
    void verifyLoginBlocksAccess() {
        this.webTestClient.get().uri("/")
                .exchange()
                .expectStatus().isUnauthorized();
    }

    @Test
    @WithMockUser(username = "user1")
    void verifyLoginWorks() {
        this.webTestClient.get().uri("/")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    @WithMockUser(username = "user2", roles = { "SOME_OTHER_ROLE" })
    void deleteWithoutProperRoleFails() {
        this.webTestClient.delete().uri("/del")
                .exchange()
                .expectStatus().isForbidden();
    }

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void deleteWithAdminRoleOk() {
        this.webTestClient.delete().uri("/del")
                .exchange()
                .expectStatus().isOk();
    }
}
