package demo.hao;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Bean
    public UserDetailsService userDetailsService(UserRepository repository) {
        return username -> repository.findByName(username)
                .map(user -> User.withDefaultPasswordEncoder()
                        .username(user.getName())
                        .password(user.getPassword())
                        .authorities(user.getRoles().toArray(new String[0]))
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    static final String USER = "USER";
    static final String ADMIN = "ADMIN";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .mvcMatchers("/admin").hasRole(ADMIN)
                .mvcMatchers(HttpMethod.DELETE, "/**").hasRole(ADMIN)
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin()
                .and()
                .csrf().disable();
    }

    static String role(String auth) {
        return "ROLE_" + auth;
    }

    @Bean
    CommandLineRunner userLoader(UserRepository repository) {
        return args -> {
            repository.save(new MyUser(
                    "hao", "pass", Arrays.asList(role(USER))));

            repository.save(new MyUser(
                    "admin", "pass", Arrays.asList(role(ADMIN))));
        };
    }
}
