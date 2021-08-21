Demo of how spring security test works. Example:

    @Test
    @WithMockUser(username = "user1")
    void getAdminPageUserRoleFails() {
        this.webTestClient.get().uri("/admin")
                .exchange()
                .expectStatus().isForbidden();
    }

Check out test cases for details

# method level authorize
To decouple the url matchers in SecurityConfig, method level auth is recommended.

    @EnableGlobalMethodSecurity(prePostEnabled = true)
    public class DemoApplication {
    
    // config use method level authorize
        http.
                authorizeRequests(registry -> registry.anyRequest().authenticated())
                .httpBasic()
                .and()
                .formLogin()
                .and()
                .csrf().disable();
    
    # config via PreAuthorize          
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/del")
    public String delete() {
        return "Hi, delete works";
    }
    
# Hyper media
It's possible to getAuthorities of roles 
and give different links representation to different roles

        Links allLinks;
        if (auth.getAuthorities().contains(SecurityConfig.ROLE_ADMIN)) {
            Link deleteLink = linkTo(controller.deleteItem(id)).withRel("delete");
            allLinks = Links.of(selfLink, deleteLink);
        }
        else
        {
            allLinks = Links.of(selfLink);
        }