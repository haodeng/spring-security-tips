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
public class AdminDemoControllerTest {
    WebTestClient webTestClient;

    @BeforeEach
    void setUp(@Autowired MockMvc mockMvc) {
        this.webTestClient = MockMvcWebTestClient.bindTo(mockMvc).build();
    }

    @Test
    @WithMockUser(username = "user1")
    void getAdminPageUserRoleFails() {
        this.webTestClient.get().uri("/admin")
                .exchange()
                .expectStatus().isForbidden();
    }

    @Test
    @WithMockUser(username = "user1", roles = { "SOME_ROLES" })
    void getAdminPageWithoutProperRoleFails() {
        this.webTestClient.get().uri("/admin")
                .exchange()
                .expectStatus().isForbidden();
    }

    @Test
    @WithMockUser(username = "admin", roles = { "ADMIN" })
    void getAdminPageWorks() {
        this.webTestClient.get().uri("/admin")
                .exchange()
                .expectStatus().isOk();
    }
}
