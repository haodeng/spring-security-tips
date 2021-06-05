package demo.hao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
public class Oauth2ResourceServerApplication {


    @EnableWebSecurity
    class SecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .mvcMatchers("/claims/**").hasAuthority("SCOPE_openid")
                    .mvcMatchers("/email/**").hasAuthority("SCOPE_email")
                    .anyRequest().authenticated()
                    .and()
                    .oauth2ResourceServer().jwt();
        }
    }
    public static void main(String[] args) {
        SpringApplication.run(Oauth2ResourceServerApplication.class, args);
    }
}
