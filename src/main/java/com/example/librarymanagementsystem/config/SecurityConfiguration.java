package com.example.librarymanagementsystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests()
//        hasAnyRole(UserRole.ADMIN.name(), UserRole.PUBLISHER.name())
                .antMatchers("/edit/**").authenticated()
//        hasRole(UserRole.ADMIN.name())
                .antMatchers("/delete/**").authenticated()
                .antMatchers("/user/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin();


        return http.build();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
//    public UserDetailsService userDetailsService(){
//
//        UserDetails admin = User.withUsername("user_admin")
//                .password("{noop}1234")
//                .roles(UserRole.ADMIN.name())
//                .build();
//        UserDetails publisher = User.withUsername("user_publisher")
//                .password("{noop}123")
//                .roles(UserRole.PUBLISHER.name())
//                .build();
//        UserDetails readOnlyUser = User.withUsername("user_read_only")
//                .password("{noop}12")
//                .roles(UserRole.READ_ONLY.name())
//                .build();
//        return new InMemoryUserDetailsManager(admin,publisher,readOnlyUser);
//    }

}
