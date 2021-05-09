package com.freshvotes.springboot.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder getPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

	//Authentication
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//in-memory authentication
//		auth.inMemoryAuthentication()
//			.withUser("pardeep001").password(getPasswordEncoder().encode("myapp")).roles("USER").and()
//			.withUser("sandeep").password(getPasswordEncoder().encode("myapp")).roles("ADMIN");
		
		//authentication from database
		auth.userDetailsService(userDetailsService).passwordEncoder(getPasswordEncoder());
	}

	//Authorization
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
			.antMatchers("/").permitAll()
			.antMatchers("/admin/**").hasRole("ADMIN")
			.anyRequest().hasAnyRole("USER","ADMIN").and()
		.formLogin()
			.loginPage("/login").permitAll().defaultSuccessUrl("/dashboard").and()
			
		.logout()
			.logoutUrl("/logout").permitAll();
	}

	
	
}
