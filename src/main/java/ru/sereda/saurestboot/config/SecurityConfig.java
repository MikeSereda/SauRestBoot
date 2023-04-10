package ru.sereda.saurestboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.sereda.saurestboot.security.jwt.JwtAuthFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        String pr = "/api/v1/";
        httpSecurity
                .csrf().disable()
                .cors().disable()
                .authorizeHttpRequests()
                .requestMatchers(
                        pr +"updates/**", pr +"deltas/**",
                        pr +"parameters/**", pr +"devices/**",
                        pr +"device-types", pr +"phones/**",
                        pr +"sessions/**", pr +"approximated/**",
                        pr +"greet/**", pr +"popups",
                        "/swagger-ui/**", "/v3/api-docs/**",
                        pr+"test", pr+"test2", pr+"test3", pr+"test4").permitAll()
                .requestMatchers(HttpMethod.POST,pr+"**").hasAnyAuthority("SAT_OPERATOR", "SAT_ADMIN", "ADMIN")
                .requestMatchers(HttpMethod.DELETE,pr+"**").hasAnyAuthority("SAT_ADMIN", "ADMIN")
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//??????
                .and()
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
