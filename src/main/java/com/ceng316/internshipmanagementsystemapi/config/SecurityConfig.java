package com.ceng316.internshipmanagementsystemapi.config;

import com.ceng316.internshipmanagementsystemapi.security.JwtAuthenticationEntryPoint;
import com.ceng316.internshipmanagementsystemapi.security.JwtAuthenticationFilter;
import com.ceng316.internshipmanagementsystemapi.services.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private UserDetailsServiceImpl userDetailsService;
    private JwtAuthenticationEntryPoint handler;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService, JwtAuthenticationEntryPoint handler) {
        this.userDetailsService = userDetailsService;
        this.handler = handler;
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> {
                    request.requestMatchers("/auth/**").permitAll();
                    request.requestMatchers("/users/**").permitAll();
                    request.requestMatchers("/api/v1/**").permitAll();
                    request.requestMatchers("/announcement/**").permitAll();
                    request.requestMatchers("/student/**").permitAll();
//                            .hasAnyAuthority("STUDENT");
                    request.requestMatchers("/ubys/students/**").permitAll();
                    request.requestMatchers("/secretary/**").permitAll();
//                            .hasAnyAuthority("SECRETARY");
                    request.requestMatchers("/coordinator/**").permitAll();
                    request.requestMatchers("/company/**").permitAll();
//                            .hasAnyAuthority("COMPANY");
                    request.requestMatchers("/*").authenticated();
                }).addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class).build();

//        httpSecurity.cors(Customizer.withDefaults()).csrf(Customizer.withDefaults())
//                .authorizeHttpRequests()
//                .requestMatchers("/api/v1/").hasAuthority("USER")
//                .requestMatchers(HttpMethod.GET, "/api/v1/months").permitAll()
//                .requestMatchers(HttpMethod.GET, "/api/v1/quarters/**").permitAll()
//                .requestMatchers(HttpMethod.GET, "/api/v1/sprints/getAllByQuarterAndTeam").permitAll()
//                .requestMatchers(HttpMethod.GET, "/api/v1/sprints/getAllByEmployeeIdAndQuarterIdAndTeamIdAndStatusIsFalse").permitAll()
//                .requestMatchers(HttpMethod.POST, "/api/v1/quarters/**").hasAuthority("ADMIN")
//                .requestMatchers(HttpMethod.GET, "/api/v1/assessments/status").hasAuthority("ADMIN")
//                .requestMatchers(HttpMethod.POST, "/api/v1/sprints/**").hasAuthority("ADMIN")
//                .requestMatchers(HttpMethod.GET, "/api/v1/sprints/**").hasAuthority("ADMIN")
//                .requestMatchers(HttpMethod.PUT, "/api/v1/employees/**").hasAuthority("ADMIN")
//                .requestMatchers("/api/v1/auth/**", "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs", "/v3/api-docs/**"
//                        , "/", "/error", "/csrf", "/api/v1/employees/**", "/api/v1/individual-performance/**")
//                .permitAll()
//                .requestMatchers("/api/v1/employees").hasAuthority("ADMIN")
//                .requestMatchers("/api/v1/employees/edit/**").hasAnyAuthority( "USER","ADMIN")
//                .requestMatchers("/api/v1/team-performances").hasAuthority("ADMIN")
//                .requestMatchers("/api/v1/sprint-executions").hasAuthority("ADMIN")
//                .requestMatchers("/api/v1/quarters").hasAnyAuthority("ADMIN", "USER")
//                .requestMatchers("/api/v1/sprints").hasAnyAuthority("ADMIN", "USER")
//                .requestMatchers("/api/v1/primary-pools/**").hasAuthority("ADMIN")
//                .requestMatchers("/api/v1/sprint-executions/**").hasAuthority("ADMIN")
//                .requestMatchers("/api/v1/levels").hasAnyAuthority("ADMIN", "USER")
//                .requestMatchers("/api/v1/kpis").hasAuthority("ADMIN")
//                .requestMatchers("/api/v1/individual-performance").hasAuthority("ADMIN")
//                .requestMatchers("/api/v1/attitude-values").hasAuthority("ADMIN")
//                .requestMatchers("/api/v1/assessments").hasAuthority("ADMIN")
//                .requestMatchers("/api/v1/teams").hasAnyAuthority("ADMIN", "USER")
//                .anyRequest()
//                .authenticated()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authenticationProvider(authenticationProvider)
//                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
