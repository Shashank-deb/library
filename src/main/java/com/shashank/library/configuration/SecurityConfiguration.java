package com.shashank.library.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;


@Configuration
public class SecurityConfiguration {
    /**
     * Roles of higher version of authority
     * Authentication
     * Authorization
     * Role can be accessible by the Manager using the given entity of the flow
     * <p>
     * <p>
     * <p>
     * Application workflow about spring security
     * <p>
     * Request ->Need to check the user using password and username JsonEncoder or Spring Security Configuration this acts as a wall
     * Api send final response
     * 401 Unauthorized
     * 403 Forbidden
     * 406 Not Acceptable
     * <p>
     * <p>
     * <p>
     * <p>
     * Terms using in Spring Boot Security for an API
     * 1.Encoding->Transform of data into one from to another format which can be reversible can be easily decoded.
     * <p>
     * 2.Hashing->One way transformation  of data into another format which is non-reversible   it is ensured that same hash will be generated for same values always until the under-lying logic changes  done by a some algorithm   SHA-265.
     * <p>
     * 3.Encryption->Transform of data into non-consumable format and can be converted to original data by a decryption process where decryption will be done by the help of key public,private .
     * <p>
     * Secure our APIs
     * <p>
     * 1.Onboard the user with credentials.->user object->UserDetails
     * 2.Accept username and password from user.->AuthenticationProvider->Authentication object
     * 3.Fetch the user from the database. ->UserDetailService  Implementation
     * 4.Compare the password hash of the user .-> AuthenticationProvider->PasswordEncoder
     * 5.Check if the user has authority an API.->  SecurityFilterChain
     * 6.Let the user use the API.->AuthenticationProvider
     * <p>
     * <p>
     * <p>
     * Browser is acts in the given below ways
     * Local Storage
     * Session Storage
     * Cookies->Generic for everyone
     **/


    @Bean
    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
        return new BCryptPasswordEncoder();
    }


    @Bean
    public CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.csrf(csrf -> csrf.csrfTokenRepository(csrfTokenRepository()));
        httpSecurity.csrf(csrf -> csrf.disable());
        httpSecurity.authorizeHttpRequests(authorize ->
                authorize
                        .requestMatchers("/admin/**").hasAuthority("ADMIN")
                        .requestMatchers("/greet/**").hasAuthority("USER")
                        .requestMatchers("/signup").permitAll()
                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/login").permitAll()
                        //.requestMatchers("/error").permitAll()
                        .anyRequest().authenticated()

        ).formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults());
        return httpSecurity.build();

    }


    /**
     * Encode -> transform of data into one from another format. which can be reversible easily
     *
     * hashing -> transform of data into non-consumable format which is non reversible and it ensures that same hash
     *              will be generated for same values always until the under-laying logic changes.
     *              algorithm  -> SHA-256
     *
     * encryption -> transform of data into non-consumable format and can be converted to original via decryption process.
     *
     *
     *
     * Secure our APIs
     *
     * 1. Onboard the user with credentials. -> user object -> UserDetail.
     * 2. accept username and password from user. -> AuthenticationProvider -> Authentication object
     * 3. Fetch the user from the databases -> UserDetailService Implementation
     * 4. Compare the password hash of the user. -> AuthenticationProvider -> PasswordEncoder.
     * 5. Check if the user has authority on API. -> SecurityFilterChain
     * 6. Let the user use the API.-> AuthenticationProvider
     *
     *
     *
     * CoRs -> Cross-Origin Resource Sharing
     *
     * CSRF-> Cross - Site Request Forgery
     * -> XSS attacks
     *
     *
     *
     * */


}
