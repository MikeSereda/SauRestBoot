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
                .authorizeHttpRequests()
                .requestMatchers(
                        pr +"updates/**", pr +"deltas/**",
                        pr +"parameters/**", pr +"devices/**",
                        pr +"device-types", pr +"phones/**",
                        pr +"sessions/**", pr +"approximated/**",
                        pr +"greet/**", pr +"popups").permitAll()
                .requestMatchers(HttpMethod.POST,"**").hasAnyAuthority("OPERATOR", "ADMIN")
                .requestMatchers(HttpMethod.DELETE,"**").hasAuthority("ADMIN")
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//??????
                .and()
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
