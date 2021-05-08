package com.freshvotes.springboot.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public PasswordEncoder getPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

	//Authentication
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("pardeep001").password(getPasswordEncoder().encode("myapp")).roles("USER").and()
			.withUser("sandeep").password(getPasswordEncoder().encode("myapp")).roles("ADMIN");
	}

	//Authorization
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests().antMatchers("/").permitAll()
		.anyRequest().hasAnyRole("USER","ADMIN").and()
		.formLogin()
			.loginPage("/login").permitAll().and()
		.logout()
			.logoutUrl("/logout").permitAll();
	}

	
	
}
