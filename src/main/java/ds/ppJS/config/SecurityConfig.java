package ds.ppJS.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

         http
                .csrf().disable()
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/static/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                        .anyRequest()
                        .authenticated())
                        .formLogin()
                .successHandler(new CustomLoginSuccessHandler());


//        http
//                .csrf().disable()
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/api/**").permitAll().requestMatchers("/admin").permitAll());
//
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}