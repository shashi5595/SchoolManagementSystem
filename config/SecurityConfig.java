package com.bms.schoolmanagementsystem.config;


import com.bms.schoolmanagementsystem.entity.Role;
import com.bms.schoolmanagementsystem.entity.User;
import com.bms.schoolmanagementsystem.repository.RoleRepository;
import com.bms.schoolmanagementsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/student/**").hasRole("STUDENT")
                .antMatchers("/teacher/**").hasRole("TEACHER")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic(); // Enable Basic Authentication for RESTful APIs (optional)
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return username -> {
            User user = userRepository.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User not found");
            }
            Set<GrantedAuthority> authorities = new HashSet<>();
            for (Role role : user.getRoles()) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
            }
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
        };
    }
}

