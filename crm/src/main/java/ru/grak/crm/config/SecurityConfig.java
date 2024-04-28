package ru.grak.crm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import ru.grak.crm.security.UserDetailsServiceImpl;
import ru.grak.crm.security.jwt.JwtConfigurer;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@Configuration
public class SecurityConfig {

    private static final String MANAGER_ENDPOINT = "/api/manager/**";
    private static final String USER_ENDPOINT = "/api/user/**";
    private static final String AUTH_ENDPOINT = "/api/auth/**";

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtConfigurer configurerFilter;

    @Autowired
    public SecurityConfig(UserDetailsServiceImpl userDetailsService, JwtConfigurer configurerFilter) {
        this.userDetailsService = userDetailsService;
        this.configurerFilter = configurerFilter;
    }

    private static final String[] AUTH_WHITELIST = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",

            "/v3/api-docs/**",
            "/swagger-ui/**"
    };


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(bCryptPasswordEncoder());

        return authProvider;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .requestMatchers(AUTH_ENDPOINT).permitAll()
                .requestMatchers(AUTH_WHITELIST).permitAll()
                .requestMatchers(USER_ENDPOINT).hasRole("USER")
                .requestMatchers(MANAGER_ENDPOINT).hasRole("ADMIN")
                .and()
                .apply(configurerFilter);

        return http.build();
    }
}
