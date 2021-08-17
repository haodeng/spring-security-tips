Demo of how spring security test works. Example:

    @Test
    @WithMockUser(username = "user1")
    void getAdminPageUserRoleFails() {
        this.webTestClient.get().uri("/admin")
                .exchange()
                .expectStatus().isForbidden();
    }

Check out test cases for details