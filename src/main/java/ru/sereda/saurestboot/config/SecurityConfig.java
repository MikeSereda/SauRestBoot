package ru.sereda.saurestboot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import ru.sereda.saurestboot.security.entrypoints.Http401UnauthorizedEntryPoint;
import ru.sereda.saurestboot.security.jwt.JwtConfigurer;
import ru.sereda.saurestboot.security.jwt.JwtTokenProvider;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public SecurityConfig(JwtTokenProvider jwtTokenProvider){
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Autowired
    private Http401UnauthorizedEntryPoint authEntrypoint;

    private final String[] administratingRoles = new String[]{
            "ADMIN_SAT",
            "ADMIN_ALL"
    };

    private final String[] configRoles = new String[]{
            "ADMIN_SAT",
            "OPERATOR_SAT",
            "ADMIN_ALL"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .exceptionHandling().authenticationEntryPoint(authEntrypoint)
                .and()
                .httpBasic().disable()
                .cors().and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/admin/**").hasAnyAuthority(administratingRoles)
                .antMatchers("/api/auth/new-user").hasAnyAuthority(administratingRoles)
                .antMatchers("/api/authenticated/**").authenticated()
                .antMatchers("/api/phones/**").permitAll()
//                .antMatchers("/api/phones/**").hasAnyAuthority(configRoles)
//                .antMatchers("/api/devices/**").hasAnyAuthority(configRoles)
                .antMatchers("/api/**").permitAll()
                .and()

                .apply(new JwtConfigurer(jwtTokenProvider));
    }
}
