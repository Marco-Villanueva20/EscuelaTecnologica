package com.escuelatecnologica.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.escuelatecnologica.core.services.UsuarioService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	private UsuarioService usuarioService;
	
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	return http
    	        .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
    	        .authorizeHttpRequests(registry -> {
    	            registry.requestMatchers("register/user", "register/all").permitAll();
    	            registry.requestMatchers("alumnos").hasRole("PROFESOR");
    	            registry.requestMatchers("profesores").hasRole("ALUMNOS");
    	            registry.requestMatchers("/**").hasRole("ADMIN");
    	            registry.anyRequest().authenticated();
    	        })
    	        .formLogin(form -> form.permitAll())
    	        .logout(logout -> logout.permitAll())
    	        .build();
    }


    @Bean
    UserDetailsService userDetailsService(){
    	return usuarioService;
    }
    
    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
    
    @Bean
    AuthenticationProvider authenticationProvider() {
    	DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    	provider.setUserDetailsService(usuarioService);
    	provider.setPasswordEncoder(passwordEncoder());
    	return provider;
    }
    
    
//  @Bean
//  AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
//      return authenticationConfiguration.getAuthenticationManager();
//  }

  //usuarios en memoria
//  @Bean
//  UserDetailsService userDetailsService(){
//  	UserDetails normalUser = User.builder()
//  			.username("ar")
//  			.password("$2a$12$0zSGsA9nW1Bhdx6erWCo.egabNBzar.cX9xRl3c7m1XuGdp9D7AHa")
//  			.roles("USER")
//  			.build();
//  	UserDetails adminUser = User.builder()
//  			.username("mv")
//  			.password("$2a$12$ivrPQCP6GMyviCw2w/Vz8.xGKw7sW3OSU/h41X7mSV7CQTNv7z/eK")
//  			.roles("ADMIN","USER")
//  			.build();
//  	return new InMemoryUserDetailsManager(normalUser,adminUser);
//  }

}