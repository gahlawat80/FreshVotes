package com.freshvotes.springboot.app.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	//Authentication
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("pardeep001").password("myapp").roles("USER").and()
			.withUser("sandeep").password("myapp").roles("customer");
	}

	//Authorization
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests().antMatchers("/").permitAll()
		.anyRequest().hasAnyRole("USER").and()
		.formLogin()
			.loginPage("/login").permitAll().and()
		.logout()
			.logoutUrl("/logout").permitAll();
	}

	
	
}
